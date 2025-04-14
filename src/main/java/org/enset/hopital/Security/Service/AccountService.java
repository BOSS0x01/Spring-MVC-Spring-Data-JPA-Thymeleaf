package org.enset.hopital.Security.Service;

import org.enset.hopital.Security.Entities.AppRole;
import org.enset.hopital.Security.Entities.AppUser;

public interface AccountService {
    AppUser addUser(String username, String password,String email,String confirmPassword);
    AppRole addRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);
}
