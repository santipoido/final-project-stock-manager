package com.tpfinal.stockmanager.model.implementations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="The username can't be empty")
    @Size(min = 4)
    private String username;

    @NotBlank(message="The password can't be empty")
    @Size(min = 6)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
            message = "La contraseña debe contener al menos una letra mayúscula y un número"
    )
    private String passw;

    //rol
}
