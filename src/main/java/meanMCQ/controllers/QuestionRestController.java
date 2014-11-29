package meanMCQ.controllers;

import meanMCQ.domain.Choice;
import meanMCQ.domain.Question;
import meanMCQ.service.ChoiceRepository;
import meanMCQ.service.QuestionRepository;

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
import java.util.Set;

/**
 * Created by red on 11/25/14.
 */
@RestController
@RequestMapping("/questions")
class QuestionRestController {
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody Question input) {
        Question q = new Question(input.getContent());
        q = questionRepository.save(q);
        final Question finalQ = q;
        input.getChoices().forEach(c -> {
            c.setQuestion(finalQ);
        });
        choiceRepository.save(input.getChoices());


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(q.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Question> getQuestions(){
        return this.questionRepository.findAll();
    }


    @Autowired
    public QuestionRestController(QuestionRepository questionRepository, ChoiceRepository choiceRepository) {
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
    }
}