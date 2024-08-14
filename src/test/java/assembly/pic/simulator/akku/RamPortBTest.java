package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.ram_enums.PortB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RamPortBTest {

    private static Ram ram;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    void testRB0_INT() {
        assertThat(ram.getPortB(PortB.RB0_INT)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB0_INT, 0);
        ram.setPortB(PortB.RB0_INT, 1);
        assertThat(ram.getPortB(PortB.RB0_INT)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(1);

        ram.setPortB(PortB.RB0_INT, 0);
        assertThat(ram.getPortB(PortB.RB0_INT)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(20);
        ram.setPortB(PortB.RB0_INT, 1);
        assertThat(ram.getPortB(PortB.RB0_INT)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(21);
    }

    @Test
    void testRB1() {
        assertThat(ram.getPortB(PortB.RB1)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB1, 0);
        ram.setPortB(PortB.RB1, 1);
        assertThat(ram.getTrisB(PortB.RB1)).isZero();
        assertThat(ram.getPortB(PortB.RB1)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(2);

        ram.setPortB(PortB.RB1, 0);
        assertThat(ram.getPortB(PortB.RB1)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(20);
        ram.setPortB(PortB.RB1, 1);
        assertThat(ram.getPortB(PortB.RB1)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(22);
    }

    @Test
    void testRB2() {
        assertThat(ram.getPortB(PortB.RB2)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB2, 0);
        ram.setPortB(PortB.RB2, 1);
        assertThat(ram.getPortB(PortB.RB2)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(4);

        ram.setPortB(PortB.RB2, 0);
        assertThat(ram.getPortB(PortB.RB2)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(33);
        ram.setPortB(PortB.RB2, 1);
        assertThat(ram.getPortB(PortB.RB2)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(37);
    }

    @Test
    void testRB3() {
        assertThat(ram.getPortB(PortB.RB3)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB3, 0);
        ram.setPortB(PortB.RB3, 1);
        assertThat(ram.getPortB(PortB.RB3)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(8);

        ram.setPortB(PortB.RB3, 0);
        assertThat(ram.getPortB(PortB.RB3)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(70);
        ram.setPortB(PortB.RB3, 1);
        assertThat(ram.getPortB(PortB.RB3)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(78);
    }

    @Test
    void testRB4() {
        assertThat(ram.getPortB(PortB.RB4)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB4, 0);
        ram.setPortB(PortB.RB4, 1);
        assertThat(ram.getPortB(PortB.RB4)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(16);

        ram.setPortB(PortB.RB4, 0);
        assertThat(ram.getPortB(PortB.RB4)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(70);
        ram.setPortB(PortB.RB4, 1);
        assertThat(ram.getPortB(PortB.RB4)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(86);
    }

    @Test
    void testRB5() {
        assertThat(ram.getPortB(PortB.RB5)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB5, 0);
        ram.setPortB(PortB.RB5, 1);
        assertThat(ram.getPortB(PortB.RB5)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(32);

        ram.setPortB(PortB.RB5, 0);
        assertThat(ram.getPortB(PortB.RB5)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(70);
        ram.setPortB(PortB.RB5, 1);
        assertThat(ram.getPortB(PortB.RB5)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(102);
    }

    @Test
    void testRB6() {
        assertThat(ram.getPortB(PortB.RB6)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB6, 0);
        ram.setPortB(PortB.RB6, 1);
        assertThat(ram.getPortB(PortB.RB6)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(64);

        ram.setPortB(PortB.RB6, 0);
        assertThat(ram.getPortB(PortB.RB6)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(40);
        ram.setPortB(PortB.RB6, 1);
        assertThat(ram.getPortB(PortB.RB6)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(104);
    }

    @Test
    void testRB7() {
        assertThat(ram.getPortB(PortB.RB7)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setTrisB(PortB.RB7, 0);
        ram.setPortB(PortB.RB7, 1);
        assertThat(ram.getPortB(PortB.RB7)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(128);

        ram.setPortB(PortB.RB7, 0);
        assertThat(ram.getPortB(PortB.RB7)).isZero();
        assertThat(ram.getPortB()).isZero();

        ram.setPortB(70);
        ram.setPortB(PortB.RB7, 1);
        assertThat(ram.getPortB(PortB.RB7)).isEqualTo(1);
        assertThat(ram.getPortB()).isEqualTo(198);
    }
}
