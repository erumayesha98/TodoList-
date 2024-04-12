package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.TodoRowMapper;

import java.util.*;

@Service
public class TodoH2Service implements TodoRepository {
  @Autowired
  private JdbcTemplate db;

  @Override
  public ArrayList<Todo> getTodos()

  {
    List<Todo> li = db.query("select * from TODOLIST", new TodoRowMapper());
    ArrayList<Todo> todos = new ArrayList<>(li);
    return todos;
  }

  @Override
  public Todo getTodoById(int todoId) {
    try {
      Todo t = db.queryForObject("select * from TODOLIST where id=?", new TodoRowMapper(), todoId);
      return t;
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
  }

  @Override
  public Todo addTodo(Todo todo) {
    db.update("insert into TODOLIST(todo,priority,status) values(?,?,?)", todo.getTodo(), todo.getPriority(),
        todo.getStatus());

    Todo t = db.queryForObject("select * from TODOLIST where todo=? and priority=? and status=?", new TodoRowMapper(),
        todo.getTodo(), todo.getPriority(), todo.getStatus());
    return t;
  }

  @Override
  public Todo updateTodo(int todoId, Todo todo) {
    if (todo.getTodo() != null) {
      db.update("update TODOLIST set todo=? where id=?", todo.getTodo(), todoId);
    }
    if (todo.getPriority() != null) {
      db.update("update TODOLIST set priority=? where id=?", todo.getPriority(), todoId);
    }
    if (todo.getStatus() != null) {
      db.update("update TODOLIST set status=? where id=?", todo.getStatus(), todoId);
    }

    return getTodoById(todoId);
  }

  @Override
  public void deleteTodo(int todoId) {
    db.update("delete from TODOLIST where id=?", todoId);

  }
}