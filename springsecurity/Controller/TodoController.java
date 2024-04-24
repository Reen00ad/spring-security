package com.example.springsecurity.Controller;

import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


//    @GetMapping("/getall")
//    public ResponseEntity getAllTodos(){
//        return ResponseEntity.status(200).body(todoService.getAllTodos());
//    }

    @GetMapping("/getalla")
    public ResponseEntity getAllTodos(){
        return ResponseEntity.status(200).body(todoService.getAllTodos());
    }

    //مااخذ اوبجكت اخذ اللوقن
    @GetMapping("/get")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal User user){
    return ResponseEntity.status(200).body(todoService.getMyTodos(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addTodos(@AuthenticationPrincipal User user, @RequestBody @Valid Todo todo){
        todoService.addTodo(user.getId(),todo);
        return ResponseEntity.status(200).body("todo added successfully");
    }
    @PutMapping("/update/{todoid}")
    public ResponseEntity updateTodo(@AuthenticationPrincipal User user,@PathVariable Integer todoid, @RequestBody @Valid Todo todo){
        todoService.updateTodo(user.getId(),todoid,todo);
        return ResponseEntity.status(200).body("todo updated successfully");
    }
    @DeleteMapping("/delete/{todoid}")
    public ResponseEntity deleteTodo(@AuthenticationPrincipal User user,@PathVariable Integer todoid){
        todoService.deleteTodo(user.getId(),todoid);
        return ResponseEntity.status(200).body("todo deleted successfully");
    }
}
