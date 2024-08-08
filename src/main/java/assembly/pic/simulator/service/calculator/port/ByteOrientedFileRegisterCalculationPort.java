package assembly.pic.simulator.service.calculator.port;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.timer.ProgramTimer;

public interface ByteOrientedFileRegisterCalculationPort {

    /**
     * Adds the value of the WRegister to a general-purpose register and
     * stores the result in the selected register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the ADDWF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void addwf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Adds the value of the WRegister to a general-purpose register and
     * stores the result in the same register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the ADDWF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void andwf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Clears a general-purpose register and updates the zero status bit in the status register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the CLRF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void clrf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Clears the WRegister and updates the zero status bit in the status register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void clrw(Ram ram, ProgramTimer programTimer);

    /**
     * Computes the one's complement of a value in a register and stores the result back in the same register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the COMF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void comf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Decrements a value from a specified general-purpose register by 1 and
     * stores the result back in the same register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the DECF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void decf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Decrements the value stored in a specified general-purpose register by 1 and
     * puts the result back in the same register.
     * If the result is 0, the program counter is increased to skip the next instruction.
     * <p>
     * The execution time is set to 1 cycle if the decf value is 0. Otherwise, it is 2.
     *
     * @param opcode       The opcode of the DECFSZ instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void decfsz(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Increments the value stored in a specified general-purpose register by 1 and
     * stores the result back in the same register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the INCF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void incf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Increments the value stored in a specified general-purpose register by 1 and
     * stores the result back in the same register.
     * If the result is 0, the program counter is incremented to skip the next instruction.
     * <p>
     * The execution time is set to 2 cycle if the incf value is 0. Otherwise, it is set to 1.
     *
     * @param opcode       The opcode of the INCFSZ instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void incfsz(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Performs a bitwise logical OR operation between the value of the WRegister
     * and the value stored in a specified general-purpose register. The result is stored back
     * in the same general-purpose register.
     * The zero status bit in the status register is set if the result is 0. Otherwise, it is cleared.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the IORWF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void iorwf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Moves the value stored in a specified general-purpose register to either the WRegister
     * or the same general-purpose register, based on the opcode.
     * The zero status bit in the status register is set if the result is 0. Otherwise, it is cleared.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the MOVF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void movf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Moves a value from the WRegister to a general-purpose register. The WRegister remains unchanged.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the MOVWF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void movwf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * No operation (NOP). This instruction does nothing.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the NOP instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void nop(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Rotates bits of a register left through the carry flag.
     * The least significant bit goes in the carry flag and
     * the carry flag goes in the most significant bit of the register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the RLF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void rlf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Rotates bits of a register to the right through the Carry flag.
     * The most significant bit goes in the Carry flag and
     * the Carry flag goes in the least significant bit of the register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the RRF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void rrf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Subtracts the value of the WRegister from a general-purpose register and
     * stores the result back in the same register.
     * The result is stored in two's complement form.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the SUBWF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void subwf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Swaps the nibbles (4-bit groups) of a specified general-purpose register.
     * The lower nibble is exchanged with the upper nibble.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the SWAPF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void swapf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Performs a bitwise XOR between WRegister and a specified register.
     * The result is stored back in the same register.
     * The zero status bit in the status register is set if the result is 0. Otherwise, it is cleared.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the XORWF instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void xorwf(int opcode, Ram ram, ProgramTimer programTimer);
}
