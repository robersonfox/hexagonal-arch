package br.dev.dreamdigital.usuarios.entrypoints;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.dreamdigital.usuarios.gateways.reponse.LoginResponse;
import br.dev.dreamdigital.usuarios.gateways.request.LoginRequest;
import br.dev.dreamdigital.usuarios.usecases.LoginUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/login")
public class LoginEntryPoint {

    private final LoginUseCase loginUseCase;

    @ResponseBody
    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest params) {
        log.info("LoginEntryPoint.get()");

        try {
            LoginResponse login = loginUseCase.execute(params);
            return ResponseEntity.ok(login);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
