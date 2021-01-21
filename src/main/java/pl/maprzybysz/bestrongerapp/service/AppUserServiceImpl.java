package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maprzybysz.bestrongerapp.model.AppUser;
import pl.maprzybysz.bestrongerapp.model.Role;
import pl.maprzybysz.bestrongerapp.model.VerificationToken;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.RoleRepo;
import pl.maprzybysz.bestrongerapp.repository.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepo appUserRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private MailSenderService mailSenderService;
    private VerificationTokenRepo verificationTokenRepo;


    @Autowired
    public AppUserServiceImpl(AppUserRepo appUserRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder,
                              MailSenderService mailSenderService, VerificationTokenRepo verificationTokenRepo) {
        this.appUserRepo = appUserRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.verificationTokenRepo = verificationTokenRepo;
    }


    @Override
    public void addNewUser(AppUser appUser, HttpServletRequest request) {

        List<Role> authorities = new ArrayList<>();
        Role defaultRole = roleRepo.findRoleByRole("ROLE_USER").get();
        authorities.add(defaultRole);
        System.out.println(appUser.isRulesAccepted());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(authorities);
        appUserRepo.save(appUser);
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, appUser);
        verificationTokenRepo.save(verificationToken);

        String url = "http://" + request.getServerName() + ":" + request.getServerPort()+request.getContextPath()+
                "/verify-token/"+token;
        try {
            mailSenderService.sendMail(appUser.getEmail(), "Verification Token", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void verifyToken(String token) {
        AppUser appUser = verificationTokenRepo.findByValue(token).getAppUser();
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
    }

    @Override
    public void removeToken(String token) {
        verificationTokenRepo.deleteByValue(token);
    }



}