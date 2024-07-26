package assembly.pic.simulator.akku.test_file;


import assembly.pic.simulator.akku.Ram;
import assembly.pic.simulator.akku.ram_enums.Status;

public class TestRam {

    private final int wRegister;
    private final int carryBit;
    private final int dCarryBit;
    private final int zeroBit;
    private final int value1;
    private final int value2;
    private final int pc;
    private final int pcl;
    private final int pclath;

    private final Ram ram;

    public TestRam(Ram ram) {
        wRegister = ram.getWRegister();
        carryBit = ram.getStatus(Status.CARRY_BIT);
        dCarryBit = ram.getStatus(Status.DCARRY_BIT);
        zeroBit = ram.getStatus(Status.ZERO_BIT);
        value1 = ram.getGeneralPurposeRegisterBank1(12);
        value2 = ram.getGeneralPurposeRegisterBank1(13);

        pc = ram.getProgramCounter();
        pcl = ram.getPCL();
        pclath = ram.getPCLATH();

        this.ram = ram;
    }

    public int getwRegister() {
        return wRegister;
    }

    public int getCarryBit() {
        return carryBit;
    }

    public int getDCarryBit() {
        return dCarryBit;
    }

    public int getZeroBit() {
        return zeroBit;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getPc() {
        return pc;
    }

    public int getPcl() {
        return pcl;
    }

    public int getPclath() {
        return pclath;
    }
}
