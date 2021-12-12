package com.dslprog.springdatarestdemo.service;

import com.dslprog.springdatarestdemo.dto.RoleDto;

import javax.validation.Valid;

public interface RoleService {
    public RoleDto create(@Valid RoleDto roleDto);
}
