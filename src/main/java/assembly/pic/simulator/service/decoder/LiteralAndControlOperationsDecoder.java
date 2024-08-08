package assembly.pic.simulator.service.decoder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LiteralAndControlOperationsDecoder {

    public Optional<CommandCode> decode(int opcode) {
        return switch (opcode >> 11) {
            case 0 -> getLiteralAndControlOperations0(opcode);
            case 4 -> Optional.of(CommandCode.CALL);
            case 5 -> Optional.of(CommandCode.GOTO);
            case 6 -> getLiteralAndControlOperations6(opcode);
            case 7 -> getLiteralAndControlOperations7(opcode);
            default -> Optional.empty();
        };
    }

    private Optional<CommandCode> getLiteralAndControlOperations0(int opcode) {
        return switch (opcode) {
            case 100 -> Optional.of(CommandCode.CLRWDT);
            case 9 -> Optional.of(CommandCode.RETFIE);
            case 8 -> Optional.of(CommandCode.RETURN);
            case 99 -> Optional.of(CommandCode.SLEEP);
            default -> Optional.empty();
        };
    }

    private Optional<CommandCode> getLiteralAndControlOperations6(int opcode) {
        return switch ((opcode & 0b11100000000) >> 8) {
            case 0, 1, 2, 3 -> Optional.of(CommandCode.MOVLW);
            case 4, 5, 6, 7 -> Optional.of(CommandCode.RETLW);
            default -> Optional.empty();
        };
    }

    private Optional<CommandCode> getLiteralAndControlOperations7(int opcode) {
        return switch ((opcode & 0b11100000000) >> 8) {
            case 0 -> Optional.of(CommandCode.IORLW);
            case 1 -> Optional.of(CommandCode.ANDLW);
            case 2 -> Optional.of(CommandCode.XORLW);
            case 4, 5 -> Optional.of(CommandCode.SUBLW);
            case 6, 7 -> Optional.of(CommandCode.ADDLW);
            default -> Optional.empty();
        };
    }

}
