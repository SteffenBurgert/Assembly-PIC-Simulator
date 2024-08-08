package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.ram_enums.SpecialPurpose;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RamTest {

    private static Ram ram;

    private static final int maxBankSize = 128;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @AfterEach
    public void cleanup() {
        assertThat(ram.getGeneralPurposeRegisterBank0(maxBankSize - 1)).isEqualTo(0);
        assertThatThrownBy(() -> ram.getGeneralPurposeRegisterBank0(maxBankSize)).isInstanceOf(IndexOutOfBoundsException.class);

        assertThat(ram.getGeneralPurposeRegisterBank1(maxBankSize - 1)).isEqualTo(0);
        assertThatThrownBy(() -> ram.getGeneralPurposeRegisterBank1(maxBankSize)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testRamSetup() {
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
    }

    @Test
    public void testGeneralPurposeRegisterBank1() {
        assertThat(ram.getGeneralPurposeRegisterBank0(20)).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank0(20, 31);
        assertThat(ram.getGeneralPurposeRegisterBank0(20)).isEqualTo(31);
    }

    @Test
    public void testGeneralPurposeRegisterBank2() {
        assertThat(ram.getGeneralPurposeRegisterBank1(30)).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank1(30, 50);
        assertThat(ram.getGeneralPurposeRegisterBank1(30)).isEqualTo(50);
    }

    @Test
    public void testWRegister() {
        assertThat(ram.getWRegister()).isEqualTo(0);
        ram.setWRegister(12);
        assertThat(ram.getWRegister()).isEqualTo(12);
    }

    @Test
    public void testProgramCounter() {
        assertThat(ram.getProgramCounter()).isEqualTo(0);
        assertThat(ram.getPCL()).isEqualTo(0);
        assertThat(ram.getPCLATH()).isEqualTo(0);

        ram.increaseProgramCounter();
        assertThat(ram.getProgramCounter()).isEqualTo(1);
        assertThat(ram.getPCL()).isEqualTo(1);
        assertThat(ram.getPCLATH()).isEqualTo(0);

        ram.setProgramCounter(111);
        assertThat(ram.getProgramCounter()).isEqualTo(111);
        assertThat(ram.getPCL()).isEqualTo(111);
        assertThat(ram.getPCLATH()).isEqualTo(0);
    }

    @Test
    public void setProgramCounterCallGoto() {
        assertThat(ram.getProgramCounter()).isEqualTo(0);
        assertThat(ram.getPCL()).isEqualTo(0);
        assertThat(ram.getPCLATH()).isEqualTo(0);

        ram.setProgramCounterCallGoto(1);
        assertThat(ram.getProgramCounter()).isEqualTo(1);
        assertThat(ram.getPCL()).isEqualTo(1);
        assertThat(ram.getPCLATH()).isEqualTo(0);

        ram.setGeneralPurposeRegisterBank0(10, 31);
        assertThat(ram.getProgramCounter()).isEqualTo(1);
        assertThat(ram.getPCL()).isEqualTo(1);
        assertThat(ram.getPCLATH()).isEqualTo(31);

        ram.setProgramCounterCallGoto(967);
        assertThat(ram.getProgramCounter()).isEqualTo(7111);
        assertThat(ram.getPCL()).isEqualTo(199);
        assertThat(ram.getPCLATH()).isEqualTo(31);
    }

    @Test
    public void testTos() {
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
    }

    @Test
    public void testINDF() {
        assertThat(ram.getINDF()).isEqualTo(0);
        ram.setINDF(20);
        assertThat(ram.getINDF()).isEqualTo(20);
    }

    @Test
    public void testPCL() {
        assertThat(ram.getPCL()).isEqualTo(0);
        ram.setPCL(111);
        assertThat(ram.getPCL()).isEqualTo(111);
    }

    @Test
    public void testStatus() {
        assertThat(ram.getStatus()).isEqualTo(24);
        ram.setStatus(40);
        assertThat(ram.getStatus()).isEqualTo(40);
    }

    @Test
    public void testFSR() {
        assertThat(ram.getFSR()).isEqualTo(0);
        ram.setFSR(50);
        assertThat(ram.getFSR()).isEqualTo(50);
    }

    @Test
    public void testPCLATH() {
        assertThat(ram.getPCLATH()).isEqualTo(0);
        ram.setGeneralPurposeRegisterBank0(10, 50);
        assertThat(ram.getPCLATH()).isEqualTo(50);
    }

    @Test
    public void testINTCON() {
        assertThat(ram.getINTCON()).isEqualTo(0);
        ram.setINTCON(60);
        assertThat(ram.getINTCON()).isEqualTo(60);
    }

    /**
     * Register in Bank1 -----------------------------------------------------------------------------------------------
     */

    @Test
    public void testTRM0() {
        assertThat(ram.getTRM0()).isEqualTo(0);
        ram.setTRM0(70);
        assertThat(ram.getTRM0()).isEqualTo(70);
    }

    @Test
    public void testPortA() {
        assertThat(ram.getPortA()).isEqualTo(0);
        ram.setPortA(20);
        assertThat(ram.getPortA()).isEqualTo(20);
    }

    @Test
    public void testPortB() {
        assertThat(ram.getPortB()).isEqualTo(0);
        ram.setPortB(100);
        assertThat(ram.getPortB()).isEqualTo(100);
    }

    @Test
    public void testEEDATA() {
        assertThat(ram.getEEDATA()).isEqualTo(0);
        ram.setEEDATA(55);
        assertThat(ram.getEEDATA()).isEqualTo(55);
    }

    @Test
    public void testEEADR() {
        assertThat(ram.getEEADR()).isEqualTo(0);
        ram.setEEADR(77);
        assertThat(ram.getEEADR()).isEqualTo(77);
    }

    /**
     * Register in Bank2 -----------------------------------------------------------------------------------------------
     */

    @Test
    public void testOPTION_REG() {
        assertThat(ram.getOptionReg()).isEqualTo(255);
        ram.setOptionReg(22);
        assertThat(ram.getOptionReg()).isEqualTo(22);
    }

    @Test
    public void testTrisA() {
        assertThat(ram.getTrisA()).isEqualTo(31);
        ram.setTrisA(11);
        assertThat(ram.getTrisA()).isEqualTo(11);
    }

    @Test
    public void testTrisB() {
        assertThat(ram.getTrisB()).isEqualTo(255);
        ram.setTrisB(35);
        assertThat(ram.getTrisB()).isEqualTo(35);
    }

    @Test
    public void testEECON1() {
        assertThat(ram.getEECON1()).isEqualTo(0);
        ram.setEECON1(42);
        assertThat(ram.getEECON1()).isEqualTo(42);
    }

    @Test
    public void testEECON2() {
        assertThat(ram.getEECON2()).isEqualTo(0);
        ram.setEECON2(65);
        assertThat(ram.getEECON2()).isEqualTo(65);
    }
}