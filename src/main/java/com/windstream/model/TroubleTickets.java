package com.windstream.model;


import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.internal.xml.BootstrapConfigurationImpl;

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
@Table(name = "trouble_tickets")
@TableGenerator(name="trouble_ticket_gen", initialValue= 1, allocationSize=50)
public class TroubleTickets
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="trouble_ticket_gen")
	private Integer id;

	@NotEmpty
	@Column(name = "trouble_ticket_name")
	private String name;

	@NotEmpty
	@Column(name = "trouble_ticket_desc")
	private String desc;

	@NotNull
	@Column(name = "resolved")
	private Boolean resolved;


	public Integer getId()
	{
		return id;
	}


	public void setId(Integer id)
	{
		this.id = id;
	}


	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getDesc()
	{
		return desc;
	}


	public void setDesc(String desc)
	{
		this.desc = desc;
	}


	public Boolean getResolved()
	{
		return resolved;
	}


	public void setResolved(Boolean resolved)
	{
		this.resolved = resolved;
	}


	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		TroubleTickets that = (TroubleTickets) o;

		if (!getId().equals(that.getId()))
			return false;
		if (!getName().equals(that.getName()))
			return false;
		return getDesc().equals(that.getDesc());

	}


	@Override
	public int hashCode()
	{
		int result = getId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getDesc().hashCode();
		return result;
	}


	@Override
	public String toString()
	{
		return "TroubleTickets{" + "id=" + id + ", name='" + name + '\'' + ", desc='" + desc + '\'' + ", resolved=" + resolved + '}';
	}
}


