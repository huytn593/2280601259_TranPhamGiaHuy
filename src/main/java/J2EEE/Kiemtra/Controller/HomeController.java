package J2EEE.Kiemtra.Controller;

import J2EEE.Kiemtra.Model.Doctor;
import J2EEE.Kiemtra.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping({"/", "/home"})
    public String home(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        Page<Doctor> doctorPage = doctorService.getDoctorsPaginated(page, pageSize);
        
        model.addAttribute("doctorPage", doctorPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", doctorPage.getTotalPages());
        
        return "home";
    }
}
