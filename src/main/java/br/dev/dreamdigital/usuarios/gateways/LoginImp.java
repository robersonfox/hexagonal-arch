package br.dev.dreamdigital.usuarios.gateways;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.dev.dreamdigital.usuarios.entities.Login;
import br.dev.dreamdigital.usuarios.gateways.repositories.LoginRepository;
import br.dev.dreamdigital.usuarios.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginImp implements LoginGateway {

    private final LoginRepository loginRepository;

    @Override
    public void save(Login login) {
        log.info("Saving login: {}", login.getUsername());

        try {
            loginRepository.save(login);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        loginRepository.findById(id)
                .ifPresent(a -> {
                    log.info("Deleting login: {}", a.getUsername());
                    loginRepository.delete(a);
                });
    }

    @Override
    public Login findByUsernameAndPassword(String username, String password) throws Exception {

        Optional<Login> opLogin = loginRepository.findByUsernameAndPassword(
                username, StringUtils.toSha256(password));

        if (opLogin.isPresent()) {
            return opLogin.get();
        } else {
            throw new Exception("Login not found");
        }
    }

    @Override
    public String getToken(String id) {
        return StringUtils.toSha256(id);
    }
}
