package akku.timer;

import akku.Ram;
import akku.ram_enums.Intcon;
import akku.ram_enums.OptionReg;

public class ProgramTimer {

    double quartzTact;
    double timer = 0.0;

    public ProgramTimer(double quartzTact) {
        this.quartzTact = quartzTact;
    }

    public void calculateTimer(int cycles, Ram ram) {
        timer += (double) cycles / quartzTact;
        // Timer mode
        if (ram.getOptionReg(OptionReg.T0CS) == 0) {
            for (int i = 0; i < cycles; i++) {
                ram.increaseTRM0();
            }
        }
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }
}
