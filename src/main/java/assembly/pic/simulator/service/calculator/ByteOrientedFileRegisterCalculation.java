package assembly.pic.simulator.service.calculator;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.Status;
import assembly.pic.simulator.akku.timer.ProgramTimer;
import assembly.pic.simulator.service.calculator.general.GeneralCalculation;
import assembly.pic.simulator.service.calculator.port.ByteOrientedFileRegisterCalculationPort;
import org.springframework.stereotype.Service;

@Service
public class ByteOrientedFileRegisterCalculation implements
        ByteOrientedFileRegisterCalculationPort {

    private static final GeneralCalculation generalCalculation = new GeneralCalculation();

    @Override
    public void addwf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int w = ram.getWRegister();
        int result = generalCalculation.calculateAddOperation(ram, w, registerValue);

        setResultToLocation(ram, result, opcode);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void andwf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int result = ram.getWRegister() & registerValue;

        setResultToLocation(ram, result, opcode);
        generalCalculation.setZeroBit(ram, result);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void clrf(int opcode, Ram ram, ProgramTimer programTimer) {
        int f = getF(opcode, ram);
        ram.setGeneralPurposeRegister(f, 0);
        generalCalculation.setZeroBit(ram, 0);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void clrw(Ram ram, ProgramTimer programTimer) {
        ram.setWRegister(0);
        generalCalculation.setZeroBit(ram, 0);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void comf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int comfValue = ~registerValue & 255;

        setResultToLocation(ram, comfValue, opcode);
        generalCalculation.setZeroBit(ram, comfValue);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void decf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int decFValue = (registerValue - 1) & 255;

        setResultToLocation(ram, decFValue, opcode);
        generalCalculation.setZeroBit(ram, decFValue);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void decfsz(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int decfValue = (registerValue - 1) & 255;

        setResultToLocation(ram, decfValue, opcode);

        if (decfValue == 0) {
            ram.increaseProgramCounter();
            programTimer.calculateTimer(1, ram);
        } else programTimer.calculateTimer(2, ram);
    }

    @Override
    public void incf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int incfValue = (registerValue + 1) & 255;

        setResultToLocation(ram, incfValue, opcode);
        generalCalculation.setZeroBit(ram, incfValue);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void incfsz(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int incFValue = (registerValue + 1) & 255;

        setResultToLocation(ram, incFValue, opcode);
        if (incFValue == 0) {
            ram.increaseProgramCounter();
            programTimer.calculateTimer(2, ram);
        } else programTimer.calculateTimer(1, ram);
    }

    @Override
    public void iorwf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int result = ram.getWRegister() | registerValue;

        setResultToLocation(ram, result, opcode);
        generalCalculation.setZeroBit(ram, result);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void movf(int opcode, Ram ram, ProgramTimer programTimer) {
        int moveValue = getGeneralPurposeRegisterValue(ram, opcode);

        int d = getD(opcode);
        if (d == 0) {
            ram.setWRegister(moveValue);
        }
        generalCalculation.setZeroBit(ram, moveValue);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void movwf(int opcode, Ram ram, ProgramTimer programTimer) {
        int f = getF(opcode, ram);
        ram.setGeneralPurposeRegister(f, ram.getWRegister());
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void nop(int opcode, Ram ram, ProgramTimer programTimer) {
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void rlf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int rotate = registerValue << 1;

        int carryBit = ram.getStatus(Status.CARRY_BIT);
        ram.setStatus(Status.CARRY_BIT, rotate & ~255);
        setResultToLocation(ram, (rotate & 255) + carryBit, opcode);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void rrf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);

        int carryBit = ram.getStatus(Status.CARRY_BIT);
        int rotateCarryValue = carryBit << 8;
        int rotate = (rotateCarryValue | registerValue) >> 1;
        ram.setStatus(Status.CARRY_BIT, registerValue & 1);
        setResultToLocation(ram, rotate, opcode);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void subwf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int w = ram.getWRegister();
        int result = generalCalculation.calculateSubOperation(ram, registerValue, w);

        setResultToLocation(ram, result, opcode);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void swapf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int right = registerValue & 15;
        int left = registerValue >> 4;
        int result = (right << 4) + left;

        setResultToLocation(ram, result, opcode);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void xorwf(int opcode, Ram ram, ProgramTimer programTimer) {
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int result = ram.getWRegister() ^ registerValue;

        setResultToLocation(ram, result, opcode);
        generalCalculation.setZeroBit(ram, result);
        programTimer.calculateTimer(1, ram);
    }

    private int getD(int opcode) {
        return (opcode & 0b10000000) >> 7;
    }

    private int getGeneralPurposeRegisterValue(Ram ram, int opcode) {
        int f = getF(opcode, ram);
        return ram.getGeneralPurposeRegister(f);
    }

    private int getF(int opcode, Ram ram) {
        int f = (opcode & 0b1111111);
        if (f == 0) {
            return ram.getFSR();
        }
        return f;
    }

    private void setResultToLocation(Ram ram, int result, int opcode) {
        int d = getD(opcode);
        if (d == 1) {
            int f = getF(opcode, ram);
            switch (f) {
                case 0: {
                    int fsr = ram.getFSR();
                    ram.setGeneralPurposeRegister(fsr, result);
                    break;
                }
                case 4: {
                    ram.setFSR(result);
                    break;
                }
                default: ram.setGeneralPurposeRegister(f, result);
            }
        } else {
            ram.setWRegister(result);
        }
    }
}
