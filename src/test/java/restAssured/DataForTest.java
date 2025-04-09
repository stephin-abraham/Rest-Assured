package restAssured;

import org.testng.annotations.DataProvider;

public class DataForTest {
    @DataProvider(name = "Data_for_Post")
    public Object[][] data_for_post(){
//        Object[][] data= new Object[3][2];
//        data[0][0] = "Stephy";
//        data[0][1] = 25;
//
//        data[1][0] = "Sagar";
//        data[1][1] = 23;
//
//        data[2][0] = "Panda";
//        data[2][1] = 25;

        return new Object[][]
                {
                        {"bueb",4},
                        {"DjdbwGF",64}
                };
    }
}
