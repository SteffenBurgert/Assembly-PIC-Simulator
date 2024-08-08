package assembly.pic.simulator.akku.assembly_file;

import java.util.List;
import java.util.Map;

public interface AssemblyFile <File, OpcodeAndLine> {

    void setFile(List<File> file);

    void setOperations(Map<Integer, OpcodeAndLine> operations);

    List<File> getFile();

    Map<Integer, OpcodeAndLine> getOperations();
}
