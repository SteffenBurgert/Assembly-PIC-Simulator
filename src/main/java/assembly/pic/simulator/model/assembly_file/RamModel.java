package assembly.pic.simulator.model.assembly_file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RamModel {

    @JsonProperty("wRegister")
    private int wRegister;
    @JsonProperty("pc")
    private int pc;
    @JsonProperty("pcl")
    private int pcl;
    @JsonProperty("pclath")
    private int pclath;
    @JsonProperty("carry")
    private int carry;
    @JsonProperty("digitCarry")
    private int digitCarry;
    @JsonProperty("zeroFlag")
    private int zeroFlag;
    @JsonProperty("bank0")
    private List<Integer> bank0;
    @JsonProperty("bank1")
    private List<Integer> bank1;
    @JsonProperty("tos")
    private List<Integer> tos;

}
