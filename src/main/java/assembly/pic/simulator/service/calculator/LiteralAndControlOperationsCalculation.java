package assembly.pic.simulator.service.calculator;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.timer.ProgramTimer;
import assembly.pic.simulator.akku.timer.WatchDogTimer;
import assembly.pic.simulator.akku.ram_enums.Intcon;
import assembly.pic.simulator.akku.ram_enums.Status;
import assembly.pic.simulator.service.calculator.general.GeneralCalculation;
import assembly.pic.simulator.service.calculator.port.LiteralAndControlOperationsPort;

public class LiteralAndControlOperationsCalculation implements LiteralAndControlOperationsPort {

    private static final GeneralCalculation generalCalculation = new GeneralCalculation();

    @Override
    public void addlw(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get8K(opcode);
        int w = ram.getWRegister();
        int result = generalCalculation.calculateAddOperation(ram, w, k);
        ram.setWRegister(result);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void andlw(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get8K(opcode);
        int w = ram.getWRegister() & k;

        ram.setWRegister(w);
        generalCalculation.setZeroBit(ram, w);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void call(int opcode, Ram ram, ProgramTimer programTimer) throws StackOverflowError {
        int k = get11K(opcode);
        ram.setTos(ram.getProgramCounter());
        ram.setProgramCounterCallGoto(k);
        programTimer.calculateTimer(2, ram);
    }

    @Override
    public void clrwdt(Ram ram, WatchDogTimer watchDogTimer, ProgramTimer programTimer) {
        // TODO: Test
        watchDogTimer.clearWdtCounter();
        watchDogTimer.clearWdtPrescaler();
        ram.setStatus(Status.TIME_OUT_BIT, 1);
        ram.setStatus(Status.POWER_DOWN_BIT, 1);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void gotoAssy(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get11K(opcode);
        ram.setProgramCounterCallGoto(k);
        programTimer.calculateTimer(2, ram);
    }

    @Override
    public void iorlw(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get8K(opcode);
        int w = ram.getWRegister() | k;
        ram.setWRegister(w);
        generalCalculation.setZeroBit(ram, w);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void movlw(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get8K(opcode);
        ram.setWRegister(k);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void retfie(Ram ram, ProgramTimer programTimer) {
        ram.setProgramCounter(ram.getTos());
        ram.setINTCON(Intcon.GIE, 1);
        programTimer.calculateTimer(2, ram);
    }

    @Override
    public void retlw(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get8K(opcode);
        ram.setWRegister(k);
        ram.setProgramCounter(ram.getTos());
        programTimer.calculateTimer(2, ram);
    }

    @Override
    public void returnAssy(Ram ram, ProgramTimer programTimer) {
        ram.setProgramCounter(ram.getTos());
        programTimer.calculateTimer(2, ram);
    }

    @Override
    public void sleep(Ram ram, WatchDogTimer watchDogTimer, ProgramTimer programTimer) {
        watchDogTimer.clearWdtCounter();
        watchDogTimer.clearWdtPrescaler();
        ram.setStatus(Status.TIME_OUT_BIT, 1);
        ram.setStatus(Status.POWER_DOWN_BIT, 0);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void sublw(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get8K(opcode);
        int w = ram.getWRegister();
        int result = generalCalculation.calculateSubOperation(ram, k, w);
        ram.setWRegister(result);
        programTimer.calculateTimer(1, ram);
    }

    @Override
    public void xorlw(int opcode, Ram ram, ProgramTimer programTimer) {
        int k = get8K(opcode);
        int w = ram.getWRegister() ^ k;

        ram.setWRegister(w);
        generalCalculation.setZeroBit(ram, w);
        programTimer.calculateTimer(1, ram);
    }

    private int get8K(int opcode) {
        return opcode & 0b11111111;
    }

    private int get11K(int opcode) {
        return opcode & 0b11111111111;
    }
}
