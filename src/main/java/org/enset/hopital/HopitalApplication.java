package org.enset.hopital;

import org.enset.hopital.Entities.Patient;
import org.enset.hopital.Repositoty.PatientRepository;
import org.enset.hopital.Security.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        patientRepository.save(new Patient(null,"Hassane",new Date(),false,4));
//        patientRepository.save(new Patient(null,"khdija",new Date(),true,8));
//        patientRepository.save(new Patient(null,"karim",new Date(),false,4));
//        patientRepository.save(new Patient(null,"Imran",new Date(),false,7));
    }
//    @Bean
    CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {

        };
    }

//    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
        return args -> {
            PasswordEncoder encoder = passwordEncoder();
            UserDetails user11 = jdbcUserDetailsManager.loadUserByUsername("user11");
            if (user11 == null)
                jdbcUserDetailsManager.createUser(
                User.withUsername("user11").password(encoder.encode( "1234")).roles("USER").build()
                );
            UserDetails user22 = jdbcUserDetailsManager.loadUserByUsername("user22");
            if (user22 == null)
                jdbcUserDetailsManager.createUser(
                User.withUsername("user22").password(encoder.encode( "1234")).roles("USER").build()
                );
            UserDetails admin2 = jdbcUserDetailsManager.loadUserByUsername("admin2");
            if (admin2 == null)
                jdbcUserDetailsManager.createUser(
                User.withUsername("admin2").password(encoder.encode( "1234")).roles("ADMIN","USER").build()
                );
        };
    }

//    @Bean
    CommandLineRunner userDetailsManger(AccountService accountService) {
        return args -> {
            accountService.addRole("USER");
            accountService.addRole("ADMIN");

            accountService.addUser("user0","1234","gejeje","1234");
            accountService.addUser("user00","1234","gejeje","1234");
            accountService.addUser("user000","1234","gejeje","1234");

            accountService.addRoleToUser("user0","USER");
            accountService.addRoleToUser("user00","ADMIN");
            accountService.addRoleToUser("user000","ADMIN");
            accountService.addRoleToUser("user000","USER");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
