package assembly.pic.simulator.service;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.exeption.Result;
import assembly.pic.simulator.mapper.IOPinsModelMapper;
import assembly.pic.simulator.mapper.RamModelMapper;
import assembly.pic.simulator.model.assembly_file.AssemblyFileModel;
import assembly.pic.simulator.model.assembly_file.FileType;
import assembly.pic.simulator.model.assembly_file.LstLineModel;
import assembly.pic.simulator.service.assembly_file_reader.AssemblyLstFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AssemblyCompilerService {

    private final RamModelMapper ramModelMapper;
    private final AssemblyLstFileReader assemblyLstFileReader;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AssemblyCompilerService(RamModelMapper ramModelMapper,
                                   AssemblyLstFileReader assemblyLstFileReader) {
        this.ramModelMapper = ramModelMapper;
        this.assemblyLstFileReader = assemblyLstFileReader;
    }

    public Result<AssemblyFileModel> initializeSimulator(MultipartFile file) {
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).toUpperCase();

        if (Arrays.stream(FileType.values()).map(Enum::name).anyMatch(fileName::contains)) {
            AssemblyFileModel assemblyFileModel = new AssemblyFileModel();

            if (fileName.endsWith(FileType.LST.toString())) {
                Ram ram = new Ram();
                int portA = ram.getPortA();
                int portB = ram.getPortB();

                List<LstLineModel> assemblyFileModels;
                try {
                    assemblyFileModels = assemblyLstFileReader.readFile(file).getFile();
                } catch (IOException e) {
                    log.error("Problem with reading Filﬂe: {} error message: {}", file.getOriginalFilename(),
                            e.getMessage());
                    return Result.failure(new IOException("Couldn't read lst file."));
                }

                assemblyFileModel = new AssemblyFileModel(
                        FileType.LST,
                        ramModelMapper.mapToRamModel(ram),
                        new IOPinsModelMapper().mapToIOPinsModel(ram, portA, portB),
                        assemblyFileModels
                );
            }

            assemblyFileModel.setFileName(file.getOriginalFilename());
            log.info("Assembly model for {} build successfully.", file.getOriginalFilename());
            return Result.success(assemblyFileModel);
        }

        String originalFilename = file.getOriginalFilename();
        String fileType = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            fileType = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }

        String fileTypeError =
                fileType.isEmpty() ? "Filetype is NOT PRESENT" : "Unknown filetype: " + fileType;
        log.warn(fileTypeError);
        return Result.failure(new Exception(fileTypeError));
    }
}
