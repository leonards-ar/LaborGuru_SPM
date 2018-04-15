package com.mindpool.laborguru.sapistore.mapper.configuration;

import com.mindpool.laborguru.sapistore.mapper.dto.DayOfWeekDataDto;
import com.mindpool.laborguru.sapistore.mapper.dto.OperationTimeDto;
import com.mindpool.laborguru.sapistore.mapper.dto.StoreDto;
import com.mindpool.laborguru.sapistore.model.DayOfWeekData;
import com.mindpool.laborguru.sapistore.model.OperationTime;
import com.mindpool.laborguru.sapistore.model.Store;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CustomOrikaMapperFactoryConfigurer implements OrikaMapperFactoryConfigurer {


    @Override
    public void configure(MapperFactory mapperFactory) {
        mapperFactory.getConverterFactory().registerConverter(new EnumToStringCustomConverter());
        mapperFactory.classMap(Store.class, StoreDto.class).fieldAToB("area.region.customer.name", "customer").byDefault().register();
        mapperFactory.getConverterFactory().registerConverter("DayOfWeekConverter", new DayOfWeekConverter());
        mapperFactory.classMap(OperationTime.class, OperationTimeDto.class).fieldMap("dayOfWeek", "dayOfWeek").converter("DayOfWeekConverter").add().byDefault().register();
        mapperFactory.classMap(DayOfWeekData.class, DayOfWeekDataDto.class).fieldMap("dayOfWeek", "dayOfWeek").converter("DayOfWeekConverter").add().byDefault().register();

    }

}
