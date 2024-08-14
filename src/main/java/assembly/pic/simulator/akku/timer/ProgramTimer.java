package assembly.pic.simulator.akku.timer;

import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.OptionReg;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProgramTimer {

    private final double quartzTact;
    private double timer = 0.0;

    public ProgramTimer(double quartzTact) {
        this.quartzTact = quartzTact;
    }

    public void calculateTimer(int cycles, Ram ram) {
        timer += cycles / quartzTact;
        // Timer mode
        if (ram.getOptionReg(OptionReg.T0CS) == 0) {
            for (int i = 0; i < cycles; i++) {
                ram.increaseTRM0();
            }
        }
    }
}
