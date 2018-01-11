package com.shareit.ocr.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SimulatorFactory {
	
	private static List<Simulator> map = new ArrayList<Simulator>();
	
	public static Simulator CreateInstance() {
		Simulator sm = new Simulator();
		map.add(sm);
		return sm;
	}
	
	public static Simulator getSimulator(String id) {
	
		Simulator sm=null;
		for(int i=0;i<map.size();i++) {
			if(id.equalsIgnoreCase(map.get(i).getid())) {
				sm = map.get(i);
			}
		}
		return sm;
	}
	
	public static void closeSimulator(String id) {
		Simulator sm=null;
		for(int i=0;i<map.size();i++) {
			if(id.equalsIgnoreCase(map.get(i).getid())) {
				sm = map.get(i);
			}
		}
		if(sm != null) {
			sm.close();
		}
	}

}
