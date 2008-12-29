package com.laborguru.service.store.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.laborguru.exception.InvalidUploadFileException;
import com.laborguru.model.Store;

/**
 * Parses a store definition file
 * 
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreDefinitionFileParserBean implements StoreDefinitionFileParser {

	private static final Logger log = Logger.getLogger(StoreDefinitionFileParserBean.class);

	/**
	 * @param storeToUpload
	 * @return
	 * @see com.laborguru.service.store.file.StoreDefinitionFileParser#parseStore(java.io.File)
	 */
	public Store parseStore(File storeToUpload) {
		
		InputStream inp = null;
		
		try {
			 inp = new FileInputStream(storeToUpload);
			 
			 HSSFWorkbook wb = new HSSFWorkbook(inp);
			 HSSFSheet sheet = wb.getSheetAt(0);	
			 
			 StoreAssembler storeAssembler = StoreAssembler.getStoreAssembler();

			 Iterator<HSSFRow> rit = (Iterator<HSSFRow>)sheet.rowIterator();
			 			 
			 //Ignoring the header
			 rit.next();
			 
			 while(rit.hasNext()) {
				HSSFRow row = rit.next();
				storeAssembler.addToStore(row);
			}
			 
			 Store store = storeAssembler.assemble();
			 
			 return store;

		} catch (FileNotFoundException exceptionFNF) {
			String message = "File to parse:"+storeToUpload.getName()+ "is not found";
			log.error(message);
			throw new InvalidUploadFileException(exceptionFNF, message);
		} catch (IOException e) {
			String msg = "The file " + storeToUpload.getName() +" passed in as parameter cannot be read.";
			log.error(msg);
			throw new InvalidUploadFileException(msg);			
		} finally{
			if (inp != null){
				try {
					inp.close();
				} catch (IOException e) {
					String msg = "The file " + storeToUpload.getName() +" passed in as parameter could not be closed.";
					log.error(msg);
				}
			}
		}
	}
}
