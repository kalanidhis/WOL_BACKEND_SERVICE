package com.windstream.controller;


import com.windstream.model.Invoice;
import com.windstream.repository.InvoiceRepo;

import org.apache.commons.lang3.StringUtils;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by sachi on 11/28/2016.
 */
@CrossOrigin
@RestController
@RequestMapping("/invoice")
public class InvoiceController
{

	@Autowired
	private InvoiceRepo invoiceRepo;
	private static final Logger logger = LogManager.getLogger(InvoiceController.class.getName());


	/**
	 * Create an Invoice
	 * @param month
	 * @param year
	 * @param amount
	 * @param paidAmount
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public Invoice createInvoice(String month, int year, double amount, double paidAmount,String dueDate,double previousAmount,double adjustment,double currentCharge,String paidDate,double pastDue) {
		Invoice invoice = new Invoice(month, year, amount, paidAmount,dueDate,previousAmount,adjustment,currentCharge,paidDate,pastDue);
		try {
			invoiceRepo.save(invoice);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		return invoice;
	}


	/**
	 * Update Invoice Amount
 	 * @param id
	 * @param paidAmount
	 * @return
	 */
	@RequestMapping(value="/update" , method = RequestMethod.POST)
	@ResponseBody
	public Invoice updateInvoice(@RequestBody String jsonData) {
		Invoice invoice = null;
		long id =0;
		double paidAmount=0;
		try {
		
			JSONObject obj = new JSONObject(jsonData);
			String billId = obj.getString("billId");  if(billId==null)billId="";
			String paidAmountStr = obj.getString("paidAmount"); if(paidAmountStr==null || paidAmountStr.length()==0 || paidAmountStr.isEmpty())paidAmountStr="0";
			
			
			logger.debug("billId--------------"+billId+"paidAmountStr-----------"+paidAmountStr);
			
			paidAmount = Double.parseDouble(paidAmountStr);
			if(StringUtils.isNumeric(billId)){	
				id = Long.parseLong(billId);
				invoice = invoiceRepo.findOne(id);
				
				LocalDate now = LocalDate.now(); 
				
				int currentMonth  = now.getMonthValue();
				int currentYear   = now.getYear();

				
				String currentMonthSName = getMonthNameShort(currentMonth);
				String payDate = now.getDayOfMonth()+"-"+currentMonthSName+"-"+currentYear;
				
				invoice.setPaidAmount(paidAmount);
				invoice.setPaidDate(payDate);
				invoiceRepo.saveAndFlush(invoice);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return invoice;
	}

	@RequestMapping("/all")
	public List<Invoice> getAllInvoices() {
		return invoiceRepo.findAll();
	}
	
	@RequestMapping("/last2Month")
	public List<Invoice> getLastTwoMonthInvoices() {
		Invoice invoice = null;
	LocalDate now = LocalDate.now(); 
	LocalDate earlier = now.minusMonths(1); 
	
	int currentMonth  = now.getMonthValue();
	int currentYear   = now.getYear();
	
	int previousMonth  = earlier.getMonthValue();
	int previousYear  = earlier.getYear();
	
	String currentMonthSName = getMonthNameShort(currentMonth);
	String previousMonthSName = getMonthNameShort(previousMonth);

	return invoiceRepo.findInvoiceByTwoMonthAndYear(currentMonthSName,previousMonthSName, currentYear+"",previousYear+"");
	}
	
	@RequestMapping("/currectinvoice")
	public List<Invoice> getCurrentMonthInvoices() {
	
	LocalDate now = LocalDate.now(); 
	
	int currentMonth  = now.getMonthValue();
	int currentYear   = now.getYear();

	
	String currentMonthSName = getMonthNameShort(currentMonth);
	
	return invoiceRepo.findInvoiceByMonthAndYear(currentMonthSName, currentYear);
	}
	
	
	@RequestMapping("/billingDetails")
	public List<Invoice> getBillAmountbyYear() {
	LocalDate now = LocalDate.now(); 	
	int currentYear   = now.getYear();
	
	return invoiceRepo.findBillingAmountByYear(currentYear);
	}
	

	@RequestMapping("/{id}")
	@ResponseBody
	public Invoice getById(@PathVariable("id") long id) {
		Invoice invoice = null;
		try {
			invoice = invoiceRepo.findOne(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return invoice;
	}

	@RequestMapping("/search")
	@ResponseBody
	public Invoice getInvoiceByMonthAndYear(@RequestParam("month") String month, @RequestParam("year") int year) {
		Invoice invoice = null;
		logger.debug("month--------------"+month+"year-----------"+year);
		try {
			invoice = invoiceRepo.findInvoiceByMonthAndYear(month,year).get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return invoice;
	}

	
	@RequestMapping(value="/billingdetails" , method = RequestMethod.POST)
	@ResponseBody
	public List<Invoice> getmonthyear(@RequestBody String jsonData){
		List<Invoice> invoice = null;
		logger.debug("jsonData--------------"+jsonData);
		try {
    		JSONObject obj = new JSONObject(jsonData);
			//String monthId = obj.getString("monthId");
			String monthNameStr = obj.getString("monthName");
			
			Date date = new SimpleDateFormat("MMMM").parse(monthNameStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			logger.debug(cal.get(Calendar.MONTH));
			int monthIdval = cal.get(Calendar.MONTH)+1;
			
			String year = obj.getString("year");
			String monthName="";
			int yearValue =0;
			if(StringUtils.isNumeric(year)){
				monthName = this.getMonthNameShort(monthIdval);
				yearValue = Integer.parseInt(year);
			}
			logger.debug("monthId: " + monthName);
			logger.debug("year: " + yearValue);
			
			invoice = invoiceRepo.findInvoiceByMonthAndYear(monthName,yearValue);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug(invoice);
		return invoice;
	}
	
	
	  public static String getMonthNameShort(int month)
	    {
	        Calendar cal = Calendar.getInstance();
	        // Calendar numbers months from 0
	        cal.set(Calendar.MONTH, month - 1);
	        return cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
	    }
}
