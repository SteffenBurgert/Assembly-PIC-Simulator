package service.calculator.general;

import akku.Ram;
import akku.ram_enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class GeneralCalculationTest {

    private static Ram ram;

    private static final GeneralCalculation generalCalculation = new GeneralCalculation();

    @BeforeEach
    public void setup() {
        ram = new Ram();
    }

    @Test
    void testSetDCarryADD() {
        generalCalculation.setDCarry(ram, 241, 14, DCOperation.ADD);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        generalCalculation.setDCarry(ram, 241, 15, DCOperation.ADD);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
    }

    @Test
    void testSetDCarrySUB() {
        generalCalculation.setDCarry(ram, 15, 14, DCOperation.SUB);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);

        generalCalculation.setDCarry(ram, 14, 15, DCOperation.SUB);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        generalCalculation.setDCarry(ram, 14, 14, DCOperation.SUB);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        generalCalculation.setDCarry(ram, 0, 0, DCOperation.SUB);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
    }

    @Test
    void testSetZeroBit() {
        generalCalculation.setZeroBit(ram, 200);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
        generalCalculation.setZeroBit(ram, 0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);
        generalCalculation.setZeroBit(ram, 12);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);
    }

    @Test
    void testCalculateAddOperation() {
        int result = generalCalculation.calculateAddOperation(ram, 200, 100);
        assertThat(result).isEqualTo(44);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        result = generalCalculation.calculateAddOperation(ram, 200, 50);
        assertThat(result).isEqualTo(250);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        result = generalCalculation.calculateAddOperation(ram, 0, 0);
        assertThat(result).isEqualTo(0);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);

        result = generalCalculation.calculateAddOperation(ram, 1, 15);
        assertThat(result).isEqualTo(16);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

    }

    @Test
    void testCalculateSubOperation() {
        int result = generalCalculation.calculateSubOperation(ram, 200, 100);
        assertThat(result).isEqualTo(100);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        result = generalCalculation.calculateSubOperation(ram, 200, 50);
        assertThat(result).isEqualTo(150);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

        result = generalCalculation.calculateSubOperation(ram, 0, 0);
        assertThat(result).isEqualTo(0);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(1);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(1);

        result = generalCalculation.calculateSubOperation(ram, 1, 15);
        assertThat(result).isEqualTo(242);
        assertThat(ram.getStatus(Status.CARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.DCARRY_BIT)).isEqualTo(0);
        assertThat(ram.getStatus(Status.ZERO_BIT)).isEqualTo(0);

    }
}