package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.ram_enums.EECON1;
import assembly.pic.simulator.akku.ram_enums.Intcon;

public class Interrupt {

    public void checkInterrupt(Ram ram) {
        if (ram.getINTCON(Intcon.GIE) == 1 && (
                (ram.getINTCON(Intcon.T0IE) == 1 && ram.getINTCON(Intcon.T0IF) == 1) ||
                        (ram.getINTCON(Intcon.INTE) == 1 && ram.getINTCON(Intcon.INTF) == 1) ||
                        (ram.getINTCON(Intcon.RBIE) == 1 && ram.getINTCON(Intcon.RBIF) == 1) ||
                        (ram.getINTCON(Intcon.EEIE) == 1 && ram.getEECON1(EECON1.EEIF) == 1))) {

            blockSecondInterrupt(ram);
            ram.setProgramCounter(4);
        }
    }

    private void blockSecondInterrupt(Ram ram) {
        ram.setINTCON(Intcon.GIE, 0);
        ram.setTos(ram.getPCL());
    }
}
