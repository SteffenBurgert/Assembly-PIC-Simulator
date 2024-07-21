package akku.ram_enums;

public enum SpecialPurpose {

    INDF(0x0),
    TMR0(0x1),
    PCL(0x2),
    STATUS(0x3),
    FSR(0x4),
    PORT_A(0x5),
    PORT_B(0x6),
    EEDATA(0x8),
    EEADR(0x9),
    PCLATH(0xA),
    INTCON(0xB),
    // EQUALS 0x81
    OPTION_REG(0x1),
    // EQUALS 0x85
    TRIS_A(0x5),
    // EQUALS 0x86
    TRIS_B(0x6),
    // EQUALS 0x88
    EECON1(0x8),
    // EQUALS 0x89
    EECON2(0x9);

    public final Integer location;

    SpecialPurpose(Integer location) {
        this.location = location;
    }
}

