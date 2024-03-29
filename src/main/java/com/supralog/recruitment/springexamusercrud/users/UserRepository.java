package com.supralog.recruitment.springexamusercrud.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.supralog.recruitment.springexamusercrud.users.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
