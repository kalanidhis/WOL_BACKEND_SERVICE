package com.windstream.repository;


import com.windstream.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by sachi on 11/28/2016.
 */
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

	@Query("SELECT inv FROM Invoice inv where inv.month= ?1 AND inv.year= ?2")
	List<Invoice> findInvoiceByMonthAndYear(String month, int year);
}
