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
    private final ChoiceRepository choiceRepository;

    // submit answer
    @RequestMapping(value = "/mcqtest/{mcqTestId}/question/{questionId}/answers", method = RequestMethod.POST)
    ResponseEntity<?> submitAnswer(@PathVariable Long mcqTestId, @PathVariable Long questionId,
                                   @RequestBody AnswerDto answerDto) {

        HttpHeaders httpHeaders = new HttpHeaders();

        // check if test is still valid
        McqTest mcqTest = mcqTestRepository.findOne(mcqTestId);

        if (!mcqTest.isValid())
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

        User user = getUser();

        if (!mcqTest.getUsers().contains(user))
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

        Question question = questionRepository.findOne(questionId);

        // check if this answer has already been submitted
        List<Answer> a = answerRepository.findByQuestionAndUser(question, user);
        if (!a.isEmpty())
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CONFLICT);

        Set<Choice> choices = new HashSet<>();
        for (Long choiceId : answerDto.choiceIds) {
            Choice c = choiceRepository.findOne(choiceId);
            if (c.getQuestion().getId() == question.getId())
                choices.add(c);
        }

        Answer answer = answerRepository.save(new Answer(choices, question, mcqTest, user));


        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(answer.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.ACCEPTED);
    }

    // get result
    @RequestMapping(value = "/mcqtest/{mcqTestId}/result", method = RequestMethod.GET)
    McqResult getResult(@PathVariable Long mcqTestId) {
        McqTest mcqTest = mcqTestRepository.findOne(mcqTestId);
        User user = getUser();
        List<McqResult> mcqResult = mcqResultRepository.findByMcqTestAndUser(mcqTest, user);

        // generate result if it has not been generated
        if (mcqResult.size() == 0)
            return GenerateResult(mcqTest, user);

        return mcqResult.get(0);
    }

    // get results of a specific test
    @RequestMapping(value = "/mcqtest/{mcqTestId}/results")
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

    // generate result
    private McqResult GenerateResult(McqTest mcqTest, User user) {
        // the total marks obtained by the student
        double marks = 0;

        // mark percentage per question
        int num_questions = mcqTest.getQuestions().size();
        double q_mark = (100 / num_questions);
        // mark percentage per choice for questions with multiple answers
        double c_mark = 0;

        // get all the answers
        Collection<Answer> answers = answerRepository.findByMcqTestAndUser(mcqTest, user);

        for (Answer answer : answers) {
            // get mark_per_choice for the question
            int num_answer_choice = 0;
            Question q = questionRepository.findOne(answer.getQuestion().getId());
            for (Choice choice : q.getChoices()) {
                if (choice.isAnswer())
                    num_answer_choice++;
            }
            c_mark = q_mark / num_answer_choice;

            // calculate mark
            for (Choice choice : answer.getChoices()) {
                if (choice.isAnswer())
                    marks += c_mark;
            }
        }

        // create and return the result
        McqResult mcqResult = mcqResultRepository.save(new McqResult(marks, user, mcqTest));
        return mcqResult;
    }

    @Autowired
    public ExamRestController(QuestionRepository questionRepository, McqTestRepository mcqTestRepository, UserRepository userRepository, AnswerRepository answerRepository, McqResultRepository mcqResultRepository, ChoiceRepository choiceRepository) {
        this.questionRepository = questionRepository;
        this.mcqTestRepository = mcqTestRepository;
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.mcqResultRepository = mcqResultRepository;
        this.choiceRepository = choiceRepository;
    }
}
