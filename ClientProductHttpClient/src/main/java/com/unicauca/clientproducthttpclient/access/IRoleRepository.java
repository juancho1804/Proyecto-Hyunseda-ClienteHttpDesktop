package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Role;

public interface IRoleRepository {
    public Role findById(Long id);
}
