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
"startTimestampMs",
"endTimestampMs"
})
public class Duration {
	
	@JsonProperty("startTimestampMs")
	private String startTimestampMs;
	@JsonProperty("endTimestampMs")
	private String endTimestampMs;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("startTimestampMs")
	public String getStartTimestampMs() {
	return startTimestampMs;
	}

	@JsonProperty("startTimestampMs")
	public void setStartTimestampMs(String startTimestampMs) {
	this.startTimestampMs = startTimestampMs;
	}

	@JsonProperty("endTimestampMs")
	public String getEndTimestampMs() {
	return endTimestampMs;
	}

	@JsonProperty("endTimestampMs")
	public void setEndTimestampMs(String endTimestampMs) {
	this.endTimestampMs = endTimestampMs;
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
