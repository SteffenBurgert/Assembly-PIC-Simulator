package service;

import akku.Ram;
import akku.timer.ProgramTimer;
import akku.timer.WatchDogTimer;
import service.calculator.BitOrientedFileRegisterCalculation;
import service.calculator.ByteOrientedFileRegisterCalculation;
import service.calculator.LiteralAndControlOperationsCalculation;

public class Calculator {

    private static final Decoder decoder = new Decoder();

    private static final ByteOrientedFileRegisterCalculation byteOrientedCalculator =
            new ByteOrientedFileRegisterCalculation();

    private static final BitOrientedFileRegisterCalculation bitOrientedCalculator =
            new BitOrientedFileRegisterCalculation();

    private final  LiteralAndControlOperationsCalculation lacOperationsCalculator =
            new LiteralAndControlOperationsCalculation();

    public void calculateOpcode(int opcode, Ram ram, WatchDogTimer watchDogTimer, ProgramTimer programTimer) throws Exception {
        ram.increaseProgramCounter();
        switch (decoder.decodeOpcode(opcode)) {
            // BYTE-ORIENTED FILE REGISTER OPERATIONS
            case ADDWF -> byteOrientedCalculator.addwf(opcode, ram, programTimer);
            case ANDWF -> byteOrientedCalculator.andwf(opcode, ram, programTimer);
            case CLRF -> byteOrientedCalculator.clrf(opcode, ram, programTimer);
            case CLRW -> byteOrientedCalculator.clrw(ram, programTimer);
            case COMF -> byteOrientedCalculator.comf(opcode, ram, programTimer);
            case DECF -> byteOrientedCalculator.decf(opcode, ram, programTimer);
            case DECFSZ -> byteOrientedCalculator.decfsz(opcode, ram, programTimer);
            case INCF -> byteOrientedCalculator.incf(opcode, ram, programTimer);
            case INCFSZ -> byteOrientedCalculator.incfsz(opcode, ram, programTimer);
            case IORWF -> byteOrientedCalculator.iorwf(opcode, ram, programTimer);
            case MOVF -> byteOrientedCalculator.movf(opcode, ram, programTimer);
            case MOVWF -> byteOrientedCalculator.movwf(opcode, ram, programTimer);
            case NOP -> byteOrientedCalculator.nop(opcode, ram, programTimer);
            case RLF -> byteOrientedCalculator.rlf(opcode, ram, programTimer);
            case RRF -> byteOrientedCalculator.rrf(opcode, ram, programTimer);
            case SUBWF -> byteOrientedCalculator.subwf(opcode, ram, programTimer);
            case SWAPF -> byteOrientedCalculator.swapf(opcode, ram, programTimer);
            case XORWF -> byteOrientedCalculator.xorwf(opcode, ram, programTimer);
            // BIT-ORIENTED FILE REGISTER OPERATIONS
            case BCF -> bitOrientedCalculator.bcf(opcode, ram, programTimer);
            case BSF -> bitOrientedCalculator.bsf(opcode, ram, programTimer);
            case BTFSC -> bitOrientedCalculator.btfsc(opcode, ram, programTimer);
            case BTFSS -> bitOrientedCalculator.btfss(opcode, ram, programTimer);
            // LITERAL AND CONTROL OPERATIONS
            case ADDLW -> lacOperationsCalculator.addlw(opcode, ram, programTimer);
            case ANDLW -> lacOperationsCalculator.andlw(opcode, ram, programTimer);
            case CALL -> lacOperationsCalculator.call(opcode, ram, programTimer);
            case CLRWDT -> lacOperationsCalculator.clrwdt(ram, watchDogTimer, programTimer);
            case GOTO -> lacOperationsCalculator.gotoAssy(opcode, ram, programTimer);
            case IORLW -> lacOperationsCalculator.iorlw(opcode, ram, programTimer);
            case MOVLW -> lacOperationsCalculator.movlw(opcode, ram, programTimer);
            case RETFIE -> lacOperationsCalculator.retfie(ram, programTimer);
            case RETLW -> lacOperationsCalculator.retlw(opcode, ram, programTimer);
            case RETURN -> lacOperationsCalculator.returnAssy(ram, programTimer);
            case SLEEP -> lacOperationsCalculator.sleep(ram, watchDogTimer, programTimer);
            case SUBLW -> lacOperationsCalculator.sublw(opcode, ram, programTimer);
            case XORLW -> lacOperationsCalculator.xorlw(opcode, ram, programTimer);
        }
    }
}
