package com.supralog.recruitment.springexamusercrud.users;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    List<User> getAll(){
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    Optional<User> findById(Long id){
        return repository.findById(id);
    }

    User create(User toCreate){
        return repository.save(toCreate);
    }
}
