package assembly.pic.simulator.akku.ram_enums;

public enum Status {

    CARRY_BIT(0),
    DCARRY_BIT(1),
    ZERO_BIT(2),
    POWER_DOWN_BIT(3),
    TIME_OUT_BIT(4),
    RP0(5),
    RP1(6),
    IRP(7);
    public final Integer location;

    Status(Integer location) {
        this.location = location;
    }
}
