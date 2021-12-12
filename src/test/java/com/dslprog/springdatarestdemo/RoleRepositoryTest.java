package com.dslprog.springdatarestdemo;

import com.dslprog.springdatarestdemo.dto.RoleDto;
import com.dslprog.springdatarestdemo.model.Role;
import com.dslprog.springdatarestdemo.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        admin = roleRepository.save(admin);
        user = roleRepository.save(user);
    }

    @Test
    public void shouldPersistRoles(){
        Role admin = roleRepository.findByName("ROLE_ADMIN");
        assertNotNull(admin);
    }

    @Test
    void shouldCopyEntityToDto(){
        Role admin = roleRepository.findByName("ROLE_ADMIN");

        ModelMapper modelMapper = new ModelMapper();
        RoleDto roleDto = modelMapper.map(admin, RoleDto.class);
        assertEquals(admin.getId(),roleDto.getId());
        assertEquals(admin.getName(),roleDto.getName());
    }
}
