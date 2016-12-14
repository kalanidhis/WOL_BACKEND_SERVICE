package com.windstream.repository;


import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.windstream.model.TroubleTickets;


/**
 * Created by sachi on 11/29/2016.
 */
public interface TroubleTicketRepo extends JpaRepository<TroubleTickets, Long> {

	@Query("SELECT ticket FROM TroubleTickets ticket where ticket.name= ?1 AND ticket.desc= ?2")
	List<TroubleTickets> findTicketNyNameAndDesc(String name, String desc);
	
	
	

	
	@Query(value="SELECT * FROM trouble_tickets order by id desc limit 0,2",nativeQuery=true)
	List<TroubleTickets> findLastTwoTicket();
	
	

}
