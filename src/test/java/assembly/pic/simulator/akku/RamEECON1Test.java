package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.EECON1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RamEECON1Test {

    private static Ram ram;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    public void testRD() {
        assertThat(ram.getEECON1(EECON1.RD)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(EECON1.RD, 1);
        assertThat(ram.getEECON1(EECON1.RD)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(1);

        ram.setEECON1(EECON1.RD, 0);
        assertThat(ram.getEECON1(EECON1.RD)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(20);
        ram.setEECON1(EECON1.RD, 1);
        assertThat(ram.getEECON1(EECON1.RD)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(21);
    }

    @Test
    public void testWR() {
        assertThat(ram.getEECON1(EECON1.WR)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(EECON1.WR, 1);
        assertThat(ram.getEECON1(EECON1.WR)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(2);

        ram.setEECON1(EECON1.WR, 0);
        assertThat(ram.getEECON1(EECON1.WR)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(20);
        ram.setEECON1(EECON1.WR, 1);
        assertThat(ram.getEECON1(EECON1.WR)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(22);
    }

    @Test
    public void testWREN() {
        assertThat(ram.getEECON1(EECON1.WREN)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(EECON1.WREN, 1);
        assertThat(ram.getEECON1(EECON1.WREN)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(4);

        ram.setEECON1(EECON1.WREN, 0);
        assertThat(ram.getEECON1(EECON1.WREN)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(33);
        ram.setEECON1(EECON1.WREN, 1);
        assertThat(ram.getEECON1(EECON1.WREN)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(37);
    }

    @Test
    public void testWRERR() {
        assertThat(ram.getEECON1(EECON1.WRERR)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(EECON1.WRERR, 1);
        assertThat(ram.getEECON1(EECON1.WRERR)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(8);

        ram.setEECON1(EECON1.WRERR, 0);
        assertThat(ram.getEECON1(EECON1.WRERR)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(70);
        ram.setEECON1(EECON1.WRERR, 1);
        assertThat(ram.getEECON1(EECON1.WRERR)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(78);
    }

    @Test
    public void testEEIF() {
        assertThat(ram.getEECON1(EECON1.EEIF)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(EECON1.EEIF, 1);
        assertThat(ram.getEECON1(EECON1.EEIF)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(16);

        ram.setEECON1(EECON1.EEIF, 0);
        assertThat(ram.getEECON1(EECON1.EEIF)).isEqualTo(0);
        assertThat(ram.getEECON1()).isEqualTo(0);

        ram.setEECON1(70);
        ram.setEECON1(EECON1.EEIF, 1);
        assertThat(ram.getEECON1(EECON1.EEIF)).isEqualTo(1);
        assertThat(ram.getEECON1()).isEqualTo(86);
    }
}
