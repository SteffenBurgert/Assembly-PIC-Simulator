package service;

import akku.AssemblyFile;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.File;
import java.util.List;
import java.util.Map;

class AssemblyFileReaderTest {

    private static final AssemblyFileReader assemblyFileReader = new AssemblyFileReader();

    @Test
    void testReadFile_readFile() {
        AssemblyFile assemblyFile = assemblyFileReader.readFile(new File("src/test/resources/testReadFile.LST"));

        List<String> readerFile = assemblyFile.getFile();
        assertThat(readerFile).hasSize(15);
        assertThat(readerFile.getFirst()).isEqualTo("                    00001           ;testReadFileLST");
        assertThat(readerFile.get(3)).isEqualTo("0000 3011           00004           movlw 11h           ;W = 11h");
        assertThat(readerFile.get(12)).isEqualTo("0006 2806           00013           goto ende");
        assertThat(readerFile.getLast()).isEqualTo("                    00015");
    }

    @Test
    void testReadFile_operations() {
        AssemblyFile assemblyFile = assemblyFileReader.readFile(new File("src/test/resources/testReadFile.LST"));

        Map<Integer, Integer> operations = assemblyFile.getOperations();
        assertThat(operations).hasSize(7);
        assertThat(operations.get(0)).isEqualTo(12305);
        assertThat(operations.get(1)).isEqualTo(14640);
        assertThat(operations.get(2)).isEqualTo(14349);
        assertThat(operations.get(3)).isEqualTo(15421);
        assertThat(operations.get(4)).isEqualTo(14880);
        assertThat(operations.get(5)).isEqualTo(15909);
        assertThat(operations.get(6)).isEqualTo(10246);
    }

    @Test
    void testReadFile_notExistingFile() {
        assertThatThrownBy(() -> assemblyFileReader.readFile(new File("some-not-existing.LTS"))).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("java.io.FileNotFoundException: some-not-existing.LTS (No such file or directory)");
    }

}