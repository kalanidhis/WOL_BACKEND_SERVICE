package com.windstream.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "sample_data")
@TableGenerator(name="sample_data_gen", initialValue= 1, allocationSize=50)
public class SampleData
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="sample_data_gen")
	private Long id;

	@NotEmpty
	@Column(name = "name")
	private String name;

	@NotEmpty
	@Column(name = "services")
	private String services;

	@NotEmpty
	@Column(name = "date_purchased")
	private String datePurchased;


	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public String getServices()
	{
		return services;
	}


	public void setServices(String services)
	{
		this.services = services;
	}

	
	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	
	public String getDatePurchased()
	{
		return datePurchased;
	}


	public void setDatePurchased(String datePurchased)
	{
		this.datePurchased = datePurchased;
	}



	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SampleData that = (SampleData) o;

		if (!getId().equals(that.getId()))
			return false;
		if (!getName().equals(that.getName()))
			return false;
		return getServices().equals(that.getServices());

	}


	@Override
	public int hashCode()
	{
		int result = getId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getServices().hashCode();
		return result;
	}


	@Override
	public String toString()
	{
		return "SampleData{" + "id=" + id + ", name='" + name + '\'' + ", services='" + services + '\'' + ", datePurchased=" + datePurchased + '}';
	}


	public SampleData(String name, String services, String datePurchased)
	{
		this.name = name;
		this.services = services;
		this.datePurchased = datePurchased;
	}


	public SampleData()
	{
	}
}


