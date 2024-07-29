package assembly.pic.simulator.adapter.controller;

import assembly.pic.simulator.akku.AssemblyFile;
import assembly.pic.simulator.model.FileUpload;
import assembly.pic.simulator.service.AssemblyFileReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<List<FileUpload>> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        String[] fileNameSplit = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        if (fileNameSplit[fileNameSplit.length-1].equalsIgnoreCase("lst")) {
            AssemblyFile assemblyFile = new AssemblyFileReader().readFile(file);
            return ResponseEntity.ok(assemblyFile.getFileUpload());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
