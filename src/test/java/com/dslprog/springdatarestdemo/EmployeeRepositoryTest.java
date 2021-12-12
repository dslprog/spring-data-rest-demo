package com.dslprog.springdatarestdemo;

import com.dslprog.springdatarestdemo.model.Employee;
import com.dslprog.springdatarestdemo.model.Project;
import com.dslprog.springdatarestdemo.model.Role;
import com.dslprog.springdatarestdemo.repository.EmployeeRepository;
import com.dslprog.springdatarestdemo.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        admin = roleRepository.save(admin);
        user = roleRepository.save(user);

        Project project1 = new Project("Project 1");
        Project project2 = new Project("Project 2");
        Project project3 = new Project("Project 3");

        Employee john = new Employee("John", "Wick", "emp001", "jwick@test.com", admin);
        john.setProjects(new ArrayList<Project>());
        john.getProjects().add(project1);
        john.getProjects().add(project2);
        employeeRepository.save(john);

        Employee claire = new Employee("Claire", "Redfield", "emp002", "credfield@test.com", user);
        claire.setProjects(new ArrayList<Project>());
        claire.getProjects().add(project3);
        employeeRepository.save(claire);

        employeeRepository.flush();
    }

    @Test
    public void shouldSaveEmployee() {
        assertEquals(2, employeeRepository.findAll().size());
    }

    @Test
    public void shouldFindByBadgeNumber(){
        String badgeNumber = "emp002";
        Employee claire = employeeRepository.findByBadgeNumber(badgeNumber);
        assertEquals(claire.getBadgeNumber(), badgeNumber);
    }

    @Test
    public void shouldPersistEmployeeProjects(){
        Employee john = employeeRepository.findByBadgeNumber("emp001");
        assertEquals(2, john.getProjects().size());

        Employee claire = employeeRepository.findByBadgeNumber("emp002");
        assertEquals(1, claire.getProjects().size());
    }
}
