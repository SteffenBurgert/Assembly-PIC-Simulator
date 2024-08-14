package assembly.pic.simulator.akku.service;

import assembly.pic.simulator.akku.assembly_file.lst.AssemblyLstFile;
import assembly.pic.simulator.akku.assembly_file.lst.LstOpcodeAndLine;
import assembly.pic.simulator.exeption.Result;
import assembly.pic.simulator.mapper.RamModelMapper;
import assembly.pic.simulator.model.assembly_file.*;
import assembly.pic.simulator.service.AssemblyCompilerService;
import assembly.pic.simulator.service.assembly_file_reader.AssemblyLstFileReader;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AssemblyCompilerServiceTest {

    private final LogCaptor log = LogCaptor.forClass(AssemblyCompilerService.class);

    @AfterEach
    void cleanUp() {
        log.clearLogs();
    }

    @Test
    void uploadLstFile() throws IOException {
        AssemblyLstFileReader assemblyLstFileReaderMock = Mockito.mock(AssemblyLstFileReader.class);

        AssemblyCompilerService assemblyCompilerService = new AssemblyCompilerService(
                Mockito.mock(RamModelMapper.class),
                assemblyLstFileReaderMock
        );

        String fileName = "testReadFile.LST";

        AssemblyLstFile assemblyLstFileMock = new AssemblyLstFile(
                List.of(new LstLineModel(false, "line", "opcode", "assemblyCode")),
                Map.of(0, new LstOpcodeAndLine(42, 0x1337))
        );

        Result<AssemblyFileModel> assemblyFileModel = null;

        MockMultipartFile file = new MockMultipartFile("file", fileName, "text/plain", new FileInputStream("src/test/resources/testReadFile.LST"));
        Mockito.when(assemblyLstFileReaderMock.readFile(file)).thenReturn(assemblyLstFileMock);

        assemblyFileModel = assemblyCompilerService.initializeSimulator(file);

        assert assemblyFileModel != null;
        assertThat(assemblyFileModel.getValue()).satisfies(
                asmFile -> {
                    assertThat(asmFile.getFileName()).isEqualTo(fileName);
                    assertThat(asmFile.getFileType()).isEqualTo(FileType.LST);
                    assertThat(asmFile.getLstLineModel()).satisfies(lstLines -> {
                        assertThat(lstLines).hasSize(1);
                        assertThat(lstLines).first().satisfies(lstLine -> {
                            assertThat(lstLine.isDebug()).isFalse();
                            assertThat(lstLine.getLine()).isEqualTo("line");
                            assertThat(lstLine.getOpcode()).isEqualTo("opcode");
                            assertThat(lstLine.getAssemblyCode()).isEqualTo("assemblyCode");
                        });
                    });
                }
        );

        assertThat(log.getInfoLogs()).hasSize(1);
        assertThat(log.getWarnLogs()).isEmpty();
        assertThat(log.getErrorLogs()).isEmpty();
        assertThat(log.getInfoLogs()).containsExactly(
                "Assembly model for " + fileName + " build successfully."
        );
    }

    @Test
    void uploadFaultyLstFile() throws IOException {
        AssemblyLstFileReader assemblyLstFileReaderMock = Mockito.mock(AssemblyLstFileReader.class);

        AssemblyCompilerService assemblyCompilerService = new AssemblyCompilerService(
                Mockito.mock(RamModelMapper.class),
                assemblyLstFileReaderMock
        );

        String fileName = "testReadFile.LST";

        Result<AssemblyFileModel> assemblyFileModel = null;

        MockMultipartFile file = new MockMultipartFile("file", fileName, "text/plain", new FileInputStream("src/test/resources/testReadFile.LST"));
        Mockito.when(assemblyLstFileReaderMock.readFile(file)).thenThrow(IOException.class);

        assemblyFileModel = assemblyCompilerService.initializeSimulator(file);

        assert assemblyFileModel != null;
        assertThat(assemblyFileModel.getError()).satisfies(
                error -> {
                    assertThat(error).isInstanceOf(IOException.class);
                    assertThat(error.getMessage()).isEqualTo("Couldn't read lst file.");
                }
        );

        assertThat(log.getInfoLogs()).isEmpty();
        assertThat(log.getWarnLogs()).hasSize(1);
        assertThat(log.getErrorLogs()).isEmpty();
        assertThat(log.getWarnLogs()).containsExactly(
                "Couldn't read lst file. Reason: null"
        );
    }

    @Test
    void uploadUnknownFile() throws IOException {
        AssemblyLstFileReader assemblyLstFileReaderMock = Mockito.mock(AssemblyLstFileReader.class);

        AssemblyCompilerService assemblyCompilerService = new AssemblyCompilerService(
                Mockito.mock(RamModelMapper.class),
                assemblyLstFileReaderMock
        );

        String fileName = "some-not-existing-file.unknown";

        Result<AssemblyFileModel> assemblyFileModel = null;

        MockMultipartFile file = new MockMultipartFile("file", fileName, "text/plain", new FileInputStream("src/test/resources/testReadFile.LST"));
        Mockito.when(assemblyLstFileReaderMock.readFile(file)).thenThrow(IOException.class);

        assemblyFileModel = assemblyCompilerService.initializeSimulator(file);

        assert assemblyFileModel != null;
        assertThat(assemblyFileModel.getError()).satisfies(
                error -> {
                    assertThat(error).isInstanceOf(Exception.class);
                    assertThat(error.getMessage()).isEqualTo("Unknown filetype: unknown");
                }
        );

        assertThat(log.getInfoLogs()).isEmpty();
        assertThat(log.getWarnLogs()).hasSize(1);
        assertThat(log.getErrorLogs()).isEmpty();
        assertThat(log.getWarnLogs()).containsExactly(
                "Unknown filetype: unknown"
        );
    }
}
