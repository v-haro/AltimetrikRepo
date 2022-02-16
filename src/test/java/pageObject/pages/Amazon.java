package pageObject.pages;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import java.io.*;
import java.util.List;

public class Amazon {

    WebDriver driver;
    //Constructor that will be automatically called as soon as the object of the class is created
    public Amazon(WebDriver driver) {
        this.driver=driver;
    }

    //Locators for the page title and the logout button
    By searchbar = By.id("twotabsearchtextbox");
    By logoutBtn = By.id("submit");
    By orderBy = By.className("a-dropdown-label");
    By cheapestOrder = By.id("s-result-sort-select_1");
    By articleName= By.className("a-size-medium");
    By starRating= By.className("a-icon-alt");
    By ratings= By.cssSelector("span[class='a-size-base s-underline-text']");
    By priceOffscreen= By.xpath("//span[@class='a-offscreen']");

    //Method to search in amazon webpage
    public void searchItemAmazonWebPage(String item) {
        driver.findElement(searchbar).clear();
        driver.findElement(searchbar).sendKeys(item);
        driver.findElement(searchbar).sendKeys(Keys.RETURN);
    }

    public void orderByPriceCheapestPrice(){
        driver.findElement(orderBy).click();
        driver.findElement(cheapestOrder).click();
    }

    public void saveAtributesOfArticles() throws InterruptedException, IOException {
        Thread.sleep(2000);
        List<WebElement> name=driver.findElements(articleName);
        System.out.println("name first article: "+name.get(0).getText());
        System.out.println("name second article: "+name.get(1).getText());

        List<WebElement> stars=driver.findElements(starRating);
        System.out.println("stars first article: "+stars.get(0).getAttribute("innerHTML"));
        System.out.println("stars second article: "+stars.get(1).getAttribute("innerHTML"));

        List<WebElement> rat=driver.findElements(ratings);
        System.out.println("rating first article: "+rat.get(0).getAttribute("innerHTML"));
        System.out.println("rating second article: "+rat.get(1).getAttribute("innerHTML"));

        List<WebElement> _priceO=driver.findElements(priceOffscreen);

        System.out.println("price first article: "+_priceO.get(0).getAttribute("innerHTML"));
        System.out.println("price first article: "+_priceO.get(1).getAttribute("innerHTML"));

        // Creating file object of existing excel file
        File xlsxFile = new File("./src/test/resources/excelResults/amazonscrapping.xlsx");

        //New students records to update in excel file
        Object[][] newStudents = {
                { name.get(0).getText(), stars.get(0).getAttribute("innerHTML"), rat.get(0).getAttribute("innerHTML"), _priceO.get(0).getAttribute("innerHTML") },
                { name.get(1).getText(), stars.get(1).getAttribute("innerHTML"), rat.get(1).getAttribute("innerHTML"), _priceO.get(1).getAttribute("innerHTML") }
        };

        try {
            //Creating input stream
            FileInputStream inputStream = new FileInputStream(xlsxFile);
            //Creating workbook from input stream
            Workbook workbook = WorkbookFactory.create(inputStream);
            //Reading first sheet of excel file
            Sheet sheet = workbook.getSheetAt(0);
            //Getting the count of existing records
            int rowCount = sheet.getLastRowNum();
            //Iterating new students to update
            for (Object[] student : newStudents) {
                //Creating new row from the next row count
                Row row = sheet.createRow(++rowCount);
                int columnCount = 0;
                //Iterating student informations
                for (Object info : student) {
                    //Creating new cell and setting the value
                    Cell cell = row.createCell(columnCount++);
                    if (info instanceof String) {
                        cell.setCellValue((String) info);
                    } else if (info instanceof Integer) {
                        cell.setCellValue((Integer) info);
                    }
                }
            }
            //Close input stream
            inputStream.close();
            //Crating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(xlsxFile);
            workbook.write(os);
            //Close the workbook and output stream
            workbook.close();
            os.close();
            System.out.println("Excel file has been updated successfully.");
        } catch (EncryptedDocumentException | IOException | InvalidFormatException e) {
            System.err.println("Exception while updating an existing excel file.");
            e.printStackTrace();
        }
    }

    //Method to click on Logout button
    public void clickLogout() {
        driver.findElement(logoutBtn).click();
    }
}