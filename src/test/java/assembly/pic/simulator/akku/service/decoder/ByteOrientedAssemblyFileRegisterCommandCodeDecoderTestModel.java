package assembly.pic.simulator.akku.service.decoder;

import assembly.pic.simulator.service.decoder.CommandCode;
import org.junit.jupiter.api.Test;
import assembly.pic.simulator.service.Decoder;

import static org.assertj.core.api.Assertions.assertThat;

class ByteOrientedAssemblyFileRegisterCommandCodeDecoderTestModel {

    private final Decoder decoder = new Decoder();

    @Test
    public void testADDWF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00011110000000)).isEqualTo(CommandCode.ADDWF);
        assertThat(decoder.decodeOpcode(0b00011100000000)).isEqualTo(CommandCode.ADDWF);
    }

    @Test
    public void testANDWF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00010110000000)).isEqualTo(CommandCode.ANDWF);
        assertThat(decoder.decodeOpcode(0b00010100000000)).isEqualTo(CommandCode.ANDWF);
    }

    @Test
    public void testCLRF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000110000000)).isEqualTo(CommandCode.CLRF);
    }

    @Test
    public void testCLRW() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000100000000)).isEqualTo(CommandCode.CLRW);
    }

    @Test
    public void testCOMF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00100110000000)).isEqualTo(CommandCode.COMF);
        assertThat(decoder.decodeOpcode(0b00100100000000)).isEqualTo(CommandCode.COMF);
    }

    @Test
    public void testDECF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00001110000000)).isEqualTo(CommandCode.DECF);
        assertThat(decoder.decodeOpcode(0b00001100000000)).isEqualTo(CommandCode.DECF);
    }

    @Test
    public void testDECFSZ() throws Exception {
        assertThat(decoder.decodeOpcode(0b00101110000000)).isEqualTo(CommandCode.DECFSZ);
        assertThat(decoder.decodeOpcode(0b00101100000000)).isEqualTo(CommandCode.DECFSZ);
    }

    @Test
    public void testINCF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00101010000000)).isEqualTo(CommandCode.INCF);
        assertThat(decoder.decodeOpcode(0b00101000000000)).isEqualTo(CommandCode.INCF);
    }

    @Test
    public void testINCFSZ() throws Exception {
        assertThat(decoder.decodeOpcode(0b00111110000000)).isEqualTo(CommandCode.INCFSZ);
        assertThat(decoder.decodeOpcode(0b00111100000000)).isEqualTo(CommandCode.INCFSZ);
    }

    @Test
    public void testIORWF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00010010000000)).isEqualTo(CommandCode.IORWF);
        assertThat(decoder.decodeOpcode(0b00010000000000)).isEqualTo(CommandCode.IORWF);
    }

    @Test
    public void testMOVF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00100010000000)).isEqualTo(CommandCode.MOVF);
        assertThat(decoder.decodeOpcode(0b00100000000000)).isEqualTo(CommandCode.MOVF);
    }

    @Test
    public void testMOVWF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000010000000)).isEqualTo(CommandCode.MOVWF);
    }

    @Test
    public void testNOP() throws Exception {
        assertThat(decoder.decodeOpcode(0b00000000000000)).isEqualTo(CommandCode.NOP);
    }

    @Test
    public void testRLF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00110110000000)).isEqualTo(CommandCode.RLF);
        assertThat(decoder.decodeOpcode(0b00110100000000)).isEqualTo(CommandCode.RLF);
    }

    @Test
    public void testRRF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00110010000000)).isEqualTo(CommandCode.RRF);
        assertThat(decoder.decodeOpcode(0b00110000000000)).isEqualTo(CommandCode.RRF);
    }

    @Test
    public void testSUBWF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00001010000000)).isEqualTo(CommandCode.SUBWF);
        assertThat(decoder.decodeOpcode(0b00001000000000)).isEqualTo(CommandCode.SUBWF);
    }

    @Test
    public void testSWAPF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00111010000000)).isEqualTo(CommandCode.SWAPF);
        assertThat(decoder.decodeOpcode(0b00111000000000)).isEqualTo(CommandCode.SWAPF);
    }

    @Test
    public void testXORWF() throws Exception {
        assertThat(decoder.decodeOpcode(0b00011010000000)).isEqualTo(CommandCode.XORWF);
        assertThat(decoder.decodeOpcode(0b00011000000000)).isEqualTo(CommandCode.XORWF);
    }
}