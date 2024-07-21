package service.calculator;

import akku.AssemblyFile;
import akku.Ram;
import akku.ram_enums.Intcon;
import akku.ram_enums.Status;
import akku.timer.ProgramTimer;
import akku.timer.WatchDogTimer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LiteralAndControlOperationsCalculationTest {

    private final LiteralAndControlOperationsCalculation literalAndControlOperationsCalculation = new LiteralAndControlOperationsCalculation();
    private Ram ram;

    private ProgramTimer programTimer = new ProgramTimer(4);

    @BeforeEach
    void setup() {
        ram = new Ram();
        programTimer = new ProgramTimer(4);
    }


    /**
     * ADDLW
     * <p>
     * Status affected: Carry-Bit, DC-Bit, Zero-Bit
     */
    @Test
    void testAddlwSmaller255() {
        literalAndControlOperationsCalculation.addlw(100, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(100);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    @Test
    void testAddlwHigher255() {
        ram.setWRegister(200);
        literalAndControlOperationsCalculation.addlw(100, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(44);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    @Test
    void testAddlwWithCarry1() {
        ram.setWRegister(44);
        ram.setStatus(Status.CARRY_BIT, 1);
        literalAndControlOperationsCalculation.addlw(100, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(144);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    @Test
    void testAddlwWithDC0() {
        literalAndControlOperationsCalculation.addlw(4, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(4);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    @Test
    void testAddlwWithZeroBit1() {
        literalAndControlOperationsCalculation.addlw(0, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    /**
     * ANDLW
     * <p>
     * Status affected: Zero-Bit
     */
    @Test
    void andlwWithZeroBit0() {
        literalAndControlOperationsCalculation.andlw(12, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    @Test
    void andlwWithZeroBit1() {
        ram.setWRegister(12);
        literalAndControlOperationsCalculation.andlw(10, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(8);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    /**
     * CALL
     */
    @Test
    void call() {
        ram.setProgramCounter(20);

        // first 11 Bit of 5526 equals 1430 in decimal
        literalAndControlOperationsCalculation.call(5526, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1430);
        assertThat(ram.getTos()).isEqualTo(20);
        assertThat(programTimer.getTimer()).isEqualTo(0.5);
    }

    /**
     * CLRWDT
     * <p>
     * Status affected: !TO, !PD
     */
    @Test
    void clrwdt() {
        // TODO: test
        WatchDogTimer watchDogTimer = new WatchDogTimer();
        literalAndControlOperationsCalculation.clrwdt(ram, watchDogTimer, programTimer);

        assertThat(programTimer.getTimer()).isEqualTo(0.25);
    }

    /**
     * GOTO
     */
    @Test
    void gotoAssy() {
        ram.setProgramCounter(20);
        AssemblyFile assemblyFile = new AssemblyFile();
        assemblyFile.setFile(List.of("some", "random", "value", "goto ende"));

        // first 11 Bit of 5526 equals 1430 in decimal
        literalAndControlOperationsCalculation.gotoAssy(5526, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1430);
        assertThat(programTimer.getTimer()).isEqualTo(0.5);
    }

    /**
     * IORLW
     * <p>
     * Status affected: Zero-Bit
     */
    @Test
    void iorlwWithZeroBit0() {
        literalAndControlOperationsCalculation.iorlw(0, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
    }

    @Test
    void iorlwWithZeroBit1() {
        ram.setWRegister(1);
        literalAndControlOperationsCalculation.iorlw(12, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(13);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
    }

    /**
     * MOVLW
     */
    @Test
    void movlw() {
        literalAndControlOperationsCalculation.movlw(12, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(12);

        literalAndControlOperationsCalculation.movlw(20, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(20);
    }

    /**
     * RETFIE
     */
    @Test
    void retfie() {
        ram.setProgramCounter(33);
        ram.setTos(12);

        literalAndControlOperationsCalculation.retfie(ram, programTimer);

        assertThat(ram.getProgramCounter()).isEqualTo(12);
        assertThat(ram.getINTCON(Intcon.GIE)).isEqualTo(1);
        assertThatThrownBy(() -> ram.getTos()).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * RETLW
     */
    @Test
    void retlw() {
        ram.setTos(23);

        literalAndControlOperationsCalculation.retlw(230, ram, programTimer);

        assertThat(ram.getProgramCounter()).isEqualTo(23);
        assertThat(ram.getWRegister()).isEqualTo(230);
        assertThatThrownBy(() -> ram.getTos()).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * RETURN
     */
    @Test
    void returnAssy() {
        ram.setTos(55);

        literalAndControlOperationsCalculation.returnAssy(ram, programTimer);

        assertThat(ram.getProgramCounter()).isEqualTo(55);
        assertThatThrownBy(() -> ram.getTos()).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * SLEEP
     * <p>
     * Status affected: !TO, !PD
     */
    @Test
    void sleep() {
    }

    /**
     * SUBLW
     * <p>
     * Status affected: Carry-Bit, DC-Bit, Zero-Bit
     */
    @Test
    void sublw() {
    }

    /**
     * XORLW
     * <p>
     * Status affected: Zero-Bit
     */
    @Test
    void xorlw() {
    }
}