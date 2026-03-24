package J2EEE.Kiemtra.Controller;

import J2EEE.Kiemtra.Model.Patient;
import J2EEE.Kiemtra.Model.Role;
import J2EEE.Kiemtra.Repository.PatientRepository;
import J2EEE.Kiemtra.Repository.RoleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class AuthController {
    
    private final PatientRepository patientRepository;
    private final RoleRepository roleRepository;

    public AuthController(PatientRepository patientRepository, RoleRepository roleRepository) {
        this.patientRepository = patientRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("patient", new Patient());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(Patient patient, Model model) {
        if (patientRepository.findByUsername(patient.getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }
        Role patientRole = roleRepository.findByName("PATIENT").orElseGet(() -> {
            Role r = new Role();
            r.setName("PATIENT");
            return roleRepository.save(r);
        });
        patient.setRoles(Collections.singleton(patientRole));
        patientRepository.save(patient);
        return "redirect:/login?registered";
    }
}
