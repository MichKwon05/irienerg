package com.ineel.ifm.controller.usuario;

import com.ineel.ifm.config.ApiResponse;
import com.ineel.ifm.config.CustomResponse;
import com.ineel.ifm.dtos.UsuarioDto;
import com.ineel.ifm.model.usuario.Usuario;
import com.ineel.ifm.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioService userService;
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Usuario>>> getAll(){
        return new ResponseEntity<>(
                this.userService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Usuario>> getById(
            @PathVariable("id") Long id
    ){
        return new ResponseEntity<>(
                this.userService.getOne(id),
                HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<CustomResponse<List<Usuario>>> getActive() {
        return new ResponseEntity<>(
                this.userService.getActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/inactive")
    public ResponseEntity<CustomResponse<List<Usuario>>> getInactive() {
        return new ResponseEntity<>(
                this.userService.getInactive(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Usuario>> insert(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(
                this.userService.insert(usuario),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Usuario>> update(
            @PathVariable("id") Long id,
            @RequestBody UsuarioDto usuarioDto
    ) {
        return new ResponseEntity<>(
                this.userService.update(id, usuarioDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.userService.delete(id),
                HttpStatus.OK
        );
    }

}
