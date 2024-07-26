package assembly.pic.simulator.akku;

import assembly.pic.simulator.model.FileUpload;

import java.util.*;

public class AssemblyFile {

    private List<FileUpload> file = new ArrayList<>();
    private Map<Integer, Integer> operations = new HashMap<>();

    public void setFile(List<FileUpload> file) {
        this.file = file;
    }

    public void setOperations(Map<Integer, Integer> operations) {
        this.operations = operations;
    }

    public List<FileUpload> getFileUpload() {
        return file;
    }

    public Map<Integer, Integer> getOperations() {
        return operations;
    }
}
