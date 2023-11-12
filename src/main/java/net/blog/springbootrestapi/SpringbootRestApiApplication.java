package net.blog.springbootrestapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import net.blog.springbootrestapi.entity.Role;
import net.blog.springbootrestapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(title = "Spring Boot Blog Application with REST APIs")
)
public class SpringbootRestApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role();
		adminRole.setName("Role_ADMIN");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
	}
}
