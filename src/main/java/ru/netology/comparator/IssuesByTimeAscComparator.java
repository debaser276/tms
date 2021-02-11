package ru.netology.comparator;

import ru.netology.issue.Issue;

import java.util.Comparator;

public class IssuesByTimeAscComparator implements Comparator<Issue> {
    @Override
    public int compare(Issue o1, Issue o2) {
        if (o1.getCreated().isAfter(o2.getCreated())) {
            return 1;
        } if (o1.getCreated().isBefore(o2.getCreated())) {
            return -1;
        } else {
            return 0;
        }
    }
}
