package com.asj.bootcamp.controller;

import com.asj.bootcamp.dto.UserCompletoDTO;
import com.asj.bootcamp.dto.UserLoginDTO;
import com.asj.bootcamp.entity.User;
import com.asj.bootcamp.mapper.UserMapper;
import com.asj.bootcamp.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/users")
@Api(value = "Allowed actios for the User Entity", tags = "User Controller")
public class UserController {

        private final UserService service;
        private final UserMapper userMapper;

        public UserController(UserService service, UserMapper userMapper) {
                this.service = service;
                this.userMapper = userMapper;
        }

        @PostMapping
        public ResponseEntity<?> createUser(@RequestBody UserCompletoDTO userCompletoDTO){
                try{
                        User user = userMapper.userCompletoDTOToUserEntity(userCompletoDTO);

                        User updated = service.createUser(user);
                        UserCompletoDTO tmp = userMapper.userEntityToUserCompletoDTO(updated);

                        return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
                }
                catch (Exception ex){
                        return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario con mail " + userCompletoDTO.getMail() + " ya registrado");
                }

        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getUser(@PathVariable Integer id){
                try {
                        User user =  service.getUser(id);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userMapper.userEntityToUserCompletoDTO(user));
                }
                catch (Exception ex){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
                }
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserCompletoDTO userCompletoDTO){
                try {
                        User userTmp = userMapper.userCompletoDTOToUserEntity(userCompletoDTO);
                        User updated = service.updateUser(id, userTmp);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
                }
                catch (RuntimeException ex){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
                }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Integer id){
                try {
                        service.deleteUser(id);
                        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
                }
                catch (RuntimeException ex){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
                }
        }

        @PostMapping("/login")
        public ResponseEntity<?> validateUser(@RequestBody UserLoginDTO userLoginDTO){
                try {
                        User userValidated = service.validateUser(userMapper.userLoginDTOToUserEntity(userLoginDTO));
                        return ResponseEntity.ok(userValidated.getIdUser());
                }
                catch (RuntimeException ex){
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Datos mal ingresados");
                }
        }
}