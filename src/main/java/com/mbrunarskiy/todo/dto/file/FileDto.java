package com.mbrunarskiy.todo.dto.file;

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
public class FileDto implements BaseDto {
    private String name;
    private String path;
}
