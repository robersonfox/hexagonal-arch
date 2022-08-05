package br.dev.dreamdigital.usuarios.entrypoints;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.dreamdigital.usuarios.gateways.request.UserRequest;
import br.dev.dreamdigital.usuarios.usecases.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserEntryPoint {

    private final UserUseCase userUseCase;

    @ResponseBody
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserRequest params) {
        log.info("UserEntryPoint.createUser()");

        try {
            userUseCase.create(params);

            return ResponseEntity.ok("User created");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Usuário já existe ou erro ao criar usuário");
        }
    }
}
