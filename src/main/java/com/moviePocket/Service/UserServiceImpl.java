package com.moviePocket.Service;

import com.moviePocket.controller.dto.UserRegistrationDto;
import com.moviePocket.entities.Role;
import com.moviePocket.entities.User;
import com.moviePocket.repository.RoleRepository;
import com.moviePocket.repository.UserRepository;
import com.moviePocket.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;


    @Override
    public void save(UserRegistrationDto userDto) throws MessagingException {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        User user = new User(userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role), UUID.randomUUID().toString());
        userRepository.save(user);
        String message = String.format(
                "Hello, %s! \n" +
                        "Welcome to MoviePocket. We want to make sure it's really you. Please, visit next link: http://localhost:8080/activate/%s",
                user.getUsername(),
                user.getActivationCode()
        );
        emailSenderService.sendMailWithAttachment(user.getEmail(),message,"MoviePocket Email Verification");
    }

    public boolean setNewLostPassword(String token,String pas){
        User user = userRepository.findByTokenLostPassword(token);
        if(user!=null && user.getEmailVerification()) {
            user.setPassword(passwordEncoder.encode(pas));
            user.setTokenLostPassword("");
            userRepository.save(user);
            return true;
        }else
            return false;
    }

    public boolean setNewPassword(String email, String passwordOld,String passwordNew){
        User user = userRepository.findByEmail(email);
        if(user!=null && user.getEmailVerification() && passwordEncoder.matches(passwordOld,user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordNew));
            userRepository.save(user);
            return true;
        }else
            return false;
    }

    public boolean deleteUser(String email,String pas){
        User user = findUserByEmail(email);
        if(user != null){
            if(passwordEncoder.matches(pas,user.getPassword())){
                user.setAccountActive(false);
                user.setEmail(user.getEmail()+ " not active");
                user.setPassword("");
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }




    public boolean setTokenPassword(String mail) throws MessagingException {
        User user = userRepository.findByEmail(mail);
        if (user != null && user.getEmailVerification()){
            user.setTokenLostPassword(UUID.randomUUID().toString());
            userRepository.save(user);
            String message = String.format(
                    "Hello, %s! \n" +
                            "We want to make sure it's really you. Please, visit next link: http://localhost:8080/lostpassword/reset?token=%s",
                    user.getUsername(),
                    user.getTokenLostPassword()
            );
            emailSenderService.sendMailWithAttachment(user.getEmail(),message,"Reset Password");
            return true;
        }
        return false;
    }

    public boolean setTokenEmail(String oldEmail, String newEmail) throws MessagingException {
        User user = userRepository.findByEmail(oldEmail);
        if (user != null ) {
            user.setNewEmail(newEmail);
            user.setNewEmailToken(UUID.randomUUID().toString());
            userRepository.save(user);
            String message = String.format(
                    "Hello, %s! \n" +
                            "We want to make sure it's really you. Please, visit next link: http://localhost:8080/user/edit/newemail/%s",
                    user.getUsername(),
                    user.getNewEmailToken()
            );
            emailSenderService.sendMailWithAttachment(user.getNewEmail(),message,"Reset Password");
            return true;
        }
        return false;
    }


    public boolean activateNewEmail(String token) {
        User user = userRepository.findByNewEmailToken(token);
        if (user != null ) {
            user.setEmail(user.getNewEmail());
            user.setNewEmail("");
            user.setNewEmailToken("");
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(usernameOrEmail);
        if (user != null ) {
            if(user.getEmailVerification()){
                return new org.springframework.security.core.userdetails.User(user.getEmail()
                        , user.getPassword(),
                        user.getRoles().stream()
                                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList()));
            }else {
                throw new UsernameNotFoundException("You have not verified your email");
            }
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user != null){
            user.setEmailVerification(Boolean.TRUE);
            user.setActivationCode("");
            userRepository.save(user);
            return true;
        }
        else
            return false;
    }
}
