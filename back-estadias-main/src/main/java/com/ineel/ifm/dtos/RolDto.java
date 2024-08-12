package com.ineel.ifm.dtos;

import com.ineel.ifm.model.rol.Rol;
import com.ineel.ifm.model.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RolDto {

    private Long id;
    private String name;
    private Boolean status;
    private Set<Usuario> usuario;

    public Rol getRol(){
        return new Rol(
                getId(),
                getName(),
                getStatus(),
                getUsuario()
        );
    }
}
