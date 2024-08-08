package assembly.pic.simulator.model.assembly_file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AssemblyFileModel {

    @JsonProperty("fileName")
    private String fileName;
    @JsonProperty("fileType")
    private FileType fileType = FileType.UNDEFINED;
    @JsonProperty("ram")
    private RamModel ram;
    @JsonProperty("ioPins")
    private IOPinsModel ioPinsModel;
    @JsonProperty("lstFile")
    private List<LstFileModel> lstFileModel;

    public AssemblyFileModel(FileType fileType, RamModel ram, IOPinsModel ioPinsModel, List<LstFileModel> lstFileModel) {
        this.fileType = fileType;
        this.ioPinsModel = ioPinsModel;
        this.ram = ram;
        this.lstFileModel = lstFileModel;
    }
}
