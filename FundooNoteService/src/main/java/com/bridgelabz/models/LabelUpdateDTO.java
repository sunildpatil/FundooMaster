package com.bridgelabz.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LabelUpdateDTO {
	
	private int labelId;
	
	@NotNull(message="Please Enter Valid Label Name")
	@NotEmpty(message="Label Name should Not be Empty")
	private String labelName;

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	@Override
	public String toString() {
		return "LabelUpdateDTO [labelId=" + labelId + ", labelName=" + labelName + "]";
	}
}
