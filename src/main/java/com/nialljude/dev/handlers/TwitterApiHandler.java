package com.nialljude.dev.handlers;

import com.nialljude.dev.twitter.Tweet;

import java.util.List;

public class TwitterApiHandler extends Handler {

    private List<Tweet> statuses;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;

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
