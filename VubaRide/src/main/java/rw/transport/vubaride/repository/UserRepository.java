package rw.transport.vubaride.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import rw.transport.vubaride.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);
    Optional<User>findByEmail(String email);

}
