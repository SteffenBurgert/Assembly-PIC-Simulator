package assembly.pic.simulator.adapter.controller;

import assembly.pic.simulator.akku.AssemblyFile;
import assembly.pic.simulator.model.FileUpload;
import assembly.pic.simulator.service.AssemblyFileReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public ResponseEntity<List<FileUpload>> uploadFile(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        System.out.println(new String(file.getBytes()));
        AssemblyFile assemblyFile = new AssemblyFileReader().readFile(file);

        return ResponseEntity.ok(assemblyFile.getFileUpload());
    }
}
