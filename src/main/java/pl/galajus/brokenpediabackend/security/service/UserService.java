package pl.galajus.brokenpediabackend.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.galajus.brokenpediabackend.security.model.User;
import pl.galajus.brokenpediabackend.security.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User getByUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
    public User getByUuid(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return userRepository.findByUuid(uuid).orElseThrow();
    }
    public User getByConfirmAccountHash(String hash) {
        return userRepository.findByConfirmAccountHash(hash).orElseThrow();
    }
    public User getByLostPasswordHash(String hash) {
        return userRepository.findByConfirmAccountHash(hash).orElseThrow();
    }

    public boolean userExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
