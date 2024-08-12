package com.ineel.ifm.segurity.controller;

import com.ineel.ifm.config.CustomResponse;
import com.ineel.ifm.segurity.jwt.JwtProvider;
import com.ineel.ifm.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
public class AuthController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtProvider provider;

    @Autowired
    UsuarioService service;

    @PostMapping("/login")
    public ResponseEntity<CustomResponse<Object>> login(
            @Valid @RequestBody LoginDto login
    ) {
        try {
            // Autenticación del usuario
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getEmail(), login.getPassword()
                    )
            );

            // Establece la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Obtiene el nombre de usuario del principal autenticado
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();

            // Genera el token usando el nombre de usuario
            String token = provider.generateToken(username);

            // Obtiene los detalles del usuario
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Prepara la respuesta
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("user", userDetails);

            return new ResponseEntity<>(
                    new CustomResponse<>(data, false, 200, "OK"),
                    HttpStatus.OK
            );
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(
                    new CustomResponse<>(null, true, 400, "No se pudo iniciar sesión"),
                    HttpStatus.OK
            );
        }
    }
}
