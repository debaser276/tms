package ru.netology.issue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Data
public class Issue {
    private int id;
    private LocalDateTime created;
    private String title;
    private int authorId;
    private String author;
    private boolean isOpened;
    private Set<String> tags;
    private Set<String> assignees;
}
