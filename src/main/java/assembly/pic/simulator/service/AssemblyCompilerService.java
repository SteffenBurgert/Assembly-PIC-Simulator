package assembly.pic.simulator.service;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.mapper.IOPinsModelMapper;
import assembly.pic.simulator.mapper.RamModelMapper;
import assembly.pic.simulator.model.assembly_file.AssemblyFileModel;
import assembly.pic.simulator.model.assembly_file.FileType;
import assembly.pic.simulator.service.assembly_file_reader.AssemblyLstFileReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssemblyCompilerService {

    private final RamModelMapper ramModelMapper = new RamModelMapper();

    public Optional<AssemblyFileModel> initializeSimulator(MultipartFile file) {
        String fileName = Objects.requireNonNull(file.getOriginalFilename()).toUpperCase();

        if (Arrays.stream(FileType.values()).map(Enum::name).anyMatch(fileName::contains)) {
            AssemblyFileModel assemblyFileModel = new AssemblyFileModel();

            if (fileName.endsWith(FileType.LST.toString())) {
                Ram ram = new Ram();
                int portA = ram.getPortA();
                int portB = ram.getPortB();

                assemblyFileModel = new AssemblyFileModel(
                        FileType.LST,
                        ramModelMapper.mapToRamModel(ram),
                        new IOPinsModelMapper().mapToIOPinsModel(ram, portA, portB),
                        new AssemblyLstFileReader().readFile(file).getFile()
                );
            }

            assemblyFileModel.setFileName(file.getOriginalFilename());
            return Optional.of(assemblyFileModel);
        }

        return Optional.empty();
    }
}
