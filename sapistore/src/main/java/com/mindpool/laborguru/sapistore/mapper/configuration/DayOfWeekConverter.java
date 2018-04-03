package com.mindpool.laborguru.sapistore.mapper.configuration;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.text.DateFormatSymbols;
import java.util.Arrays;

public class DayOfWeekConverter extends BidirectionalConverter<Integer,String> {

    @Override
    public String convertTo(Integer source, Type<String> type, MappingContext mappingContext) {
        return DateFormatSymbols.getInstance().getWeekdays()[source + 1];
    }

    @Override
    public Integer convertFrom(String s, Type<Integer> type, MappingContext mappingContext) {
        return Arrays.asList(DateFormatSymbols.getInstance().getWeekdays()).indexOf(s) - 1;
    }
}
