package assembly.pic.simulator.service.calculator.port;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.timer.ProgramTimer;

public interface BitOrientedFileRegisterCalculationPort {

    /**
     * This operation is used to clear a specific bit (specified by the opcode) in the register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       contains the bit index to be cleared
     * @param ram          contains all register values
     * @param programTimer calculate execution time
     */
    void bcf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * This operation is used to set a specific bit (specified by the opcode) in the register.
     * <p>
     * The execution time is set to 1 cycle.
     *
     * @param opcode       contains the bit index to be cleared
     * @param ram          contains all register values
     * @param programTimer calculate execution time
     */
    void bsf(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * This operation is used to skip a command, if a specific bit (specified by the opcode) in the register is cleared.
     * <p>
     * The execution time is 1 cycle for the non-skipped case and 2 cycles for the skipped case.
     *
     * @param opcode       contains the bit index to be cleared
     * @param ram          contains all register values
     * @param programTimer calculate execution time
     */
    void btfsc(int opcode, Ram ram, ProgramTimer programTimer);

    /**
     * This operation is used to skip a command, if a specific bit (specified by the opcode) in the register is set.
     * <p>
     * The execution time is 1 cycle for the non-skipped case and 2 cycles for the skipped case.
     *
     * @param opcode       contains the bit index to be cleared
     * @param ram          contains all register values
     * @param programTimer calculate execution time
     */
    void btfss(int opcode, Ram ram, ProgramTimer programTimer);

}
