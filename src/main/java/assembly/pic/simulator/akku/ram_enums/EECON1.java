package assembly.pic.simulator.akku.ram_enums;

public enum EECON1 {

    RD(0),
    WR(1),
    WREN(2),
    WRERR(3),
    EEIF(4);

    public final Integer location;

    EECON1(Integer location) {
        this.location = location;
    }

}
