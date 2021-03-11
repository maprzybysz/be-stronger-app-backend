package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maprzybysz.bestrongerapp.Entity.*;
import pl.maprzybysz.bestrongerapp.Entity.DTO.SignUpDTO;
import pl.maprzybysz.bestrongerapp.exception.EmailAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.repository.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepo appUserRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private MailSenderService mailSenderService;
    private VerificationTokenRepo verificationTokenRepo;
    private RecoveryTokenRepo recoveryTokenRepo;
    private AppUserDetailsService appUserDetailsService;



    @Autowired
    public AppUserServiceImpl(AppUserRepo appUserRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder,
                              MailSenderService mailSenderService, VerificationTokenRepo verificationTokenRepo,
                              RecoveryTokenRepo recoveryTokenRepo, AppUserDetailsService appUserDetailsService) {
        this.appUserRepo = appUserRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.verificationTokenRepo = verificationTokenRepo;
        this.recoveryTokenRepo = recoveryTokenRepo;
        this.appUserDetailsService = appUserDetailsService;

    }


    @Override
    public void addNewUser(SignUpDTO signUpDTO, HttpServletRequest request) {
        AppUser appUser = new AppUser();
        //Check credentials
        if(appUserRepo.findByUsername(signUpDTO.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }else if(appUserRepo.findByEmail(signUpDTO.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException();
        }else{
            appUser.setUsername(signUpDTO.getUsername());
            appUser.setEmail(signUpDTO.getEmail());
            //Add user role
            List<Role> authorities = new ArrayList<>();
            Role defaultRole = roleRepo.findRoleByRole("ROLE_USER").get();
            authorities.add(defaultRole);
            //Set user password
            appUser.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
            appUser.setRoles(authorities);
            appUserRepo.save(appUser);
            //Set rules accepted
            appUser.setRulesAccepted(signUpDTO.isRulesAccepted());
            //Generate verification token
            String token = UUID.randomUUID().toString();
            VerificationToken verificationToken = new VerificationToken(token, appUser);
            verificationTokenRepo.save(verificationToken);
            //Create user details
            AppUserDetails appUserDetails = new AppUserDetails();
            appUserDetails.setSex(signUpDTO.getSex());
            appUserDetails.setUserGoal(UserGoal.valueOf(signUpDTO.getUserGoal()));
            appUserDetails.setUserActivity(UserActivity.valueOf(signUpDTO.getUserActivity()));
            appUserDetails.setBirthday(signUpDTO.getBirthday());
            appUserDetails.setHeight(signUpDTO.getHeight());
            appUserDetails.setAppUser(appUser);
            appUser.setUserDetails(appUserDetails);
            //Create weightList
            UserWeight userWeight = new UserWeight(signUpDTO.getWeight(), LocalDate.now(), appUserDetails);
            appUserDetails.getWeights().add(userWeight);
            appUserDetailsService.addTMRbyUser(appUser);
            String url = "http://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+
                    "/verify-token/"+token;
            try {
                mailSenderService.sendMail(appUser.getEmail(), "Verification Token", url, false);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }


    }



    @Override
    public void verifyToken(String token) {
        AppUser appUser = verificationTokenRepo.findByValue(token).getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
    }
    @Override
    public void sendRecoveryToken(String username) {
        AppUser appUser = appUserRepo.findByUsername(username).get();
        String token = UUID.randomUUID().toString();
        RecoveryToken recoveryToken = new RecoveryToken(token, appUser);
        recoveryTokenRepo.save(recoveryToken);
        String url = "http://localhost:3000/recoverypassword/"+token;
        try {
            mailSenderService.sendMail(appUser.getEmail(), "Restart hasła", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restartPassword(String token, String password, String confirmPassword) {
        AppUser appUser = recoveryTokenRepo.findByValue(token).getAppUser();
        if(password.equals(confirmPassword)){
            appUser.setPassword(passwordEncoder.encode(password));
            removeRecoveryToken(token);
        }
    }

    @Override
    public void removeToken(String token) {
        verificationTokenRepo.deleteByValue(token);
    }

    @Override
    public void removeRecoveryToken(String token) {
        recoveryTokenRepo.deleteByValue(token);
    }




}