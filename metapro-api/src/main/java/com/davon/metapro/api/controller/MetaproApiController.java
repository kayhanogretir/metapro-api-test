package com.davon.metapro.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.davon.metapro.api.dao.MessageDao;
import com.davon.metapro.api.domain.Message;
import com.davon.metapro.api.dto.MessageDTO;

@Controller
public class MetaproApiController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MetaproApiController.class);

	@Autowired
	private MessageDao dao;

	@RequestMapping(value = "/message/getAll", method = RequestMethod.GET)
	@ResponseBody
	public List<Message> findAll() {
		return dao.findAll();
	}

	@RequestMapping(value = "/message/add", method = RequestMethod.POST)
	@ResponseBody
	public Message add(@Valid @RequestBody MessageDTO message) {
		Message model = new Message();
		model.setCreatedAt(new Date());
		model.setMessage(message.getMessage());
		model = dao.saveAndFlush(model);
		return model;
	}

	@RequestMapping(value = "/message/add", method = RequestMethod.GET)
	@ResponseBody
	public String showAddTodoForm(@Valid @RequestBody MessageDTO message) {
		MessageDTO formObject = new MessageDTO();
		// model.addAttribute("todo", formObject);

		return "/message/add";
	}

	// @RequestMapping(value = "/message/add", method = RequestMethod.POST)
	// public String add(@Valid @ModelAttribute("todo") MessageDTO dto,
	// BindingResult result, RedirectAttributes attributes) {
	// if (result.hasErrors()) {
	// return "todo/add";
	// }
	//
	// Message toBeAdded = new Message();
	// BeanUtils.copyProperties(dto, toBeAdded);
	// toBeAdded.setId(null);
	//
	// Message added = dao.saveAndFlush(toBeAdded);
	//
	// attributes.addFlashAttribute("feedbackMessage", added.getMessage());
	// // addFeedbackMessage(attributes, "feedback.message.todo.added",
	// // added.getMessage());
	// attributes.addAttribute("id", added.getId());
	//
	// return createRedirectViewPath("/message/{id}");
	// }

	private String createRedirectViewPath(String requestMapping) {
		StringBuilder redirectViewPath = new StringBuilder();
		redirectViewPath.append("redirect:");
		redirectViewPath.append(requestMapping);
		return redirectViewPath.toString();
	}

	// private void addFeedbackMessage(RedirectAttributes attributes,
	// String messageCode, Object... messageParameters) {
	// String localizedFeedbackMessage = getMessage(messageCode,
	// messageParameters);
	// attributes.addFlashAttribute("feedbackMessage",
	// localizedFeedbackMessage);
	// }

	// private String getMessage(String messageCode, Object...
	// messageParameters) {
	// Locale current = LocaleContextHolder.getLocale();
	// return messageSource.getMessage(messageCode, messageParameters,
	// current);
	// }
}
