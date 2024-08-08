package assembly.pic.simulator.model.assembly_file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class IOPinsModel {
    @JsonProperty("portA")
    private List<Integer> portA;
    @JsonProperty("portB")
    private List<Integer> portB;
    @JsonProperty("trisA")
    private List<Integer> trisA;
    @JsonProperty("trisB")
    private List<Integer> trisB;
}
