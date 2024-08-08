package assembly.pic.simulator.mapper;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.PortA;
import assembly.pic.simulator.akku.ram_enums.PortB;
import assembly.pic.simulator.model.assembly_file.IOPinsModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IOPinsModelMapper {

    public IOPinsModel mapToIOPinsModel(Ram ram, int portA, int portB) {
        return new IOPinsModel(
                mapToPortA(ram, portA),
                mapToPortB(ram, portB),
                mapToTrisA(ram),
                mapToTrisB(ram)
        );
    }

    private List<Integer> mapToPortA(Ram ram, int portA) {
        List<Integer> rowA = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            int bitValue = (portA >> i) & 1;
            if (((ram.getTrisA() >> i) & 1) == 0) {
                bitValue = (ram.getPortA() >> i) & 1;
                portA = (portA & ~(1 << i)) | ((bitValue & 1) << i);
            }
            rowA.add(bitValue);
        }

        return rowA;
    }

    private List<Integer> mapToPortB(Ram ram, int portB) {
        List<Integer> rowB = new ArrayList<>(8);

        for (int i = 0; i < 8; i++) {
            int bitValue = (portB >> i) & 1;
            if (((ram.getPortB() >> i) & 1) == 0) {
                bitValue = (ram.getPortB() >> i) & 1;
                portB = (portB & ~(1 << i)) | ((bitValue & 1) << i);
            }
            rowB.add(bitValue);
        }

        return rowB;
    }

    private List<Integer> mapToTrisA(Ram ram) {
        return List.of(
                ram.getTrisA(PortA.RA0),
                ram.getTrisA(PortA.RA1),
                ram.getTrisA(PortA.RA2),
                ram.getTrisA(PortA.RA3),
                ram.getTrisA(PortA.RA4_T0CKI)
        );
    }

    private List<Integer> mapToTrisB(Ram ram) {
        return List.of(
                ram.getTrisB(PortB.RB0_INT),
                ram.getTrisB(PortB.RB1),
                ram.getTrisB(PortB.RB2),
                ram.getTrisB(PortB.RB3),
                ram.getTrisB(PortB.RB4),
                ram.getTrisB(PortB.RB5),
                ram.getTrisB(PortB.RB6),
                ram.getTrisB(PortB.RB7)
        );
    }
}
