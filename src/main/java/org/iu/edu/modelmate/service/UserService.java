package org.iu.edu.modelmate.service;

import org.iu.edu.modelmate.model.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserCreatedResponse createUser(User user);


    public UserLoginResponse loginUser(UserLogin userLogin);


}
