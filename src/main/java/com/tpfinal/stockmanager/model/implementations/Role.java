package com.tpfinal.stockmanager.model.implementations;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
