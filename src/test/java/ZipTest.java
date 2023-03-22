import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipTest {

    private ClassLoader cl = ZipTest.class.getClassLoader();
    @Test
    void  zipTest () throws Exception {
        try (
                InputStream isf = cl.getClass().getResourceAsStream("zipArchive.zip");
                ZipInputStream zipFile = new ZipInputStream(isf)
        ) {

                ZipEntry entry;

                while ((entry = zipFile.getNextEntry()) != null) {

                    if (entry.getName().contains("CsvDocFile")) {
                        CSVReader csvFile = new CSVReader(new InputStreamReader(zipFile));
                        List<String[]> csvContent = csvFile.readAll();
                        Assertions.assertArrayEquals(new String[]{"Dog, Busia"}, csvContent.get(0));
                    } else if (entry.getName().contains("PdfDoc")) {
                        PDF pdfFile = new PDF(zipFile);
                        Assertions.assertTrue((pdfFile.text).contains("Good night mother"));
                    } else if (entry.getName().contains("XlsDoc")) {
                        XLS xlsFile = new XLS(zipFile);
                        Assertions.assertTrue(
                                xlsFile.excel.getSheetAt(0).getRow(5).getCell(1).
                                        getStringCellValue().contains("March")
                        );

                    }

                }

        }

    }

}
