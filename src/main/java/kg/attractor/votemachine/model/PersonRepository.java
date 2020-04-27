package kg.attractor.votemachine.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {
    public Optional<Person> findByLogin(String s);
}