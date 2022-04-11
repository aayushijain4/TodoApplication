package com.example.web.spring.boot.web.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web.spring.boot.web.project.entity.Todo;
import com.example.web.spring.boot.web.project.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@RequestMapping("/todo")
	public String displayTodo(ModelMap model) {
		String name = getLoggedInUserName(model);
		List<Todo> todoList = todoService.retrieveTodos(name);
		model.put("list", todoList);
		return "todoPage";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/addtodo", method = RequestMethod.GET)
	public String addTodo(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "Default Description", new Date(), false));
		return "AddTodoFormPage";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors())
			return "AddTodoFormPage";

		todoService.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/todo";
	}

	@RequestMapping(value = "/deletetodo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {

		// exception will occur if we delete the todo with id=1
		if (id == 1)
			throw new RuntimeException("Something went wrong");

		todoService.deleteTodo(id);
		return "redirect:/todo";
	}

	@RequestMapping(value = "/updateTodo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "AddTodoFormPage";
	}

	@RequestMapping(value = "/updateTodo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "AddTodoFormPage";
		}

		todo.setUser(getLoggedInUserName(model));

		todoService.updateTodo(todo);

		return "redirect:/todo";
	}

	@RequestMapping(value = "/deletetodoById", method = RequestMethod.GET)
	public String deletetodoById(ModelMap model) {
		return "DeleteTodoFormPage";
	}

	@RequestMapping(value = "/deletetodoById", method = RequestMethod.POST)
	public String deletetodoById(ModelMap model, @RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/todo";
	}

}
