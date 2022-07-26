package DataLayer;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProvider {

	/* Defining variables */
	XSSFWorkbook workBook = null;
	XSSFSheet sheet = null;
	String ExcelScheet;
	String Location;

	/*
	 * Constructor
	 * 
	 * @param excelScheet, location: Sheetname of the excel and physical location
	 */
	public DataProvider(String excelScheet, String location) {
		ExcelScheet = excelScheet;
		Location = location;
	}

	/*
	 * Constructor Default Constructor
	 */
	public DataProvider() {
	}

	/*
	 * Method: getRowCount
	 * 
	 * @param: no parameters
	 * 
	 * @return: number of excel rows
	 */
	public int getRowCount() {

		int i = 0;

		try {
			workBook = new XSSFWorkbook(Location);
			sheet = workBook.getSheet(ExcelScheet);
			i = sheet.getPhysicalNumberOfRows();

		} catch (IOException e) {

		}

		return i;
	}

	/*
	 * Method: getCellCount
	 * 
	 * @param: no parameters
	 * 
	 * @return: number of excel columns
	 */
	public int getCellCount() {

		int i = 0;

		try {
			workBook = new XSSFWorkbook(Location);
			sheet = workBook.getSheet(ExcelScheet);
			i = sheet.getRow(0).getPhysicalNumberOfCells();

		} catch (IOException e) {

		}

		return i;
	}

	/*
	 * Method: getCellCount
	 * 
	 * @param: row, colum: Specific row number, Specific column number.
	 * 
	 * @return: data from a specific cell.
	 */
	public String getCellDataValue(int row, int col) {

		String CellData = null;

		try {
			workBook = new XSSFWorkbook(Location);
			sheet = workBook.getSheet(ExcelScheet);
			CellData = sheet.getRow(row).getCell(col).getStringCellValue();

		} catch (Exception e) {

		}

		return CellData;
	}

	/*
	 * Method: getCellCount
	 * 
	 * @param: no parameters.
	 * 
	 * @return: List of values.
	 */
	public Object[][] getDataPool() {

		int countRow = getRowCount();
		int countColu = getCellCount();

		Object pool[][] = new Object[countRow - 1][countColu];

		for (int f = 1; f < countRow; f++) {
			for (int c = 0; c < countColu; c++) {

				pool[f - 1][c] = getCellDataValue(f, c);
			}
		}

		return pool;
	}

	/* Setters and getters */
	public String getExcelScheet() {
		return ExcelScheet;
	}

	public void setExcelScheet(String excelScheet) {
		ExcelScheet = excelScheet;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

}
