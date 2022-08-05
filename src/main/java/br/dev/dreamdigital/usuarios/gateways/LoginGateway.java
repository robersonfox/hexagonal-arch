package br.dev.dreamdigital.usuarios.gateways;

import br.dev.dreamdigital.usuarios.entities.Login;

public interface LoginGateway {
    void save(Login login) throws Exception;

    void delete(Long id) throws Exception;

    Login findByUsernameAndPassword(String username, String password) throws Exception;

    String getToken(String id);
}
