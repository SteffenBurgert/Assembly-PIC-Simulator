package assembly.pic.simulator.service.calculator.port;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.timer.ProgramTimer;
import assembly.pic.simulator.akku.timer.WatchDogTimer;

public interface LiteralAndControlOperationsPort {

    /**
     * Adds an 8-bit constant to the value in the WRegister. The result is stored in the Working register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the ADDLW instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void addlw(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Performs a bitwise AND operation between an 8-bit constant and the WRegister value.
     * The result is stored back in the WRegister.
     * The zero status bit in the status register is set if the result is 0. Otherwise, it is cleared.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the ANDLW instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void andlw(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Calls a subroutine at the specified 11-bit address.
     * This involves saving the current program counter to the top of the stack and
     * then setting the program counter to the address in the opcode.
     * <p>
     * The execution time is set to 2 cycles.
     *
     * @param opcode       The opcode of the CALL instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     * @throws StackOverflowError If the call stack is full and cannot accommodate another subroutine call.
     */
    void call(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Clears the Watchdog Timer counter and prescaler, preventing a Watchdog Timer reset.
     * It also sets the TIME_OUT_BIT and POWER_DOWN_BIT in the status register to 1
     * to indicate that the device has been reset due to a Watchdog Timer timeout.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param ram            A Ram object containing memory and registers.
     * @param watchDogTimer  A WatchDogTimer object for managing the Watchdog Timer.
     * @param programTimer   A ProgramTimer object for tracking execution time.
     */
    void clrwdt(Ram ram, WatchDogTimer watchDogTimer, ProgramTimer programTimer);

    /**
     * Sets the program counter to the specified address, effectively branching to that address.
     * <p>
     * The execution time is set to 2 cycles.
     *
     * @param opcode       The opcode of the GOTO instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void gotoAssy(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Performs an OR operation between an 8-bit constant and the WRegister value.
     * The result is stored in the WRegister.
     * The zero status bit in the status register is set if the result is 0. Otherwise, it is cleared.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the IORLW instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void iorlw(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Moves a constant into the WRegister.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the MOVLW instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void movlw(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Returns from an interrupt service routine (ISR) and restores the program counter to
     * the address saved on the top of the stack. It enables global interrupts
     * by setting the Global Interrupt Enable (GIE) bit in the INTCON register.
     * <p>
     * The execution time is set to 2 cycles.
     *
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void retfie(Ram ram, ProgramTimer programTimer);

    /**
     * Returns from an interrupt service routine (ISR) and restores the program counter to
     * the address saved on the top of the stack. Additionally, it moves an immediate 8-bit
     * constant (literal) into the WRegister.
     * <p>
     * The execution time is set to 2 cycles.
     *
     * @param opcode       The opcode of the RETLW instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void retlw(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * The program counter is restored to the address saved on the stack after a subroutine call.
     * <p>
     * The execution time is set to 2 cycles.
     *
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void returnAssy(Ram ram, ProgramTimer programTimer);

    /**
     * Puts the device to sleep, stopping it from working until it is woken up by an
     * external interrupt or a Watchdog Timer timeout.
     * It clears the Watchdog Timer counter and prescaler,
     * sets the TIME_OUT_BIT in the status register to 1 and
     * the POWER_DOWN_BIT to 0.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param ram            A Ram object containing memory and registers.
     * @param watchDogTimer  A WatchDogTimer object for managing the Watchdog Timer.
     * @param programTimer   A ProgramTimer object for tracking execution time.
     */
    void sleep(Ram ram, WatchDogTimer watchDogTimer, ProgramTimer programTimer);

    /**
     * Subtracts an 8-bit constant from the value in the WRegister.
     * The result is stored back in the Working register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the SUBLW instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void sublw(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * Performs an XOR operation between an 8-bit constant and the WRegister value.
     * The result is stored in the Working register.
     * The status register's zero status bit is set if the result is 0. Otherwise, it is cleared.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       The opcode of the XORLW instruction.
     * @param ram          A Ram object containing memory and registers.
     * @param programTimer A ProgramTimer object for tracking execution time.
     */
    void xorlw(int opcode, Ram ram, ProgramTimer programTimer);
}
