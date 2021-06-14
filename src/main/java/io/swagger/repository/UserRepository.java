package io.swagger.repository;

import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> getById(Long value);

    User getUserById(Long value);

    User findByUsername(String value);

    User findByLastName(String value);

}
