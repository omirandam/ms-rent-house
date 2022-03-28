package com.renthouse.omar.adapter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "house")
public class House {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

	@Column(nullable = false, unique = true)
	public String address;

	@Column(nullable = false)
	public String owner;

	@Column(nullable = false)
	public String telephone_contact;

	@Column(nullable = false)
	public String contact_email;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "id_house")
	public List<Availability> availabilitys;
	
}
