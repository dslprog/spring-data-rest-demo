package com.dslprog.springdatarestdemo;

import com.dslprog.springdatarestdemo.dto.RoleDto;
import com.dslprog.springdatarestdemo.service.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RoleConstraintsValidationExceptionTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Test
    public void constraintsValidationExceptionTest(){
        assertThrows(ConstraintViolationException.class, ()->{roleService.create(new RoleDto(null));});
        assertThrows(ConstraintViolationException.class, ()->{roleService.create(new RoleDto(""));});
        assertThrows(ConstraintViolationException.class, ()->{roleService.create(new RoleDto("1234567890123456"));});
    }

    @Test
    public void shouldCreateRole(){
        String roleName = "ROLE_ADMIN";
        RoleDto roleDto = roleService.create(new RoleDto(roleName));
        assertEquals(roleDto.getName(), roleName);
        assertNotNull(roleDto.getId());
    }
}
