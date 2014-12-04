package meanMCQ.controllers;

import meanMCQ.domain.McqTest;
import meanMCQ.service.McqTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

/**
 * Created by red on 12/3/14.
 */
@RestController
@RequestMapping("/mcqtests/")
class McqTestRestController {
    private final McqTestRepository mcqTestRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> create(@RequestBody McqTest mcqTest) {
        HttpHeaders httpHeaders = new HttpHeaders();

        McqTest test = new McqTest(mcqTest.schedule, mcqTest.duration, mcqTest.questions);
        mcqTestRepository.save(test);

        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(test.getId()).toUri());

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<McqTest> getTests() {
        return mcqTestRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    McqTest getTest(@PathVariable Long id) {
        return mcqTestRepository.findOne(id);
    }

    @Autowired
    McqTestRestController(McqTestRepository mcqTestRepository) {
        this.mcqTestRepository = mcqTestRepository;
    }


}
