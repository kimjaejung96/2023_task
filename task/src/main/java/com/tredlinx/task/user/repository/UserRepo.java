package com.tredlinx.task.user.repository;

import com.tredlinx.task.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserId(String userId);
    boolean existsByUserId(String userId);
    Optional<UserEntity> findByUid(String uid);
}
