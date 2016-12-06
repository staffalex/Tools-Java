package hello.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the salary database table.
 * 
 */
@Entity
@Table(name="salary")
@NamedQuery(name="Salary.findAll", query="SELECT s FROM Salary s")
public class Salary implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SalaryPK id;

	@Column(nullable=false)
	private int salary;

	@Temporal(TemporalType.DATE)
	@Column(name="to_date", nullable=false)
	private Date toDate;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="emp_no", nullable=false, insertable=false, updatable=false)
	private Employee employee;

	public Salary() {
	}

	public SalaryPK getId() {
		return this.id;
	}

	public void setId(SalaryPK id) {
		this.id = id;
	}

	public int getSalary() {
		return this.salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}