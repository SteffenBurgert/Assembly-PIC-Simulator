package assembly.pic.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FileUpload {

    @JsonProperty("isDebugg")
    private boolean isDebugg;
    @JsonProperty("line")
    private String line;
    @JsonProperty("opcode")
    private String opcode;
    @JsonProperty("assemblyCode")
    private String assemblyCode;

    public FileUpload(boolean isDebugg, String line, String opcode, String assemblyCode) {
        this.isDebugg = isDebugg;
        this.line = line;
        this.opcode = opcode;
        this.assemblyCode = assemblyCode;
    }
}
