package com.dslprog.springdatarestdemo.model.projection;

import com.dslprog.springdatarestdemo.model.Employee;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "web", types = {Employee.class})
public interface EmployeeWebProjection {
    public String getFirstName();

    public String getEmail();
}
