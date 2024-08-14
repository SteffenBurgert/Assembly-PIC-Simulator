package assembly.pic.simulator.akku.assembly_file.lst;

import assembly.pic.simulator.akku.assembly_file.AssemblyFile;
import assembly.pic.simulator.model.assembly_file.LstLineModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class AssemblyLstFile implements AssemblyFile<LstLineModel, LstOpcodeAndLine> {

    private List<LstLineModel> file;
    private Map<Integer, LstOpcodeAndLine> operations;
}
