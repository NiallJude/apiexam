package com.nialljude.dev.github;

import java.util.List;

public class GithubApiHandler {

    private int total_count;
    private Boolean incomplete_results;
    private List<Project> items;

    public GithubApiHandler(int total_count, Boolean incomplete_results, List<Project> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(Boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<Project> getItems() {
        return items;
    }

    public void setItems(List<Project> items) {
        this.items = items;
    }
}
