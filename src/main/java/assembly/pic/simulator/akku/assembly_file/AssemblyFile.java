package assembly.pic.simulator.akku.assembly_file;

import java.util.List;
import java.util.Map;

public interface AssemblyFile<F, O> {

    void setFile(List<F> file);

    void setOperations(Map<Integer, O> operations);

    List<F> getFile();

    Map<Integer, O> getOperations();
}
