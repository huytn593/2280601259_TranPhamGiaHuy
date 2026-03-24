package J2EEE.Kiemtra.Service;

import J2EEE.Kiemtra.Model.Doctor;
import J2EEE.Kiemtra.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Page<Doctor> getDoctorsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return doctorRepository.findAll(pageable);
    }
}
