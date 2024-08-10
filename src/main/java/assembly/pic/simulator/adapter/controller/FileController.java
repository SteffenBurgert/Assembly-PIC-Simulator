package assembly.pic.simulator.adapter.controller;

import assembly.pic.simulator.exeption.Result;
import assembly.pic.simulator.model.assembly_file.AssemblyFileModel;
import assembly.pic.simulator.service.AssemblyCompilerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/file")
public class FileController {

    private final AssemblyCompilerService assemblyCompilerService = new AssemblyCompilerService();

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        Result<Optional<AssemblyFileModel>> result = assemblyCompilerService.initializeSimulator(file);
        if (result.isSuccess()) {
            return result.getValue().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null));
        }

        return ResponseEntity.badRequest().body(result.getError());
    }
}
