package assembly.pic.simulator.akku.service.calculator;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.Status;
import assembly.pic.simulator.akku.timer.ProgramTimer;
import assembly.pic.simulator.service.calculator.ByteOrientedFileRegisterCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class ByteOrientedFileRegisterCalculationTest {

    private final ByteOrientedFileRegisterCalculation byteOrientedFileRegisterCalculation = new ByteOrientedFileRegisterCalculation();
    private Ram ram;

    private ProgramTimer programTimer = new ProgramTimer(4);

    @BeforeEach
    void setup() {
        ram = new Ram();
        programTimer = new ProgramTimer(4);
    }

    @Test
    void addwf() {
        ram.setWRegister(20);
        ram.setGeneralPurposeRegisterBank1(13, 50);
        byteOrientedFileRegisterCalculation.addwf(13, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(70);
        assertThat(ram.getGeneralPurposeRegisterBank1(13)).isEqualTo(50);

        ram.setWRegister(60);
        ram.setGeneralPurposeRegisterBank1(13, 200);
        byteOrientedFileRegisterCalculation.addwf(141, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(60);
        assertThat(ram.getGeneralPurposeRegisterBank1(13)).isEqualTo(4);
    }

    @Test
    void andwf() {
        // TODO: test Zero bit
        ram.setWRegister(30);
        ram.setGeneralPurposeRegisterBank1(22, 11);
        byteOrientedFileRegisterCalculation.andwf(22, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(10);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(11);

        ram.setWRegister(30);
        ram.setGeneralPurposeRegisterBank1(22, 11);
        byteOrientedFileRegisterCalculation.andwf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(30);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(10);

        ram.setWRegister(0x25);
        ram.setGeneralPurposeRegisterBank1(22, 0x36);
        byteOrientedFileRegisterCalculation.andwf(22, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0x24);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(0x36);
    }

    @Test
    void clrf() {
        ram.setWRegister(20);
        ram.setGeneralPurposeRegisterBank1(23, 40);
        byteOrientedFileRegisterCalculation.clrf(23, ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(20);
        assertThat(ram.getGeneralPurposeRegisterBank1(23)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
    }

    @Test
    void clrw() {
        ram.setWRegister(26);
        ram.setGeneralPurposeRegisterBank1(25, 43);
        byteOrientedFileRegisterCalculation.clrw(ram, programTimer);

        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(25)).isEqualTo(43);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
    }

    @Test
    void comf() {
        //TODO: test zero bit
        ram.setWRegister(12);
        ram.setGeneralPurposeRegisterBank1(33, 66);

        byteOrientedFileRegisterCalculation.comf(33, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(189);
        assertThat(ram.getGeneralPurposeRegisterBank1(33)).isEqualTo(66);

        ram.setWRegister(12);
        ram.setGeneralPurposeRegisterBank1(33, 66);
        byteOrientedFileRegisterCalculation.comf(161, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(12);
        assertThat(ram.getGeneralPurposeRegisterBank1(33)).isEqualTo(189);
    }

    @Test
    void decf() {
        assertThat(ram.getWRegister()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(22, 2);

        byteOrientedFileRegisterCalculation.decf(22, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(2);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        byteOrientedFileRegisterCalculation.decf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        byteOrientedFileRegisterCalculation.decf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);

        byteOrientedFileRegisterCalculation.decf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(255);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
    }

    @Test
    void decfsz() {
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getProgramCounter()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(22, 2);

        byteOrientedFileRegisterCalculation.decfsz(22, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(2);
        assertThat(ram.getProgramCounter()).isEqualTo(0);

        byteOrientedFileRegisterCalculation.decfsz(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(1);
        assertThat(ram.getProgramCounter()).isEqualTo(0);

        byteOrientedFileRegisterCalculation.decfsz(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(0);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        byteOrientedFileRegisterCalculation.decfsz(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(1);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(255);
        assertThat(ram.getProgramCounter()).isEqualTo(1);
    }

    @Test
    void incf() {
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getProgramCounter()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(22, 255);

        byteOrientedFileRegisterCalculation.incf(22, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(255);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);

        byteOrientedFileRegisterCalculation.incf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);

        byteOrientedFileRegisterCalculation.incf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        byteOrientedFileRegisterCalculation.incf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(2);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
    }

    @Test
    void incfsz() {
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getProgramCounter()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(22, 255);

        byteOrientedFileRegisterCalculation.incfsz(22, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(255);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        byteOrientedFileRegisterCalculation.incfsz(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(0);
        assertThat(ram.getProgramCounter()).isEqualTo(2);

        byteOrientedFileRegisterCalculation.incfsz(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(1);
        assertThat(ram.getProgramCounter()).isEqualTo(2);

        byteOrientedFileRegisterCalculation.incfsz(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(2);
        assertThat(ram.getProgramCounter()).isEqualTo(2);
    }

    @Test
    void iorwf() {
        ram.setWRegister(30);
        ram.setGeneralPurposeRegisterBank1(34, 55);

        byteOrientedFileRegisterCalculation.iorwf(34, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(34)).isEqualTo(55);
        assertThat(ram.getWRegister()).isEqualTo(63);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        ram.setWRegister(34);
        ram.setGeneralPurposeRegisterBank1(34, 61);

        byteOrientedFileRegisterCalculation.iorwf(162, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(34)).isEqualTo(63);
        assertThat(ram.getWRegister()).isEqualTo(34);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        ram.setWRegister(0);
        ram.setGeneralPurposeRegisterBank1(10, 0);

        byteOrientedFileRegisterCalculation.iorwf(10, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(10)).isEqualTo(0);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
    }

    @Test
    void movf() {
        assertThat(ram.getWRegister()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(27, 20);
        byteOrientedFileRegisterCalculation.movf(27, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(20);
        assertThat(ram.getGeneralPurposeRegisterBank1(27)).isEqualTo(20);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        ram.setGeneralPurposeRegisterBank1(23, 50);
        byteOrientedFileRegisterCalculation.movf(151, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(20);
        assertThat(ram.getGeneralPurposeRegisterBank1(23)).isEqualTo(50);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        ram.setGeneralPurposeRegisterBank1(27, 0);
        byteOrientedFileRegisterCalculation.movf(27, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
        assertThat(ram.getGeneralPurposeRegisterBank1(27)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
    }

    @Test
    void movwf() {
        ram.setWRegister(12);
        byteOrientedFileRegisterCalculation.movwf(20, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(20)).isEqualTo(12);

        ram.setWRegister(0x11);
        byteOrientedFileRegisterCalculation.movwf(0xc, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(0xc)).isEqualTo(0x11);
    }

    @Test
    void nop() {
        byteOrientedFileRegisterCalculation.nop(0, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0);
    }

    @Test
    void rlf() {
        ram.setGeneralPurposeRegister(0x11, 0x21);
        ram.setStatus(Status.CARRY_BIT, 1);
        byteOrientedFileRegisterCalculation.rlf(0x11, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0x43);

        ram.setGeneralPurposeRegister(0x11, 0x21);
        ram.setStatus(Status.CARRY_BIT, 0);
        byteOrientedFileRegisterCalculation.rlf(0x11, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0x42);
    }

    @Test
    void rrf() {
        ram.setGeneralPurposeRegister(0x11, 0x21);
        ram.setStatus(Status.CARRY_BIT, 1);
        byteOrientedFileRegisterCalculation.rrf(0x11, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0x90);

        ram.setGeneralPurposeRegister(0x11, 0x21);
        ram.setStatus(Status.CARRY_BIT, 0);
        byteOrientedFileRegisterCalculation.rrf(0x11, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0x10);
    }

    @Test
    void subwf() {
        ram.setWRegister(200);
        ram.setGeneralPurposeRegisterBank1(13, 100);
        byteOrientedFileRegisterCalculation.subwf(13, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(156);
        assertThat(ram.getGeneralPurposeRegisterBank1(13)).isEqualTo(100);

        ram.setWRegister(60);
        ram.setGeneralPurposeRegisterBank1(13, 200);
        byteOrientedFileRegisterCalculation.subwf(141, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(60);
        assertThat(ram.getGeneralPurposeRegisterBank1(13)).isEqualTo(140);
    }

    @Test
    void swapf() {
        ram.setGeneralPurposeRegisterBank1(23, 0x25);

        byteOrientedFileRegisterCalculation.swapf(23, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0x52);
        assertThat(ram.getGeneralPurposeRegisterBank1(23)).isEqualTo(0x25);

        byteOrientedFileRegisterCalculation.swapf(151, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(0x52);
        assertThat(ram.getGeneralPurposeRegisterBank1(23)).isEqualTo(0x52);
    }

    @Test
    void xorwf() {
        ram.setWRegister(30);
        ram.setGeneralPurposeRegisterBank1(22, 40);

        byteOrientedFileRegisterCalculation.xorwf(22, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(54);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(40);

        ram.setWRegister(21);
        ram.setGeneralPurposeRegisterBank1(22, 33);

        byteOrientedFileRegisterCalculation.xorwf(150, ram, programTimer);
        assertThat(ram.getWRegister()).isEqualTo(21);
        assertThat(ram.getGeneralPurposeRegisterBank1(22)).isEqualTo(52);
    }
}