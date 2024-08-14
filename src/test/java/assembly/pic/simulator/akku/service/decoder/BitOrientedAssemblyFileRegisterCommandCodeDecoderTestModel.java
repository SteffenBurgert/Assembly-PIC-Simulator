package assembly.pic.simulator.akku.service.decoder;

import assembly.pic.simulator.service.decoder.CommandCode;
import org.junit.jupiter.api.Test;
import assembly.pic.simulator.service.Decoder;

import static org.assertj.core.api.Assertions.assertThat;

class BitOrientedAssemblyFileRegisterCommandCodeDecoderTestModel {

    private final Decoder decoder = new Decoder();

    @Test
    void testBCF() throws Exception {
        assertThat(decoder.decodeOpcode(0b01001110000000)).isEqualTo(CommandCode.BCF);
    }

    @Test
    void testBSF() throws Exception {
        assertThat(decoder.decodeOpcode(0b01011110000000)).isEqualTo(CommandCode.BSF);
    }

    @Test
    void testBTFSC() throws Exception {
        assertThat(decoder.decodeOpcode(0b01101110000000)).isEqualTo(CommandCode.BTFSC);
    }

    @Test
    void testBTFSS() throws Exception {
        assertThat(decoder.decodeOpcode(0b01111110000000)).isEqualTo(CommandCode.BTFSS);
    }

}