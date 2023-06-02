package Blogify.services;

import Blogify.entities.Provider;
import Blogify.entities.Role;
import Blogify.entities.User;
import Blogify.repositories.UserRepo;


import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("Користувач з іменем " + username + " не існує");
            throw new UsernameNotFoundException("Користувач з іменем " + username + " не існує");
        }
        return user;
    }

    public void processOAuthPostLogin(String email, String username) {
        User user = new User();
            user.setEmail(email);
            user.setUsername(username);
            user.setRoles(Collections.singleton(Role.USER));
            user.setProvider(Provider.GOOGLE);
            userRepo.save(user);
    }

    public boolean userExists(User user) {
        return userRepo.findByUsername(user.getUsername()) != null;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        user.setProvider(Provider.LOCAL);
        userRepo.save(user);
    }

}
