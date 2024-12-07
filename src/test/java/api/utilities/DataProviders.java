package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {


    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException{

        String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        int colnum = xl.getCellCount("Sheet1",1);
        Object apidata [][]=new Object[rownum][colnum];
        for(int i = 1; i<=rownum; i++){
            for(int j=0;j<colnum;j++){

                apidata[i-1][j]= xl.getCellData("Sheet1",i,j);
            }

        }
        return apidata;
    }

    @DataProvider(name = "UserNames")
    public Object[][] getUserNames() throws IOException{

        String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        Object[][] userNames =new Object[rownum][1];
        for(int i = 1; i<=rownum; i++){
            userNames[i-1][0]= xl.getCellData("Sheet1",i,1);

        }
        return userNames;
    }

}
