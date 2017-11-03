import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class DownloadStuff implements Callable {
    @Override
    public String call() throws Exception {
        List<String> fileUrls = new ArrayList<>();
        String saveDir = "C:\\Users\\Elev1\\IdeaProjects\\Lab6_uppg3_1\\files";
        fileUrls.add("https://github.com/TobbeLOL/Lab5_uppg6/blob/master/src/Main.java");
        String fileUrl = fileUrls.get(0);

        String fileName = downloadFile(fileUrl, saveDir);
        return fileName;
    }

    public String downloadFile(String fileUrl, String saveDir) throws IOException {
        final URL url = new URL(fileUrl);
        final URLConnection conn = url.openConnection();
        String fileName;

        fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length());

        //read the file lul
        InputStream inputStream = conn.getInputStream();
        String saveFilePath = saveDir + File.separator + fileName;
        final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        final String pageText = reader.lines().collect(Collectors.joining("\n"));
        Files.write(Paths.get(saveFilePath), pageText.getBytes());

        inputStream.close();
        reader.close();

        System.out.println("File downloaded: " + fileName);
        return fileName;
    }
}
