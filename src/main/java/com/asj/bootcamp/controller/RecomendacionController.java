package com.asj.bootcamp.controller;

import com.asj.bootcamp.dto.RecomendacionDTO;
import com.asj.bootcamp.entity.Recomendacion;
import com.asj.bootcamp.mapper.RecomendacionMapper;
import com.asj.bootcamp.service.RecomendacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    private final RecomendacionService service;
    private final RecomendacionMapper mapper;

    public RecomendacionController(RecomendacionService service, RecomendacionMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> createRecomedacion(@RequestBody RecomendacionDTO recomendacionDTO){
        Recomendacion recomendacion = mapper.recomendacionDTOToRecomendacionEntity(recomendacionDTO);
        service.createRecomendacion(recomendacion);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecomendacion(@PathVariable Integer id){
        try {
            Recomendacion recomendacion =  service.getRecomendacion(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.recomendacionEntityToRecomendacionDTO(recomendacion));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendacion no encontrada");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecomendacion(@PathVariable Integer id, @RequestBody RecomendacionDTO recomendacionDTO){
        try {
            Recomendacion tmp = mapper.recomendacionDTOToRecomendacionEntity(recomendacionDTO);
            RecomendacionDTO updated = mapper.recomendacionEntityToRecomendacionDTO(service.updateRecomendacion(id, tmp));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendacion no encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecomendacion(@PathVariable Integer id){
        try {
            service.deleteRecomendacion(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendacion no encontrada");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllRecomendaciones(){
        List<Recomendacion> recomendaciones = service.getAllRecomendaciones();
        List<RecomendacionDTO> recomendacionesDTO = new ArrayList<>();
        for (Recomendacion recomendacion : recomendaciones){
            recomendacionesDTO.add(mapper.recomendacionEntityToRecomendacionDTO(recomendacion));
        }

        return ResponseEntity.status(HttpStatus.OK).body(recomendacionesDTO);
    }

}