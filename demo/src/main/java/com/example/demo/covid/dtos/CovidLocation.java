package com.example.demo.covid.dtos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author explorervarun
 *
 */
@Document(collection = "InfectedAreas")
public class CovidLocation {

	private String latitude;
	private String longitude;
	private String dateField;
	private String addressName;
	private String name;
	
	@Id
	private String id;
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getDateField() {
		return dateField;
	}
	public void setDateField(String dateField) {
		this.dateField = dateField;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
