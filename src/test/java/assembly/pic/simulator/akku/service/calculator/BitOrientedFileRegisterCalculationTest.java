package assembly.pic.simulator.akku.service.calculator;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.timer.ProgramTimer;
import assembly.pic.simulator.service.calculator.BitOrientedFileRegisterCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BitOrientedFileRegisterCalculationTest {

    private final BitOrientedFileRegisterCalculation bitOrientedFileRegisterCalculation = new BitOrientedFileRegisterCalculation();
    private Ram ram;

    private ProgramTimer programTimer = new ProgramTimer(4);

    @BeforeEach
    void setup() {
        ram = new Ram();
        programTimer = new ProgramTimer(4);
    }

    @Test
    void bcf() {
        ram.setGeneralPurposeRegisterBank1(28, 43);
        bitOrientedFileRegisterCalculation.bcf(412, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(28)).isEqualTo(35);

        ram.setGeneralPurposeRegisterBank1(30, 27);
        bitOrientedFileRegisterCalculation.bcf(414, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(30)).isEqualTo(19);

        ram.setGeneralPurposeRegisterBank1(28, 129);
        bitOrientedFileRegisterCalculation.bcf(924, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(28)).isEqualTo(1);
    }

    @Test
    void bsf() {
        ram.setGeneralPurposeRegisterBank1(30, 27);
        bitOrientedFileRegisterCalculation.bsf(414, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(30)).isEqualTo(27);

        ram.setGeneralPurposeRegisterBank1(28, 30);
        bitOrientedFileRegisterCalculation.bsf(412, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(28)).isEqualTo(30);

        ram.setGeneralPurposeRegisterBank1(28, 16);
        bitOrientedFileRegisterCalculation.bsf(412, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(28)).isEqualTo(24);

        ram.setGeneralPurposeRegisterBank1(28, 30);
        bitOrientedFileRegisterCalculation.bsf(924, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(28)).isEqualTo(158);

        ram.setGeneralPurposeRegisterBank1(0x10, 0x08);
        bitOrientedFileRegisterCalculation.bsf(912, ram, programTimer);
        assertThat(ram.getGeneralPurposeRegisterBank1(0x10)).isEqualTo(0x88);
    }

    @Test
    void btfsc() {
        assertThat(ram.getProgramCounter()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(20, 10);
        bitOrientedFileRegisterCalculation.btfsc(20, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        ram.setGeneralPurposeRegisterBank1(20, 11);
        bitOrientedFileRegisterCalculation.btfsc(20, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        ram.setGeneralPurposeRegisterBank1(20, 30);
        bitOrientedFileRegisterCalculation.btfsc(404, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        ram.setGeneralPurposeRegisterBank1(20, 21);
        bitOrientedFileRegisterCalculation.btfsc(404, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(2);
    }

    @Test
    void btfss() {
        assertThat(ram.getProgramCounter()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(20, 11);
        bitOrientedFileRegisterCalculation.btfss(20, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        ram.setGeneralPurposeRegisterBank1(20, 10);
        bitOrientedFileRegisterCalculation.btfss(20, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        ram.setGeneralPurposeRegisterBank1(20, 22);
        bitOrientedFileRegisterCalculation.btfss(404, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(1);

        ram.setGeneralPurposeRegisterBank1(20, 26);
        bitOrientedFileRegisterCalculation.btfss(404, ram, programTimer);
        assertThat(ram.getProgramCounter()).isEqualTo(2);
    }
}