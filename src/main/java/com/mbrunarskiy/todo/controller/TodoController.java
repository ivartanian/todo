package com.mbrunarskiy.todo.controller;

import com.mbrunarskiy.todo.dto.todo.TodoCreateDto;
import com.mbrunarskiy.todo.dto.todo.TodoDto;
import com.mbrunarskiy.todo.dto.todo.TodoUpdateDto;
import com.mbrunarskiy.todo.service.TodoService;
import com.mbrunarskiy.todo.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Actions with issues
 *
 * @author maks
 */
@RestController
public class TodoController {

    private final IOUtils ioUtils;
    private final TodoService todoService;

    @Autowired
    public TodoController(IOUtils ioUtils, TodoService todoService) {
        this.ioUtils = ioUtils;
        this.todoService = todoService;
    }

    /**
     * Get all issues
     *
     * @return found list issues
     */
    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todoList = todoService.findAll();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    /**
     * Create new issue
     *
     * @param file for attach
     * @param title for issue
     * @return created issue
     */
    @PostMapping("/todos")
    public ResponseEntity<TodoDto> createTodo(@RequestPart(value = "file", required = false) MultipartFile file, @RequestParam("title") String title) {
        String filePath = ioUtils.write(file);
        TodoDto todo = todoService.create(new TodoCreateDto(title, filePath));
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    /**
     * Update issue
     *
     * @param id issue identifier
     * @param dto data for update
     * @return updated issue
     */
    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable long id, @RequestBody @NotNull @Valid TodoUpdateDto dto) {
        TodoDto todo = todoService.update(id, dto);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    /**
     * Update issue file
     *
     * @param id issue identifier
     * @param file for attach
     * @return updated issue
     */
    @PostMapping("/todos/{id}/file")
    public ResponseEntity<TodoDto> updateTodoFile(@PathVariable long id, @RequestPart(value = "file", required = false) MultipartFile file) {
        String filePath = ioUtils.write(file);
        TodoDto todo = todoService.updateFile(id, filePath);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

}
