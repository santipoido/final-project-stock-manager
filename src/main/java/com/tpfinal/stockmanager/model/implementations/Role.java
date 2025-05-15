package com.tpfinal.stockmanager.model.implementations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="The role name can't be empty")
    private String roleName;

    private String description;
}
