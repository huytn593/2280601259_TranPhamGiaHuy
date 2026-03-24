package J2EEE.Kiemtra.Controller;

import J2EEE.Kiemtra.Model.Doctor;
import J2EEE.Kiemtra.Repository.DepartmentRepository;
import J2EEE.Kiemtra.Repository.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/doctors")
public class AdminDoctorController {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public AdminDoctorController(DoctorRepository doctorRepository, DepartmentRepository departmentRepository) {
        this.doctorRepository = doctorRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String listDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        return "admin-doctor-list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin-doctor-form";
    }

    @PostMapping("/save")
    public String saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "redirect:/admin/doctors";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorRepository.findById(id).orElseThrow());
        model.addAttribute("departments", departmentRepository.findAll());
        return "admin-doctor-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/admin/doctors";
    }
}
