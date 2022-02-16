package pageObject.testCases;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class csvTest {

    private static final String SAMPLE_CSV_FILE_PATH = "./" + CreateCVS.fileName;

    @Test
    public void UpdateExcel() throws IOException{
        try (
                Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                CSVReader csvReader = new CSVReader(reader);
        ) {

            ArrayList<ColumItem> list = new ArrayList<>();
            String[] nextRecord;
            int i = 0;
            while ((nextRecord = csvReader.readNext()) != null) {
                //TODO: ELIMINAR SYSOS

                String id = nextRecord[0];
                String firstName = nextRecord[1];
                String lastName = nextRecord[2];
                String subject1 = nextRecord[3];
                String subject2 = nextRecord[4];
                String subject3 = nextRecord[5];
                String subject4 = nextRecord[6];
                String subject5 = nextRecord[7];

                if (i > 0) {
                    String average = calculateAverage(subject1, subject2, subject3, subject4, subject5);
                    list.add(new ColumItem(id, firstName, lastName, subject1, subject2, subject3,
                            subject4, subject5, average));
                }
                i++;
            }
            updateCVSWithAverage(list);
        }
    }

    private static void updateCVSWithAverage(ArrayList<ColumItem> listColumItems) throws IOException {
        CSVWriter csvWriter = new CSVWriter(new FileWriter(CreateCVS.fileName));
        CreateCVS.createTitleColum(csvWriter, new String[]{
                CreateCVS.id,
                CreateCVS.firstname,
                CreateCVS.lastname,
                CreateCVS.subject1,
                CreateCVS.subject2,
                CreateCVS.subject3,
                CreateCVS.subject4,
                CreateCVS.subject5,
                "Average"});

        for (ColumItem columItem : listColumItems) {
            csvWriter.writeNext(new String[]{
                    columItem.id, columItem.firstName, columItem.lastName, columItem.subject1, columItem.subject2,
                    columItem.subject3, columItem.subject4, columItem.subject5, columItem.average
            });
        }
        csvWriter.close();
    }

    private static String calculateAverage(String subject1, String subject2, String subject3, String subject4, String subject5) {
        Integer total = (Integer.valueOf(subject1) + Integer.valueOf(subject2) + Integer.valueOf(subject3) + Integer.valueOf(subject4) +
                Integer.valueOf(subject5)) / 5;
        return String.valueOf(total);
    }

    private static String[] createColum(int position, String firstName, String lastName, int subject1Qualification,
                                        int subject2Qualification, int subject3Qualification, int subject4Qualification,
                                        int subject5Qualification) {
        return new String[]{String.valueOf(position), firstName, lastName, String.valueOf(subject1Qualification),
                String.valueOf(subject2Qualification), String.valueOf(subject3Qualification), String.valueOf(subject4Qualification),
                String.valueOf(subject5Qualification)};
    }

    public static void createTitleColum(CSVWriter csvWriter, String[] arrayTitles) {
        csvWriter.writeNext(arrayTitles);
    }

    public static int getRandomQualification() {
        return ((int) (Math.random() * (200 - 0))) + 0;
    }

}









