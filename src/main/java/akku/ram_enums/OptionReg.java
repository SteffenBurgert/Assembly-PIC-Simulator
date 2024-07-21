package akku.ram_enums;

public enum OptionReg {
    PS0(0),
    PS1(1),
    PS2(2),
    PSA(3),
    T0SE(4),
    T0CS(5),
    INTEDG(6),
    RBPU(7);

    public final Integer location;

    OptionReg(Integer location) {
        this.location = location;
    }
}
