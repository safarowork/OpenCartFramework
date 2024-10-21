package utilties;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	ExcelUtility excelutility;
	
	@DataProvider(name="logindata")
	public String[][] loginData() throws IOException{
		excelutility = new ExcelUtility(".\\testData\\data.xlsx");
		
		int rows = excelutility.getRowCount("login");
		int cells = excelutility.getCellCount("login", 1);
		
		String data[][] = new String[rows][cells];
		
		for(int r=1;r<=rows;r++)
		{
			for(int c=0;c<cells;c++)
			{
				data[r-1][c]=excelutility.getCellData("login", r, c);
			}
		}
		return data;
	}
	
	
	@DataProvider(name="registeruser")
	public String[][] registerUserData() throws IOException
	{
		excelutility = new ExcelUtility(".\\testData\\data.xlsx");
		
		int rows = excelutility.getRowCount("registration");
		int cells = excelutility.getCellCount("registration", 1);
		
		String data[][] = new String[rows][cells];
		
		for(int r=1;r<=rows;r++)
		{
			for(int c=0;c<cells;c++)
			{
				data[r-1][c]=excelutility.getCellData("registration", r, c);
			}
		}
		return data;
	}
	
}
