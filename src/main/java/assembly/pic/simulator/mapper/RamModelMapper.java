package assembly.pic.simulator.mapper;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.Status;
import assembly.pic.simulator.model.assembly_file.RamModel;
import org.springframework.stereotype.Component;

@Component
public class RamModelMapper {

    public RamModel mapToRamModel(Ram ram) {
        return new RamModel(
                ram.getWRegister(),
                ram.getProgramCounter(),
                ram.getPCL(),
                ram.getPCLATH(),
                ram.getStatus(Status.CARRY_BIT),
                ram.getStatus(Status.DCARRY_BIT),
                ram.getStatus(Status.ZERO_BIT),
                ram.getBank0(),
                ram.getBank1(),
                ram.getTosStack()
        );
    }
}
