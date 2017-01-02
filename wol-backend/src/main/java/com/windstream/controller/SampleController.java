package com.windstream.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.windstream.model.SampleData;
import com.windstream.repository.SampleRepo;


@CrossOrigin
@RestController
@RequestMapping("/sample")
public class SampleController
{

	@Autowired
	private SampleRepo sampleRepo;
	private static final Logger logger = LogManager.getLogger(SampleController.class.getName());


	@RequestMapping("/getData")
	public List<SampleData> getAllData() {
		logger.debug("calling service");
		
		return sampleRepo.findAll();
	}
	
}
