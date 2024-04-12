package com.example.todo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.*;

@RestController
public class TodoController {
   @Autowired
   public TodoH2Service ts;

   @GetMapping("/todos")
   public ArrayList<Todo> getTodos() {
      return ts.getTodos();
   }

   @GetMapping("/todos/{todoId}")
   public Todo getTodoById(@PathVariable("todoId") int todoId) {
      return ts.getTodoById(todoId);
   }

   @PostMapping("/todos")
   public Todo addTodo(@RequestBody Todo todo) {
      return ts.addTodo(todo);
   }

   @PutMapping("/todos/{todoId}")
   public Todo updateTodo(@PathVariable("todoId") int todoId, @RequestBody Todo todo) {
      return ts.updateTodo(todoId, todo);
   }

   @DeleteMapping("/todos/{todoId}")

   public void deleteTodo(@PathVariable("todoId") int todoId) {
      ts.deleteTodo(todoId);
   }
}