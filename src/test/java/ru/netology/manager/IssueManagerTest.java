package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.IssuesByTimeAscComparator;
import ru.netology.comparator.IssuesByTimeDescComparator;
import ru.netology.issue.Issue;
import ru.netology.repository.IssueRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    Issue issue1 = new Issue(1, LocalDateTime.of(2021, 2, 4, 10, 10), "title1", 1, "author1", false, Set.of("tag1"), Set.of("assignee1"));
    Issue issue2 = new Issue(2, LocalDateTime.of(2021, 2, 4, 10, 15), "title2", 2, "author2", true, Set.of("tag2", "tag3"), Set.of("assignee1"));
    Issue issue3 = new Issue(3, LocalDateTime.of(2021, 2, 4, 9, 10), "title3", 2, "author2", false, Set.of("tag1", "tag3"), Set.of("assignee2", "assignee3"));
    Issue issue4 = new Issue(4, LocalDateTime.of(2021, 2, 3, 9, 10), "title4", 1, "author1", true, Set.of("tag2"), Set.of("assignee1", "assignee2"));
    Issue issue5 = new Issue(5, LocalDateTime.of(2021, 2, 6, 14, 10), "title5", 3, "author3", true, Set.of("tag3"), Set.of("assignee3"));

    @BeforeEach
    public void setup() {
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
    }

    @Test
    public void shouldOpenIssue() {
        manager.openIssue(1);
        assertTrue(manager.getById(1).isOpened());
    }

    @Test
    public void shouldCloseIssue() {
        manager.closeIssue(2);
        assertFalse(manager.getById(2).isOpened());
    }

    @Test
    public void shouldFilterByAuthor() {
        List<Issue> expected = List.of(issue1, issue4);
        List<Issue> actual = IssueManager.filterBy(manager.getAll(), issue -> issue.getAuthor().equalsIgnoreCase("author1"));

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetOpened() {
        List<Issue> expected = List.of(issue2, issue4, issue5);
        List<Issue> actual = manager.getOpened();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetClosed() {
        List<Issue> expected = List.of(issue1, issue3);
        List<Issue> actual = manager.getClosed();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByTag() {
        List<Issue> expected = List.of(issue2, issue4);
        List<Issue> actual = IssueManager.filterBy(manager.getAll(), issue -> issue.getTags().contains("tag2"));

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByTags() {
        List<Issue> expected = List.of(issue3);
        List<Issue> actual = IssueManager.filterBy(manager.getAll(), issue -> issue.getTags().containsAll(Set.of("tag1", "tag3")));

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssignee() {
        List<Issue> expected = List.of(issue1, issue2, issue4);
        List<Issue> actual = IssueManager.filterBy(manager.getAll(), issue -> issue.getAssignees().contains("assignee1"));

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssignees() {
        List<Issue> expected = List.of(issue4);
        List<Issue> actual = IssueManager.filterBy(manager.getAll(), issue -> issue.getAssignees().containsAll(Set.of("assignee1", "assignee2")));

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortAsc() {
        List<Issue> expected = List.of(issue4, issue3, issue1, issue2, issue5);
        List<Issue> actual = manager.getAll();
        actual.sort(new IssuesByTimeAscComparator());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSortDesc() {
        List<Issue> expected = List.of(issue5, issue2, issue1, issue3, issue4);
        List<Issue> actual = manager.getAll();
        actual.sort(new IssuesByTimeDescComparator());

        assertEquals(expected, actual);
    }

}