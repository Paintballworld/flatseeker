package com.yakzhanov.flatseeker.repository.infrastructure;

import com.yakzhanov.flatseeker.model.infrastructure.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    @Query("from Authority where name = 'ROLE_USER'")
    Authority loadDefaultUserAuthority();
}
