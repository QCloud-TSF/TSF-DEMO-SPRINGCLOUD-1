package com.tsf.demo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
	private static final Logger LOG = LoggerFactory.getLogger(EchoController.class);
	
	@Autowired
	private ProviderNameConfig providerNameConfig;
	
    @RequestMapping(value = "/echo/{param}", method = RequestMethod.GET)
    public String echo(@PathVariable String param) {
    	LOG.info("provider-demo -- request param: [" + param + "]");
    	String result = "request param: " + param + ", response from " + providerNameConfig.getName();
    	LOG.info("provider-demo -- provider config name: [" + providerNameConfig.getName() + ']');
        LOG.info("provider-demo -- response info: [" + result + "]");
        return result;
    }
}