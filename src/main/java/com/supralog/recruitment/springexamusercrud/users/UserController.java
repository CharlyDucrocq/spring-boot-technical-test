package com.supralog.recruitment.springexamusercrud.users;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("users")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    List<User> getUsers(){
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    Optional<User> getUserById(@PathVariable("id") Long id ){
        return this.userService.findById(id);
    }

    @PostMapping()@Valid
    User createUser(@RequestBody @Valid User usr ){
        usr.validate();
        return this.userService.create(usr);
    }
}
