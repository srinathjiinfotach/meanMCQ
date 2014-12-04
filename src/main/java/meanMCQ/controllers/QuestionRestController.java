package meanMCQ.controllers;

import meanMCQ.domain.Question;
import meanMCQ.service.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by red on 11/25/14.
 */
@RestController
@RequestMapping("/questions")
class QuestionRestController {
    private final QuestionRepository questionRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody Question question) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Question q = new Question(question.content);
        q.setChoices(question.choices);
        q.choices.forEach(c -> c.setQuestion(q));
        questionRepository.save(q);

        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(q.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Question getQuestion(@PathVariable Long id) {
        return questionRepository.findOne(id);
    }


    @Autowired
    public QuestionRestController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}
