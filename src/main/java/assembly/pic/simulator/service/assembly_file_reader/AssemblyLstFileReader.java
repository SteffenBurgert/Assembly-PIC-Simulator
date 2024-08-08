package assembly.pic.simulator.service.assembly_file_reader;

import assembly.pic.simulator.akku.assembly_file.lst.AssemblyLstFile;
import assembly.pic.simulator.akku.assembly_file.lst.LstOpcodeAndLine;
import assembly.pic.simulator.model.assembly_file.LstFileModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;

@Service
public class AssemblyLstFileReader {

    private static final Logger LOGGER = Logger.getLogger(AssemblyLstFile.class.getName());

    public AssemblyLstFile readFile(MultipartFile file) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), Charset.forName("ISO-8859-15")))) {
            String line = bufferedReader.readLine();
            List<LstFileModel> readFile = new ArrayList<>();
            Map<Integer, LstOpcodeAndLine> assemblerArguments = new HashMap<>();
            int lineCounter = 0;

            while (line != null) {
                readFile.add(mapLstFile(line));
                mapOperations(line, assemblerArguments, lineCounter++);
                line = bufferedReader.readLine();
            }

            return setAssemblyFile(readFile, assemblerArguments);
        } catch (IOException e) {
            LOGGER.warning("Problem with reading File: " + file.getName() + " error message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private LstFileModel mapLstFile(String line) {
        String[] splitLine = line.split(" ");
        if (splitLine[0].length() == 4 && splitLine[1].length() == 4) {
            return new LstFileModel(false, splitLine[0], splitLine[1], line.substring(20));
        }
        return new LstFileModel(false, "", "", line.substring(20));
    }

    private void mapOperations(String line, Map<Integer, LstOpcodeAndLine> assemblerArguments, int lineCounter) {
        String[] splitLine = line.split(" ");
        if (splitLine[0].length() == 4 && splitLine[1].length() == 4) {
            assemblerArguments.put(
                    Integer.parseInt(splitLine[0], 16),
                    new LstOpcodeAndLine(Integer.parseInt(splitLine[1], 16), lineCounter)
            );
        }
    }

    private AssemblyLstFile setAssemblyFile(List<LstFileModel> readFile, Map<Integer, LstOpcodeAndLine> assemblerArguments) {
        AssemblyLstFile assemblyLstFile = new AssemblyLstFile();
        assemblyLstFile.setFile(readFile);
        assemblyLstFile.setOperations(assemblerArguments);
        return assemblyLstFile;
    }

}
