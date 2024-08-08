package assembly.pic.simulator.akku.service.decoder;

import assembly.pic.simulator.service.decoder.CommandCode;
import org.junit.jupiter.api.Test;
import assembly.pic.simulator.service.Decoder;

import static org.assertj.core.api.Assertions.assertThat;

class LiteralAndControlCommandCodeDecoderTest {

    private final Decoder decoder = new Decoder();

    @Test
    public void testADDLW() throws Exception {
        assertThat(decoder.decodeOpcode(0b11111000000000)).isEqualTo(CommandCode.ADDLW);
    }

    @Test
    public void testANDLW() throws Exception {
        assertThat(decoder.decodeOpcode(0b11100100000000)).isEqualTo(CommandCode.ANDLW);
    }

    @Test
    public void testCALL() throws Exception {
        assertThat(decoder.decodeOpcode(0b10000000000000)).isEqualTo(CommandCode.CALL);
    }

    @Test
    public void testCLRWDT() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000001100100)).isEqualTo(CommandCode.CLRWDT);
    }

    @Test
    public void testGOTO() throws Exception {
        assertThat(decoder.decodeOpcode(0b10100000000000)).isEqualTo(CommandCode.GOTO);
    }

    @Test
    public void testIORLW() throws Exception {
        assertThat(decoder.decodeOpcode(0b11100000000000)).isEqualTo(CommandCode.IORLW);
    }

    @Test
    public void testMOVLW() throws Exception {
        assertThat(decoder.decodeOpcode(0b11000000000000)).isEqualTo(CommandCode.MOVLW);
        assertThat(decoder.decodeOpcode(0b11000100000000)).isEqualTo(CommandCode.MOVLW);
        assertThat(decoder.decodeOpcode(0b11001000000000)).isEqualTo(CommandCode.MOVLW);
        assertThat(decoder.decodeOpcode(0b11001100000000)).isEqualTo(CommandCode.MOVLW);
    }

    @Test
    public void testRETFIE() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000000001001)).isEqualTo(CommandCode.RETFIE);
    }

    @Test
    public void testRETLW() throws Exception {
        assertThat(decoder.decodeOpcode(0b11010000000000)).isEqualTo(CommandCode.RETLW);
        assertThat(decoder.decodeOpcode(0b11010100000000)).isEqualTo(CommandCode.RETLW);
        assertThat(decoder.decodeOpcode(0b11011000000000)).isEqualTo(CommandCode.RETLW);
        assertThat(decoder.decodeOpcode(0b11011100000000)).isEqualTo(CommandCode.RETLW);
    }

    @Test
    public void testRETURN() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000000001000)).isEqualTo(CommandCode.RETURN);
    }

    @Test
    public void testSLEEP() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000001100011)).isEqualTo(CommandCode.SLEEP);
    }

    @Test
    public void testSUBLW() throws Exception {
        assertThat(decoder.decodeOpcode(0b11110000000000)).isEqualTo(CommandCode.SUBLW);
        assertThat(decoder.decodeOpcode(0b11110100000000)).isEqualTo(CommandCode.SUBLW);
    }

    @Test
    public void testXORLW() throws Exception {
        assertThat(decoder.decodeOpcode(0b11101000000000)).isEqualTo(CommandCode.XORLW);
    }
}