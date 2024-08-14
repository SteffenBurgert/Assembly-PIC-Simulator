package assembly.pic.simulator.akku;

import assembly.pic.simulator.akku.timer.WatchDogTimer;
import assembly.pic.simulator.akku.ram_enums.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ram {

    private int wRegister;
    @Getter
    private int programCounter = 0;
    @Getter
    private static final int BANK_SIZE = 128;
    private static final int SPECIAL_PURPOSE_REGISTER_SIZE = 12;
    @Getter
    private final List<Integer> bank0 = new ArrayList<>(BANK_SIZE);
    @Getter
    private final List<Integer> bank1 = new ArrayList<>(BANK_SIZE);
    private List<Integer> tos = new ArrayList<>();
    private int tosPointer = -1;

    private int prescaler = 0;

    private int prescalerCountdown = 0;

    /**
     * Constructor initializes Ram
     */
    public Ram() {
        for (int i = 0; i < BANK_SIZE; i++) {
            bank0.add(0);
            bank1.add(0);
        }

        setStatus(24);
        bank1.set(SpecialPurpose.OPTION_REG.location, 255);
        bank1.set(SpecialPurpose.TRIS_A.location, 31);
        bank1.set(SpecialPurpose.TRIS_B.location, 255);

        setPrescaler();
    }

    public void reset(WatchDogTimer watchDogTimer) {
        //Bank1
        setPCL(0);
        setStatus(Status.TIME_OUT_BIT, 1);
        setStatus(Status.IRP, 0);
        setStatus(Status.RP1, 0);
        setStatus(Status.RP0, 0);

        if (watchDogTimer.getWatchDogCounter() == 0) {
            setStatus(Status.TIME_OUT_BIT, 1);
            setStatus(Status.POWER_DOWN_BIT, 1);
        } else {
            setStatus(Status.TIME_OUT_BIT, 0);
            setStatus(Status.POWER_DOWN_BIT, 0);
        }

        setPCLATH(0);
        setINTCON(Intcon.INTF, 0);
        setINTCON(Intcon.T0IF, 0);
        setINTCON(Intcon.RBIE, 0);
        setINTCON(Intcon.INTE, 0);
        setINTCON(Intcon.T0IE, 0);
        setINTCON(Intcon.EEIE, 0);
        setINTCON(Intcon.GIE, 0);

        //Bank1
        setOptionReg(255);
        setTrisA(31);
        setTrisB(255);
        setEECON1(EECON1.EEIF, 0);

        if (watchDogTimer.getWatchDogCounter() == 0) {
            setEECON1(EECON1.WRERR, 1);
        } else {
            setEECON1(EECON1.WRERR, 0);
        }

        setEECON1(EECON1.WREN, 0);
        setEECON1(EECON1.WR, 0);
        setEECON1(EECON1.RD, 0);

        setPrescaler();
    }

    public int getGeneralPurposeRegister(int position) {
        if (getStatus(Status.RP0) == 1) {
            return getGeneralPurposeRegisterBank1(position);
        }
        return getGeneralPurposeRegisterBank0(position);
    }

    public void setGeneralPurposeRegister(int position, int value) {
        if (getStatus(Status.RP0) == 0 && position < SPECIAL_PURPOSE_REGISTER_SIZE) {
            if (SpecialPurpose.INDF.location == position) {
                setINDF(value);
            } else if (SpecialPurpose.TMR0.location == position) {
                setTRM0(value);
            } else if (SpecialPurpose.PCL.location == position) {
                programCounter = getPCLATH() << 8 | value & 255;
                setPCL(programCounter & 255);
            } else if (SpecialPurpose.STATUS.location == position) {
                setStatus(value);
            } else if (SpecialPurpose.FSR.location == position) {
                setFSR(value);
            } else if (SpecialPurpose.PORT_A.location == position) {
                setPortA(value);
            } else if (SpecialPurpose.PORT_B.location == position) {
                setPortB(value);
            } else if (SpecialPurpose.EEDATA.location == position) {
                setEEDATA(value);
            } else if (SpecialPurpose.EEADR.location == position) {
                setEEADR(value);
            } else if (SpecialPurpose.PCLATH.location == position) {
                setPCLATH(value);
            } else if (SpecialPurpose.INTCON.location == position) {
                setINTCON(value);
            }
        } else if (getStatus(Status.RP0) == 1 && position < SPECIAL_PURPOSE_REGISTER_SIZE) {
            if (SpecialPurpose.INDF.location == position) {
                setINDF(value);
            } else if (SpecialPurpose.OPTION_REG.location == position) {
                setOptionReg(value);
            } else if (SpecialPurpose.PCL.location == position) {
                programCounter = getPCLATH() << 8 | value & 255;
                setPCL(programCounter & 255);
            } else if (SpecialPurpose.STATUS.location == position) {
                setStatus(value);
            } else if (SpecialPurpose.FSR.location == position) {
                setFSR(value);
            } else if (SpecialPurpose.TRIS_A.location == position) {
                setTrisA(value);
            } else if (SpecialPurpose.TRIS_B.location == position) {
                setTrisB(value);
            } else if (SpecialPurpose.EECON1.location == position) {
                setEECON1(value);
            } else if (SpecialPurpose.EECON2.location == position) {
                setEECON2(value);
            } else if (SpecialPurpose.PCLATH.location == position) {
                setPCLATH(value);
            } else if (SpecialPurpose.INTCON.location == position) {
                setINTCON(value);
            }
        } else {
            if (getStatus(Status.RP0) == 1) {
                if (position >= BANK_SIZE) position -= BANK_SIZE;
                setGeneralPurposeRegisterBank1(position, value);
            } else {
                setGeneralPurposeRegisterBank0(position, value);
            }
        }
    }

    public int getGeneralPurposeRegisterBank0(int position) {
        return bank0.get(position);
    }

    public void setGeneralPurposeRegisterBank0(int position, int value) {
        bank0.set(position, value);
    }

    public int getGeneralPurposeRegisterBank1(int position) {
        return bank1.get(position);
    }

    public void setGeneralPurposeRegisterBank1(int position, int value) {
        bank1.set(position, value);
    }

    public int getWRegister() {
        return wRegister;
    }

    public void setWRegister(int wRegister) {
        this.wRegister = wRegister;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
        setPCL(this.programCounter);
    }

    public void setProgramCounterCallGoto(int argument) {
        argument = argument & 2047;
        int pclath = (getPCLATH() >> 3) << 11;

        programCounter = pclath | argument;
        setPCL(programCounter & 255);
    }

    public void increaseProgramCounter() {
        programCounter++;
        setPCL(programCounter);
    }

    public int getTos() {
        tosPointer--;
        if (tosPointer < 0) {
            tosPointer += 8;
        }
        return tos.get(tosPointer);
    }

    // Needed for GUI
    public List<Integer> getTosStack() {
        List<Integer> stack = new LinkedList<>();
        for (int i = tos.size() - 1; i >= 0; i--) {
            stack.add(tos.get(i));
        }
        return stack;
    }

    public void setTos(int tos) {
        this.tos.add(tos);
        if (this.tos.size() == 9) {
            List<Integer> newTos = new ArrayList<>();
            for (int i = 1; i < this.tos.size(); i++) {
                newTos.add(this.tos.get(i));
            }
            this.tos = newTos;
        }
        tosPointer = this.tos.size();
    }

    /**
     * Register in Bank0 and Bank1 -------------------------------------------------------------------------------------
     */

    public int getINDF() {
        return bank0.get(SpecialPurpose.INDF.location);
    }

    public void setINDF(int indirect) {
        bank0.set(SpecialPurpose.INDF.location, indirect);
        bank1.set(SpecialPurpose.INDF.location, indirect);
    }

    public int getPCL() {
        return bank0.get(SpecialPurpose.PCL.location);
    }

    public void setPCL(int pcl) {
        bank0.set(SpecialPurpose.PCL.location, pcl & 255);
        bank1.set(SpecialPurpose.PCL.location, pcl & 255);
    }

    public int getStatus() {
        return bank0.get(SpecialPurpose.STATUS.location);
    }

    public void setStatus(int status) {
        bank0.set(SpecialPurpose.STATUS.location, status);
        bank1.set(SpecialPurpose.STATUS.location, status);
    }

    public int getStatus(Status status) {
        return (bank0.get(SpecialPurpose.STATUS.location) >> status.location) & 1;
    }

    public void setStatus(Status status, int value) {
        int bitMask = (int) Math.pow(2, status.location);
        setStatus(value == 0 ? getStatus() & (~bitMask) : getStatus() | bitMask);
    }

    public int getFSR() {
        return bank0.get(SpecialPurpose.FSR.location);
    }

    public void setFSR(int fsr) {
        bank0.set(SpecialPurpose.FSR.location, fsr);
        bank1.set(SpecialPurpose.FSR.location, fsr);
    }

    private void setPCLATH(int pclath) {
        bank0.set(SpecialPurpose.PCLATH.location, pclath & 31);
        bank1.set(SpecialPurpose.PCLATH.location, pclath & 31);
    }

    public int getPCLATH() {
        return bank0.get(SpecialPurpose.PCLATH.location);
    }

    public int getINTCON() {
        return bank0.get(SpecialPurpose.INTCON.location);
    }

    public void setINTCON(int intcon) {
        bank0.set(SpecialPurpose.INTCON.location, intcon);
        bank1.set(SpecialPurpose.INTCON.location, intcon);
    }

    public int getINTCON(Intcon intcon) {
        return (bank0.get(SpecialPurpose.INTCON.location) >> intcon.location) & 1;
    }

    public void setINTCON(Intcon intcon, int value) {
        int bitMask = (int) Math.pow(2, intcon.location);
        setINTCON(value == 0 ? getINTCON() & (~bitMask) : getINTCON() | bitMask);
    }

    /**
     * Register in Bank0 -----------------------------------------------------------------------------------------------
     */

    public int getTRM0() {
        return bank0.get(SpecialPurpose.TMR0.location);
    }

    public void setTRM0(int value) {
        bank0.set(SpecialPurpose.TMR0.location, value);
    }

    public void increaseTRM0() {
        if (prescalerCountdown <= 0 && getOptionReg(OptionReg.PSA) == 0 && getOptionReg(OptionReg.T0CS) == 0) {
            int tmr0 = getTRM0() + 1;
            if (tmr0 > 255) {
                tmr0 = 0;
                setINTCON(Intcon.T0IF, 1);
            }
            prescalerCountdown = prescaler;
            bank0.set(SpecialPurpose.TMR0.location, tmr0);
        }
        prescalerCountdown--;
    }

    private void increaseTRM0inCounterMode(PortA portA, int value) {
        if (
                portA == PortA.RA4_T0CKI && getOptionReg(OptionReg.T0CS) == 1 &&
                        (getOptionReg(OptionReg.T0SE) == 0 && getPortA(PortA.RA4_T0CKI) == 0 && value == 1 ||
                                getOptionReg(OptionReg.T0SE) == 1 && getPortA(PortA.RA4_T0CKI) == 1 && value == 0)
        ) {
            processPrescaler();
        }

    }

    private void processPrescaler() {
        if (getOptionReg(OptionReg.PSA) == 0) {
            if (prescalerCountdown <= 0) {
                increaseTRM0CounterMode();
                prescalerCountdown = prescaler;
            } else {
                prescalerCountdown--;
            }
        } else {
            increaseTRM0CounterMode();
        }
    }

    private void increaseTRM0CounterMode() {
        int tmr0 = getTRM0() + 1;
        if (tmr0 > 255) {
            tmr0 = 0;
            setINTCON(Intcon.T0IF, 1);
        }
        bank0.set(SpecialPurpose.TMR0.location, tmr0);
    }

    private void setPrescaler() {
        prescaler = (int) Math.pow(2, ((getOptionReg(OptionReg.PS2) << 2) | (getOptionReg(OptionReg.PS1) << 1) | getOptionReg(OptionReg.PS0)));
        prescaler = getOptionReg(OptionReg.T0CS) == 0 ? prescaler * 2 : prescaler;
        prescalerCountdown = prescaler + 1;
    }

    public int getPortA() {
        return bank0.get(SpecialPurpose.PORT_A.location);
    }

    public void setPortA(int value) {
        bank0.set(SpecialPurpose.PORT_A.location, value & 31);
    }

    public int getPortA(PortA portA) {
        return (bank0.get(SpecialPurpose.PORT_A.location) >> portA.location) & 1;
    }

    public void setPortA(PortA portA, int value) {
        increaseTRM0inCounterMode(portA, value);

        int bitMask = (1 << portA.location);
        if (getTrisA(portA) == 0) {
            setPortA(value == 0 ? getPortA() & (~bitMask) : getPortA() | bitMask);
        }
    }

    // Needed for GUI
    public void setPortAInput(PortA portA) {
        int bitMask = (1 << portA.location);
        int invertedValue = ~getPortA() & bitMask;
        increaseTRM0inCounterMode(portA, invertedValue);

        if ((getTrisA() & bitMask) != 0) {
            setPortA(invertedValue == 0 ? getPortA() & (~bitMask) : getPortA() | bitMask);
        }
    }

    public void setPortB(int value) {
        int rb0 = value & 1;
        if (rb0 != getPortB(PortB.RB0_INT) && getTrisB(PortB.RB0_INT) == 1 && getOptionReg(OptionReg.INTEDG) == value) {
            setINTCON(Intcon.INTF, 1);
        }

        bank0.set(SpecialPurpose.PORT_B.location, value);
    }

    public int getPortB() {
        return bank0.get(SpecialPurpose.PORT_B.location);
    }

    public int getPortB(PortB portb) {
        return (bank0.get(SpecialPurpose.PORT_B.location) >> portb.location) & 1;
    }

    public void setPortB(PortB portB, int value) {
        int bitMask = (1 << portB.location);

        if (getTrisB(portB) == 0) {
            checkAndSetRBIF(portB);
            setPortB(value == 0 ? getPortB() & (~bitMask) : getPortB() | bitMask);
        }
    }

    // Needed for GUI
    public void setPortBInput(PortB portB) {
        int bitMask = (1 << portB.location);
        int invertedValue = ~getPortB() & bitMask;

        if ((getTrisB() & bitMask) != 0) {
            checkAndSetRBIF(portB);
            setPortB(invertedValue == 0 ? getPortB() & (~bitMask) : getPortB() | bitMask);
        }
    }

    private void checkAndSetRBIF(PortB portB) {
        if ((portB == PortB.RB4 && getTrisB(PortB.RB4) == 1) ||
                (portB == PortB.RB5 && getTrisB(PortB.RB5) == 1) ||
                (portB == PortB.RB6 && getTrisB(PortB.RB6) == 1) ||
                (portB == PortB.RB7 && getTrisB(PortB.RB7) == 1)) {
            setINTCON(Intcon.RBIF, 1);
        }
    }

    public int getEEDATA() {
        return bank0.get(SpecialPurpose.EEDATA.location);
    }

    public void setEEDATA(int value) {
        bank0.set(SpecialPurpose.EEDATA.location, value);
    }

    public int getEEADR() {
        return bank0.get(SpecialPurpose.EEADR.location);
    }

    public void setEEADR(int value) {
        bank0.set(SpecialPurpose.EEADR.location, value);
    }

    /**
     * Register in Bank1 -----------------------------------------------------------------------------------------------
     */

    public int getOptionReg() {
        return bank1.get(SpecialPurpose.OPTION_REG.location);
    }

    public void setOptionReg(int value) {
        bank1.set(SpecialPurpose.OPTION_REG.location, value);
        setPrescaler();
    }

    public int getOptionReg(OptionReg optionReg) {
        return (bank1.get(SpecialPurpose.OPTION_REG.location) >> optionReg.location) & 1;
    }

    public void setOptionReg(OptionReg optionReg, int value) {
        int bitMask = (int) Math.pow(2, optionReg.location);
        setOptionReg(value == 0 ? getOptionReg() & (~bitMask) : getOptionReg() | bitMask);
    }

    public int getTrisA() {
        return bank1.get(SpecialPurpose.TRIS_A.location);
    }

    public int getTrisA(PortA porta) {
        return (bank1.get(SpecialPurpose.TRIS_A.location) >> porta.location) & 1;
    }

    public void setTrisA(int value) {
        bank1.set(SpecialPurpose.TRIS_A.location, value);
    }

    public void setTrisA(PortA portA, int value) {
        int bitMask = (int) Math.pow(2, portA.location);
        setTrisA(value == 0 ? getTrisA() & (~bitMask) : getTrisA() | bitMask);
    }

    public int getTrisB() {
        return bank1.get(SpecialPurpose.TRIS_B.location);
    }

    public int getTrisB(PortB portb) {
        return (bank1.get(SpecialPurpose.TRIS_B.location) >> portb.location) & 1;
    }

    public void setTrisB(int value) {
        bank1.set(SpecialPurpose.TRIS_B.location, value);
    }

    public void setTrisB(PortB portB, int value) {
        int bitMask = (int) Math.pow(2, portB.location);
        setTrisB(value == 0 ? getTrisB() & (~bitMask) : getTrisB() | bitMask);
    }

    public int getEECON1() {
        return bank1.get(SpecialPurpose.EECON1.location);
    }

    public void setEECON1(int value) {
        bank1.set(SpecialPurpose.EECON1.location, value);
    }

    public int getEECON1(EECON1 eecon1) {
        return (bank1.get(SpecialPurpose.EECON1.location) >> eecon1.location) & 1;
    }

    public void setEECON1(EECON1 eecon1, int value) {
        int bitMask = (int) Math.pow(2, eecon1.location);
        setEECON1(value == 0 ? getEECON1() & (~bitMask) : getEECON1() | bitMask);
    }

    public int getEECON2() {
        return bank1.get(SpecialPurpose.EECON2.location);
    }

    public void setEECON2(int value) {
        bank1.set(SpecialPurpose.EECON2.location, value);
    }
}
