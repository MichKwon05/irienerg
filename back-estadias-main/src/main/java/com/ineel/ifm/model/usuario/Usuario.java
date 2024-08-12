package com.ineel.ifm.model.usuario;

import com.ineel.ifm.model.rol.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "second_surname")
    private String second_surname;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private Boolean status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}
