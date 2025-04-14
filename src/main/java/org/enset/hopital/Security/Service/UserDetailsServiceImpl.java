package org.enset.hopital.Security.Service;

import lombok.AllArgsConstructor;
import org.enset.hopital.Security.Entities.AppRole;
import org.enset.hopital.Security.Entities.AppUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = accountService.loadUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException(String.format("User %s not found", username));

        UserDetails userDetails = User
                .withUsername(username)
                .password(user.getPassword())
                .roles(user.roles.stream().map(AppRole::getRole).toArray(String[]::new))
                .build();
        return userDetails;
    }
}
