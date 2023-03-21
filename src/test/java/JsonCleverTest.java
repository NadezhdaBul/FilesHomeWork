import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

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
            Assertions.assertArrayEquals(List<String>"red,black, white", busya.color); //сравнить ожидаемый массив с ключом "color" с фактическим в объекте busia

        }

    }
}
