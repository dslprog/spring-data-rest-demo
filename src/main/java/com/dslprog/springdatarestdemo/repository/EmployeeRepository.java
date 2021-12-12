package com.dslprog.springdatarestdemo.repository;

import com.dslprog.springdatarestdemo.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    Employee findByBadgeNumber(String badgeNumber);
    Page<Employee> findByEmailContains(String s, Pageable page);

    @RestResource(path="/findByCustom")
    Page<Employee> findByFirstNameContainsIgnoreCase(@Param("customString") String s, Pageable page);
}
