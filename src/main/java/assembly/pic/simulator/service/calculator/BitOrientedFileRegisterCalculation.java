package assembly.pic.simulator.service.calculator;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.timer.ProgramTimer;
import assembly.pic.simulator.service.calculator.port.BitOrientedFileRegisterCalculationPort;
import org.springframework.stereotype.Service;

@Service
public class BitOrientedFileRegisterCalculation implements BitOrientedFileRegisterCalculationPort {

    @Override
    public void bcf(int opcode, Ram ram, ProgramTimer programTimer) {
        int b = getB(opcode);
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int bitMask = (int) Math.pow(2, b);
        setResultToLocation(ram, registerValue & (~bitMask), opcode);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void bsf(int opcode, Ram ram, ProgramTimer programTimer) {
        int b = getB(opcode);
        int registerValue = getGeneralPurposeRegisterValue(ram, opcode);
        int bitMask = (int) Math.pow(2, b);
        setResultToLocation(ram, registerValue | bitMask, opcode);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void btfsc(int opcode, Ram ram, ProgramTimer programTimer) {
        int b = getB(opcode);
        int f = getGeneralPurposeRegisterValue(ram, opcode);

        if (((f >> b) & 1) == 0) {
            ram.increaseProgramCounter();
            programTimer.calculateTimer(2, ram);
        } else programTimer.calculateTimer(1, ram);
    }

    @Override
    public void btfss(int opcode, Ram ram, ProgramTimer programTimer) {
        int b = getB(opcode);
        int f = getGeneralPurposeRegisterValue(ram, opcode);

        if (((f >> b) & 1) == 1) {
            ram.increaseProgramCounter();
            programTimer.calculateTimer(2, ram);
        } else programTimer.calculateTimer(1, ram);
    }

    private int getB(int opcode) {
        return (opcode & 0b1110000000) >> 7;
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
    }
}
