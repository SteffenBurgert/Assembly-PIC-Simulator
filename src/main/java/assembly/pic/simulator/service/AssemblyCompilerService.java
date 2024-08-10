package assembly.pic.simulator.service;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.exeption.Result;
import assembly.pic.simulator.mapper.IOPinsModelMapper;
import assembly.pic.simulator.mapper.RamModelMapper;
import assembly.pic.simulator.model.assembly_file.AssemblyFileModel;
import assembly.pic.simulator.model.assembly_file.FileType;
import assembly.pic.simulator.model.assembly_file.LstFileModel;
import assembly.pic.simulator.service.assembly_file_reader.AssemblyLstFileReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssemblyCompilerService {

    private final RamModelMapper ramModelMapper = new RamModelMapper();

    public Result<Optional<AssemblyFileModel>> initializeSimulator(MultipartFile file) {
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).toUpperCase();

        if (Arrays.stream(FileType.values()).map(Enum::name).anyMatch(fileName::contains)) {
            AssemblyFileModel assemblyFileModel = new AssemblyFileModel();

            if (fileName.endsWith(FileType.LST.toString())) {
                Ram ram = new Ram();
                int portA = ram.getPortA();
                int portB = ram.getPortB();

                List<LstFileModel> assemblyFileModels;
                try {
                    assemblyFileModels = new AssemblyLstFileReader().readFile(file).getFile();
                } catch (IOException e) {
                    return Result.failure(e);
                }

                assemblyFileModel = new AssemblyFileModel(
                        FileType.LST,
                        ramModelMapper.mapToRamModel(ram),
                        new IOPinsModelMapper().mapToIOPinsModel(ram, portA, portB),
                        assemblyFileModels
                );
            }

            assemblyFileModel.setFileName(file.getOriginalFilename());
            return Result.success(Optional.of(assemblyFileModel));
        }

        return Result.failure(new Exception("Unknown filetype"));
    }
}
