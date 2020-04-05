package com.example.demo.covid.dtos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author explorervarun
 *
 */

public class GoogleLocation {

	@JsonProperty("timelineObjects")
	private List<TimelineObject> timelineObjects = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("timelineObjects")
	public List<TimelineObject> getTimelineObjects() {
	return timelineObjects;
	}

	@JsonProperty("timelineObjects")
	public void setTimelineObjects(List<TimelineObject> timelineObjects) {
	this.timelineObjects = timelineObjects;
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
