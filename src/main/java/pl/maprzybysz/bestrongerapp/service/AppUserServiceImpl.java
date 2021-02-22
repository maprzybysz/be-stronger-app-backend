package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maprzybysz.bestrongerapp.exception.EmailAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.Role;
import pl.maprzybysz.bestrongerapp.Entity.ShoppingListElement;
import pl.maprzybysz.bestrongerapp.Entity.VerificationToken;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.RoleRepo;
import pl.maprzybysz.bestrongerapp.repository.ShoppingListElementRepo;
import pl.maprzybysz.bestrongerapp.repository.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
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
    private ShoppingListElementRepo shoppingListElementRepo;


    @Autowired
    public AppUserServiceImpl(AppUserRepo appUserRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder,
                              MailSenderService mailSenderService, VerificationTokenRepo verificationTokenRepo,
                              ShoppingListElementRepo shoppingListElementRepo) {
        this.appUserRepo = appUserRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.verificationTokenRepo = verificationTokenRepo;
        this.shoppingListElementRepo = shoppingListElementRepo;
    }


    @Override
    public void addNewUser(AppUser appUser, HttpServletRequest request) {
        //Check credentials
        if(appUserRepo.findByUsername(appUser.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        if(appUserRepo.findByEmail(appUser.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException();
        }
        //Add user role
        List<Role> authorities = new ArrayList<>();
        Role defaultRole = roleRepo.findRoleByRole("ROLE_USER").get();
        authorities.add(defaultRole);

        //Set user password
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(authorities);
        appUserRepo.save(appUser);

        //Generate verification token
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

    @Override
    public List<ShoppingListElement> getShoppingList(String username) {
        Optional<AppUser> appUser = appUserRepo.findByUsername(username);
        if(appUser.isPresent()){
            return appUser.get().getShoppingList();
        }else{
            return List.of(null);
        }
    }

    @Override
    public void deleteShoppingListElement(Long id) {
        shoppingListElementRepo.deleteById(id);
    }

    @Override
    public void addShoppingListElement(String username, String listItem) {
        Optional<AppUser> user = appUserRepo.findByUsername(username);
        if(user.isPresent()){
            ShoppingListElement shoppingListElement = new ShoppingListElement();
            shoppingListElement.setListElement(listItem);
            shoppingListElement.setAppUser(user.get());
            shoppingListElementRepo.save(shoppingListElement);
        }else{
            throw new UserDoesNotExistsException(username);
        }
    }


}