package akku.ram_enums;

public enum Intcon {

    RBIF(0),
    INTF(1),
    T0IF(2),
    RBIE(3),
    INTE(4),
    T0IE(5),
    EEIE(6),
    GIE(7);

    public final Integer location;

    Intcon(Integer location) {
        this.location = location;
    }
}
