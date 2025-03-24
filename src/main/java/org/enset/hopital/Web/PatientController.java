package org.enset.hopital.Web;

import lombok.AllArgsConstructor;
import org.enset.hopital.Entities.Patient;
import org.enset.hopital.Repositoty.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("listPatients", patients);
        return "Patients";
    }
}
