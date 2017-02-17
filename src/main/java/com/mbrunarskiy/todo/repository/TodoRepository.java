package com.mbrunarskiy.todo.repository;

import com.mbrunarskiy.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author maks
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
