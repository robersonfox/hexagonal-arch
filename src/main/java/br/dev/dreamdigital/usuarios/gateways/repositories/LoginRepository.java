package br.dev.dreamdigital.usuarios.gateways.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.dreamdigital.usuarios.entities.Login;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
    Optional<Login> findByUsernameAndPassword(String username, String password);
}
