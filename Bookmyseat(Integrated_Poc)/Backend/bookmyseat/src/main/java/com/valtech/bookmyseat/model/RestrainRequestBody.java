package com.valtech.bookmyseat.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter	
public class RestrainRequestBody {
	private List<Integer> userId;
	private int floor_id;
	private int restrainId;
}
