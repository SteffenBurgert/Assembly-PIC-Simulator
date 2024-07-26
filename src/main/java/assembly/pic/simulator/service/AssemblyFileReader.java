package assembly.pic.simulator.service;

import assembly.pic.simulator.akku.AssemblyFile;
import assembly.pic.simulator.model.FileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;

public class AssemblyFileReader {

    private static final Logger LOGGER = Logger.getLogger(AssemblyFile.class.getName());

    public AssemblyFile readFile(MultipartFile file) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), Charset.forName("ISO-8859-15")))) {
            String line = bufferedReader.readLine();
            List<FileUpload> readFile = new ArrayList<>();
            Map<Integer, Integer> assemblerArguments = new HashMap<>();

            while (line != null) {
                readFile.add(mapUploadFile(line));
                mapOperations(line, assemblerArguments);
                line = bufferedReader.readLine();
            }

            return setAssemblyFile(readFile, assemblerArguments);
        } catch (IOException e) {
            LOGGER.warning("Problem with reading File: " + file.getName() + " error message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private FileUpload mapUploadFile(String line) {
        String[] splitLine = line.split(" ");
        FileUpload fileUpload;
        if (splitLine[0].length() == 4 && splitLine[1].length() == 4) {
            // TODO: int refactoring
            fileUpload = new FileUpload(false, splitLine[0], splitLine[1], line.substring(20));
        } else {
            fileUpload = new FileUpload(false, "", "", line.substring(20));
        }

        return fileUpload;
    }

    private void mapOperations(String line, Map<Integer, Integer> assemblerArguments) {
        String[] splitLine = line.split(" ");
        if (splitLine[0].length() == 4 && splitLine[1].length() == 4) {
            assemblerArguments.put(Integer.parseInt(splitLine[0], 16), Integer.parseInt(splitLine[1], 16));
        }
    }

    private AssemblyFile setAssemblyFile(List<FileUpload> readFile, Map<Integer, Integer> assemblerArguments) {
        AssemblyFile assemblyFile = new AssemblyFile();
        assemblyFile.setFile(readFile);
        assemblyFile.setOperations(assemblerArguments);
        return assemblyFile;
    }

}
