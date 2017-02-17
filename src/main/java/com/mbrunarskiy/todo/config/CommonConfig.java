package com.mbrunarskiy.todo.config;

import com.mbrunarskiy.todo.domain.Todo;
import com.mbrunarskiy.todo.dto.file.FileDto;
import com.mbrunarskiy.todo.dto.todo.TodoUpdateDto;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Objects.isNull;

/**
 * @author maks
 */
@Configuration
public class CommonConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mappingConfig(mapper);
        convertersConfig(mapper);
        return mapper;
    }

    private void convertersConfig(ModelMapper mapper) {

        Converter<String, FileDto> stringFileDtoConverter = new AbstractConverter<String, FileDto>() {
            @Override
            protected FileDto convert(String source) {
                if (isNull(source)) {
                    return null;
                }

                int lastIndexOf = source.lastIndexOf('/');
                if (lastIndexOf == -1){
                    return null;
                }
                String name = source.substring(lastIndexOf + 1);

                return new FileDto(name, source);
            }
        };
        mapper.addConverter(stringFileDtoConverter);
    }

    private void mappingConfig(ModelMapper mapper) {

        Condition nonEquals = context -> !context.getSource().equals(context.getDestination());

        mapper.addMappings(new PropertyMap<TodoUpdateDto, Todo>() {
            @Override
            protected void configure() {
                when(nonEquals).map().setTitle(source.getTitle());
                when(nonEquals).map().setStatus(source.getStatus());
            }
        });
    }
}
