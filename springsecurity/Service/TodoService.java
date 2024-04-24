package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.Model.Todo;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import com.example.springsecurity.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;

//    public List<Todo> getAllTodos() {
//        return todoRepository.findAll();
//    }
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    public List<Todo> getMyTodos(Integer userid){
        User user=authRepository.findUserById(userid);
        //هدفي اني اوصل للايدي مو اشيك اذا موجود او لا

        return todoRepository.findAllByUser(user);
    }

    public void addTodo(Integer userid, Todo todo){
        User user=authRepository.findUserById(userid);

        todo.setUser(user);

        todoRepository.save(todo);
    }

    public void updateTodo(Integer userid,Integer todoid,Todo todo){
        Todo t=todoRepository.findTodoById(todoid);

        if(t == null){
            throw new ApiException("todo not found");
        }

        if(t.getUser().getId() != userid){
            throw new ApiException("user id mismatch");
        }
        t.setTitle(todo.getTitle());
        todoRepository.save(t);
    }

    public void deleteTodo(Integer userid, Integer todoid){
        Todo t=todoRepository.findTodoById(todoid);

        if(t == null){
            throw new ApiException("todo not found");
        }
        todoRepository.delete(t);
    }
}
