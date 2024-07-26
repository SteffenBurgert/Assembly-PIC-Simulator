package assembly.pic.simulator.service;

import java.util.Optional;

import assembly.pic.simulator.service.decoder.BitOrientedFileRegisterOperationsDecoder;
import assembly.pic.simulator.service.decoder.ByteOrientedFileRegisterOperationsDecoder;
import assembly.pic.simulator.service.decoder.LiteralAndControlOperationsDecoder;
import assembly.pic.simulator.service.decoder.CommandCode;

public class Decoder {

    private static final ByteOrientedFileRegisterOperationsDecoder byteOrientedDecoder =
            new ByteOrientedFileRegisterOperationsDecoder();

    private static final BitOrientedFileRegisterOperationsDecoder bitOrientedDecoder =
            new BitOrientedFileRegisterOperationsDecoder();

    private static final LiteralAndControlOperationsDecoder lacOperationsDecoder =
            new LiteralAndControlOperationsDecoder();

    public CommandCode decodeOpcode(int opcode) throws Exception {
        Optional<CommandCode> operation;

        operation = byteOrientedDecoder.decode(opcode);
        if (operation.isPresent()) {
            return operation.get();
        }

        operation = bitOrientedDecoder.decode(opcode);
        if (operation.isPresent()) {
            return operation.get();
        }

        operation = lacOperationsDecoder.decode(opcode);
        if (operation.isPresent()) {
            return operation.get();
        }

        throw new Exception("Operation not Found");
    }

}
