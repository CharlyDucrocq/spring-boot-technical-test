package com.supralog.recruitment.springexamusercrud.users;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import com.supralog.recruitment.springexamusercrud.users.models.User;

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

    @PostMapping()
    User createUser(@RequestBody @Valid User usr ){
        return this.userService.create(usr);
    }
}
