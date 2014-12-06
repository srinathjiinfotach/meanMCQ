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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by red on 12/3/14.
 */
@RestController
@RequestMapping("/mcqtests")
class McqTestRestController {
    private final McqTestRepository mcqTestRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody McqTest mcqTest) {
        Set<Question> questionSet = new HashSet<>();
        mcqTest.questions.forEach(q -> questionSet.add(questionRepository.findOne(q.getId())));
        McqTest test = new McqTest(mcqTest.schedule, mcqTest.duration);
        test.setQuestions(questionSet);
        mcqTestRepository.save(test);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(test.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<McqTest> getTests() {
        return mcqTestRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    McqTest getTest(@PathVariable Long id) {
        return mcqTestRepository.findOne(id);
    }

    @RequestMapping(value = "/{id}/students", method = RequestMethod.GET)
    Collection<User> getStudents(@PathVariable Long id) {
        return mcqTestRepository.findOne(id).students;
    }

    @RequestMapping(value = "/{id}/students", method = RequestMethod.POST)
    ResponseEntity<?> addStudent(@PathVariable Long id, @RequestBody User student) {
        HttpHeaders httpHeaders = new HttpHeaders();

        McqTest mcqTest = mcqTestRepository.findOne(id);
        if (mcqTest == null)
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

        userRepository.findByUsername(student.username).map(s -> mcqTest.students.add(s));
        mcqTestRepository.save(mcqTest);

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

    @Autowired
    McqTestRestController(McqTestRepository mcqTestRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.mcqTestRepository = mcqTestRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }


}
