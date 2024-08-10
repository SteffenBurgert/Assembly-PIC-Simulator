package assembly.pic.simulator.akku.service;

import assembly.pic.simulator.akku.assembly_file.AssemblyFile;
import assembly.pic.simulator.akku.assembly_file.lst.LstOpcodeAndLine;
import assembly.pic.simulator.model.assembly_file.LstFileModel;
import assembly.pic.simulator.service.assembly_file_reader.AssemblyLstFileReader;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AssemblyFileReaderTest {

    private final AssemblyLstFileReader assemblyFileReader = new AssemblyLstFileReader();

    @Test
    void testReadFile_readFile() throws IOException {
        AssemblyFile<LstFileModel, LstOpcodeAndLine> assemblyFile = assemblyFileReader.readFile(new MockMultipartFile("file", "test.txt", "text/plain", new FileInputStream("src/test/resources/testReadFile.LST")));

        assertThat(assemblyFile.getFile()).hasSize(15);
        assertThat(assemblyFile.getFile())
                .first()
                .satisfies(fileLine -> {
                    assertThat(fileLine.isDebug()).isFalse();
                    assertThat(fileLine.getLine()).isEqualTo("");
                    assertThat(fileLine.getOpcode()).isEqualTo("");
                    assertThat(fileLine.getAssemblyCode()).isEqualTo("                    00001           ;testReadFileLST");
                });
        assertThat(assemblyFile.getFile())
                .element(3)
                .satisfies(fileLine -> {
                    assertThat(fileLine.isDebug()).isFalse();
                    assertThat(fileLine.getLine()).isEqualTo("0000");
                    assertThat(fileLine.getOpcode()).isEqualTo("3011");
                    assertThat(fileLine.getAssemblyCode()).isEqualTo("          00004           movlw 11h           ;W = 11h");
                });
        assertThat(assemblyFile.getFile())
                .element(12)
                .satisfies(fileLine -> {
                    assertThat(fileLine.isDebug()).isFalse();
                    assertThat(fileLine.getLine()).isEqualTo("0006");
                    assertThat(fileLine.getOpcode()).isEqualTo("2806");
                    assertThat(fileLine.getAssemblyCode()).isEqualTo("          00013           goto ende");
                });
        assertThat(assemblyFile.getFile())
                .last()
                .satisfies(fileLine -> {
                    assertThat(fileLine.isDebug()).isFalse();
                    assertThat(fileLine.getLine()).isEqualTo("");
                    assertThat(fileLine.getOpcode()).isEqualTo("");
                    assertThat(fileLine.getAssemblyCode()).isEqualTo("                    00015");
                });
    }

    @Test
    void testReadFile_operations() throws IOException {
        AssemblyFile<LstFileModel, LstOpcodeAndLine> assemblyFile = assemblyFileReader.readFile(new MockMultipartFile("file", "test.txt", "text/plain", new FileInputStream("src/test/resources/testReadFile.LST")));

        assertThat(assemblyFile.getOperations()).hasSize(7);
        assertThat(assemblyFile.getOperations().get(0)).satisfies(val -> {
            assertThat(val.getLine()).isEqualTo(3);
            assertThat(val.getOpcode()).isEqualTo(0x3011);
        });
        assertThat(assemblyFile.getOperations().get(1)).satisfies(val -> {
            assertThat(val.getLine()).isEqualTo(4);
            assertThat(val.getOpcode()).isEqualTo(0x3930);
        });
        assertThat(assemblyFile.getOperations().get(2)).satisfies(val -> {
            assertThat(val.getLine()).isEqualTo(5);
            assertThat(val.getOpcode()).isEqualTo(0x380D);
        });
        assertThat(assemblyFile.getOperations().get(3)).satisfies(val -> {
            assertThat(val.getLine()).isEqualTo(6);
            assertThat(val.getOpcode()).isEqualTo(0x3C3D);
        });
        assertThat(assemblyFile.getOperations().get(4)).satisfies(val -> {
            assertThat(val.getLine()).isEqualTo(7);
            assertThat(val.getOpcode()).isEqualTo(0x3A20);
        });
        assertThat(assemblyFile.getOperations().get(5)).satisfies(val -> {
            assertThat(val.getLine()).isEqualTo(8);
            assertThat(val.getOpcode()).isEqualTo(0x3E25);
        });
        assertThat(assemblyFile.getOperations().get(6)).satisfies(val -> {
            assertThat(val.getLine()).isEqualTo(12);
            assertThat(val.getOpcode()).isEqualTo(0x2806);
        });
    }

    @Test
    void testReadFile_notExistingFile() {
        assertThatThrownBy(() -> assemblyFileReader.readFile(new MockMultipartFile("file", "test.txt", "text/plain", new FileInputStream("some-not-existing.LTS")))).isInstanceOf(IOException.class)
                .hasMessageContaining("some-not-existing.LTS (No such file or directory)");
    }

}