import static org.junit.jupiter.api.Assertions.assertEquals;

import austral.ingsis.FileReaderPS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileReaderTest {

  @Test
  public void fileTest() {
    String filePath = "src/test/java/script/test.txt";
    List<String> document = FileReaderPS.read(filePath);
    List<String> expectedList =
        new ArrayList<>(
            Arrays.asList("let x: number = 5;", "let y: string = \"solo una prueba\";"));
    assertEquals(document, expectedList);
  }
}
