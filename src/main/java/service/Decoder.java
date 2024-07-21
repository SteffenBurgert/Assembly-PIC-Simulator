package service;

import java.util.Optional;

import service.decoder.BitOrientedFileRegisterOperationsDecoder;
import service.decoder.ByteOrientedFileRegisterOperationsDecoder;
import service.decoder.LiteralAndControlOperationsDecoder;
import service.decoder.CommandCode;

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
