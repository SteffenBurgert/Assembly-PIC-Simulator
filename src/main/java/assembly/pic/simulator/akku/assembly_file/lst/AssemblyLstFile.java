package assembly.pic.simulator.akku.assembly_file.lst;

import assembly.pic.simulator.akku.assembly_file.AssemblyFile;
import assembly.pic.simulator.model.assembly_file.LstFileModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class AssemblyLstFile implements AssemblyFile<LstFileModel, LstOpcodeAndLine> {

    private List<LstFileModel> file;
    private Map<Integer, LstOpcodeAndLine> operations;
}
