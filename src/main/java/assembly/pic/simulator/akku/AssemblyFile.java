package assembly.pic.simulator.akku;

import java.util.*;

public class AssemblyFile {

    private List<String> file = new ArrayList<>();
    private Map<Integer, Integer> operations = new HashMap<>();

    public void setFile(List<String> file) {
        this.file = file;
    }

    public void setOperations(Map<Integer, Integer> operations) {
        this.operations = operations;
    }

    public List<String> getFile() {
        return file;
    }

    public Map<Integer, Integer> getOperations() {
        return operations;
    }
}
