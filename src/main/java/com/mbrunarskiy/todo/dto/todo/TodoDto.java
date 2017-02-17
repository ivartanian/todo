package com.mbrunarskiy.todo.dto.todo;

import com.mbrunarskiy.todo.domain.Todo;
import com.mbrunarskiy.todo.dto.BaseIdDto;
import com.mbrunarskiy.todo.dto.file.FileDto;
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
public class TodoDto extends BaseIdDto {
    private String title;
    private FileDto file;
    private Todo.Status status;
}
