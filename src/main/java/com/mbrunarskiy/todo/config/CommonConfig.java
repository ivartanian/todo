package com.mbrunarskiy.todo.config;

import com.mbrunarskiy.todo.domain.Todo;
import com.mbrunarskiy.todo.dto.file.FileDto;
import com.mbrunarskiy.todo.dto.todo.TodoUpdateDto;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static java.util.Objects.isNull;

/**
 * @author maks
 */
@Configuration
public class CommonConfig {

    @Bean
    public JavaSparkContext sc() {
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("Му Арр");
        return new JavaSparkContext(conf);
    }

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
