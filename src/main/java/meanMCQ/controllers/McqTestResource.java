package meanMCQ.controllers;

import meanMCQ.domain.McqTest;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by red on 12/3/14.
 */
class McqTestResource extends ResourceSupport {
    private final McqTest mcqTest;


    public McqTestResource(McqTest mcqTest) {
        this.mcqTest = mcqTest;
        this.add(linkTo(McqTestRestController.class, getId()).withRel("mcqtests"));
    }
}
