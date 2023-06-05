package com.moviePocket.service.impl.user;

import com.moviePocket.controller.dto.UserRegistrationDto;
import com.moviePocket.entities.user.Role;
import com.moviePocket.entities.user.User;
import com.moviePocket.repository.user.RoleRepository;
import com.moviePocket.repository.user.UserRepository;
import com.moviePocket.service.UserService;
import com.moviePocket.util.TbConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailSenderService emailSenderService;

    @Override
    public void save(UserRegistrationDto userDto) throws MessagingException {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        User user = new User(userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role), UUID.randomUUID().toString());
        userRepository.save(user);

        String username = user.getUsername();
        String link = "http://localhost:8080/activate/" + user.getActivationCode();
        String massage = "Welcome to MoviePocket. We really hope that you will enjoy being a part of MoviePocket family \n" +
                " We want to make sure it's really you. To do that please confirm your mail by clicking the link below.";

        emailSenderService.sendMailWithAttachment(user.getEmail(), buildEmail(username, massage, link), "Email Verification");

    }

    @Override
    public boolean deleteUser(String email, String pas) {
        User user = findUserByEmail(email);
        if (user != null) {
            if (passwordEncoder.matches(pas, user.getPassword())) {
                user.setAccountActive(false);
                user.setEmail(user.getEmail() + " not active");
                user.setPassword("");
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setNewLostPassword(String token, String pas) {
        User user = userRepository.findByTokenLostPassword(token);
        if (user != null && user.getEmailVerification()) {
            user.setPassword(passwordEncoder.encode(pas));
            user.setTokenLostPassword(null);
            userRepository.save(user);
            return true;
        } else
            return false;
    }

    @Override
    public boolean setNewPassword(String email, String passwordOld, String passwordNew) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getEmailVerification() && passwordEncoder.matches(passwordOld, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(passwordNew));
            userRepository.save(user);
            return true;
        } else
            return false;
    }

    @Override
    public void setNewUsername(String email, String username) {
        try {
            User user = userRepository.findByEmail(email);
            if (user != null && !userRepository.existsByUsername(username)) {
                if (username.isEmpty()) {
                    throw new MessagingException("Username cannot be empty");
                }
                user.setUsername(username);
                userRepository.save(user);
            } else {
                throw new NotFoundException("User not found");
            }
        } catch (NotFoundException | MessagingException e) {
            e.getStackTrace();
        }
    }

    @Override
    public boolean setNewBio(String email, String bio) {
        try {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                if (bio.isEmpty()) {
                    throw new MessagingException("Bio cannot be empty");
                }
                user.setBio(bio);
                userRepository.save(user);
                return true;
            } else {
                throw new NotFoundException("User not found");
            }
        } catch (NotFoundException | MessagingException e) {
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public boolean setTokenPassword(String mail) throws MessagingException {
        User user = userRepository.findByEmail(mail);
        if (user != null && user.getEmailVerification()) {
            user.setTokenLostPassword(UUID.randomUUID().toString());
            userRepository.save(user);

            String username = user.getUsername();
            String link = "http://localhost:8080/lostpassword/reset?token=" + user.getTokenLostPassword();
            String massage = "You are just in the middle of having your new password. \n Please confirm your new email address.";

            emailSenderService.sendMailWithAttachment(user.getEmail(), buildEmail(username, massage, link), "Password Recovery");


            return true;
        }
        return false;
    }

    @Override
    public boolean setTokenEmail(String oldEmail, String newEmail) throws MessagingException {
        User user = userRepository.findByEmail(oldEmail);
        if (user != null) {
            user.setNewEmail(newEmail);
            user.setNewEmailToken(UUID.randomUUID().toString());
            userRepository.save(user);

            String username = user.getUsername();
            String link = "http://localhost:8080/user/edit/newemail/" + user.getNewEmailToken();
            String massage = "You are just in the middle of setting up your new email address. \n Please confirm your new email address.";

            emailSenderService.sendMailWithAttachment(user.getNewEmail(), buildEmail(username, massage, link), "New Mail Confirmation");
            return true;
        }
        return false;
    }

    @Override
    public boolean activateNewEmail(String token) {
        User user = userRepository.findByNewEmailToken(token);
        if (user != null) {
            user.setEmail(user.getNewEmail());
            user.setNewEmail(null);
            user.setNewEmailToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user != null) {
            user.setEmailVerification(Boolean.TRUE);
            user.setActivationCode(null);
            userRepository.save(user);
            return true;
        } else
            return false;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(usernameOrEmail);
        if (user != null) {
            if (user.getEmailVerification()) {
                return new org.springframework.security.core.userdetails.User(user.getEmail()
                        , user.getPassword(),
                        user.getRoles().stream()
                                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toList()));
            } else {
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
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }


    private String buildEmail(String username, String massage, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hello " + username + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + massage + "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Confirm your email</a> </p></blockquote>\n <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
