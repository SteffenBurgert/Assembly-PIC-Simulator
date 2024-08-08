package assembly.pic.simulator.akku.ram_enums;

public class PortEnum {

    public PortA getEnumPortA(int position) {
        return switch (position) {
            case 0 -> PortA.RA0;
            case 1 -> PortA.RA1;
            case 2 -> PortA.RA2;
            case 3 -> PortA.RA3;
            case 4 -> PortA.RA4_T0CKI;
            default -> throw new IllegalStateException("Unexpected value: " + position);
        };
    }

    public PortB getEnumPortB(int position) {
        return switch (position) {
            case 0 -> PortB.RB0_INT;
            case 1 -> PortB.RB1;
            case 2 -> PortB.RB2;
            case 3 -> PortB.RB3;
            case 4 -> PortB.RB4;
            case 5 -> PortB.RB5;
            case 6 -> PortB.RB6;
            case 7 -> PortB.RB7;
            default -> throw new IllegalStateException("Unexpected value: " + position);
        };
    }
}
