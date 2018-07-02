package io.pivotal.workshop.webpolls;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PollController {

  private final List<Poll> polls = new ArrayList<>();

  public PollController() {
    polls.add(new Poll(2, 4));
    polls.add(new Poll(5, 6));
    polls.add(new Poll(2, 7));
  }

  @GetMapping("/")
  public String index() {
    return "Welcome to Spring Cloud Data Flow - Spring Boot Primer Lab";
  }

  @GetMapping("/polls")
  public Iterable<Poll> getPolls() {
    return polls;
  }

  @PostMapping("/polls")
  public ResponseEntity<Poll> savePolls(@RequestBody Poll poll) {
    polls.add(poll);

    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .buildAndExpand()
        .toUri();

    return ResponseEntity.created(location).body(poll);
  }
}
