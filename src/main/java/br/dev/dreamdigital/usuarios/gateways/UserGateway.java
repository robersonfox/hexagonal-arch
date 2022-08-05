package br.dev.dreamdigital.usuarios.gateways;

import br.dev.dreamdigital.usuarios.gateways.request.UserRequest;
import br.dev.dreamdigital.usuarios.gateways.response.UserResponse;

public interface UserGateway {
    void save(UserRequest user) throws Exception;

    void delete(Long id) throws Exception;

    UserResponse findById(Long id) throws Exception;

    UserResponse findByUsername(String username) throws Exception;
}
