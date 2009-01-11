package com.laborguru.util;

import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

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
	public static String getStringValue(HSSFCell cell){
		if ( (cell!= null) && (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)){
			return cell.getStringCellValue().trim();
		}
		
		return null;
	}
	
	/**
	 * @param cell
	 * @return
	 */
	public static Date getDateValue(HSSFCell cell){
		if ( (cell != null) && (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) && (HSSFDateUtil.isCellDateFormatted(cell))){
			return cell.getDateCellValue();
		}
		
		return null;
	}


	/**
	 * @param cell
	 * @return
	 */
	public static Double getDoubleValue(HSSFCell cell){
		if ((cell != null) && (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) && !(HSSFDateUtil.isCellDateFormatted(cell))){
			return cell.getNumericCellValue();
		}
		
		return null;
	}
	
	/**
	 * @param cell
	 * @return
	 */
	public static Integer getIntegerValue(HSSFCell cell){
		if ((cell != null) && (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) && !(HSSFDateUtil.isCellDateFormatted(cell))){
			return new Integer(((int)cell.getNumericCellValue()));
		}
		
		return null;
	}
}
