package com.example.demo.covid.dtos;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"location",
"duration"
})
public class PlaceVisit {

	@JsonProperty("location")
	private Location location;
	@JsonProperty("duration")
	private Duration duration;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("location")
	public Location getLocation() {
	return location;
	}

	@JsonProperty("location")
	public void setLocation(Location location) {
	this.location = location;
	}

	@JsonProperty("duration")
	public Duration getDuration() {
	return duration;
	}

	@JsonProperty("duration")
	public void setDuration(Duration duration) {
	this.duration = duration;
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
