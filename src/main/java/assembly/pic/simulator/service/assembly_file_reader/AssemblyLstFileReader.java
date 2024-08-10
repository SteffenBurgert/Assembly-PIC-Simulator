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

    private static final Logger LOGGER = Logger.getLogger(AssemblyLstFileReader.class.getName());

    public AssemblyLstFile readFile(MultipartFile file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), Charset.forName("ISO-8859-15")))) {
            String line = bufferedReader.readLine();
            List<LstFileModel> readFile = new ArrayList<>();
            Map<Integer, LstOpcodeAndLine> assemblerArguments = new HashMap<>();
            int lineCounter = 0;

            while (line != null) {
                String[] splitLine = line.split(" ");
                readFile.add(mapLstFile(splitLine));
                mapOperations(splitLine, assemblerArguments, lineCounter++);
                line = bufferedReader.readLine();
            }

            return new AssemblyLstFile(readFile, assemblerArguments);
        } catch (IOException e) {
            LOGGER.warning("Problem with reading File: " + file.getName() + " error message: " + e.getMessage());
            throw e;
        }
    }

    private LstFileModel mapLstFile(String[] line) {
        if (line.length > 2 && line[0].length() == 4 && line[1].length() == 4) {
            return new LstFileModel(false, line[0], line[1], joinAssemblyCodeString(line));
        }
        return new LstFileModel(false, "", "", String.join(" ", line));
    }

    private static String joinAssemblyCodeString(String[] array) {
        return String.join(" ", Arrays.asList(array).subList(2, array.length));
    }

    private void mapOperations(String[] line, Map<Integer, LstOpcodeAndLine> assemblerArguments, int lineCounter) {
        if (line.length > 2 && line[0].length() == 4 && line[1].length() == 4) {
            assemblerArguments.put(
                    Integer.parseInt(line[0], 16),
                    new LstOpcodeAndLine(Integer.parseInt(line[1], 16), lineCounter)
            );
        }
    }
}
