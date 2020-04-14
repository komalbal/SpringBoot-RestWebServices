package com.example.restWebService.userPackage;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;

//@ApiModel(description="User bean")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 2, message = "name should have minimum 2 charectors")
//	@ApiModelProperty(notes="Should have at least 2 charectors")
	private String name;

//	@ApiModelProperty(notes="DOB should not be in Future")
	@Past
	private Date DOB;
	
	@OneToMany(mappedBy = "user")
	private List<Posts> posts;

	public List<Posts> getPost() {
		return posts;
	}

	public void setPost(List<Posts> post) {
		this.posts = post;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", DOB=" + DOB + "]";
	}

	public User(Integer id, String name, Date dOB) {
		super();
		this.id = id;
		this.name = name;
		DOB = dOB;
//		this.posts = post;
	}

	public User() {
		super();
	}

}
