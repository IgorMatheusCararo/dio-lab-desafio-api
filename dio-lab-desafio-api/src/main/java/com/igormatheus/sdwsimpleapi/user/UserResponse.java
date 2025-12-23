package com.igormatheus.sdwsimpleapi.user;

public record UserResponse(Long id, String name, String email) {

    public static UserResponse fromEntity(UserEntity entity) {
        return new UserResponse(entity.getId(), entity.getName(), entity.getEmail());
    }
}
