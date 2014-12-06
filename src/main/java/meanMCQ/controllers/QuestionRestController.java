package meanMCQ.controllers;

import meanMCQ.domain.Choice;
import meanMCQ.domain.Question;
import meanMCQ.service.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by red on 11/25/14.
 */
@RestController
@RequestMapping("/questions")
class QuestionRestController {
    private final QuestionRepository questionRepository;

    // create a question
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

    // get all questions
    @RequestMapping(method = RequestMethod.GET)
    Collection<Question> getQuestions() {
        return questionRepository.findAll();
    }

    // get a specific question
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Question getQuestion(@PathVariable Long id) {
        return questionRepository.findOne(id);
    }

    // get answers of a specific question
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/answers")
    Collection<Choice> getAnswers(@PathVariable Long id) {
        Collection<Choice> choices = new ArrayList<>();
        questionRepository.findOne(id).getChoices().forEach(c -> {
            if (c.isAnswer())
                choices.add(c);
        });

        return choices;
    }

    @Autowired
    public QuestionRestController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}
