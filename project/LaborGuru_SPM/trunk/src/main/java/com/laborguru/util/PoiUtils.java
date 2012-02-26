package com.laborguru.util;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class PoiUtils {

	/**
	 * Private constructor - Enforces non instanciation of the utility class
	 */
	private PoiUtils(){
		
	}
	
	/**
	 * @param cell
	 * @return
	 */
	public static String getStringValue(Cell cell){
		if ( (cell!= null) && (cell.getCellType() == Cell.CELL_TYPE_STRING)){
			return cell.getStringCellValue().trim();
		}
		
		return null;
	}
	
	/**
	 * @param cell
	 * @return
	 */
	public static Date getDateValue(Cell cell){
		if ( (cell != null) && (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) && (HSSFDateUtil.isCellDateFormatted(cell))){
			return cell.getDateCellValue();
		}
		
		return null;
	}


	/**
	 * @param cell
	 * @return
	 */
	public static Double getDoubleValue(Cell cell){
		if ((cell != null) && (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) && !(HSSFDateUtil.isCellDateFormatted(cell))){
			return cell.getNumericCellValue();
		}
		
		return null;
	}
	
	/**
	 * @param cell
	 * @return
	 */
	public static Integer getIntegerValue(Cell cell){
		if ((cell != null) && (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) && !(HSSFDateUtil.isCellDateFormatted(cell))){
			return new Integer(((int)cell.getNumericCellValue()));
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 */
	public static boolean getBooleanValue(Cell cell) {
		Object o;
		if(cell != null && cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue();
		} else if((o = getIntegerValue(cell)) !=  null) {
			return ((Integer ) o).intValue() == 1;
		} else if((o = getStringValue(cell)) !=  null) {
			return o.toString().equalsIgnoreCase("y") || o.toString().equalsIgnoreCase("yes") || o.toString().equalsIgnoreCase("t") || o.toString().equalsIgnoreCase("true") || o.toString().equalsIgnoreCase("1");
		}
		return false;
	}
}
