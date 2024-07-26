package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.Intcon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RamINTCONTest {

    private static Ram ram;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    public void testRBIF() {
        assertThat(ram.getINTCON(Intcon.RBIF)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.RBIF, 1);
        assertThat(ram.getINTCON(Intcon.RBIF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(1);

        ram.setINTCON(Intcon.RBIF, 0);
        assertThat(ram.getINTCON(Intcon.RBIF)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(20);
        ram.setINTCON(Intcon.RBIF, 1);
        assertThat(ram.getINTCON(Intcon.RBIF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(21);
    }

    @Test
    public void testINTF() {
        assertThat(ram.getINTCON(Intcon.INTF)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.INTF, 1);
        assertThat(ram.getINTCON(Intcon.INTF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(2);

        ram.setINTCON(Intcon.INTF, 0);
        assertThat(ram.getINTCON(Intcon.INTF)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(20);
        ram.setINTCON(Intcon.INTF, 1);
        assertThat(ram.getINTCON(Intcon.INTF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(22);
    }

    @Test
    public void testT0IF() {
        assertThat(ram.getINTCON(Intcon.T0IF)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.T0IF, 1);
        assertThat(ram.getINTCON(Intcon.T0IF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(4);

        ram.setINTCON(Intcon.T0IF, 0);
        assertThat(ram.getINTCON(Intcon.T0IF)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(33);
        ram.setINTCON(Intcon.T0IF, 1);
        assertThat(ram.getINTCON(Intcon.T0IF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(37);
    }

    @Test
    public void testRBIE() {
        assertThat(ram.getINTCON(Intcon.RBIE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.RBIE, 1);
        assertThat(ram.getINTCON(Intcon.RBIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(8);

        ram.setINTCON(Intcon.RBIE, 0);
        assertThat(ram.getINTCON(Intcon.RBIE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(70);
        ram.setINTCON(Intcon.RBIE, 1);
        assertThat(ram.getINTCON(Intcon.RBIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(78);
    }

    @Test
    public void testINTE() {
        assertThat(ram.getINTCON(Intcon.INTE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.INTE, 1);
        assertThat(ram.getINTCON(Intcon.INTE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(16);

        ram.setINTCON(Intcon.INTE, 0);
        assertThat(ram.getINTCON(Intcon.INTE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(70);
        ram.setINTCON(Intcon.INTE, 1);
        assertThat(ram.getINTCON(Intcon.INTE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(86);
    }

    @Test
    public void testT0IE() {
        assertThat(ram.getINTCON(Intcon.T0IE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.T0IE, 1);
        assertThat(ram.getINTCON(Intcon.T0IE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(32);

        ram.setINTCON(Intcon.T0IE, 0);
        assertThat(ram.getINTCON(Intcon.T0IE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(70);
        ram.setINTCON(Intcon.T0IE, 1);
        assertThat(ram.getINTCON(Intcon.T0IE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(102);
    }

    @Test
    public void testEEIE() {
        assertThat(ram.getINTCON(Intcon.EEIE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.EEIE, 1);
        assertThat(ram.getINTCON(Intcon.EEIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(64);

        ram.setINTCON(Intcon.EEIE, 0);
        assertThat(ram.getINTCON(Intcon.EEIE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(40);
        ram.setINTCON(Intcon.EEIE, 1);
        assertThat(ram.getINTCON(Intcon.EEIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(104);
    }

    @Test
    public void testGIE() {
        assertThat(ram.getINTCON(Intcon.GIE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(Intcon.GIE, 1);
        assertThat(ram.getINTCON(Intcon.GIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(128);

        ram.setINTCON(Intcon.GIE, 0);
        assertThat(ram.getINTCON(Intcon.GIE)).isEqualTo(0);
        assertThat(ram.getINTCON()).isEqualTo(0);

        ram.setINTCON(70);
        ram.setINTCON(Intcon.GIE, 1);
        assertThat(ram.getINTCON(Intcon.GIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(198);
    }
}
