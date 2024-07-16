package med.voll.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService  {




    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(Long id) throws ResourceNotFoundException {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + id));
    }

    public Topic createTopic(Topic topic) throws ResourceAlreadyExistsException {
        Optional<Topic> existingTopic = topicRepository.findByTitleAndMessage(topic.getTitle(), topic.getMessage());
        if (existingTopic.isPresent()) {
           throw new ResourceAlreadyExistsException("Topic with same title and message already exists");
        }
        topic.setCreationDate(LocalDateTime.now());
        return topicRepository.save(topic);
    }

    public Topic updateTopic(Long id, Topic topicDetails) throws ResourceNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + id));


        topic.setTitle(topicDetails.getTitle());
        topic.setMessage(topicDetails.getMessage());
        topic.setStatus(topicDetails.getStatus());
        topic.setAuthor(topicDetails.getAuthor());
        topic.setCourse(topicDetails.getCourse());

        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) throws ResourceNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + id));
        topicRepository.delete(topic);
    }

    class ResourceAlreadyExistsException extends Exception {
        public ResourceAlreadyExistsException(String s) {

        }
    }
}
