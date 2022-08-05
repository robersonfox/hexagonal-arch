package br.dev.dreamdigital.usuarios.gateways;

import org.springframework.stereotype.Component;

import br.dev.dreamdigital.usuarios.entities.Perfil;
import br.dev.dreamdigital.usuarios.gateways.repositories.PerfilRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PerfilImp implements PerfilGateway {

    private final PerfilRepository perfilRepository;

    @Override
    public Perfil findByTipo(String tipo) throws Exception {
        return perfilRepository.findByTipo(tipo)
                .orElseThrow(() -> new Exception("Perfil not found"));
    }
}
