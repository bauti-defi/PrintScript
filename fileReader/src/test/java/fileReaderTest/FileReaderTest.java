package fileReaderTest;

import austral.ingsis.FileReaderPS;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTest {

    @Test
    public void fileTest(){
        String filePath = "/Users/elias/projects/faculty/PrintScript/fileReader/src/main/resources/fileTest.txt";
        List<String> document = FileReaderPS.read(filePath);
        List<String> expectedList = new ArrayList<>(Arrays.asList("let x: number = 5;","let y: string = \"solo una prueba\";"));
        assertEquals(document, expectedList);
    }
}
