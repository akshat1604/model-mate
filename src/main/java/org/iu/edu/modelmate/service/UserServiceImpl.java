package org.iu.edu.modelmate.service;

import org.iu.edu.modelmate.common.UserConstants;
import org.iu.edu.modelmate.model.*;
import org.iu.edu.modelmate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserCreatedResponse createUser(User user) {
        UserCreatedResponse userCreatedResponse = new UserCreatedResponse();
        if(doesUserExists(user.getUsername())){
            userCreatedResponse.setError(UserConstants.userExists);
            return userCreatedResponse;
        }
        encryptUserPassword(user);
        userRepository.save(user);

        userCreatedResponse.setMessage(UserConstants.userCreateSuccessfully);
        return userCreatedResponse;
    }

    @Override
    public UserLoginResponse loginUser(UserLogin userLogin) {
        User user = userRepository.findByUsername(userLogin.getUsername());


        UserLoginResponse userLoginResponse = new UserLoginResponse();
        if(user!=null && isPasswordValid(userLogin.getPassword(),user.getPassword())){
            userLoginResponse.setMessage(UserConstants.userLoggedIn);
        }
        else{
            userLoginResponse.setError(UserConstants.userLoginError);
        }

        return userLoginResponse;
    }

    private boolean isPasswordValid(String rawPassword, String encodedPassword){
        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }

    private void encryptUserPassword(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    private boolean doesUserExists(String username){
        User existingUser = userRepository.findByUsername(username);

        if(!ObjectUtils.isEmpty(existingUser) || existingUser != null){
            return true;
        }
        return false;
    }
}
