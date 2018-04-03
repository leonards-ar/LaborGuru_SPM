package com.mindpool.laborguru.sapistore.controller;


import com.mindpool.laborguru.sapistore.services.StoreService;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MapperFacade mapper;

    @Autowired
    protected StoreService storeService;
}
