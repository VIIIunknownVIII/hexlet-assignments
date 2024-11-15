package exercise;

import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String filePath1, String filePath2, String destFilePath) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String content1 = Files.readString(Path.of(filePath1));
                String content2 = Files.readString(Path.of(filePath2));

                String combinedContent = content1 + content2;
                Files.writeString(Path.of(destFilePath), combinedContent, StandardOpenOption.CREATE);

                return destFilePath;
            } catch (NoSuchFileException e) {
                System.out.println("NoSuchFileException: " + e.getFile());
                return "NoSuchFileException";
            } catch (IOException e) {
                System.out.println("Error processing files: " + e.getMessage());
                return null;
            }
        });
    }

    public static CompletableFuture<Long> getDirectorySize(String directoryPath) {
        return CompletableFuture.supplyAsync(() -> {
            AtomicLong size = new AtomicLong(0);
            try {
                Files.walkFileTree(Paths.get(directoryPath), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        size.addAndGet(attrs.size());
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                        // Пропускаем поддиректории
                        if (!dir.equals(Paths.get(directoryPath))) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                System.out.println("Error reading directory: " + e.getMessage());
            }
            return size.get();
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<Long> size = App.getDirectorySize("src/main/resources");
        System.out.println("Directory size: " + size.get() + " bytes");
        // END
    }
}
