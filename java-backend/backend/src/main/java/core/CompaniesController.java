package core;

import java.util.Collection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import core.dao.CompaniesService;
import core.dto.Company;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class CompaniesController {

    @RequestMapping("/")
    public Collection<Company> index() {
        CompaniesService sCompany = new CompaniesService();
        return sCompany.getCompanies();
    }

}