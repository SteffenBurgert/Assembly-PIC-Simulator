package service;

import org.junit.jupiter.api.Test;
import service.decoder.CommandCode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DecoderTest {

    private static final Decoder decoder = new Decoder();

    @Test
    public void test_ByteOrientedDecoder() throws Exception {
        assertThat(decoder.decodeOpcode(0b00011110000000)).isEqualTo(CommandCode.ADDWF);
    }

    @Test
    public void test_BitOrientedDecoder() throws Exception {
        assertThat(decoder.decodeOpcode(0b01001110000000)).isEqualTo(CommandCode.BCF);
    }

    @Test
    public void test_LiteralAndControlDecoder() throws Exception {
        assertThat(decoder.decodeOpcode(0b11111000000000)).isEqualTo(CommandCode.ADDLW);
    }

    @Test
    public void test_ThrowsException() {
        assertThatThrownBy(() -> decoder.decodeOpcode(10000000)).isInstanceOf(Exception.class)
                .hasMessageContaining("Operation not Found");
    }
}