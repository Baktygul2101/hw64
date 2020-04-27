package kg.attractor.votemachine.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    public void deleteAllBy(String id, String email);
}