import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonCleverTest {

    ClassLoader cl = JsonCleverTest.class.getClassLoader();

    @Test
    void jsonTest () throws Exception {
        ObjectMapper myPet = new ObjectMapper();
        try (
                InputStream inputFile = cl.getResourceAsStream("mypet.json");
                InputStreamReader readerFile = new InputStreamReader(inputFile)
        ) {
            Busya busya = myPet.readValue(readerFile, Busya.class);
            Assertions.assertEquals("shpitz", busya.breed);
            Assertions.assertTrue(busya.isDog);
            List<String> toCompare = List.of("red", "black", "white");
            Assertions.assertEquals(toCompare, busya.color);
        }

    }
}
