package com.windstream.controller;


import com.windstream.model.Invoice;
import com.windstream.repository.InvoiceRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by sachi on 11/28/2016.
 */

@RestController
@RequestMapping("/invoice")
public class InvoiceController
{

	@Autowired
	private InvoiceRepo invoiceRepo;
	static final Logger logger = LogManager.getLogger(InvoiceController.class.getName());


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
	public Invoice createInvoice(String month, int year, double amount, double paidAmount) {
		Invoice invoice = new Invoice(month, year, amount, paidAmount);
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
	@RequestMapping("/update")
	@ResponseBody
	public Invoice updateInvoice(long id, double paidAmount) {
		Invoice invoice = null;
		try {
			invoice = invoiceRepo.findOne(id);
			invoice.setPaidAmount(paidAmount);
			invoiceRepo.save(invoice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return invoice;
	}

	@RequestMapping("/all")
	public List<Invoice> getAllInvoices() {
		return invoiceRepo.findAll();
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
		try {
			invoice = invoiceRepo.findInvoiceByMonthAndYear(month,year).get(0);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return invoice;
	}
}
