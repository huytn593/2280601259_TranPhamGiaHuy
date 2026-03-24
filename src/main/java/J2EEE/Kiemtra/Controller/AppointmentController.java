package J2EEE.Kiemtra.Controller;

import J2EEE.Kiemtra.Repository.AppointmentRepository;
import J2EEE.Kiemtra.Service.AppointmentService;
import J2EEE.Kiemtra.Repository.PatientRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;

    public AppointmentController(AppointmentService appointmentService, AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/appointments/book")
    public String bookAppointment(@RequestParam Long doctorId,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                  Principal principal) {
        appointmentService.bookAppointment(principal.getName(), doctorId, date);
        return "redirect:/my-appointments";
    }

    @GetMapping("/my-appointments")
    public String myAppointments(Model model, Principal principal) {
        Long patientId = patientRepository.findByUsername(principal.getName()).orElseThrow().getId();
        model.addAttribute("appointments", appointmentRepository.findByPatientId(patientId));
        return "my-appointments";
    }
}
