package br.dev.dreamdigital.usuarios.gateways;

import br.dev.dreamdigital.usuarios.entities.Perfil;

public interface PerfilGateway {
    Perfil findByTipo(String tipo) throws Exception;
}
