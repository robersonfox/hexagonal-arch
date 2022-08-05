package br.dev.dreamdigital.usuarios.usecases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.dev.dreamdigital.usuarios.entities.Login;
import br.dev.dreamdigital.usuarios.gateways.LoginGateway;
import br.dev.dreamdigital.usuarios.gateways.request.LoginRequest;
import br.dev.dreamdigital.usuarios.gateways.response.LoginResponse;
import br.dev.dreamdigital.usuarios.gateways.response.PerfilResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginUseCase {
    private final LoginGateway loginGateway;

    public LoginResponse execute(LoginRequest params) throws Exception {
        log.info("LoginUseCase.getById()");

        try {
            String userName = params.getUsername();
            String password = params.getPassword();

            Login login = loginGateway.findByUsernameAndPassword(userName, password);
            String token = loginGateway.getToken(login.getUsername());

            Long userId = login.getUser().getId();
            String loginName = login.getUsername();

            List<PerfilResponse> perfils = new ArrayList<>();
            login.getUser().getPerfil().forEach(p -> {
                PerfilResponse perfil = new PerfilResponse();
                perfil.setTipo(p.getTipo());
                perfils.add(perfil);
            });

            return LoginResponse.builder()
                    .userId(userId)
                    .login(loginName)
                    .token(token)
                    .perfil(perfils)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());

            throw new Exception("Login not found");
        }
    }
}
