package com.ojas.ra.mapper;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;
@SuppressWarnings("serial")
public class ObjectMapper2  implements Serializable{
	@JsonProperty("valueField")
	private String valueField;
	@JsonProperty("name")
	private String name;

	public ObjectMapper2(String valueField, String name) {
		super();
		this.valueField = valueField;
		this.name = name;
	}

	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
