package com.example.restWebService.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path="/filtering")
	public MappingJacksonValue getSomeBean() {
		SomeBean somebean = new SomeBean("vwloe1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter );
		
		MappingJacksonValue mapping = new MappingJacksonValue(somebean);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping(path="/filtering-list")
	public MappingJacksonValue getSomeBeanList() {
		List<SomeBean> list =  Arrays.asList(new SomeBean("vwloe1", "value2", "value3"), 
				new SomeBean("vwloe11", "value22", "value33"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter );
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	public MappingJacksonValue mappingValue(List<SomeBean> list) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("someBeanFilter", filter );
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		
		return mapping;
	}
}
