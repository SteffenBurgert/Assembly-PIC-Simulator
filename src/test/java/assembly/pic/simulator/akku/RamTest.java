package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.ram_enums.SpecialPurpose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RamTest {

    private static Ram ram;

    private static final int MAX_BANK_SIZE = 128;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    private void bankSizeCheck() {
        assertThat(ram.getBank0()).hasSize(MAX_BANK_SIZE);
        assertThat(ram.getBank1()).hasSize(MAX_BANK_SIZE);
    }

    @Test
    void testRamSetup() {
        // STATUS
        assertThat(ram.getStatus()).isEqualTo(24);
        assertThat(ram.getGeneralPurposeRegisterBank0(SpecialPurpose.STATUS.location)).isEqualTo(24);
        assertThat(ram.getGeneralPurposeRegisterBank1(SpecialPurpose.STATUS.location)).isEqualTo(24);
        // OPTION_REG
        assertThat(ram.getOptionReg()).isEqualTo(255);
        // TRIS_A
        assertThat(ram.getTrisA()).isEqualTo(31);
        // TRIS_B
        assertThat(ram.getTrisB()).isEqualTo(255);
        bankSizeCheck();
    }

    @Test
    void testGeneralPurposeRegisterBank1() {
        assertThat(ram.getGeneralPurposeRegisterBank0(20)).isZero();
        ram.setGeneralPurposeRegisterBank0(20, 31);
        assertThat(ram.getGeneralPurposeRegisterBank0(20)).isEqualTo(31);
        bankSizeCheck();
    }

    @Test
    void testGeneralPurposeRegisterBank2() {
        assertThat(ram.getGeneralPurposeRegisterBank1(30)).isZero();
        ram.setGeneralPurposeRegisterBank1(30, 50);
        assertThat(ram.getGeneralPurposeRegisterBank1(30)).isEqualTo(50);
        bankSizeCheck();
    }

    @Test
    void testWRegister() {
        assertThat(ram.getWRegister()).isZero();
        ram.setWRegister(12);
        assertThat(ram.getWRegister()).isEqualTo(12);
        bankSizeCheck();
    }

    @Test
    void testProgramCounter() {
        assertThat(ram.getProgramCounter()).isZero();
        assertThat(ram.getPCL()).isZero();
        assertThat(ram.getPCLATH()).isZero();

        ram.increaseProgramCounter();
        assertThat(ram.getProgramCounter()).isEqualTo(1);
        assertThat(ram.getPCL()).isEqualTo(1);
        assertThat(ram.getPCLATH()).isZero();

        ram.setProgramCounter(111);
        assertThat(ram.getProgramCounter()).isEqualTo(111);
        assertThat(ram.getPCL()).isEqualTo(111);
        assertThat(ram.getPCLATH()).isZero();
        bankSizeCheck();
    }

    @Test
    void setProgramCounterCallGoto() {
        assertThat(ram.getProgramCounter()).isZero();
        assertThat(ram.getPCL()).isZero();
        assertThat(ram.getPCLATH()).isZero();

        ram.setProgramCounterCallGoto(1);
        assertThat(ram.getProgramCounter()).isEqualTo(1);
        assertThat(ram.getPCL()).isEqualTo(1);
        assertThat(ram.getPCLATH()).isZero();

        ram.setGeneralPurposeRegisterBank0(10, 31);
        assertThat(ram.getProgramCounter()).isEqualTo(1);
        assertThat(ram.getPCL()).isEqualTo(1);
        assertThat(ram.getPCLATH()).isEqualTo(31);

        ram.setProgramCounterCallGoto(967);
        assertThat(ram.getProgramCounter()).isEqualTo(7111);
        assertThat(ram.getPCL()).isEqualTo(199);
        assertThat(ram.getPCLATH()).isEqualTo(31);
        bankSizeCheck();
    }

    @Test
    void testTos() {
        assertThatThrownBy(() -> ram.getTos()).isInstanceOf(IndexOutOfBoundsException.class);
        ram.setTos(33);
        assertThat(ram.getTos()).isEqualTo(33);
        assertThatThrownBy(() -> ram.getTos()).isInstanceOf(IndexOutOfBoundsException.class);

        ram.setTos(55);
        ram.setTos(35);
        ram.setTos(66);
        assertThat(ram.getTos()).isEqualTo(66);
        assertThat(ram.getTos()).isEqualTo(35);
        assertThat(ram.getTos()).isEqualTo(55);

        ram.setTos(0);
        ram.setTos(1);
        ram.setTos(2);
        ram.setTos(3);
        ram.setTos(4);
        ram.setTos(5);
        ram.setTos(6);
        ram.setTos(7);
        ram.setTos(8);

        for (int i = 0; i < 2; i++) {
            for (int j = 8; j > 0; j--) {
                assertThat(ram.getTos()).isEqualTo(j);
            }
        }
        assertThat(ram.getTos()).isEqualTo(8);

        ram.setTos(20);
        assertThat(ram.getTos()).isEqualTo(20);
        assertThat(ram.getTos()).isEqualTo(8);
        bankSizeCheck();
    }

    @Test
    void testINDF() {
        assertThat(ram.getINDF()).isZero();
        ram.setINDF(20);
        assertThat(ram.getINDF()).isEqualTo(20);
        bankSizeCheck();
    }

    @Test
    void testPCL() {
        assertThat(ram.getPCL()).isZero();
        ram.setPCL(111);
        assertThat(ram.getPCL()).isEqualTo(111);
        bankSizeCheck();
    }

    @Test
    void testStatus() {
        assertThat(ram.getStatus()).isEqualTo(24);
        ram.setStatus(40);
        assertThat(ram.getStatus()).isEqualTo(40);
        bankSizeCheck();
    }

    @Test
    void testFSR() {
        assertThat(ram.getFSR()).isZero();
        ram.setFSR(50);
        assertThat(ram.getFSR()).isEqualTo(50);
        bankSizeCheck();
    }

    @Test
    void testPCLATH() {
        assertThat(ram.getPCLATH()).isZero();
        ram.setGeneralPurposeRegisterBank0(10, 50);
        assertThat(ram.getPCLATH()).isEqualTo(50);
        bankSizeCheck();
    }

    @Test
    void testINTCON() {
        assertThat(ram.getINTCON()).isZero();
        ram.setINTCON(60);
        assertThat(ram.getINTCON()).isEqualTo(60);
        bankSizeCheck();
    }

    /**
     * Register in Bank0 -----------------------------------------------------------------------------------------------
     */

    @Test
    void testTRM0() {
        assertThat(ram.getTRM0()).isZero();
        ram.setTRM0(70);
        assertThat(ram.getTRM0()).isEqualTo(70);
        bankSizeCheck();
    }

    @Test
    void testPortA() {
        assertThat(ram.getPortA()).isZero();
        ram.setPortA(20);
        assertThat(ram.getPortA()).isEqualTo(20);
        bankSizeCheck();
    }

    @Test
    void testPortB() {
        assertThat(ram.getPortB()).isZero();
        ram.setPortB(100);
        assertThat(ram.getPortB()).isEqualTo(100);
        bankSizeCheck();
    }

    @Test
    void testEEDATA() {
        assertThat(ram.getEEDATA()).isZero();
        ram.setEEDATA(55);
        assertThat(ram.getEEDATA()).isEqualTo(55);
        bankSizeCheck();
    }

    @Test
    void testEEADR() {
        assertThat(ram.getEEADR()).isZero();
        ram.setEEADR(77);
        assertThat(ram.getEEADR()).isEqualTo(77);
        bankSizeCheck();
    }

    /**
     * Register in Bank1 -----------------------------------------------------------------------------------------------
     */

    @Test
    void testOPTION_REG() {
        assertThat(ram.getOptionReg()).isEqualTo(255);
        ram.setOptionReg(22);
        assertThat(ram.getOptionReg()).isEqualTo(22);
        bankSizeCheck();
    }

    @Test
    void testTrisA() {
        assertThat(ram.getTrisA()).isEqualTo(31);
        ram.setTrisA(11);
        assertThat(ram.getTrisA()).isEqualTo(11);
        bankSizeCheck();
    }

    @Test
    void testTrisB() {
        assertThat(ram.getTrisB()).isEqualTo(255);
        ram.setTrisB(35);
        assertThat(ram.getTrisB()).isEqualTo(35);
        bankSizeCheck();
    }

    @Test
    void testEECON1() {
        assertThat(ram.getEECON1()).isZero();
        ram.setEECON1(42);
        assertThat(ram.getEECON1()).isEqualTo(42);
        bankSizeCheck();
    }

    @Test
    void testEECON2() {
        assertThat(ram.getEECON2()).isZero();
        ram.setEECON2(65);
        assertThat(ram.getEECON2()).isEqualTo(65);
        bankSizeCheck();
    }
}