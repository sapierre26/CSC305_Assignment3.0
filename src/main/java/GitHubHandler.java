import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GitHubHandler {
    private final String token;

    public GitHubHandler(String token) {
        this.token = token;
    }

    public List<String> listFilesRecursive(String url) throws Exception {
        List<String> list = new ArrayList<>();

        // Convert tree URL → GitHub API contents URL
        String apiUrl = convertTreeUrlToApi(url);

        URL u = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");

        if (token != null && !token.isBlank()) {
            conn.setRequestProperty("Authorization", "token " + token);
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        String json = sb.toString();

        // Very simple parsing: look for "path":"..."
        String[] parts = json.split("\"path\":\"");
        for (int i = 1; i < parts.length; i++) {
            String path = parts[i].split("\"")[0];
            list.add(path);
        }

        return list;
    }

    public String getFileContentFromUrl(String url) throws Exception {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestMethod("GET");

        // Optional: add GitHub token
        if (token != null && !token.isBlank()) {
            conn.setRequestProperty("Authorization", "token " + token);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        reader.close();
        return sb.toString();
    }

    private String convertTreeUrlToApi(String treeUrl) {
        // Input: https://github.com/user/repo/tree/main/src
        // Output: https://api.github.com/repos/user/repo/contents/src?ref=main

        String[] parts = treeUrl.split("/tree/");
        String repoPart = parts[0].replace("https://github.com/", "");
        String branchAndPath = parts[1];

        String branch = branchAndPath.substring(0, branchAndPath.indexOf("/"));
        String path = branchAndPath.substring(branch.length() + 1);

        return "https://api.github.com/repos/" + repoPart + "/contents/" + path + "?ref=" + branch;
    }
}
