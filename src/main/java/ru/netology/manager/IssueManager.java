package ru.netology.manager;

import ru.netology.issue.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

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

    public List<Issue> filterByAuthor(String author) {
        return filterBy(issue -> issue.getAuthor().equalsIgnoreCase(author));
    }

    public List<Issue> filterByTag(String tag) {
        return filterBy(issue -> issue.getTags().contains(tag));
    }

    public List<Issue> filterByTag(Set<String> tags) {
        return filterBy(issue -> issue.getTags().containsAll(tags));
    }

    public List<Issue> filterByAssignee(String assignee) {
        return filterBy(issue -> issue.getAssignees().contains(assignee));
    }

    public List<Issue> filterByAssignee(Set<String> assignees) {
        return filterBy(issue -> issue.getAssignees().containsAll(assignees));
    }

    private List<Issue> filterBy(Predicate<Issue> predicate) {
        List<Issue> tmp = new ArrayList<>();
        List<Issue> issues = repository.getAll();
        for (Issue issue : issues) {
            if (predicate.test(issue)) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

}
