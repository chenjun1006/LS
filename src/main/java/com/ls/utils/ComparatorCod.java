package com.ls.utils;

import java.util.Comparator;
import java.util.List;

public class ComparatorCod implements Comparator<List<String>> {
	
	private int sortOrder;
	
	public ComparatorCod(String order) {
		super();
		if ("desc".equalsIgnoreCase(order)) sortOrder = 1;
		else sortOrder = 0;
	}
	
	@Override
	public int compare(List<String> o1, List<String> o2) {
		// TODO Auto-generated method stub
		String seq1 = o1.get(2);
        String seq2 = o2.get(2);
        if (sortOrder == 0)
        	return seq1.compareTo(seq2);
        else
        	return seq2.compareTo(seq1);
	}

}
