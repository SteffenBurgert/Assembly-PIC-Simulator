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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("testing")
class AssemblyFileReaderTest {

    private final AssemblyLstFileReader assemblyFileReader = new AssemblyLstFileReader();
    private final LogCaptor logCaptor = LogCaptor.forClass(AssemblyLstFileReader.class);

    @AfterEach
    void cleanUp() {
        logCaptor.clearLogs();
    }

    @Test
    void testReadFile_readFile() throws IOException {
        new AssemblyLstFile(null, null);
        AssemblyFile<LstLineModel, LstOpcodeAndLine> assemblyFile;
        assemblyFile = assemblyFileReader.readFile(
                new MockMultipartFile("file", "testReadFile.LST", "text/plain",
                        new FileInputStream("src/test/resources/testReadFile.LST")));

        assertThat(assemblyFile.getFile()).hasSize(15);
        assertThat(assemblyFile.getFile())
                .first()
                .satisfies(fileLine -> {
                    assertThat(fileLine.isDebug()).isFalse();
                    assertThat(fileLine.getLine()).isEmpty();
                    assertThat(fileLine.getOpcode()).isEmpty();
                    assertThat(fileLine.getAssemblyCode()).isEqualTo(
                            "                    00001           ;testReadFileLST");
                });
        assertThat(assemblyFile.getFile())
                .element(3)
                .satisfies(fileLine -> {
                    assertThat(fileLine.isDebug()).isFalse();
                    assertThat(fileLine.getLine()).isEqualTo("0000");
                    assertThat(fileLine.getOpcode()).isEqualTo("3011");
                    assertThat(fileLine.getAssemblyCode()).isEqualTo(
                            "          00004           movlw 11h           ;W = 11h");
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
                    assertThat(fileLine.getLine()).isEmpty();
                    assertThat(fileLine.getOpcode()).isEmpty();
                    assertThat(fileLine.getAssemblyCode()).isEqualTo("                    00015");
                });

        assertThat(logCaptor.getInfoLogs()).isEmpty();
        assertThat(logCaptor.getDebugLogs()).hasSize(2);
        assertThat(logCaptor.getWarnLogs()).isEmpty();
        assertThat(logCaptor.getErrorLogs()).isEmpty();
        assertThat(logCaptor.getDebugLogs()).containsExactly(
                "Started reading testReadFile.LST file.",
                "Finished reading testReadFile.LST file."
        );
    }

    @Test
    void testReadFile_operations() throws IOException {
        AssemblyFile<LstLineModel, LstOpcodeAndLine> assemblyFile;
        assemblyFile = assemblyFileReader.readFile(
                new MockMultipartFile("file", "testReadFile.LST", "text/plain",
                        new FileInputStream("src/test/resources/testReadFile.LST")));

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

        assertThat(logCaptor.getInfoLogs()).isEmpty();
        assertThat(logCaptor.getDebugLogs()).hasSize(2);
        assertThat(logCaptor.getWarnLogs()).isEmpty();
        assertThat(logCaptor.getErrorLogs()).isEmpty();
        assertThat(logCaptor.getDebugLogs()).containsExactly(
                "Started reading testReadFile.LST file.",
                "Finished reading testReadFile.LST file."
        );
    }

    @Test
    void testReadFile_notExistingFile() throws IOException {
        MultipartFile mockFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockFile.getInputStream())
                .thenThrow(new IOException("(No such file or directory)"));

        assertThatThrownBy(() -> assemblyFileReader.readFile(mockFile)).isInstanceOf(IOException.class);
        assertThat(logCaptor.getInfoLogs()).isEmpty();
        assertThat(logCaptor.getDebugLogs()).isEmpty();
        assertThat(logCaptor.getWarnLogs()).isEmpty();
        assertThat(logCaptor.getErrorLogs()).isEmpty();
    }
}