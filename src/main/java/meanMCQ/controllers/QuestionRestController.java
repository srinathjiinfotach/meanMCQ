package meanMCQ.controllers;

import meanMCQ.domain.McqQuestion;
import meanMCQ.service.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by red on 11/25/14.
 */
@RestController
@RequestMapping("/questions")
class QuestionRestController {
    private final QuestionRepository questionRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody McqQuestion mcqQuestion) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (mcqQuestion.content == null) {
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.BAD_REQUEST);
        }
        McqQuestion q = new McqQuestion(mcqQuestion.content);
        q.setMcqChoices(mcqQuestion.mcqChoices);
        q.mcqChoices.forEach(c -> c.setMcqQuestion(q));
        questionRepository.save(q);

        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(q.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    Resources<QuestionResource> getQuestions() {
        List<QuestionResource> questionResourceList = questionRepository.findAll()
                .stream()
                .map(QuestionResource::new)
                .collect(Collectors.toList());
        return new Resources<QuestionResource>(questionResourceList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    QuestionResource getQuestion(@PathVariable Long id) {
        return new QuestionResource(this.questionRepository.findOne(id));
    }


    @Autowired
    public QuestionRestController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
}
