package com.ineel.ifm.segurity.service;

import com.ineel.ifm.model.usuario.Usuario;
import com.ineel.ifm.segurity.model.AuthUser;
import com.ineel.ifm.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService {
    @Autowired
    UsuarioService service;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = this.service.findByEmail(email);
        return AuthUser.build(user);
    }
}
