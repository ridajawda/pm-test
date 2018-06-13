package zfp.com.pmtest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CLIENT")
public class Client {

	@Id
	@Column(name = "CLIENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "TELEPHONE")
	private String telephone;

	@Column(name = "EMAIL")
	private String email;

	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<Review> reviews;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "CLIENT_PROJECT", joinColumns = { @JoinColumn(name = "CLIENT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJECT_ID") })
	Set<Project> projects;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void add(Review review) {

		if (reviews == null) {
			reviews = new ArrayList<Review>();
		}
		reviews.add(review);
		review.setClient(this);
	}

}
