package pageObject.testCases;

import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateCVS {

    static String fileName = "testCVS.csv";
    static String id = "ID";
    static String firstname = "Firstname";
    static String lastname = "Lastname";
    static String subject1= "Subject1";
    static String subject2= "Subject2";
    static String subject3= "Subject3";
    static String subject4= "Subject4";
    static String subject5= "Subject5";


    public static void main(String[] args) throws IOException {
        int size = 2000;
        Faker fakerNames = new Faker();

        CSVWriter csvWriter = new CSVWriter(new FileWriter(fileName));
        createTitleColum(csvWriter, new String[]{
                id,
                fileName,
                lastname,
                subject1,
                subject2,
                subject3,
                subject4,
                subject5});

        for (int i = 0; i < size; i++) {
            int subject1Qualification = getRandomQualification();
            int subject2Qualification = getRandomQualification();
            int subject3Qualification = getRandomQualification();
            int subject4Qualification = getRandomQualification();
            int subject5Qualification = getRandomQualification();

            csvWriter.writeNext(createColum(
                    i,
                    fakerNames.name().firstName(),
                    fakerNames.name().lastName(),
                    subject1Qualification,
                    subject2Qualification,
                    subject3Qualification,
                    subject4Qualification,
                    subject5Qualification));
        }

        csvWriter.close();
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

