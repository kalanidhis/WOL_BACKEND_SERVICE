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
	
	@Query(value="SELECT * FROM invoice inv where (inv.month IN (?1) AND inv.year IN (?3)) or (inv.month IN (?2) AND inv.year IN (?4))",nativeQuery=true)
	List<Invoice> findInvoiceByTwoMonthAndYear(String month1,String month2, String year,String year2);
	
	@Query(value="SELECT * FROM invoice inv where inv.year= ?1",nativeQuery=true)
	List<Invoice> findBillingAmountByYear(int year);
	
	@Query(value="SELECT * FROM(SELECT * FROM windstream.invoice a order by id desc limit 12)inv ORDER BY year ASC, month(str_to_date(UPPER(month),'%b')) ASC",nativeQuery=true)
	List<Invoice> findLast12monthBilling();
}
