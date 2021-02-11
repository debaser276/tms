package ru.netology.repository;

import ru.netology.issue.Issue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void add(Issue issue) {
        issues.add(issue);
    }

    public Issue getById(int id) {
        return issues.get(id);
    }

    public List<Issue> getAll() {
        return issues;
    }

    public List<Issue> getIssuesByOpen(Boolean isOpen) {
        return issues.stream().filter(issue -> issue.isOpened() == isOpen).collect(Collectors.toList());
    }

    public void update(int id, Boolean flag) {
        issues.get(id).setOpened(flag);
    }

}
