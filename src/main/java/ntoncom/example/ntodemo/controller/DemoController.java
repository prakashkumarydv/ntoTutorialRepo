package ntoncom.example.ntodemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ntoncom.example.ntodemo.entity.model.Tutorial;
import ntoncom.example.ntodemo.model.Request;
import ntoncom.example.ntodemo.model.Response;
import ntoncom.example.ntodemo.repository.TutorialRepository;

@RestController
@RequestMapping("/api")
public class DemoController {
	@Autowired
	TutorialRepository tutorialRepository;

	@Autowired
	ObjectMapping objectMapping;

	@GetMapping("/tutorial")
	public String get() {
		return "hello world";
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Response> createTutorial(@RequestBody Request request) {
		try {
			Tutorial tutorial = objectMapping.requestMappingToEntityModel(request);
			Tutorial _tutorial = tutorialRepository.save(tutorial);
			Response response = objectMapping.entityModelMappingToResponse(_tutorial);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials")
	public ResponseEntity<Response> updateTutorial(@RequestBody Request request) {
		try {
			Tutorial tutorial = objectMapping.requestMappingToEntityModel(request);
			Tutorial _tutorial = tutorialRepository.save(tutorial);
			Response response = objectMapping.entityModelMappingToResponse(_tutorial);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}