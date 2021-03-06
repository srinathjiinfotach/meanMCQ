package meanMCQ.controllers;

import meanMCQ.domain.McqTest;
import meanMCQ.domain.Question;
import meanMCQ.domain.User;
import meanMCQ.service.McqTestRepository;
import meanMCQ.service.QuestionRepository;
import meanMCQ.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by red on 12/3/14.
 */
@RestController
@RequestMapping("/mcqtests")
class McqTestRestController {
    private final McqTestRepository mcqTestRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    // get all tests  { examiner }
    @RequestMapping(method = RequestMethod.GET)
    Collection<McqTest> getTests() {
        return mcqTestRepository.findAll();
    }

    // create a test { examiner }
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody McqTest mcqTest) {
        Collection<Question> questions = new ArrayList<>();
        Collection<User> users = new ArrayList<>();
        mcqTest.questions.forEach(q -> questions.add(questionRepository.findOne(q.getId())));
        mcqTest.users.forEach(u -> users.add(userRepository.findOne(u.getId())));
        McqTest test = new McqTest(mcqTest.title, mcqTest.schedule, mcqTest.duration);
        test.setQuestions(questions);
        test.setUsers(users);
        mcqTestRepository.save(test);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(test.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    // add questions to the test { examiner }
    @RequestMapping(value = "/{id}/questions", method = RequestMethod.POST)
    ResponseEntity<?> addQuestions(@PathVariable Long id, @RequestBody Collection<Question> questions) {
        HttpHeaders httpHeaders = new HttpHeaders();
        McqTest mcqTest = mcqTestRepository.findOne(id);

        if (mcqTest == null)
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

        questions.forEach(q -> mcqTest.questions.add(questionRepository.findOne(q.getId())));
        mcqTestRepository.save(mcqTest);

        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(mcqTest.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

    // get all users enrolled { examiner }
    @RequestMapping(value = "/{id}/users", method = RequestMethod.GET)
    Collection<User> getStudents(@PathVariable Long id) {
        return mcqTestRepository.findOne(id).users;
    }

    // enroll users to a specific test { examiner }
    @RequestMapping(value = "/{id}/users", method = RequestMethod.POST)
    ResponseEntity<?> addStudent(@PathVariable Long id, @RequestBody Collection<User> users) {
        HttpHeaders httpHeaders = new HttpHeaders();

        McqTest mcqTest = mcqTestRepository.findOne(id);
        if (mcqTest == null)
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
        for (User user : users) {
            userRepository.findByUsername(user.getUsername()).map(s -> mcqTest.users.add(s));
            mcqTestRepository.save(mcqTest);
        }
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

    // get a specific test { examiner }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    McqTest getTest(@PathVariable Long id) {
        return mcqTestRepository.findOne(id);
    }

    // get authenticated user
    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<User> users = new ArrayList<>();
        userRepository.findByUsername(username).map(s -> users.add(s));
        return users.get(0);
    }

    @Autowired
    McqTestRestController(McqTestRepository mcqTestRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.mcqTestRepository = mcqTestRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

}
