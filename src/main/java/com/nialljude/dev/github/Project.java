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


    // Getters and Setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getFullName() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Overridden toString

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", url='" + url + '\'' +
                ", watchers_count=" + watchers_count +
                ", forks_count=" + forks_count +
                '}';
    }
}