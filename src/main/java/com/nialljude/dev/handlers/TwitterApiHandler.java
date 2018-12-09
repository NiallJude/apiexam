package com.nialljude.dev.handlers;

import com.nialljude.dev.twitter.Tweet;
import com.nialljude.dev.github.Project;

import java.util.List;

public class TwitterApiHandler extends Handler {

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
