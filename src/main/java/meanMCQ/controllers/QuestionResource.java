package meanMCQ.controllers;

import meanMCQ.domain.McqQuestion;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by red on 11/29/14.
 */
class QuestionResource extends ResourceSupport {
    private final McqQuestion mcqQuestion;

    public QuestionResource(McqQuestion mcqQuestion) {
        this.mcqQuestion = mcqQuestion;
        this.add(linkTo(QuestionRestController.class, getId()).withRel("questions"));
        this.add(linkTo(methodOn(QuestionRestController.class, mcqQuestion.getId()).
                getQuestion(mcqQuestion.getId())).withSelfRel());

    }

    public McqQuestion getMcqQuestion() {
        return mcqQuestion;
    }
}
