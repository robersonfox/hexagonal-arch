package br.dev.dreamdigital.usuarios.gateways.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.dev.dreamdigital.usuarios.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
