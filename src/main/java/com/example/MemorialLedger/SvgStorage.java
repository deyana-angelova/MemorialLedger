package com.example.MemorialLedger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class SvgStorage {

    //storage directory
    private static final Path DIR = Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploadsSvg");

    //accepts an uploaded file and returns the name
    public String save(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        //check if name empty and if the file is .svg
        if (name == null || !name.toLowerCase().endsWith(".svg")) {
            throw new IllegalArgumentException("Only .svg files are allowed");
        }
        //check whether the text contains the svg opening tag
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        if (!content.contains("<svg")) {
            throw new IllegalArgumentException("This does not look like a valid SVG file");
        }

        //creates the directory IF it does not exist
        Files.createDirectories(DIR);
        //generates a random unique name
        String stored = UUID.randomUUID() + ".svg";
        //save the file
        Files.writeString(DIR.resolve(stored), content);
        return stored;
    }

    public void delete(String stored) throws IOException {
        if (stored != null) {
            Files.deleteIfExists(DIR.resolve(stored));
        }
    }
    public java.util.List<String> list() throws IOException {
        if (!Files.exists(DIR)) return java.util.List.of();
        try (var stream = Files.list(DIR)) {
            return stream.map(p -> p.getFileName().toString())
                    .filter(n -> n.endsWith(".svg"))
                    .sorted()
                    .toList();
        }
    }
}