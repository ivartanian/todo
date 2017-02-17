package com.mbrunarskiy.todo.service;

import com.mbrunarskiy.todo.domain.Todo;
import com.mbrunarskiy.todo.dto.todo.TodoCreateDto;
import com.mbrunarskiy.todo.dto.todo.TodoDto;
import com.mbrunarskiy.todo.dto.todo.TodoUpdateDto;
import com.mbrunarskiy.todo.exceptions.NotFoundException;
import com.mbrunarskiy.todo.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author maks
 */
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;
    private final ModelMapper mapper;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper mapper) {
        this.todoRepository = todoRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TodoDto> findAll() {
        return mapper.map(todoRepository.findAll(), new TypeToken<List<TodoDto>>() {}.getType());
    }

    @Override
    public TodoDto update(long id, TodoUpdateDto updateDto) {
        Todo todo = todoRepository.findOne(id);
        checkNull(id, todo);
        mapper.map(updateDto, todo);
        return mapper.map(todoRepository.save(todo), TodoDto.class);
    }

    @Override
    public TodoDto updateFile(long id, String filePath) {
        Todo todo = todoRepository.findOne(id);
        checkNull(id, todo);
        todo.setFile(filePath);
        return mapper.map(todoRepository.save(todo), TodoDto.class);
    }

    @Override
    public TodoDto create(TodoCreateDto dto) {
        Todo todo = mapper.map(dto, Todo.class);
        todo.setStatus(Todo.Status.TODO);
        return mapper.map(todoRepository.save(todo), TodoDto.class);
    }

    private void checkNull(long id, Todo todo) {
        if (isNull(todo)) {
            throw new NotFoundException(String.format("Couldn't find todo with id=%s", id));
        }
    }

}
