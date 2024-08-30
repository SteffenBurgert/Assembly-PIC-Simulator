package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.ram_enums.Intcon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RamINTCONTest {

    private static Ram ram;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    void testRBIF() {
        assertThat(ram.getINTCON(Intcon.RBIF)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.RBIF, 1);
        assertThat(ram.getINTCON(Intcon.RBIF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(1);

        ram.setINTCON(Intcon.RBIF, 0);
        assertThat(ram.getINTCON(Intcon.RBIF)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(20);
        ram.setINTCON(Intcon.RBIF, 1);
        assertThat(ram.getINTCON(Intcon.RBIF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(21);
    }

    @Test
    void testINTF() {
        assertThat(ram.getINTCON(Intcon.INTF)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.INTF, 1);
        assertThat(ram.getINTCON(Intcon.INTF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(2);

        ram.setINTCON(Intcon.INTF, 0);
        assertThat(ram.getINTCON(Intcon.INTF)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(20);
        ram.setINTCON(Intcon.INTF, 1);
        assertThat(ram.getINTCON(Intcon.INTF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(22);
    }

    @Test
    void testT0IF() {
        assertThat(ram.getINTCON(Intcon.T0IF)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.T0IF, 1);
        assertThat(ram.getINTCON(Intcon.T0IF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(4);

        ram.setINTCON(Intcon.T0IF, 0);
        assertThat(ram.getINTCON(Intcon.T0IF)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(33);
        ram.setINTCON(Intcon.T0IF, 1);
        assertThat(ram.getINTCON(Intcon.T0IF)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(37);
    }

    @Test
    void testRBIE() {
        assertThat(ram.getINTCON(Intcon.RBIE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.RBIE, 1);
        assertThat(ram.getINTCON(Intcon.RBIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(8);

        ram.setINTCON(Intcon.RBIE, 0);
        assertThat(ram.getINTCON(Intcon.RBIE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(70);
        ram.setINTCON(Intcon.RBIE, 1);
        assertThat(ram.getINTCON(Intcon.RBIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(78);
    }

    @Test
    void testINTE() {
        assertThat(ram.getINTCON(Intcon.INTE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.INTE, 1);
        assertThat(ram.getINTCON(Intcon.INTE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(16);

        ram.setINTCON(Intcon.INTE, 0);
        assertThat(ram.getINTCON(Intcon.INTE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(70);
        ram.setINTCON(Intcon.INTE, 1);
        assertThat(ram.getINTCON(Intcon.INTE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(86);
    }

    @Test
    void testT0IE() {
        assertThat(ram.getINTCON(Intcon.T0IE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.T0IE, 1);
        assertThat(ram.getINTCON(Intcon.T0IE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(32);

        ram.setINTCON(Intcon.T0IE, 0);
        assertThat(ram.getINTCON(Intcon.T0IE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(70);
        ram.setINTCON(Intcon.T0IE, 1);
        assertThat(ram.getINTCON(Intcon.T0IE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(102);
    }

    @Test
    void testEEIE() {
        assertThat(ram.getINTCON(Intcon.EEIE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.EEIE, 1);
        assertThat(ram.getINTCON(Intcon.EEIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(64);

        ram.setINTCON(Intcon.EEIE, 0);
        assertThat(ram.getINTCON(Intcon.EEIE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(40);
        ram.setINTCON(Intcon.EEIE, 1);
        assertThat(ram.getINTCON(Intcon.EEIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(104);
    }

    @Test
    void testGIE() {
        assertThat(ram.getINTCON(Intcon.GIE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(Intcon.GIE, 1);
        assertThat(ram.getINTCON(Intcon.GIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(128);

        ram.setINTCON(Intcon.GIE, 0);
        assertThat(ram.getINTCON(Intcon.GIE)).isZero();
        assertThat(ram.getINTCON()).isZero();

        ram.setINTCON(70);
        ram.setINTCON(Intcon.GIE, 1);
        assertThat(ram.getINTCON(Intcon.GIE)).isEqualTo(1);
        assertThat(ram.getINTCON()).isEqualTo(198);
    }
}
