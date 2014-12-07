package meanMCQ.controllers;

import meanMCQ.domain.*;
import meanMCQ.dto.AnswerDto;
import meanMCQ.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

/**
 * Created by red on 12/6/14.
 */
@RestController
@RequestMapping("/exam")
class ExamRestController {
    private final QuestionRepository questionRepository;
    private final McqTestRepository mcqTestRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final McqResultRepository mcqResultRepository;

    // submit answer
    @RequestMapping(value = "/mcqtest/{mcqTestId}/question/{questionId}/answers", method = RequestMethod.POST)
    ResponseEntity<?> submitAnswer(@PathVariable Long mcqTestId, @PathVariable Long questionId,
                                   @RequestBody AnswerDto answerDto) {

        HttpHeaders httpHeaders = new HttpHeaders();
        McqTest mcqTest = mcqTestRepository.findOne(mcqTestId);

        if (!mcqTest.isValid())
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

        Question question = questionRepository.findOne(questionId);

        Set<Choice> choices = new HashSet<>();
        answerDto.choiceIds.forEach(c ->
                        question.choices.forEach(c1 -> {
                            if (c == c1.getId())
                                choices.add(c1);
                        })
        );
        User student = getUser();

        Answer answer = answerRepository.save(new Answer(choices, question, mcqTest, student));


        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(answer.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

    // get result : /exam/test/{mcqTestId}/result
    // if result has not been generated yet, generate result and then show
    @RequestMapping(value = "/mcqtest/{mcqTestId}/result", method = RequestMethod.GET)
    McqResult getResult(@PathVariable Long mcqTestId) {
        return mcqResultRepository.findByMcqTestAndUser(mcqTestRepository.findOne(mcqTestId), getUser()).get(0);
    }

    // get results of a specific test
    Collection<McqResult> getResults(@PathVariable Long mcqTestId) {
        return mcqResultRepository.findByMcqTest(mcqTestRepository.findOne(mcqTestId));
    }

    // get authenticated user
    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<User> student = new ArrayList<>();
        userRepository.findByUsername(username).map(s -> student.add(s));
        return student.get(0);
    }

    @Autowired
    public ExamRestController(QuestionRepository questionRepository, McqTestRepository mcqTestRepository, UserRepository userRepository, AnswerRepository answerRepository, McqResultRepository mcqResultRepository) {
        this.questionRepository = questionRepository;
        this.mcqTestRepository = mcqTestRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.mcqResultRepository = mcqResultRepository;
    }
}
