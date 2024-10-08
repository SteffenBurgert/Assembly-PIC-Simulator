package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RamStatusTest {

    private static Ram ram;

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    public void testCarry() {
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.CARRY_BIT, 1);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(25);

        ram.setStatus(Status.CARRY_BIT, 0);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(20);
        ram.setStatus(Status.CARRY_BIT, 1);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(21);
    }

    @Test
    public void testDCarry() {
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.DCARRY_BIT, 1);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(26);

        ram.setStatus(Status.DCARRY_BIT, 0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(20);
        ram.setStatus(Status.DCARRY_BIT, 1);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(22);
    }

    @Test
    public void testZeroBit() {
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.ZERO_BIT, 1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(28);

        ram.setStatus(Status.ZERO_BIT, 0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(33);
        ram.setStatus(Status.ZERO_BIT, 1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(37);
    }

    @Test
    public void testPowerDownBit() {
        assertThat(ram.getStatus(Status.POWER_DOWN_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.POWER_DOWN_BIT, 1);
        assertThat(ram.getStatus(Status.POWER_DOWN_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.POWER_DOWN_BIT, 0);
        assertThat(ram.getStatus(Status.POWER_DOWN_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(16);

        ram.setStatus(70);
        ram.setStatus(Status.POWER_DOWN_BIT, 1);
        assertThat(ram.getStatus(Status.POWER_DOWN_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(78);
    }

    @Test
    public void testTimeOutBit() {
        assertThat(ram.getStatus(Status.TIME_OUT_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.TIME_OUT_BIT, 1);
        assertThat(ram.getStatus(Status.TIME_OUT_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.TIME_OUT_BIT, 0);
        assertThat(ram.getStatus(Status.TIME_OUT_BIT)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(8);

        ram.setStatus(70);
        ram.setStatus(Status.TIME_OUT_BIT, 1);
        assertThat(ram.getStatus(Status.TIME_OUT_BIT)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(86);
    }

    @Test
    public void testRP0() {
        assertThat(ram.getStatus(Status.RP0)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.RP0, 1);
        assertThat(ram.getStatus(Status.RP0)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(56);

        ram.setStatus(Status.RP0, 0);
        assertThat(ram.getStatus(Status.RP0)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(70);
        ram.setStatus(Status.RP0, 1);
        assertThat(ram.getStatus(Status.RP0)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(102);
    }

    @Test
    public void testRP1() {
        assertThat(ram.getStatus(Status.RP1)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.RP1, 1);
        assertThat(ram.getStatus(Status.RP1)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(88);

        ram.setStatus(Status.RP1, 0);
        assertThat(ram.getStatus(Status.RP1)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(40);
        ram.setStatus(Status.RP1, 1);
        assertThat(ram.getStatus(Status.RP1)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(104);
    }

    @Test
    public void testIRP() {
        assertThat(ram.getStatus(Status.IRP)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(Status.IRP, 1);
        assertThat(ram.getStatus(Status.IRP)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(152);

        ram.setStatus(Status.IRP, 0);
        assertThat(ram.getStatus(Status.IRP)).isEqualTo(0);
        assertThat(ram.getStatus()).isEqualTo(24);

        ram.setStatus(70);
        ram.setStatus(Status.IRP, 1);
        assertThat(ram.getStatus(Status.IRP)).isEqualTo(1);
        assertThat(ram.getStatus()).isEqualTo(198);
    }
}
