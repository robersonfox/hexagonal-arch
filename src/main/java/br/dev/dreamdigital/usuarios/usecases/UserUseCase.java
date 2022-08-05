package br.dev.dreamdigital.usuarios.usecases;

import org.springframework.stereotype.Service;

import br.dev.dreamdigital.usuarios.gateways.UserGateway;
import br.dev.dreamdigital.usuarios.gateways.request.UserRequest;
import br.dev.dreamdigital.usuarios.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway user;

    public void create(UserRequest request) throws Exception {
        log.info("UserUseCase.create()");

        request.getLogin()
                .setPassword(
                        StringUtils.toSha256(request.getLogin().getPassword()));

        try {
            user.save(request);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
