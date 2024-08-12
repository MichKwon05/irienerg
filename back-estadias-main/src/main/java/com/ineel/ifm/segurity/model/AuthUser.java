package com.ineel.ifm.segurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ineel.ifm.model.usuario.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUser implements UserDetails {
    private String email;
    @JsonIgnore
    private String password;

    private Usuario user;
    public Collection<? extends GrantedAuthority> authorities;

    public AuthUser(String email, String password, Usuario user,
                    Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
    }

    public static AuthUser build(Usuario user){
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().getName());
        return new AuthUser(user.getEmail(), user.getPassword(), user, List.of(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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

    public Usuario getuser(){
        return user;
    }

    public void setuser(Usuario user){
        this.user = user;
    }

}
