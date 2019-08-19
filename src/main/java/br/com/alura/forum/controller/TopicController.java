package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;

@RestController
public class TopicController {
	@GetMapping(value="/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TopicBriefOutputDto> listaTopicos() {
		User owner = new User("Ana", "a@b.c", "1234");
		Category category = new Category("Programação");
		Category subCategory = new Category("Java", category);
		Course course = new Course("Aprendendo Spring MVC", subCategory);
		Topic topic = new Topic("como estudar spring?",
				"bom dia, não estou conseguindo estudar", owner, course);
		
		List<Topic> topics = Arrays.asList(topic, topic, topic);
		
		return TopicBriefOutputDto.listFromTopics(topics);
	}
}
