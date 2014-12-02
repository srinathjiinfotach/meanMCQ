package meanMCQ.controllers;

import meanMCQ.domain.Question;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by red on 11/29/14.
 */
class QuestionResource extends ResourceSupport {
    private final Question question;

    public QuestionResource(Question question) {
        this.question = question;
        this.add(linkTo(QuestionRestController.class, getId()).withRel("questions"));
        this.add(linkTo(methodOn(QuestionRestController.class, question.getId()).
                getQuestion(question.getId())).withSelfRel());

    }

    public Question getQuestion() {
        return question;
    }
}
