package com.ineel.ifm.dtos;
import com.ineel.ifm.model.rol.Rol;
import com.ineel.ifm.model.usuario.Usuario;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioDto {

    private Long id;
    /*@Email(message = "Correo electrónico no válido")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@gmail\\.[a-zA-Z]{2,}$", message = "El correo electrónico debe ser de Gmail")*/
    private String email;
    /*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")*/
    private String password;
    private String name;
    private String lastname;
    private String second_surname;
    private Boolean status;
    private Rol rol;

    public Usuario getUsuario(){
        return new Usuario(
                getId(),
                getEmail(),
                getPassword(),
                getName(),
                getLastname(),
                getSecond_surname(),
                getStatus(),
                getRol()
        );
    }
}
