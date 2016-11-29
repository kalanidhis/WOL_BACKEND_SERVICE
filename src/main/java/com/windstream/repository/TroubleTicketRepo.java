package com.windstream.repository;


import com.windstream.model.Invoice;
import com.windstream.model.TroubleTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by sachi on 11/29/2016.
 */
public interface TroubleTicketRepo extends JpaRepository<TroubleTickets, Long> {

	@Query("SELECT ticket FROM TroubleTickets ticket where ticket.name= ?1 AND ticket.desc= ?2")
	List<TroubleTickets> findTicketNyNameAndDesc(String name, String desc);

}
