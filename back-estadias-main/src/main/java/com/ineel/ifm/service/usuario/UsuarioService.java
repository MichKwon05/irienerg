package com.ineel.ifm.service.usuario;

import com.ineel.ifm.config.CustomResponse;
import com.ineel.ifm.dtos.UsuarioDto;
import com.ineel.ifm.model.rol.Rol;
import com.ineel.ifm.model.rol.RolRepository;
import com.ineel.ifm.model.usuario.Usuario;
import com.ineel.ifm.model.usuario.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    @Lazy
    private PasswordEncoder encoder;


    @Transactional(readOnly = true)
    public CustomResponse<List<Usuario>> getAll() {
        return new CustomResponse<>(
                this.usuarioRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Usuario> getOne(Long id) {
        Optional<Usuario> optionalUser = this.usuarioRepository.findById(id);
        if (optionalUser.isPresent()) {
            return new CustomResponse<>(optionalUser.get(), false, 200, "Usuario encontrado");
        } else {
            return new CustomResponse<>(null, true, 404, "Usuario no encontrado");
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Usuario>> getActive() {
        return new CustomResponse<>(
                this.usuarioRepository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Usuario>> getInactive() {
        return new CustomResponse<>(
                this.usuarioRepository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }


    @Transactional
    public CustomResponse<Usuario> insert(Usuario usuario) {
        // Verificar si el correo ya está registrado
        if (usuarioRepository.findByEmailAndStatusTrue(usuario.getEmail()) == null) {
            // Verificar y asignar el rol si es necesario
            if (usuario.getRol() != null) {
                if (usuario.getRol().getId() == null) {
                    usuario.getRol().setStatus(true); // Asegurar que el rol esté activo por defecto
                    Rol existingRol = rolRepository.findByName(usuario.getRol().getName());
                    if (existingRol != null) {
                        usuario.setRol(existingRol);
                    } else {
                        Rol persistedRol = rolRepository.save(usuario.getRol());
                        usuario.setRol(persistedRol);
                    }
                }
            } else {
                return new CustomResponse<>(null, true, 400, "No se encontró el rol");
            }

            // Establecer la contraseña encriptada
            String password = usuario.getPassword();
            if (password != null && password.length() >= 10 && containsUppercase(password) && containsSpecialCharacter(password)) {
                usuario.setPassword(encoder.encode(password));
                usuario.setStatus(true); // Los nuevos usuarios están activos por defecto

                Usuario savedUser = usuarioRepository.save(usuario);
                // Aquí puedes implementar el envío de correo si es necesario
                return new CustomResponse<>(savedUser, false, 201, "Usuario creado con éxito");
            } else {
                return new CustomResponse<>(null, true, 400, "La contraseña no cumple con los requisitos mínimos");
            }
        } else {
            return new CustomResponse<>(null, true, 400, "El correo ya está registrado");
        }
    }

    private boolean containsUppercase(String str) {
        return str.chars().anyMatch(Character::isUpperCase);
    }

    private boolean containsSpecialCharacter(String str) {
        return str.chars().anyMatch(ch -> !Character.isLetterOrDigit(ch));
    }

    // Editar un usuario existente
    @Transactional
    public CustomResponse<Usuario> update(Long id, UsuarioDto usuarioDto) {
        Optional<Usuario> optionalUser = this.usuarioRepository.findById(id);
        if (optionalUser.isPresent()) {
            Usuario existingUser = optionalUser.get();
            existingUser.setEmail(usuarioDto.getEmail());
            existingUser.setPassword(usuarioDto.getPassword());
            existingUser.setName(usuarioDto.getName());
            existingUser.setLastname(usuarioDto.getLastname());
            existingUser.setSecond_surname(usuarioDto.getSecond_surname());
            existingUser.setRol(usuarioDto.getRol());
            // No se cambia el estado (status)
            Usuario updatedUser = this.usuarioRepository.save(existingUser);
            return new CustomResponse<>(updatedUser, false, 200, "Usuario actualizado con éxito");
        } else {
            return new CustomResponse<>(null, true, 404, "Usuario no encontrado");
        }
    }

    // Eliminar (desactivar) un usuario
    @Transactional
    public CustomResponse<Void> delete(Long id) {
        Optional<Usuario> optionalUser = this.usuarioRepository.findById(id);
        if (optionalUser.isPresent()) {
            this.usuarioRepository.deleteById(id);
            return new CustomResponse<>(null, false, 200, "Usuario eliminado con éxito");
        } else {
            return new CustomResponse<>(null, true, 404, "Usuario no encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Usuario findByEmail(String correo) {
        return this.usuarioRepository.findByEmailAndStatusTrue(correo);
    }


}
