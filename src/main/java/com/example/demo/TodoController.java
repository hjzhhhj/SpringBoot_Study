package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

class Todo {
    private int id;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

@RestController
public class TodoController {
    private Todo[] todos = new Todo[100];
    private int currentIndex = 1;

    // Create => POST
    // Todo newTodo - new Todo();
    // newTodo.setText("니가 받은 텍스트");
    @PostMapping(value="/todos",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo createTodo(@RequestBody Todo newTodo) {
        // newTodo에 text는 잘 들어와있고, id는 0이겠죠?
        newTodo.setId(currentIndex);
        todos[currentIndex-1] = newTodo;
        currentIndex++;
        return newTodo;
    }

    // Read => GET
    @GetMapping(value="/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo getTodo(@PathVariable Integer id) {
        return todos[id-1];
    }

    // Read => GET (전체 리소스)
    @GetMapping(value="/todos")
    // 응답 메세지의 Bod
    public ArrayList<Todo> getTodo() {
        // 1. Todo 타임 객체를 저장할 수 있는 ArrayList를 만들어서
        ArrayList<Todo> returnTodo = new ArrayList<>();
        // 2. todos 배열을 길이만큼 반복하면서 null이 아니면 앞서 1에서 만든 List에 추가하고
        for (int i = 0; i < todos.length; i++) {
            if (todos[i] != null) returnTodo.add(todos[i]);
        }
        // 3. ArrayList 값을 반환
        return returnTodo;
    }

    // Delete
    @DeleteMapping("/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable("id") Integer id) {
        todos[id-1] = null;
    }
}
