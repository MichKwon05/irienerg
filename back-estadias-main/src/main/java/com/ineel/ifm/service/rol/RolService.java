package com.ineel.ifm.service.rol;

import com.ineel.ifm.config.CustomResponse;
import com.ineel.ifm.model.rol.Rol;
import com.ineel.ifm.model.rol.RolRepository;
import com.ineel.ifm.model.usuario.Usuario;
import com.ineel.ifm.service.usuario.UsuarioService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional(readOnly = false)
    public void createRoles() {
        try {
            List<Rol> roles = this.rolRepository.findAll();
            if (roles.isEmpty()) {

                Rol rolAdmin = new Rol();
                rolAdmin.setStatus(true);
                rolAdmin.setName("Admin");
                this.rolRepository.save(rolAdmin);

                Rol rolUsuario = new Rol();
                rolUsuario.setStatus(true);
                rolUsuario.setName("Usuario");
                this.rolRepository.save(rolUsuario);

                rolAdmin = this.rolRepository.findByName("ADMIN");
                rolUsuario = this.rolRepository.findByName("USUARIO");

                Usuario adminUser = new Usuario();
                adminUser.setEmail("20213tn011@utez.edu.mx");
                adminUser.setPassword("RafaRt123!");
                adminUser.setName("Michelle");
                adminUser.setLastname("Estrada");
                adminUser.setSecond_surname("Hernández");
                adminUser.setRol(rolAdmin);
                this.usuarioService.insert(adminUser);

                Usuario modeUser = new Usuario();
                modeUser.setEmail("20203tn145@utez.edu.mx");
                modeUser.setPassword("Contraseña!123");
                modeUser.setName("Ricardo");
                modeUser.setLastname("Rodríguez");
                modeUser.setRol(rolUsuario);
                this.usuarioService.insert(modeUser);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Rol>> getAllRoles() {
        try {
            List<Rol> roles = this.rolRepository.findAll();
            return new CustomResponse<>(
                    roles,
                    false,
                    200,
                    "OK"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomResponse<>(
                    null,
                    true,
                    500,
                    "Error al obtener roles"
            );
        }
    }
}
