package akku.ram_enums;

public enum PortB {
    RB0_INT(0),
    RB1(1),
    RB2(2),
    RB3(3),
    RB4(4),
    RB5(5),
    RB6(6),
    RB7(7);

    public final Integer location;

    PortB(Integer location) {
        this.location = location;
    }
}
