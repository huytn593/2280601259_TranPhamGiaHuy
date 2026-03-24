package J2EEE.Kiemtra.Service;

import J2EEE.Kiemtra.Model.Appointment;
import J2EEE.Kiemtra.Model.Doctor;
import J2EEE.Kiemtra.Model.Patient;
import J2EEE.Kiemtra.Repository.AppointmentRepository;
import J2EEE.Kiemtra.Repository.DoctorRepository;
import J2EEE.Kiemtra.Repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Appointment bookAppointment(String username, Long doctorId, LocalDateTime date) {
        Patient patient = patientRepository.findByUsername(username).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(date);
        
        return appointmentRepository.save(appointment);
    }
}
