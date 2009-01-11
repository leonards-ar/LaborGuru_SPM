package com.laborguru.service.store.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import com.laborguru.exception.ErrorEnum;
import com.laborguru.exception.InvalidFieldUploadFileException;
import com.laborguru.model.DayOfWeek;
import com.laborguru.model.DayPart;
import com.laborguru.model.DayPartData;
import com.laborguru.model.OperationTime;
import com.laborguru.model.Position;
import com.laborguru.model.PositionGroup;
import com.laborguru.model.Store;
import com.laborguru.util.PoiUtils;


/**
 *
 * @author <a href="cnunezre@gmail.com">Cristian Nunez Rebolledo</a>
 * @version 1.0
 * @since SPM 1.0
 *
 */
public class StoreOperation extends BaseStoreSection {

	private static final Logger log = Logger.getLogger(StoreOperation.class);

	private static final String FIRST_DAY_OF_WEEK = "First day of week";
	private static final String OPEN = "Open";
	private static final String CLOSE = "Close";
	
	
	public enum StoreOperationField{
		
		HOURS_OF_OPERATION("Hours of operation"),
		DAYPART_DEFINITION("Daypart definition"),
		POSITION_NAMES("Position names"),
		GROUP_NAMES("Group names"),		
		VARIABLE_DEFINITION("Variable definition");
		
		private String fieldName;
				
		StoreOperationField(String fieldName){
			this.fieldName = fieldName;
		}
		
		public String getFieldName(){
			return this.fieldName;
		}
		
		/**
		 * @param fieldName
		 * @return
		 */
		public static StoreOperationField getFielType(String fieldName){
			for (StoreOperationField field: EnumSet.allOf(StoreOperationField.class)){
				if (field.getFieldName().equalsIgnoreCase(fieldName)){
					return field;
				}
			}
			
			String message = "Store Information row is invalid  - fieldName:"+fieldName;
			log.error(message);
			throw new InvalidFieldUploadFileException(message, ErrorEnum.STORE_INVALID_ROW, new String[] {fieldName});
		}
	}
	
	private enum UploadWeekDays {
		MONDAY("Monday", DayOfWeek.MONDAY),
		TUESDAY("Tuesday", DayOfWeek.TUESDAY),
		WEDNESDAY("Wednesday", DayOfWeek.WEDNESDAY),
		THURSDAY("Thursday", DayOfWeek.THURSDAY),
		FRIDAY("Friday", DayOfWeek.FRIDAY),
		SATURDAY("Saturday", DayOfWeek.SATURDAY),
		SUNDAY("Sunday", DayOfWeek.SUNDAY);
		
		private String dayName;
		private DayOfWeek dayOfWeek;

		/**
		 * @return the dayName
		 */
		public String getDayName() {
			return dayName;
		}

		/**
		 * @return the dayOfWeek
		 */
		public DayOfWeek getDayOfWeek() {
			return dayOfWeek;
		}


		private UploadWeekDays(String day, DayOfWeek dayOfWeek){
			this.dayOfWeek = dayOfWeek;
			this.dayName = day;
		}
		
		/**
		 * @param fieldName
		 * @return
		 */
		public static UploadWeekDays getUploadWeekDay(String fieldName){
			for (UploadWeekDays uploadWeekDay: EnumSet.allOf(UploadWeekDays.class)){
				if (uploadWeekDay.getDayName().equalsIgnoreCase(fieldName)){
					return uploadWeekDay;
				}
			}
			
			return null;
		}
	}
	
	private OperationTime[] hoursOfOperation = new OperationTime[7];
	private List<DayPart> dayPartDefinitions = new ArrayList<DayPart>();
	private List<String> positionNames = new ArrayList<String>();
	private List<String> groupNames = new ArrayList<String>();
	private List<String> variableDefinitions = new ArrayList<String>();
	private DayOfWeek firstDayOfWeek;

	
	/**
	 * Default Constructor
	 */
	public StoreOperation(){
		super();
		setSection(StoreSection.STORE_OPERATIONS);
	}
	
	/**
	 * @param row
	 * @see com.laborguru.service.store.file.BaseStoreSection#addRowToSection(org.apache.poi.hssf.usermodel.HSSFRow)
	 */
	@Override
	protected void addRowToSection(HSSFRow row) {	
				
		String category = PoiUtils.getStringValue(row.getCell((short)1));
		
		StoreOperationField infoStoreType = StoreOperationField.getFielType(category);
		
		switch(infoStoreType){		
			case HOURS_OF_OPERATION:
				addHoursOfOperation(row);
				break;
			case DAYPART_DEFINITION:
				addDaypartDefinition(row);
				break;
			case POSITION_NAMES:
				addPositionNames(row);
				break;
			case GROUP_NAMES:
				addGroupNames(row);
				break;
			case VARIABLE_DEFINITION:
				addVariableDefinitions(row);
				break;
			default: throw new IllegalArgumentException("The type passed as parameter is wrong");			
		}
	}
	
	/**
	 * @param row
	 * @see com.laborguru.service.store.file.BaseStoreSection#validateRow(org.apache.poi.hssf.usermodel.HSSFRow)
	 */
	@Override
	protected void validateRow(HSSFRow row){
		HSSFCell category = row.getCell((short)1);
		HSSFCell fieldValue = row.getCell((short)4);
		
		if ((category == null) || (fieldValue == null)){
			String message = getSection().getStoreSection()+" row is invalid - category:"+category+" - fieldValue:"+fieldValue;
			log.error(message);
			throw new InvalidFieldUploadFileException(message, ErrorEnum.STORE_INVALID_ROW, new String[] {getSection().getStoreSection()});
		}
	}
	
	private void addVariableDefinitions(HSSFRow row){
		String fieldValue = PoiUtils.getStringValue(row.getCell((short)4));
		getVariableDefinitions().add(fieldValue.trim());
	}
	
	private void addGroupNames(HSSFRow row) {
		String fieldValue = PoiUtils.getStringValue(row.getCell((short)4));
		getGroupNames().add(fieldValue.trim());
	}

	private void addPositionNames(HSSFRow row) {
		String fieldValue = PoiUtils.getStringValue(row.getCell((short)4));
		getPositionNames().add(fieldValue.trim());
		
	}

	private void addDaypartDefinition(HSSFRow row) {		
		String fieldName = PoiUtils.getStringValue(row.getCell((short)2));
		Date startTime = PoiUtils.getDateValue(row.getCell((short)4));

		if (fieldName == null || startTime == null){
			String message = getSection().getStoreSection()+" row is invalid - category: day part definition - fieldName:"+fieldName + " - startTime:"+startTime;
			log.error(message);
			throw new InvalidFieldUploadFileException(message, new String[] {getSection().getStoreSection(), 
					StoreOperationField.DAYPART_DEFINITION.getFieldName()});
		}

		DayPart dayPart = new DayPart();
		dayPart.setName(fieldName.trim());
		dayPart.setStartHour(startTime);
		
		getDayPartDefinitions().add(dayPart);
	}

	/**
	 * @param fieldName
	 * @param fieldAux
	 * @param fieldValue
	 */
	private void addHoursOfOperation(HSSFRow row) {
		
		String fieldName = PoiUtils.getStringValue(row.getCell((short)2));
				
		if (FIRST_DAY_OF_WEEK.equalsIgnoreCase(fieldName)){
			String fieldValue = row.getCell((short)4).getStringCellValue();
			UploadWeekDays firstDay = UploadWeekDays.getUploadWeekDay(fieldValue);
			
			if (firstDay!=null){
				setFirstDayOfWeek(firstDay.getDayOfWeek());
			}
			
			return;
		}
		
		Date hour = PoiUtils.getDateValue(row.getCell((short)4));
		String fieldAux = PoiUtils.getStringValue(row.getCell((short)3));		

		OperationTime operationTime = getHourOfOperation(fieldName);
		
		if ((hour == null) || (operationTime == null) ){
			String message = getSection().getStoreSection()+" row is invalid - category: Hour of operation - fieldName:"+fieldName + " - fieldAux:"+fieldAux+ " - hour: "+ hour;
			log.error(message);
			throw new InvalidFieldUploadFileException(message, new String[] {getSection().getStoreSection(), 
					StoreOperationField.HOURS_OF_OPERATION.getFieldName()});
		}
		
		if (OPEN.equalsIgnoreCase(fieldAux)){
			operationTime.setOpenHour(hour);
		}else if (CLOSE.equalsIgnoreCase(fieldAux)){
			operationTime.setCloseHour(hour);
		} else {
			String message = getSection().getStoreSection()+" row is invalid - category: Hour of operation - fieldName:"+fieldName + " - fieldAux:"+fieldAux+ " - hour: "+ hour;
			log.error(message);
			throw new InvalidFieldUploadFileException(message, new String[] {getSection().getStoreSection(), 
					StoreOperationField.HOURS_OF_OPERATION.getFieldName()});
		}
	}


	/**
	 * @param fieldName
	 * @return
	 */
	private OperationTime getHourOfOperation(String fieldName) {

		UploadWeekDays dayOfWeek = UploadWeekDays.getUploadWeekDay(fieldName);
		
		if (dayOfWeek == null){
			return null;
		}
		
		if (getHoursOfOperation()[dayOfWeek.ordinal()] == null){
			OperationTime operationTime = new OperationTime();
			operationTime.setDayOfWeek(dayOfWeek.getDayOfWeek());
			getHoursOfOperation()[dayOfWeek.ordinal()] = operationTime;
		}
		return getHoursOfOperation()[dayOfWeek.ordinal()];
	}

	
	/**
	 * @param store
	 */
	public void assembleStore(Store store) {
		
		for (OperationTime operationTime: getHoursOfOperation()){
			store.addOperationTime(operationTime);
		}
		
		int index = 0;		
		for (DayPart dayPart: getDayPartDefinitions()){
			dayPart.setPositionIndex(index++);
			store.addDayPart(dayPart);
		}

		index=0;
		for (String positionName: getPositionNames()){
			Position position = new Position();
			position.setPositionIndex(index++);
			position.setName(positionName);
			
			for (DayPart dayPart: store.getDayParts()){
				DayPartData dayPartData = new DayPartData();
				dayPartData.setDayPart(dayPart);
				dayPartData.setPosition(position);
				position.getDayPartData().add(dayPartData);
			}			
					
			store.addPosition(position);
		}

		for (String groupName: getGroupNames()){
			PositionGroup positionGroup = new PositionGroup();
			positionGroup.setName(groupName);
			store.addPositionGroup(positionGroup);
		}
		
		if (getFirstDayOfWeek() != null){
			store.setFirstDayOfWeek(getFirstDayOfWeek());
		}
	}

	
	/**
	 * @return the hoursOfOperation
	 */
	public OperationTime[] getHoursOfOperation() {
		return hoursOfOperation;
	}


	/**
	 * @return the dayPartDefinitions
	 */
	public List<DayPart> getDayPartDefinitions() {
		return dayPartDefinitions;
	}

	/**
	 * @param dayPartDefinitions the dayPartDefinitions to set
	 */
	public void setDayPartDefinitions(List<DayPart> dayPartDefinitions) {
		this.dayPartDefinitions = dayPartDefinitions;
	}

	/**
	 * @return the positionNames
	 */
	public List<String> getPositionNames() {
		return positionNames;
	}

	/**
	 * @param positionNames the positionNames to set
	 */
	public void setPositionNames(List<String> positionNames) {
		this.positionNames = positionNames;
	}

	/**
	 * @return the groupNames
	 */
	public List<String> getGroupNames() {
		return groupNames;
	}

	/**
	 * @param groupNames the groupNames to set
	 */
	public void setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
	}

	/**
	 * @return the variableDefinitions
	 */
	public List<String> getVariableDefinitions() {
		return variableDefinitions;
	}

	/**
	 * @param variableDefinitions the variableDefinitions to set
	 */
	public void setVariableDefinitions(List<String> variableDefinitions) {
		this.variableDefinitions = variableDefinitions;
	}

	/**
	 * @return the firstDayOfWeek
	 */
	public DayOfWeek getFirstDayOfWeek() {
		return firstDayOfWeek;
	}

	/**
	 * @param firstDayOfWeek the firstDayOfWeek to set
	 */
	public void setFirstDayOfWeek(DayOfWeek firstDayOfWeek) {
		this.firstDayOfWeek = firstDayOfWeek;
	}

}
