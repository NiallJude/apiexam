package com.nialljude.dev.github;

/**
 * A Custom object designed to hold
 * Project objects.
 *
 * @author Niall Jude Collins
 */
public class Project {

    // Vars
    private int id;
    private String description;
    private String name;
    private String full_name;
    private String url;
    private int watchers_count;
    private int forks_count;

    // Constructor

    public Project(int id, String description, String name, String full_name, String url, int watchers_count, int forks_count) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.full_name = full_name;
        this.url = url;
        this.watchers_count = watchers_count;
        this.forks_count = forks_count;
    }

    // Getter for name
    public String getName() {
        return name;
    }
}