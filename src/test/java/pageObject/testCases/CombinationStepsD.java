package pageObject.testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CombinationStepsD {

    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {2}, {3}, {5}
        };
    }

    @Test(dataProvider = "test-data")
    public void CombinationStepsD(int n) {
        findStep(n, "", "");
    }

    public static int findStep(int n, String k, String l) {
        if (n - 1 > 0 && n - 2 >= 0) {
            k = k + "1 ";
            l = l + "2 ";
            findStep(n - 1, k, k);
            findStep(n - 2, l, l);
        }
        if (n - 1 > 0 && n - 2 == 0) {

            System.out.println(l);
        }
        if (n - 1 == 0 && n - 2 < 0) {

            k = k + "1";
            System.out.println(k);
        }
        return 1;
    }
}









