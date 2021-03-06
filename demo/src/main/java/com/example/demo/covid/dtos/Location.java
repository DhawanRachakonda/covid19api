package com.example.demo.covid.dtos;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author explorervarun
 *
 */
public class Location {
	
	@JsonProperty("latitudeE7")
	private Integer latitudeE7;
	@JsonProperty("longitudeE7")
	private Integer longitudeE7;
	@JsonProperty("placeId")
	private String placeId;
	@JsonProperty("address")
	private String address;
	@JsonProperty("name")
	private String name;
	@JsonProperty("locationConfidence")
	private Double locationConfidence;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("latitudeE7")
	public Integer getLatitudeE7() {
	return latitudeE7;
	}

	@JsonProperty("latitudeE7")
	public void setLatitudeE7(Integer latitudeE7) {
	this.latitudeE7 = latitudeE7;
	}

	@JsonProperty("longitudeE7")
	public Integer getLongitudeE7() {
	return longitudeE7;
	}

	@JsonProperty("longitudeE7")
	public void setLongitudeE7(Integer longitudeE7) {
	this.longitudeE7 = longitudeE7;
	}

	@JsonProperty("placeId")
	public String getPlaceId() {
	return placeId;
	}

	@JsonProperty("placeId")
	public void setPlaceId(String placeId) {
	this.placeId = placeId;
	}

	@JsonProperty("address")
	public String getAddress() {
	return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
	this.address = address;
	}

	@JsonProperty("name")
	public String getName() {
	return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
	this.name = name;
	}

	@JsonProperty("locationConfidence")
	public Double getLocationConfidence() {
	return locationConfidence;
	}

	@JsonProperty("locationConfidence")
	public void setLocationConfidence(Double locationConfidence) {
	this.locationConfidence = locationConfidence;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

}
