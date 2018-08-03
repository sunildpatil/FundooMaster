package com.bridgelabz.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LabelCreateDTO {
		
	@NotNull(message="Please Enter Valid Label Name")
	@NotEmpty(message="Label Name should Not be Empty")
	private String labelName;

	public LabelCreateDTO() {

	}
	
	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@Override
	public String toString() {
		return "LabelCreateDTO [labelName=" + labelName + "]";
	}

}
