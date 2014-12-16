package meanMCQ.controllers;

import meanMCQ.domain.Choice;
import meanMCQ.domain.Question;
import meanMCQ.service.ChoiceRepository;
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
    private final ChoiceRepository choiceRepository;

    // create a question
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody Question question) {
        HttpHeaders httpHeaders = new HttpHeaders();

        Question q = new Question(question.content);
        question.getChoices().forEach(c-> q.choices.add(c));
        q.choices.forEach(c -> c.setQuestion(q));
        questionRepository.save(q);

        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(q.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    // delete a question
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id){
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
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
        Question question = getQuestion(id);
        Collection<Choice> choices = choiceRepository.findByQuestionAndAnswer(question, true);
        return choices;
    }

    @Autowired
    public QuestionRestController(QuestionRepository questionRepository, ChoiceRepository choiceRepository) {
        this.questionRepository = questionRepository;
        this.choiceRepository = choiceRepository;
    }
}
