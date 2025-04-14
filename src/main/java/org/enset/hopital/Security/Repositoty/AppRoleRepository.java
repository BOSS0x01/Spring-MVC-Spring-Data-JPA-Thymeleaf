package org.enset.hopital.Security.Repositoty;

import org.enset.hopital.Security.Entities.AppRole;
import org.enset.hopital.Security.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    Optional<AppRole> findByRole(String role);
}
