package com.mbrunarskiy.todo.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * @author maks
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "todos", indexes = {@Index(name = "status_index", columnList = "status")})
@EqualsAndHashCode(of = "title", callSuper = false)
public class Todo extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "file")
    private String file;

    @Column(name = "status", nullable = false)
    private Status status;

    public enum Status {
        TODO, DONE, DELETE;
    }
}
