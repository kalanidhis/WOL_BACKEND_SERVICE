package com.windstream.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;


/**
 * Created by sachi on 11/28/2016.
 */
@Entity
@Table(name = "invoice")
@TableGenerator(name="invoice_gen", initialValue= 100, allocationSize=50)
public class Invoice
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="invoice_gen")
	private Long id;

	@NotEmpty
	@Column(name = "month")
	private String month;

	@NotNull
	@Column(name = "year")
	private Integer year;

	@NotNull
	@Column(name = "amount")
	private Double amount;

	@Column(name = "paid_amount")
	private Double paidAmount;

	@Column(name = "due_date")
	private String dueDate;

	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public String getMonth()
	{
		return month;
	}


	public void setMonth(String month)
	{
		this.month = month;
	}


	public Integer getYear()
	{
		return year;
	}


	public void setYear(Integer year)
	{
		this.year = year;
	}


	public Double getAmount()
	{
		return amount;
	}


	public void setAmount(Double amount)
	{
		this.amount = amount;
	}


	public Double getPaidAmount()
	{
		return paidAmount;
	}


	public void setPaidAmount(Double paidAmount)
	{
		this.paidAmount = paidAmount;
	}
	
	
	public String getDueDate()
	{
		return dueDate;
	}


	public void setDueDate(String dueDate)
	{
		this.dueDate = dueDate;
	}




	@Override
	public String toString()
	{
		return "Invoice{" + "id=" + id + ", month='" + month + '\'' + ", year='" + year + '\'' + ", amount=" + amount + ", paidAmount=" + paidAmount +", dueDate=" + dueDate + '}';
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Invoice invoice = (Invoice) o;

		if (!getMonth().equals(invoice.getMonth()))
			return false;
		return getYear().equals(invoice.getYear());

	}


	@Override
	public int hashCode()
	{
		int result = getMonth().hashCode();
		result = 31 * result + getYear().hashCode();
		return result;
	}


	public Invoice(String month, Integer year, double amount, double paidAmount,String dueDate)
	{
		this.month = month;
		this.year = year;
		this.amount = amount;
		this.paidAmount = paidAmount;
		this.dueDate = dueDate;
	}


	public Invoice()
	{
	}
}
