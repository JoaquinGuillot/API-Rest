package com.example.inicial1.controllers;

import com.example.inicial1.entities.Domicilio;
import com.example.inicial1.services.DomicilioServices;
import lombok.Builder;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/domicilio")
public class AutorController {
    //  @Autowired


    private DomicilioServices domicilioServices;

    public AutorController(DomicilioServices domicilioServices){
        this.domicilioServices = domicilioServices;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).
                    body(domicilioServices.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body("Busqué este domicilio por Id:" + domicilioServices.findById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Domicilio entity){

        System.out.println("Estos datos los tomo del cuerpo del Formulario");
        System.out.println("Calle :" + entity.getCalle());
        System.out.println("Numero :" + entity.getNumero());

        try{
            return ResponseEntity.status(HttpStatus.OK).body("Grabé los datos anteriores " + domicilioServices.save(entity));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Domicilio entity){
        System.out.println("EL ID LO TOMO DE LA URL");
        System.out.println("Nombre :" + entity.getId());
        System.out.println("Estos datos los tomo del cuerpo del Formulario");
        System.out.println("Calle :" + entity.getCalle());
        System.out.println("Numero :" + entity.getNumero());
        try{
            return ResponseEntity.status(HttpStatus.OK).body("Actualicé los datos anteriores" + domicilioServices.update(id,entity));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminé el registro " + domicilioServices.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}