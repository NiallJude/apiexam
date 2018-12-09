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

    // Constructor
    public Project(int id, String nodeId, String description, String htmlUrl, String projectUrl, String name, String full_name, String summary) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.full_name = full_name;
    }

    // Getters and Setters
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
                '}';
    }
}