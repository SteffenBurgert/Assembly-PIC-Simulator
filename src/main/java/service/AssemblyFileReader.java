package service;

import akku.AssemblyFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

public class AssemblyFileReader {

    private static final Logger LOGGER = Logger.getLogger(AssemblyFile.class.getName());

    public AssemblyFile readFile(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("ISO-8859-15")))) {
            String line = bufferedReader.readLine();
            List<String> readFile = new ArrayList<>();
            Map<Integer, Integer> assemblerArguments = new HashMap<>();

            while (line != null) {
                readFile.add(line);
                mapOperations(line, assemblerArguments);
                line = bufferedReader.readLine();
            }

            return setAssemblyFile(readFile, assemblerArguments);
        } catch (IOException e) {
            LOGGER.warning("Problem with reading File: " + file.getName() + " error message: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void mapOperations(String line, Map<Integer, Integer> assemblerArguments) {
        String[] splitLine = line.split(" ");
        if (splitLine[0].length() == 4 && splitLine[1].length() == 4) {
            assemblerArguments.put(Integer.parseInt(splitLine[0], 16), Integer.parseInt(splitLine[1], 16));
        }
    }

    private AssemblyFile setAssemblyFile(List<String> readFile, Map<Integer, Integer> assemblerArguments) {
        AssemblyFile assemblyFile = new AssemblyFile();
        assemblyFile.setFile(readFile);
        assemblyFile.setOperations(assemblerArguments);
        return assemblyFile;
    }

}
