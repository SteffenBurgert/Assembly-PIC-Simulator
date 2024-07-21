package service.calculator.general;

import akku.Ram;
import akku.ram_enums.Status;

public class GeneralCalculation {

    public void setDCarry(Ram ram, int firstNumber, int secondNumber, DCOperation operation) {
        if (operation == DCOperation.ADD) {
            ram.setStatus(Status.DCARRY_BIT, (firstNumber & 15) + (secondNumber & 15) > 15 ? 1 : 0);
        } else if (operation == DCOperation.SUB){
            ram.setStatus(Status.DCARRY_BIT, (firstNumber & 15) - (secondNumber & 15) < 0 ? 0 : 1);
        }
    }

    public void setZeroBit(Ram ram, int result) {
        ram.setStatus(Status.ZERO_BIT, result == 0 ? 1 : 0);
    }

    public int calculateAddOperation(Ram ram, int firstNumber, int secondNumber) {
        setDCarry(ram, firstNumber, secondNumber, DCOperation.ADD);
        int result = firstNumber + secondNumber;

        if (result > 255) {
            result &= 255;
            ram.setStatus(Status.CARRY_BIT, 1);
        } else {
            ram.setStatus(Status.CARRY_BIT, 0);
        }

        setZeroBit(ram, result);
        return result;
    }

    public int calculateSubOperation(Ram ram, int firstNumber, int secondNumber) {
        setDCarry(ram, firstNumber, secondNumber, DCOperation.SUB);
        int result = firstNumber - secondNumber;

        if (result < 0) {
            result += 256;
            ram.setStatus(Status.CARRY_BIT, 0);
        } else {
            ram.setStatus(Status.CARRY_BIT, 1);
        }

        setZeroBit(ram, result);
        return result;
    }
}

