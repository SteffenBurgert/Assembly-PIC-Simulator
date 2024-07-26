package assembly.pic.simulator.service.decoder;

import java.util.Optional;

public class ByteOrientedFileRegisterOperationsDecoder {

    public Optional<CommandCode> decode(int opcode) {
        return switch (opcode >> 10) {
            case 0 -> byteOrientedFileRegisterOperations0(opcode);
            case 1 -> byteOrientedFileRegisterOperations1(opcode);
            case 2 -> byteOrientedFileRegisterOperations2(opcode);
            case 3 -> byteOrientedFileRegisterOperations3(opcode);
            default -> Optional.empty();
        };
    }

    private Optional<CommandCode> byteOrientedFileRegisterOperations0(int opcode) {
        return switch ((opcode & 0b1110000000) >> 7) {
            case 0 -> opcode == 0 ? Optional.of(CommandCode.NOP) : Optional.empty();
            case 1 -> Optional.of(CommandCode.MOVWF);
            case 2 -> Optional.of(CommandCode.CLRW);
            case 3 -> Optional.of(CommandCode.CLRF);
            case 4, 5 -> Optional.of(CommandCode.SUBWF);
            case 6, 7 -> Optional.of(CommandCode.DECF);
            default -> Optional.empty();
        };
    }

    private Optional<CommandCode> byteOrientedFileRegisterOperations1(int opcode) {
        return switch ((opcode & 0b1110000000) >> 7) {
            case 0, 1 -> Optional.of(CommandCode.IORWF);
            case 2, 3 -> Optional.of(CommandCode.ANDWF);
            case 4, 5 -> Optional.of(CommandCode.XORWF);
            case 6, 7 -> Optional.of(CommandCode.ADDWF);
            default -> Optional.empty();
        };
    }

    private Optional<CommandCode> byteOrientedFileRegisterOperations2(int opcode) {
        return switch ((opcode & 0b1110000000) >> 7) {
            case 0, 1 -> Optional.of(CommandCode.MOVF);
            case 2, 3 -> Optional.of(CommandCode.COMF);
            case 4, 5 -> Optional.of(CommandCode.INCF);
            case 6, 7 -> Optional.of(CommandCode.DECFSZ);
            default -> Optional.empty();
        };
    }

    private Optional<CommandCode> byteOrientedFileRegisterOperations3(int opcode) {
        return switch ((opcode & 0b1110000000) >> 7) {
            case 0, 1 -> Optional.of(CommandCode.RRF);
            case 2, 3 -> Optional.of(CommandCode.RLF);
            case 4, 5 -> Optional.of(CommandCode.SWAPF);
            case 6, 7 -> Optional.of(CommandCode.INCFSZ);
            default -> Optional.empty();
        };
    }
}
