import java.io.IOException;
import java.nio.file.*;

public class App {
    public static void main(String[] args) throws Exception {
        pegarArquivo();
    }

    public static void pegarArquivo() {
        Path dir = Paths.get("src\\files");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    Path fileName = file.getFileName();
                    String fileNameStr = file.getFileName().toString();
                    System.out.println("========================================");
                    System.out.printf("Open file: %s\n", fileNameStr);

                    Reader.ler("src\\files\\" + fileName);
                }
            }
        } catch (IOException | DirectoryIteratorException e) {
            System.err.printf("Error opening directory: %s\n", e);
        }
    }
}
