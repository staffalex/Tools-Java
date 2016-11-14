//package de.ast.timezones;
//
//import java.io.Serializable;
//import javax.persistence.*;
//import java.util.Date;
//
//
///**
// * The persistent class for the dish database table.
// * 
// */
//@Entity
//@NamedQuery(name="Dish.findAll", query="SELECT d FROM Dish d")
//public class Dish implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	private int id;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="CREATED_DATE")
//	private Date createdDate;
//
//	private String name;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="UPDATED_DATE")
//	private Date updatedDate;
//
//	public Dish() {
//	}
//
//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Date getCreatedDate() {
//		return this.createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Date getUpdatedDate() {
//		return this.updatedDate;
//	}
//
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//}