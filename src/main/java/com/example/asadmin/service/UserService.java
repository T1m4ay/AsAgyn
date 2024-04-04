package com.example.asadmin.service;

import com.example.asadmin.model.User;
import com.example.asadmin.repository.UserRepository;
import com.example.asadmin.security.jwt.JwtUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getFullName(),
                user.getPassword(),
                user.getRoles());
    }

}
