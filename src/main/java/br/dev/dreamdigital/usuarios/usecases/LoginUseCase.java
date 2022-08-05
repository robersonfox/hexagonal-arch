package br.dev.dreamdigital.usuarios.usecases;

import org.springframework.stereotype.Service;

import br.dev.dreamdigital.usuarios.entities.Login;
import br.dev.dreamdigital.usuarios.gateways.ILogin;
import br.dev.dreamdigital.usuarios.gateways.reponse.LoginResponse;
import br.dev.dreamdigital.usuarios.gateways.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginUseCase {
    private final ILogin loginGateway;

    public LoginResponse execute(LoginRequest params) throws Exception {
        log.info("LoginUseCase.getById()");

        try {
            String userName = params.getUsername();
            String password = params.getPassword();

            Login login = loginGateway.findByUsernameAndPassword(userName, password);
            String token = loginGateway.getToken(login.getUsername());

            Long userId = login.getUser().getId();
            String loginName = login.getUsername();

            return LoginResponse.builder()
                    .userId(userId)
                    .login(loginName)
                    .token(token)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());

            throw new Exception("Login not found");
        }
    }
}
