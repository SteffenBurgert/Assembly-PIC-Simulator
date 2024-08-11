package assembly.pic.simulator.model.assembly_file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LstLineModel {

    @JsonProperty("isDebug")
    private boolean isDebug;
    @JsonProperty("line")
    private String line;
    @JsonProperty("opcode")
    private String opcode;
    @JsonProperty("assemblyCode")
    private String assemblyCode;
}
