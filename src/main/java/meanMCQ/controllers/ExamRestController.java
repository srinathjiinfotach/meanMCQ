package meanMCQ.controllers;

import meanMCQ.domain.*;
import meanMCQ.dto.AnswerDto;
import meanMCQ.service.AnswerRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by red on 12/6/14.
 */
@RestController
@RequestMapping("/exam")
class ExamRestController {

    // TODO: get result : /exam/test/{mcqTestId}/result
    // TODO: get results: /exam/test/{mcqTestId}/results
    private final QuestionRepository questionRepository;
    private final McqTestRepository mcqTestRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    // submit answer : /exam/test/{mcqTestId}/question/{questionId}/answers
    // check test validity
    //
    @RequestMapping(value = "/mcqtest/{mcqTestId}/question/{questionId}/answers", method = RequestMethod.POST)
    ResponseEntity<?> submitAnswer(@PathVariable Long mcqTestId, @PathVariable Long questionId,
                                   @RequestBody AnswerDto answerDto) {

        HttpHeaders httpHeaders = new HttpHeaders();
        McqTest mcqTest = mcqTestRepository.findOne(mcqTestId);

        if (!mcqTest.isValid())
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Question question = questionRepository.findOne(questionId);

        Set<Choice> choices = new HashSet<>();
        answerDto.choiceIds.forEach(c ->
                        question.choices.forEach(c1 -> {
                            if (c == c1.getId())
                                choices.add(c1);
                        })
        );

        List<User> student = new ArrayList<>();
        userRepository.findByUsername(username).map(s -> student.add(s));
        Answer answer = answerRepository.save(new Answer(choices, question, mcqTest, student.get(0)));


        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(answer.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

    @Autowired
    public ExamRestController(QuestionRepository questionRepository, McqTestRepository mcqTestRepository, UserRepository userRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.mcqTestRepository = mcqTestRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
    }
}
