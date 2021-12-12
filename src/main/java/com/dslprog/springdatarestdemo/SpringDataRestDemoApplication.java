package com.dslprog.springdatarestdemo;

import com.dslprog.springdatarestdemo.model.Employee;
import com.dslprog.springdatarestdemo.model.Role;
import com.dslprog.springdatarestdemo.repository.EmployeeRepository;
import com.dslprog.springdatarestdemo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class SpringDataRestDemoApplication {
    public static void main(String[] args) {
        System.out.println("Spring version: " + SpringVersion.getVersion());
        SpringApplication.run(SpringDataRestDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(EmployeeRepository employeeJpaRepository, RoleRepository roleJpaRepository){
        return args -> {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");

            admin = roleJpaRepository.save(admin);
            user = roleJpaRepository.save(user);

            employeeJpaRepository.save(new Employee("John", "Wick", "emp001", "jwick@test.com", admin));
            employeeJpaRepository.save(new Employee("Claire", "Redfield", "emp002", "credfield@test.com", user));

            employeeJpaRepository.findAll().forEach(employee -> {
                System.out.println(employee.getFirstName()+" "+employee.getLastName());
            });
        };
    }
}
