package com.mbrunarskiy.todo.dto.todo;

import com.mbrunarskiy.todo.domain.Todo;
import com.mbrunarskiy.todo.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author maks
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoUpdateDto implements BaseDto {
    private String title;
    private Todo.Status status;
}
