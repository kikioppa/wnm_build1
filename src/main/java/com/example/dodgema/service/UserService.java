package com.example.dodgema.service;

import com.example.dodgema.model.Role;
import com.example.dodgema.model.User;
import com.example.dodgema.repository.RoleRepository;
import com.example.dodgema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service("userService")

public class UserService {

    private static UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository,
                       @Qualifier("roleRepository") RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User LoginUserByEmailAndPassword(String email, String password) {
        Optional<User> loginUser = userRepository.findUserByEmailAndPassword(email, password);
        if (loginUser == null) {
            return null;
        }
        return loginUser.get();
    }

    public static User findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public User getUserByEmail(String email) {
        User getUser = userRepository.findByEmail(email);
        if (getUser == null) {
            return null;
        }
        return getUser;
    }
    public User findByIsEnabled(boolean isEnabled) {
        return userRepository.findByIsEnabled(isEnabled);
    }
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    public void saveKakaoUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

}