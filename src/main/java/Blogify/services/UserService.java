package Blogify.services;

import Blogify.entities.Role;
import Blogify.entities.User;
import Blogify.repositories.UserRepo;
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
       if(user==null){
           throw new UsernameNotFoundException("Користувач з іменем "+username+" не існує");
       }else {
          return user;
       }
    }

    public boolean userExists(User user) {
        return userRepo.findByUsername(user.getUsername())!=null;
    }

    public void addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(Collections.singleton(Role.USER));
    userRepo.save(user);
    }
}
