package project;

public class Project {

    // Vars
    private String projectUrl;
    private String name;
    private String fullName;
    private String summary;

    // Default Constructor
    public Project(String projectUrl, String name, String fullName, String summary) {
        this.projectUrl = projectUrl;
        this.name = name;
        this.fullName = fullName;
        this.summary = summary;
    }

    // Getters and Setters
    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
