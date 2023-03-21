import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonCleverTest {
    @Test
    void jsonTest () throws Exception {
        Gson myPet = new Gson();
        try (
                InputStream inputFile = getClass().getResourceAsStream("mypet.json");
                InputStreamReader readerFile = new InputStreamReader(inputFile)
        ) {
            Busya busya = myPet.fromJson(readerFile, Busya.class);
            Assertions.assertEquals("shpitz", busya.breed);
            Assertions.assertTrue(busya.isDog);
            List<String> toCompare = List.of("red", "black", "white");
            Assertions.assertEquals(toCompare, busya.color);
        }

    }
}
