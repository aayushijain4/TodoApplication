package com.example.web.spring.boot.web.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.spring.boot.web.project.entity.Todo;

@Service
public class TodoService {

	@Autowired
	Todo todo;

	private static List<Todo> todoList = new ArrayList<Todo>();
	private static int todoCount = 3;

	static {
		todoList.add(new Todo(1, "Brutus", "Learn Spring MVC", new Date(), false));
		todoList.add(new Todo(2, "Brutus", "Learn Struts", new Date(), false));
		todoList.add(new Todo(3, "Brutus", "Learn Hibernate", new Date(), false));
	}

	public List<Todo> retrieveTodos(String userName) {

		List<Todo> displayList = new ArrayList<Todo>();
		for (Todo todos : todoList) {
			if (todos.getUser().equals(userName))
				displayList.add(todos);
		}
		return displayList;

	}

	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todoList.add(new Todo(++todoCount, name, desc, targetDate, isDone));
	}

	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todoList.iterator();

		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id)
				iterator.remove();
		}
	}

	public Todo retrieveTodo(int id) {
		for (Todo todo : todoList) {
			if (todo.getId() == id)
				return todo;
		}
		return null;
	}

	public void updateTodo(Todo todo) {
		deleteTodo(todo.getId());
		todoList.add(todo);
	}


}
