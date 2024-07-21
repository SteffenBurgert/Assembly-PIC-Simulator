package akku;

import akku.ram_enums.PortA;
import akku.ram_enums.PortB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RamPortATest {

    private static Ram ram;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    public void testRA0() {
        assertThat(ram.getPortA(PortA.RA0)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setTrisA(PortA.RA0, 0);
        ram.setPortA(PortA.RA0, 1);
        assertThat(ram.getTrisA(PortA.RA0)).isEqualTo(0);
        assertThat(ram.getPortA(PortA.RA0)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(1);

        ram.setPortA(PortA.RA0, 0);
        assertThat(ram.getPortA(PortA.RA0)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setPortA(20);
        ram.setPortA(PortA.RA0, 1);
        assertThat(ram.getPortA(PortA.RA0)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(21);
    }

    @Test
    public void testRA1() {
        assertThat(ram.getPortA(PortA.RA1)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setTrisA(PortA.RA1, 0);
        ram.setPortA(PortA.RA1, 1);
        assertThat(ram.getPortA(PortA.RA1)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(2);

        ram.setPortA(PortA.RA1, 0);
        assertThat(ram.getPortA(PortA.RA1)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setPortA(20);
        ram.setPortA(PortA.RA1, 1);
        assertThat(ram.getPortA(PortA.RA1)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(22);
    }

    @Test
    public void testRA2() {
        assertThat(ram.getPortA(PortA.RA2)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setTrisA(PortA.RA2, 0);
        ram.setPortA(PortA.RA2, 1);
        assertThat(ram.getPortA(PortA.RA2)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(4);

        ram.setPortA(PortA.RA2, 0);
        assertThat(ram.getPortA(PortA.RA2)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setPortA(33);
        ram.setTrisA(PortA.RA2, 0);
        ram.setPortA(PortA.RA2, 1);
        assertThat(ram.getPortA(PortA.RA2)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(5);
    }

    @Test
    public void testRA3() {
        assertThat(ram.getPortA(PortA.RA3)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setTrisA(PortA.RA3, 0);
        ram.setPortA(PortA.RA3, 1);
        assertThat(ram.getPortA(PortA.RA3)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(8);

        ram.setPortA(PortA.RA3, 0);
        assertThat(ram.getPortA(PortA.RA3)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setPortA(70);
        ram.setPortA(PortA.RA3, 1);
        assertThat(ram.getPortA(PortA.RA3)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(14);
    }

    @Test
    public void testRA4_T0CKI() {
        assertThat(ram.getPortA(PortA.RA4_T0CKI)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setTrisA(PortA.RA4_T0CKI, 0);
        ram.setPortA(PortA.RA4_T0CKI, 1);
        assertThat(ram.getPortA(PortA.RA4_T0CKI)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(16);

        ram.setPortA(PortA.RA4_T0CKI, 0);
        assertThat(ram.getPortA(PortA.RA4_T0CKI)).isEqualTo(0);
        assertThat(ram.getPortA()).isEqualTo(0);

        ram.setPortA(70);
        ram.setPortA(PortA.RA4_T0CKI, 1);
        assertThat(ram.getPortA(PortA.RA4_T0CKI)).isEqualTo(1);
        assertThat(ram.getPortA()).isEqualTo(22);
    }
}
