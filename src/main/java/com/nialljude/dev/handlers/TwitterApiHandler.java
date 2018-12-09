package com.nialljude.dev.handlers;

import com.nialljude.dev.twitter.Tweet;
import com.nialljude.dev.github.Project;

import java.util.List;

/**
 * A Custom object designed to hold
 * Twitter objects (Tweet and User).
 *
 * @author Niall Jude Collins
 */
public class TwitterApiHandler {

    private List<Tweet> statuses;

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TwitterApiHandler(List<Tweet> statuses) {
        this.statuses = statuses;
    }

    public List<Tweet> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Tweet> statuses) {
        this.statuses = statuses;
    }
}
