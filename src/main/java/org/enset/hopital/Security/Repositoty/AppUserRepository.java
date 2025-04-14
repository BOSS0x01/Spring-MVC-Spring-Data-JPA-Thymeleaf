package org.enset.hopital.Security.Repositoty;

import org.enset.hopital.Entities.Patient;
import org.enset.hopital.Security.Entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
