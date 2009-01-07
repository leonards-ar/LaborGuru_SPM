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

	private PoiUtils(){
		
	}
	
	/**
	 * @param cell
	 * @return
	 */
	public static String getStringValue(HSSFCell cell){
		if ( (cell!= null) && (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)){
			return cell.getStringCellValue();
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
}
