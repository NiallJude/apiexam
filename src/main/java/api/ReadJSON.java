package api;

import com.google.gson.Gson;
import project.GithubApiHandler;
import project.Project;

import java.io.*;
import java.util.List;

public class ReadJSON {

    // Rate Limit will not change (therefore is final)
    private static final int rateLimit = 10;

    public void readJSONFile() {

        ReadJSON readJSON = new ReadJSON();

        // Get JSON String from the file
        String json = readJSON.getJSONString();
        // Now read the json String using the GithubApiHandler and GSON
        GithubApiHandler githubApiHandler = getGithubApiHandler(json);

        // Get a list of all projects from Github API response
        List<Project> projects = githubApiHandler.getItems();
        // Get a subList of first 10 projects (cope with Twitter rate limits)
        List<Project> projectsToSearch = getRateLimitedList(projects);

        System.out.println("\nProjects selected by name: \n");

        for (Project project : projectsToSearch) {
            System.out.println(project.getName());
        }
    }

    private List<Project> getRateLimitedList(List<Project> projects) {
        List<Project> projectsToSearch = projects.subList(0, rateLimit);

        if (projectsToSearch.size() <= rateLimit) {
            System.out.println("Rate Limit: " + rateLimit);
            System.out.println("Projects Selected: " + projectsToSearch.size());
        }
        return projectsToSearch;
    }

    private GithubApiHandler getGithubApiHandler(String json) {
        return new Gson().fromJson(json, GithubApiHandler.class);
    }

    private String getJSONString() {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = new FileInputStream("foo.json");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
}