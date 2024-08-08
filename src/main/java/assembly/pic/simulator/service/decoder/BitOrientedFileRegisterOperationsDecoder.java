package assembly.pic.simulator.service.decoder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BitOrientedFileRegisterOperationsDecoder {

    public Optional<CommandCode> decode(int opcode) {
        return switch (opcode >> 10) {
            case 4 -> Optional.of(CommandCode.BCF);
            case 5 -> Optional.of(CommandCode.BSF);
            case 6 -> Optional.of(CommandCode.BTFSC);
            case 7 -> Optional.of(CommandCode.BTFSS);
            default -> Optional.empty();
        };
    }
}
