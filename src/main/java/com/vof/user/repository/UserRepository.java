package com.vof.user.repository;

import com.vof.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity,String> {
    @Query("select u from UserEntity u where u.username = ?1")
    Optional<UserEntity> findByUsername(String username);
}
