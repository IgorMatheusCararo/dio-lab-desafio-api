package com.igormatheus.sdwsimpleapi.user;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Transactional
    public UserEntity create(UserCreateRequest request) {
        String name = normalize(request.name());
        String email = normalize(request.email());

        if (name.isBlank() || email.isBlank()) {
            throw new BadRequestException("Name and email are required");
        }

        userRepository.findByEmail(email).ifPresent(existing -> {
            throw new ConflictException("Email already in use");
        });

        UserEntity user = new UserEntity(name, email);
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity update(Long id, UserUpdateRequest request) {
        UserEntity user = findById(id);

        String name = normalize(request.name());
        String email = normalize(request.email());

        if (!name.isBlank()) {
            user.setName(name);
        }

        if (!email.isBlank() && !email.equalsIgnoreCase(user.getEmail())) {
            userRepository.findByEmail(email).ifPresent(existing -> {
                throw new ConflictException("Email already in use");
            });
            user.setEmail(email);
        }

        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        UserEntity user = findById(id);
        userRepository.delete(user);
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim();
    }
}
