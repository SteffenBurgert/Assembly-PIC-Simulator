package assembly.pic.simulator.adapter.controller;

import assembly.pic.simulator.exeption.Result;
import assembly.pic.simulator.model.assembly_file.AssemblyFileModel;
import assembly.pic.simulator.service.AssemblyCompilerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    private final AssemblyCompilerService assemblyCompilerService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public FileController(AssemblyCompilerService assemblyCompilerService) {
        this.assemblyCompilerService = assemblyCompilerService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        Result<AssemblyFileModel> result = assemblyCompilerService.initializeSimulator(file);
        if (result.isSuccess()) {
            log.info("Assembly file: " + file.getOriginalFilename() + " uploaded");
            return ResponseEntity.ok(result.getValue());
        }

        log.info("Could not upload file " + file.getOriginalFilename() + ", do to: " + result.getError()
                .getMessage());
        return ResponseEntity.badRequest().body(result.getError());
    }
}
