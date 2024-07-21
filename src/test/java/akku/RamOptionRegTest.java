package akku;

import akku.ram_enums.OptionReg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RamOptionRegTest {

    private static Ram ram;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    public void testPS0() {
        assertThat(ram.getOptionReg(OptionReg.PS0)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PS0, 1);
        assertThat(ram.getOptionReg(OptionReg.PS0)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PS0, 0);
        assertThat(ram.getOptionReg(OptionReg.PS0)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(254);

        ram.setOptionReg(20);
        ram.setOptionReg(OptionReg.PS0, 1);
        assertThat(ram.getOptionReg(OptionReg.PS0)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(21);
    }

    @Test
    public void testPS1() {
        assertThat(ram.getOptionReg(OptionReg.PS1)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PS1, 1);
        assertThat(ram.getOptionReg(OptionReg.PS1)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PS1, 0);
        assertThat(ram.getOptionReg(OptionReg.PS1)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(253);

        ram.setOptionReg(20);
        ram.setOptionReg(OptionReg.PS1, 1);
        assertThat(ram.getOptionReg(OptionReg.PS1)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(22);
    }

    @Test
    public void testPS2() {
        assertThat(ram.getOptionReg(OptionReg.PS2)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PS2, 1);
        assertThat(ram.getOptionReg(OptionReg.PS2)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PS2, 0);
        assertThat(ram.getOptionReg(OptionReg.PS2)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(251);

        ram.setOptionReg(33);
        ram.setOptionReg(OptionReg.PS2, 1);
        assertThat(ram.getOptionReg(OptionReg.PS2)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(37);
    }

    @Test
    public void testPSA() {
        assertThat(ram.getOptionReg(OptionReg.PSA)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PSA, 1);
        assertThat(ram.getOptionReg(OptionReg.PSA)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.PSA, 0);
        assertThat(ram.getOptionReg(OptionReg.PSA)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(247);

        ram.setOptionReg(70);
        ram.setOptionReg(OptionReg.PSA, 1);
        assertThat(ram.getOptionReg(OptionReg.PSA)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(78);
    }

    @Test
    public void testT0SE() {
        assertThat(ram.getOptionReg(OptionReg.T0SE)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.T0SE, 1);
        assertThat(ram.getOptionReg(OptionReg.T0SE)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.T0SE, 0);
        assertThat(ram.getOptionReg(OptionReg.T0SE)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(239);

        ram.setOptionReg(70);
        ram.setOptionReg(OptionReg.T0SE, 1);
        assertThat(ram.getOptionReg(OptionReg.T0SE)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(86);
    }

    @Test
    public void testT0CS() {
        assertThat(ram.getOptionReg(OptionReg.T0CS)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.T0CS, 1);
        assertThat(ram.getOptionReg(OptionReg.T0CS)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.T0CS, 0);
        assertThat(ram.getOptionReg(OptionReg.T0CS)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(223);

        ram.setOptionReg(70);
        ram.setOptionReg(OptionReg.T0CS, 1);
        assertThat(ram.getOptionReg(OptionReg.T0CS)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(102);
    }

    @Test
    public void testINTEDG() {
        assertThat(ram.getOptionReg(OptionReg.INTEDG)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.INTEDG, 1);
        assertThat(ram.getOptionReg(OptionReg.INTEDG)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.INTEDG, 0);
        assertThat(ram.getOptionReg(OptionReg.INTEDG)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(191);

        ram.setOptionReg(40);
        ram.setOptionReg(OptionReg.INTEDG, 1);
        assertThat(ram.getOptionReg(OptionReg.INTEDG)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(104);
    }

    @Test
    public void testRBPU() {
        assertThat(ram.getOptionReg(OptionReg.RBPU)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.RBPU, 1);
        assertThat(ram.getOptionReg(OptionReg.RBPU)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(255);

        ram.setOptionReg(OptionReg.RBPU, 0);
        assertThat(ram.getOptionReg(OptionReg.RBPU)).isEqualTo(0);
        assertThat(ram.getOptionReg()).isEqualTo(127);

        ram.setOptionReg(70);
        ram.setOptionReg(OptionReg.RBPU, 1);
        assertThat(ram.getOptionReg(OptionReg.RBPU)).isEqualTo(1);
        assertThat(ram.getOptionReg()).isEqualTo(198);
    }
}
