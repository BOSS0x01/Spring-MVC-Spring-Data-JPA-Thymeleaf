package org.enset.hopital.Web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.enset.hopital.Entities.Patient;
import org.enset.hopital.Repositoty.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {
private PatientRepository patientRepository;

    @GetMapping({"/", "/user/index"})
    public String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name="size",defaultValue="5") int size,
                        @RequestParam(name = "keyword",defaultValue= "") String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "Patients";
    }

    @PreAuthorize("hasRole('ROLE_AMDIN')")
    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword,int page) {
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @PreAuthorize("hasRole('ROLE_AMDIN')")
    @GetMapping("/admin/addPatient")
    public String addPatient(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("action","Add");
        return "formPatient";
    }

    @PreAuthorize("hasRole('ROLE_AMDIN')")
    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, Long id,String keyword,int page) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException("Patient not found");
        model.addAttribute("patient", patient);
        model.addAttribute("action","Edit");
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        return "formPatient";
    }

    @PreAuthorize("hasRole('ROLE_AMDIN')")
    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient , BindingResult bindingResult,@RequestParam(name="keyword",defaultValue = "") String keyword,@RequestParam(name="page",defaultValue = "0") int page) {
        if (bindingResult.hasErrors()) return "redirect:/admin/addPatient";
        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

}
