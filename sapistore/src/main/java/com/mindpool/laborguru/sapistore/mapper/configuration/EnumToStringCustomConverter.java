package com.mindpool.laborguru.sapistore.mapper.configuration;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class EnumToStringCustomConverter extends CustomConverter<Enum, String> {

    @Override
    public String convert(Enum source, Type<? extends String> destinationType, MappingContext mappingContext) {
        return source != null ? source.name() : null;
    }
}
