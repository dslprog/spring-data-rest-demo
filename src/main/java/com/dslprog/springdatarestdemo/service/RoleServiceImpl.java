package com.dslprog.springdatarestdemo.service;

import com.dslprog.springdatarestdemo.dto.RoleDto;
import com.dslprog.springdatarestdemo.model.Role;
import com.dslprog.springdatarestdemo.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDto create(RoleDto roleDto) {
        Role role = modelMapper.map(roleDto, Role.class);
        role = roleJpaRepository.save(role);
        return modelMapper.map(role,RoleDto.class);
    }
}
