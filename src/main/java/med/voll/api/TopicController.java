package med.voll.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {




   @Autowired
    private TopicService topicService;



    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }



    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable Long id) throws ResourceNotFoundException {
        return topicService.getTopicById(id);
    }

    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) throws ResourceAlreadyExistsException, TopicService.ResourceAlreadyExistsException {
        Topic createdTopic = topicService.createTopic(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
    }

    @PutMapping("/{id}")
    public Topic updateTopic(@PathVariable Long id, @RequestBody Topic topic) throws ResourceNotFoundException {
        return topicService.updateTopic(id, topic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) throws ResourceNotFoundException {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
