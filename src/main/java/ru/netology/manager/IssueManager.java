package ru.netology.manager;

import ru.netology.issue.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.add(issue);
    }

    public Issue getById(int id) {
        return repository.getById(id);
    }

    public List<Issue> getAll() {
        return repository.getAll();
    }

    public List<Issue> getOpened() {
        return repository.getIssuesByOpen(true);
    }

    public List<Issue> getClosed() {
        return repository.getIssuesByOpen(false);
    }

    public void openIssue(int id) {
        repository.update(id, true);
    }

    public void closeIssue(int id) {
        repository.update(id, false);
    }

    public static List<Issue> filterBy(List<Issue> issues, Predicate<Issue> predicate) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (predicate.test(issue)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

}
