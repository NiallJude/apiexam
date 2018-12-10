package com.nialljude.dev.handlers;

import com.nialljude.dev.github.Project;

import java.util.List;

/**
 * Abstract class for handlers.
 *
 * @author Niall Jude Collins
 */
public abstract class Handler {
    
    private List<Project> items;

    public List<Project> getItems() {
        return items;
    }
}
