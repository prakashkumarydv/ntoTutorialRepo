package ntoncom.example.ntodemo.controller;

import org.springframework.stereotype.Component;

import ntoncom.example.ntodemo.entity.model.Tutorial;
import ntoncom.example.ntodemo.model.Request;
import ntoncom.example.ntodemo.model.Response;

@Component
public class ObjectMapping {

	public Tutorial requestMappingToEntityModel(Request request) {
		Tutorial tutorial = new Tutorial();
		tutorial.setDescription(request.getDescription());
		tutorial.setTitle(request.getTitle());
		tutorial.setPublished(request.getPublished());
		return tutorial;
	}

	public Response entityModelMappingToResponse(Tutorial tutorial) {

		Response response = new Response();
		response.setDescription(tutorial.getDescription());
		response.setTitle(tutorial.getTitle());
		response.setPublished(tutorial.getPublished());
		response.setId(tutorial.getId());
		return response;
	}

}
