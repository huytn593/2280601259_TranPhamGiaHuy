package J2EEE.Kiemtra.Controller;

import J2EEE.Kiemtra.Model.Doctor;
import J2EEE.Kiemtra.Repository.DoctorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DoctorRestController {

    private final DoctorRepository doctorRepository;

    public DoctorRestController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/api/doctors/search")
    public List<Doctor> searchDoctors(@RequestParam String name) {
        return doctorRepository.findAll().stream()
                .filter(d -> d.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
