package com.tpfinal.stockmanager.repository.interfaces;

import com.tpfinal.stockmanager.model.implementations.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
