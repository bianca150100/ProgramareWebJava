package cabinetMedical;

import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;
import cabinetMedical.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class CabinetMedicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabinetMedicalApplication.class, args);
	}


}
