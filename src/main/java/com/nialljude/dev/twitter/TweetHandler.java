package com.nialljude.dev.twitter;

import java.util.List;

public class TweetHandler {

    private List<Tweet> statuses;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;

    public TweetHandler(List<Tweet> statuses) {
        this.statuses = statuses;
    }

    public List<Tweet> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Tweet> statuses) {
        this.statuses = statuses;
    }
}
