package in.arka.billingsoftware.service;

import in.arka.billingsoftware.io.UserRequest;
import in.arka.billingsoftware.io.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readUsers();

    void deleteUser(String id);
}
