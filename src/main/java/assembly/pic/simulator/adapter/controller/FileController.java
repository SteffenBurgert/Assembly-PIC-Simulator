package assembly.pic.simulator.adapter.controller;

import assembly.pic.simulator.model.assembly_file.AssemblyFileModel;
import assembly.pic.simulator.service.AssemblyCompilerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    final AssemblyCompilerService assemblyCompilerService = new AssemblyCompilerService();

    @PostMapping("/upload")
    public ResponseEntity<AssemblyFileModel> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        return assemblyCompilerService.initializeSimulator(file)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
    }
}
