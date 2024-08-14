package assembly.pic.simulator.service.assembly_file_reader;

import assembly.pic.simulator.akku.assembly_file.lst.AssemblyLstFile;
import assembly.pic.simulator.akku.assembly_file.lst.LstOpcodeAndLine;
import assembly.pic.simulator.model.assembly_file.LstLineModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;

@Service
public class AssemblyLstFileReader {

    private final Logger log = Logger.getLogger(this.getClass().getName());

    public AssemblyLstFile readFile(MultipartFile file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), Charset.forName("ISO-8859-15")))) {
            log.info("Started reading " + file.getOriginalFilename() + " file.");
            String line = bufferedReader.readLine();
            List<LstLineModel> lstLines = new ArrayList<>();
            Map<Integer, LstOpcodeAndLine> assemblerArguments = new HashMap<>();
            int lineCounter = 0;

            while (line != null) {
                String[] splitLine = line.split(" ");
                lstLines.add(mapLstFile(splitLine));
                mapOperations(splitLine, assemblerArguments, lineCounter++);
                line = bufferedReader.readLine();
            }

            log.info("Finished reading " + file.getOriginalFilename() + " file.");
            return new AssemblyLstFile(lstLines, assemblerArguments);
        } catch (IOException e) {
            log.warning("Problem with reading File: " + file.getOriginalFilename() + " error message: " + e.getMessage());
            throw e;
        }
    }

    private LstLineModel mapLstFile(String[] line) {
        if (line.length > 2 && line[0].length() == 4 && line[1].length() == 4) {
            return new LstLineModel(false, line[0], line[1], joinAssemblyCodeString(line));
        }
        return new LstLineModel(false, "", "", String.join(" ", line));
    }

    private String joinAssemblyCodeString(String[] array) {
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
