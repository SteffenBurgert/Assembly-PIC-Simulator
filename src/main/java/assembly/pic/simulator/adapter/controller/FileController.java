package assembly.pic.simulator.adapter.controller;

import assembly.pic.simulator.exeption.Result;
import assembly.pic.simulator.model.assembly_file.AssemblyFileModel;
import assembly.pic.simulator.service.AssemblyCompilerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.logging.Logger;

@RestController
@RequestMapping("/file")
public class FileController {

    private final AssemblyCompilerService assemblyCompilerService;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public FileController(AssemblyCompilerService assemblyCompilerService) {
        this.assemblyCompilerService = assemblyCompilerService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        Result<AssemblyFileModel> result = assemblyCompilerService.initializeSimulator(file);
        if (result.isSuccess()) {
            LOGGER.info("Assembly file: " + file.getOriginalFilename() + " uploaded");
            return ResponseEntity.ok(result.getValue());
        }

        LOGGER.info("Could not upload file " + file.getOriginalFilename() + ", do to: " + result.getError().getMessage());
        return ResponseEntity.badRequest().body(result.getError());
    }
}
