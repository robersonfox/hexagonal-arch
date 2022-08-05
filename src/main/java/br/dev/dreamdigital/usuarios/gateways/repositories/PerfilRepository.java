package br.dev.dreamdigital.usuarios.gateways.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.dreamdigital.usuarios.entities.Perfil;

@Repository
public interface PerfilRepository extends CrudRepository<Perfil, Long> {
    Optional<Perfil> findByTipo(String tipo);
}
