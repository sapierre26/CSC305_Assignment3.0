//import java.util.List;
//
//public class Delegate implements Runnable {
//    private final String url;
//
//    public Delegate(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public void run() {
//        Blackboard.getInstance().clear();
//
//        try {
//            String token = "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";
//            GitHubHandler gh = new GitHubHandler(token);
//            List<String> allFromUrl = gh.listFilesRecursive(url);
//
//            for (String path : allFromUrl) {
//                if (path.endsWith(".java")) {
//                    String content = gh.getFileContentFromUrl(convertToBlobUrl(url, path));
//                    System.out.println("Content of " + path + ":");
//
//                    int lines = countLines(content);
//
//                    String fileContent = gh.getFileContentFromUrl(convertToBlobUrl(url, path));
//
//                    Square square = new Square(path, lines);
//                    Metrics metrics = square.getMetrics();
//                    metrics.setTotalClasses(countClasses(fileContent));
//                    metrics.setTotalAbstractClasses(countAbstractClasses(fileContent));
//                    metrics.setOutgoingDependencies(countImports(fileContent));
//
//                    Blackboard.getInstance().addSquare(square);
//                }
//            }
//
//            Blackboard.getInstance().setReady();
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private int countImports(String content) {
//        return (int) content.lines()
//                .filter(s -> s.trim().startsWith("import "))
//                .count();
//    }
//
//    private int countLines(String content) {
//        return (int) content.lines().count();
//    }
//
//
//    private int countClasses(String content) {
//        return (int) content.lines()
//                .filter(s -> s.contains("class "))
//                .count();
//    }
//
//    private int countAbstractClasses(String content) {
//        return (int) content.lines()
//                .filter(s -> s.contains("abstract class "))
//                .count();
//    }
//
//    private String convertToBlobUrl(String url, String path) {
//        if (url.contains("/tree/")) {
//            String[] parts = url.split("/tree/");
//            return parts[0] + "/blob/" + parts[1].split("/")[0] + "/" + path;
//        } else {
//            return url.replace("/tree/", "/blob/") + "/" + path;
//        }
//    }
//}
