package meanMCQ.controllers;

import meanMCQ.domain.User;
import meanMCQ.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by red on 12/6/14.
 */
@RestController
class UserRestController {
    private final UserRepository userRepository;

    // create user
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody User user) {
        HttpHeaders httpHeaders = new HttpHeaders();

        // create random password and send it to user's email
        user.setPassword("abc123");
        User u = userRepository.save(user);

        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(u.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    // get users
    @RequestMapping(method = RequestMethod.GET)
    Collection<User> getUsers(){
        return userRepository.findAll();
    }

    // get specific user
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
