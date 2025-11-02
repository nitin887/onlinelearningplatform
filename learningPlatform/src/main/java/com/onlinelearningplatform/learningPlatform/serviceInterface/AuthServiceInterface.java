package com.onlinelearningplatform.learningPlatform.serviceInterface;

import com.onlinelearningplatform.learningPlatform.dto.UserDTO;

public interface AuthServiceInterface {
    //login
   UserDTO creatingLogin(String username, String password);
    //logout
    UserDTO creatingLogout();
    //reset password
    UserDTO creatingResetPassword(String newPassword);
    //change password
    UserDTO creatingChangePassword(String oldPassword, String newPassword);
    //forgot password
    UserDTO creatingForgotPassword(String email);
    //register
    UserDTO creatingRegister(String username, String email, String password);

}
