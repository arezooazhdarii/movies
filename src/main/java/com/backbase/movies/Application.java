package com.backbase.movies;

import com.backbase.movies.service.RoleService;
import com.backbase.movies.service.UserService;
import com.backbase.movies.service.dto.RoleDTO;
import com.backbase.movies.service.dto.UserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@SpringBootApplication()
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}



	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService) {
		if (roleService.getRoles().isEmpty()) {
			return args -> {
				roleService.saveRole(new RoleDTO(null,"ROLE_USER"));
				roleService.saveRole(new RoleDTO(null,"ROLE_ADMIN"));

				userService.saveUser(new UserDTO(null,"user user","user","user",new ArrayList<>()));
				userService.saveUser(new UserDTO(null,"admin admin","admin","admin",new ArrayList<>()));

				roleService.addRoleToUser("user","ROLE_USER");
				roleService.addRoleToUser("admin","ROLE_ADMIN");
			};
		}
		return null;
	}

}
