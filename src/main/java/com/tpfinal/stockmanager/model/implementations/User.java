package com.tpfinal.stockmanager.model.implementations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tpfinal.stockmanager.service.implementations.SaleService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    @Table (name = "userDB")
    public class User implements UserDetails {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private int id;

        private String name;

        private String lastname;

        private String username;

        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @JsonIgnore
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Sale> sales;

        public User(User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.lastname = user.getLastname();
            this.username = user.getUsername();
            this.password = user.getPassword();
            this.role = user.getRole();
            this.sales = user.getSales();
        }


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }


        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
