package com.tpfinal.stockmanager.repository.interfaces;

import com.tpfinal.stockmanager.model.implementations.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
