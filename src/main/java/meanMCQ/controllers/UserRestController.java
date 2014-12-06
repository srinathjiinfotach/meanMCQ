package meanMCQ.controllers;

import meanMCQ.domain.User;
import meanMCQ.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by red on 12/6/14.
 */
@RestController
@RequestMapping(value = "/users")
class UserRestController {
    private final UserRepository userRepository;

    // create user
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody User user){
        userRepository.save(new User(user.getUsername(), user.getPassword(), user.getRole()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    // get users
    @RequestMapping(method = RequestMethod.GET)
    Collection<User> getUsers(){
        return userRepository.findAll();
    }

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
