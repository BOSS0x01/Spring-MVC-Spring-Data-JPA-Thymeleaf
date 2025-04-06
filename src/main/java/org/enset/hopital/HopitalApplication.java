package org.enset.hopital;

import org.enset.hopital.Entities.Patient;
import org.enset.hopital.Repositoty.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {

        };
    }
}
