package com.windstream.controller;


import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.windstream.model.TroubleTickets;
import com.windstream.repository.TroubleTicketRepo;


/**
 * Created by sachi on 11/29/2016.
 */
@CrossOrigin
@RestController
@RequestMapping("/tickets")
public class TroubleTicketController
{
	@Autowired
	private TroubleTicketRepo ticketRepo;
	private static final Logger logger = LogManager.getLogger(TroubleTicketController.class.getName());

	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
	public TroubleTickets createTicket(@RequestBody String ticketjsonData)
	{
		logger.debug("createTicket-- >"+ticketjsonData);
		
		JSONObject obj = new JSONObject(ticketjsonData);
		logger.debug("Category: " + obj.getString("category"));
		logger.debug("Description: " + obj.getString("description"));
		
		
		TroubleTickets ticket = new TroubleTickets(obj.getString("category"),obj.getString("description"),false);
		try{
			ticketRepo.save(ticket);
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return ticket;
	}
	/*
	
	
	@RequestMapping("/create")
	@ResponseBody
	public TroubleTickets createTicket(String name, String desc, boolean resolved)
	{
		
		logger.debug("NAME JSON --- >"+name);
		TroubleTickets ticket = new TroubleTickets(name,desc,resolved);
		try{
			ticketRepo.save(ticket);
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return ticket;
	}*/

	@RequestMapping("/update")
	@ResponseBody
	public TroubleTickets updateTicket(long id, Optional<String> name, Optional<String> desc, boolean resolved)
	{
		TroubleTickets ticket = null;
		try{
			ticket = ticketRepo.findOne(id);
			name.ifPresent(ticket::setName);
			desc.ifPresent(ticket::setDesc);
			ticket.setResolved(resolved);
			ticketRepo.save(ticket);
		}catch (Exception e){
			logger.error(e.getMessage());
		}
		return ticket;
	}
	
	@RequestMapping("/lasttwo")
	public List<TroubleTickets> getLast2Tickets() {
		return ticketRepo.findLastTwoTicket();
	}

	@RequestMapping("/all")
	public List<TroubleTickets> getAllTickets() {
		return ticketRepo.findAll();
	}

	@RequestMapping("/{id}")
	@ResponseBody
	public TroubleTickets getById(@PathVariable("id") long id) {
		TroubleTickets ticket = null;
		try {
			ticket = ticketRepo.findOne(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ticket;
	}

	@RequestMapping("/search")
	@ResponseBody
	public TroubleTickets getTicketByNameAndDesc(@RequestParam("name") String name, @RequestParam("desc") String desc) {
		TroubleTickets ticket = null;
		try {
			ticket = ticketRepo.findTicketNyNameAndDesc(name,desc).get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return ticket;
	}

}
