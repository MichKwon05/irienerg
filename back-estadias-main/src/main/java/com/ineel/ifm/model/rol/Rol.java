package com.ineel.ifm.model.rol;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ineel.ifm.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name= "name",nullable = false)
    private String name;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private Boolean status;

    @OneToMany(mappedBy = "rol")
    @JsonIgnore
    private Set<Usuario> usuario;
}
