package com.moviePocket.controller.user;

import com.moviePocket.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@Controller
@RequestMapping("/user/edit")
@Api(value = "User edition controller", tags = "Bio, username, email or password edition")
public class UserEditController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String editForm() {
        return "user_edit";
    }


    @GetMapping("/delete")
    public String deleteForm() {
        return "delete";
    }

    @ApiOperation(value = "Delete a user", notes = "User set status deleted and stays in db")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully deleted the user"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/delete")
    public String deleteUser(@RequestParam String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userService.deleteUser(authentication.getName(), password))
            return "login";
        else
            return "delete";
    }


    @GetMapping("/newpas")
    public String newPasswordGetForm() {
        return "set_new_pas";
    }

    @ApiOperation("Set a new password(password is validated")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully set the new password"),
            @ApiResponse(code = 400, message = "Password does not match the criteria"),

    })
    @PostMapping("/newpas")
    public String newPasswordPostForm(
            @RequestParam("passwordold") String passwordOld,
            @RequestParam("password0") String passwordNew0,
            @RequestParam("password1") String passwordNew1) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.setNewPassword(authentication.getName(), passwordOld, passwordNew0, passwordNew1);
    }

    @PostMapping("/newemail")
    @ApiOperation("Set a new email")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully set the new email"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public String newEmailGetForm(@RequestParam("email") String email) throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setTokenEmail(authentication.getName(), email);
        return "user_edit";
    }

    @GetMapping("/newemail/{token}")
    @ApiOperation("Activate a new email")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully activated the new email"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public String activate(@PathVariable String token) {
        userService.activateNewEmail(token);
        return "user_edit";
    }

    @PostMapping("/newusername")
    @ApiOperation(value = "Set a new username", notes = "username should be unique and not empty")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully set the new username"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public String newSetNewUsername(@RequestParam("username") String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setNewUsername(authentication.getName(), username);
        return "user_edit";
    }


    @ApiOperation(value = "Set a new bio", notes = "username should be not empty")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully set the new username"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/newbio")
    public String newSetNewBio(@RequestParam("bio") String bio) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.setNewBio(authentication.getName(), bio);
        return "user_edit";
    }
}
