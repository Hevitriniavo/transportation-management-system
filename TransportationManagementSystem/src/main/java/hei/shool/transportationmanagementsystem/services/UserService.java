package hei.shool.transportationmanagementsystem.services;

import hei.shool.transportationmanagementsystem.entities.User;

import java.util.Map;

public interface UserService {
    Map<String, String> register(User user);

    User findByEmail(String email);
}
