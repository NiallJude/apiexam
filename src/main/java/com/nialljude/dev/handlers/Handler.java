package com.nialljude.dev.handlers;

import com.nialljude.dev.github.Project;

import java.util.List;

/**
 * Abstract class for handlers.
 *
 * @author Niall Jude Collins
 */
public abstract class Handler {

    private int total_count;
    private Boolean incomplete_results;
    private List<Project> items;

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

    @Override
    public String toString() {
        return "Handler{" +
                "total_count=" + total_count +
                ", incomplete_results=" + incomplete_results +
                ", items=" + items +
                '}';
    }
}
