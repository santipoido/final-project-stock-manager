package com.tpfinal.stockmanager.service.interfaces;

import com.tpfinal.stockmanager.model.dto.RegisterRequest;
import com.tpfinal.stockmanager.model.implementations.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public interface IntUserService extends BaseService<User, Integer> {
    @Transactional
    User update(Integer id, RegisterRequest entityDetails) throws EntityNotFoundException;
    //metodos extras
}
