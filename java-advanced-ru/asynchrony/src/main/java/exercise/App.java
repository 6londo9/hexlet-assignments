package exercise;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String dest) {
        Path destinationPath = Paths.get(dest).toAbsolutePath().normalize();

        return CompletableFuture.supplyAsync(() -> {
            CompletableFuture<String> firstFileFeature = CompletableFuture.supplyAsync(() -> {
                Path firstFilePath = Paths.get(path1).toAbsolutePath().normalize();
                String firstFileText;
                try {
                    firstFileText = Files.readString(firstFilePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return firstFileText;
            }).exceptionally(ex -> {
                System.out.println("We hava an exception in future1 " + ex.getMessage());
                return null;
            });

            CompletableFuture<String> secondFileFeature = CompletableFuture.supplyAsync(() -> {
                Path secondFilePath = Paths.get(path2).toAbsolutePath().normalize();
                String secondFileText;
                try {
                    secondFileText = Files.readString(secondFilePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return secondFileText;
            }).exceptionally(ex -> {
                System.out.println("We have an exception in future2 " + ex.getMessage());
                return null;
            });

            String unionFiles;
            try {
                unionFiles = firstFileFeature.get() + secondFileFeature.get();
                Files.write(destinationPath, unionFiles.getBytes(), StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return unionFiles;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles(
                "src/resources/file1.txt",
                "src/resources/file2.txt",
                "src/resources/dest.txt"
        );
        // END
    }
}

