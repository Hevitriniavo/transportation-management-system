package hei.shool.transportationmanagementsystem.services.impl;

import hei.shool.transportationmanagementsystem.entities.User;
import hei.shool.transportationmanagementsystem.entities.Role;
import hei.shool.transportationmanagementsystem.repositories.UserRepository;
import hei.shool.transportationmanagementsystem.repositories.RoleRepository;
import hei.shool.transportationmanagementsystem.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Map<String, String> register(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("USER").build()));

        if (user.getRoles() != null && !user.getRoles().contains(role)) {
            user.getRoles().add(role);
        }

        userRepository.save(user);
        return Map.of("response", "user added successfully");
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not Fountd"));
    }

}
