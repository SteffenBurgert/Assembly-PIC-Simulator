package akku.ram_enums;

public enum PortA {
    RA0(0),
    RA1(1),
    RA2(2),
    RA3(3),
    RA4_T0CKI(4);

    public final Integer location;

    PortA(Integer location) {
        this.location = location;
    }
}
