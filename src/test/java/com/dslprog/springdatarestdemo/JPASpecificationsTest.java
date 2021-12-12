package com.dslprog.springdatarestdemo;

import com.dslprog.springdatarestdemo.model.Employee;
import com.dslprog.springdatarestdemo.model.Role;
import com.dslprog.springdatarestdemo.repository.EmployeeRepository;
import com.dslprog.springdatarestdemo.repository.RoleRepository;
import com.dslprog.springdatarestdemo.specification.EmployeeSpecification;
import com.dslprog.springdatarestdemo.util.SearchCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPASpecificationsTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    private Employee john;
    private Employee tom;

    @BeforeEach
    public void setup(){
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        admin = roleRepository.save(admin);
        user = roleRepository.save(user);

        john = new Employee("John", "Wick", "emp001", "jwick@test.com", admin);
        employeeRepository.save(john);

        tom = new Employee("Tom", "Sawyer", "emp002", "tsawyer@test.com", user);
        employeeRepository.save(tom);
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        EmployeeSpecification spec =
                new EmployeeSpecification(new SearchCriteria("firstName", ":", "John"));

        List<Employee> results = employeeRepository.findAll(spec);

        assertThat(john).isIn(results);
        assertThat(tom).isNotIn(results);
    }
}
