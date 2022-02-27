package com.yakzhanov.flatseeker.repository.infrastructure;

import java.util.Optional;
import com.yakzhanov.flatseeker.model.infrastructure.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String username);

    Boolean existsByLogin(String username);

    Boolean existsByEmail(String email);
}
