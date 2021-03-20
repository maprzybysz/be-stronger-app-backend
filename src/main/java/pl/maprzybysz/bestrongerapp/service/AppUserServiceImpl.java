package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maprzybysz.bestrongerapp.Entity.*;
import pl.maprzybysz.bestrongerapp.Entity.DTO.SignUpDTO;
import pl.maprzybysz.bestrongerapp.exception.*;
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
    private DeleteTokenRepo deleteTokenRepo;
    @Value("${frontend.url}")
    private String serverUrl;


    @Autowired
    public AppUserServiceImpl(AppUserRepo appUserRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder,
                              MailSenderService mailSenderService, VerificationTokenRepo verificationTokenRepo,
                              RecoveryTokenRepo recoveryTokenRepo, AppUserDetailsService appUserDetailsService,
                              DeleteTokenRepo deleteTokenRepo) {
        this.appUserRepo = appUserRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.verificationTokenRepo = verificationTokenRepo;
        this.recoveryTokenRepo = recoveryTokenRepo;
        this.appUserDetailsService = appUserDetailsService;
        this.deleteTokenRepo = deleteTokenRepo;

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
            appUserDetailsService.addTMRbyUser(appUser, null);
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
        Optional<AppUser> appUser = appUserRepo.findByUsername(username);
        if(appUser.isEmpty()){
            throw new UserDoesNotExistsException();
        }else{
            Optional<RecoveryToken> findToken = recoveryTokenRepo.findRecoveryTokenByAppUserId(appUser.get().getId());
            String token;
            if(findToken.isPresent()){
                token = findToken.get().getValue();
            }else{
                token = UUID.randomUUID().toString();
                RecoveryToken recoveryToken = new RecoveryToken(token, appUser.get());
                recoveryTokenRepo.save(recoveryToken);
            }
            String url = serverUrl+"/recoverypassword/"+token;
            try {
                mailSenderService.sendMail(appUser.get().getEmail(), "Restart hasła", url, false);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void sendDeleteToken(String username,  HttpServletRequest request) {
        Optional<AppUser> appUser = appUserRepo.findByUsername(username);
        if(appUser.isEmpty()){
            throw new UserDoesNotExistsException();
        }else{
            String token;
            Optional<DeleteToken> findToken = deleteTokenRepo.findDeleteTokenByAppUserId(appUser.get().getId());
            if(findToken.isPresent()){
                token = findToken.get().getValue();
            }else{
                token = UUID.randomUUID().toString();
                DeleteToken deleteToken = new DeleteToken(token, appUser.get());
                deleteTokenRepo.save(deleteToken);
            }
            String url = "http://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+
                    "/delete-account/"+token;
            try {
                mailSenderService.sendMail(appUser.get().getEmail(), "Potwierdź usunięcie konta", url, false);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void deleteAccount(String token) {
        Optional<DeleteToken> deleteToken = deleteTokenRepo.findByValue(token);
        if(deleteToken.isEmpty()){
            throw new InvalidTokenException();
        }else{
            AppUser appUser = deleteToken.get().getAppUser();
            if(appUser == null){
                throw new UserDoesNotExistsException();
            }else{
                deleteTokenRepo.deleteByValue(token);
                appUserRepo.delete(appUser);
            }
        }
    }

    @Override
    public void restartPassword(String token, String password, String confirmPassword) {
        Optional<RecoveryToken> recoveryToken = recoveryTokenRepo.findByValue(token);
        if(recoveryToken.isEmpty()){
            throw new InvalidTokenException();
        }else{
            AppUser appUser = recoveryToken.get().getAppUser();
            if(passwordEncoder.matches(password, appUser.getPassword())){
                throw new PasswordAlreadyUsedException();
            }else if(!password.equals(confirmPassword)){
                throw new PasswordMismatchException();
            }else{
                appUser.setPassword(passwordEncoder.encode(password));
                removeRecoveryToken(token);
            }
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

    @Override
    public void removeDeleteToken(String token) {
        deleteTokenRepo.deleteByValue(token);
    }



}