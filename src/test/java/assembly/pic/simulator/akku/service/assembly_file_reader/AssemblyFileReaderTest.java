package assembly.pic.simulator.akku.service.assembly_file_reader;

import assembly.pic.simulator.akku.assembly_file.AssemblyFile;
import assembly.pic.simulator.akku.assembly_file.lst.AssemblyLstFile;
import assembly.pic.simulator.akku.assembly_file.lst.LstOpcodeAndLine;
import assembly.pic.simulator.model.assembly_file.LstLineModel;
import assembly.pic.simulator.service.assembly_file_reader.AssemblyLstFileReader;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AssemblyFileReaderTest {

    private final AssemblyLstFileReader assemblyFileReader = new AssemblyLstFileReader();
    private final LogCaptor LOGGER = LogCaptor.forClass(AssemblyLstFileReader.class);

    @AfterEach
    void cleanUp() {
        LOGGER.clearLogs();
    }

    @Test
    void testReadFile_readFile() {
        AssemblyFile<LstLineModel, LstOpcodeAndLine> assemblyFile = new AssemblyLstFile(null, null);
        try {
            assemblyFile = assemblyFileReader.readFile(new MockMultipartFile("file", "testReadFile.LST", "text/plain", new FileInputStream("src/test/resources/testReadFile.LST")));
        } catch (Exception ignored) {
        }

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

        assertThat(LOGGER.getInfoLogs()).hasSize(2);
        assertThat(LOGGER.getWarnLogs()).hasSize(0);
        assertThat(LOGGER.getErrorLogs()).hasSize(0);
        assertThat(LOGGER.getInfoLogs()).containsExactly(
                "Started reading testReadFile.LST file.",
                "Finished reading testReadFile.LST file."
        );
    }

    @Test
    void testReadFile_operations() {
        AssemblyFile<LstLineModel, LstOpcodeAndLine> assemblyFile = new AssemblyLstFile(null, null);
        try {
            assemblyFile = assemblyFileReader.readFile(new MockMultipartFile("file", "testReadFile.LST", "text/plain", new FileInputStream("src/test/resources/testReadFile.LST")));
        } catch (Exception ignored) {
        }

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

        assertThat(LOGGER.getInfoLogs()).hasSize(2);
        assertThat(LOGGER.getWarnLogs()).hasSize(0);
        assertThat(LOGGER.getErrorLogs()).hasSize(0);
        assertThat(LOGGER.getInfoLogs()).containsExactly(
                "Started reading testReadFile.LST file.",
                "Finished reading testReadFile.LST file."
        );
    }

    @Test
    void testReadFile_notExistingFile() {
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);

        try {
            Mockito.when(mockFile.getInputStream()).thenThrow(new IOException("(No such file or directory)"));
        } catch (Exception ignored) {
        }

        assertThatThrownBy(() -> assemblyFileReader.readFile(mockFile));

        assertThat(LOGGER.getInfoLogs()).hasSize(0);
        assertThat(LOGGER.getWarnLogs()).hasSize(1);
        assertThat(LOGGER.getErrorLogs()).hasSize(0);
        assertThat(LOGGER.getWarnLogs()).containsExactly(
                "Problem with reading File: null error message: (No such file or directory)"
        );
    }

}