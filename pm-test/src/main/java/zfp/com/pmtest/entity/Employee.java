package zfp.com.pmtest.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@Column(name = "EMPLOYEE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DOB")
	private Date dob;

	@Column(name = "HIRING_DATE")
	private Date hiringDate;

	@Column(name = "TERMINATION_DATE")
	private Date terminationDate;

	@Column(name = "TELEPHONE")
	private String telephone;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "JOB_TITLE")
	private String jobTitle;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;

	@OneToOne(cascade=CascadeType.ALL)
	private EmployeeInfo empInfo;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "EMPLOYEE_PROJECT", joinColumns = { @JoinColumn(name = "EMPLOYEE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJECT_ID") })
	Set<Project> projects;
	
	
	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName, Date dob, Date hiringDate, Date terminationDate,
			String telephone, String email, String jobTitle, Employee manager, EmployeeInfo empInfo,
			Set<Project> projects) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.hiringDate = hiringDate;
		this.terminationDate = terminationDate;
		this.telephone = telephone;
		this.email = email;
		this.jobTitle = jobTitle;
		this.manager = manager;
		this.empInfo = empInfo;
		this.projects = projects;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getHiringDate() {
		return hiringDate;
	}

	public void setHiringDate(Date hiringDate) {
		this.hiringDate = hiringDate;
	}

	public Date getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public EmployeeInfo getEmpInfo() {
		return empInfo;
	}

	public void setEmpInfo(EmployeeInfo empInfo) {
		this.empInfo = empInfo;
	}
	
}
