package com.mbrunarskiy.todo.service;

import com.mbrunarskiy.todo.dto.todo.TodoCreateDto;
import com.mbrunarskiy.todo.dto.todo.TodoDto;
import com.mbrunarskiy.todo.dto.todo.TodoUpdateDto;

import java.util.List;

/**
 * @author maks
 */
public interface TodoService {
    /**
     * Find all issues
     *
     * @return list of issues
     */
    List<TodoDto> findAll();

    /**
     * Update issue
     *
     * @param id issue identifier
     * @param updateDto data for update
     * @return updated issue
     */
    TodoDto update(long id, TodoUpdateDto updateDto);

    /**
     * Update issue file
     *
     * @param id issue identifier
     * @param filePath file path to file
     * @return updated issue
     */
    TodoDto updateFile(long id, String filePath);

    /**
     * Create issue
     *
     * @param dto data for create
     * @return created issue
     */
    TodoDto create(TodoCreateDto dto);
}
