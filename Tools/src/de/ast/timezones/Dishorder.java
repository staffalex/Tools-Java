//package de.ast.timezones;
//
//import java.io.Serializable;
//import javax.persistence.*;
//import java.util.Date;
//
//
///**
// * The persistent class for the dishorder database table.
// * 
// */
//@Entity
//@NamedQuery(name="Dishorder.findAll", query="SELECT d FROM Dishorder d")
//public class Dishorder implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	private int id;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="CREATED_DATE")
//	private Date createdDate;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="UPDATED_DATE")
//	private Date updatedDate;
//
//	//uni-directional many-to-one association to Dish
//	@ManyToOne
//	@JoinColumn(name="DISH")
//	private Dish dishBean;
//
//	public Dishorder() {
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
//	public Date getUpdatedDate() {
//		return this.updatedDate;
//	}
//
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//	public Dish getDishBean() {
//		return this.dishBean;
//	}
//
//	public void setDishBean(Dish dishBean) {
//		this.dishBean = dishBean;
//	}
//
//}