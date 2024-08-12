package com.ineel.ifm.controller.rol;

import com.ineel.ifm.config.CustomResponse;
import com.ineel.ifm.model.rol.Rol;
import com.ineel.ifm.service.rol.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rol/")
@CrossOrigin(origins = {"*"})
public class RolController {

    @Autowired
    private RolService rolService;

    public void createRolIfNotExist(){
        this.rolService.createRoles();
    }

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Rol>>> getAll(){
        return new ResponseEntity<>(
                this.rolService.getAllRoles(),
                HttpStatus.OK
        );
    }
}