package com.example.springrest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Todo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="Todo controller")
@RequestMapping("/todoApp")
public class TodoController {

	private static List<Todo> todos = new ArrayList<>();
	private static int maxId=0;
	static {

		Todo t1 = new Todo();
		Todo t2 = new Todo();

		t1.setId(1);
		t1.setName("Kapil");
		t1.setDesc("Learn hibernate");

		t2.setId(2);
		t2.setName("Jasleen");
		t2.setDesc("Learn spring");

		todos.add(t1);
		todos.add(t2);
		

	}

	@RequestMapping("/allTodos")
	@ApiOperation("Get all todos")
	public List<Todo> getAllTodos() {
		return todos;
	}

	@RequestMapping("/todo/{id}")
	public Todo getAllTodos(@PathVariable int id) throws Exception {
		Todo t = null;
		for (Todo todo : todos) {
			if (id == todo.getId()) {
				t = todo;
			}
		}

		if (t == null) {
			throw new Exception();
		}

		return t;
	}
	
	private Todo addTodo(String name , Todo todo) {
		for(Todo t: todos) {
			maxId = t.getId();
		}
		todo.setId(maxId+1);
		todo.setName(name);
		todos.add(todo);
		return todo;
	}
	
	
	@RequestMapping("/{name}/addTodo")
	public Todo add(@PathVariable String name , @RequestBody Todo todo){
		if(todo.getDesc() == null) {
			ResponseEntity.noContent().build();
		}
		return addTodo(name,todo);
	}
	
	
	/*@RequestMapping("/{name}/addTodo")
	public ResponseEntity<?> add(@PathVariable String name , @RequestBody Todo todo){
		if(todo.getDesc() == null) {
			ResponseEntity.noContent().build();
		}
		
		Todo t1 = addTodo(name,todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(t1.getId()).toUri();
		System.out.println(uri.toString());
		System.out.println("Id ==== "+ t1.getId());
		return ResponseEntity.created(uri).build();
	}*/

}
