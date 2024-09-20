package com.example.inicial1.controllers;


import com.example.inicial1.entities.Localidad;
import com.example.inicial1.services.LocalidadServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/localidad")
public class LocalidadController {
    //  @Autowired


    private LocalidadServices localidadServices;

    public LocalidadController(LocalidadServices localidadServices){
        this.localidadServices = localidadServices;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).
                    body(localidadServices.findAll());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body("Busqué esta persona por Id:" + localidadServices.findById(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Localidad entity){

        System.out.println("Estos datos los tomo del cuerpo del Formulario");
        System.out.println("Deniminación :" + entity.getDenominacion());


        try{
            return ResponseEntity.status(HttpStatus.OK).body("Grabé los datos anteriores " + localidadServices.save(entity));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Localidad entity){

        try{
            return ResponseEntity.status(HttpStatus.OK).body("Actualicé los datos anteriores" + localidadServices.update(id,entity));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminé el registro " + localidadServices.delete(id));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }
    }
}