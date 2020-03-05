package com.ls.utils;

public class StringFilter {

	public static boolean phraseAnagramCheck(String s1, String s2) {
		int count = 0;
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		s1 = s1.replaceAll(" ", "");
		s2 = s2.replaceAll(" ", "");
		if (count == s1.length() && count == s2.length()) {
			return true;
		}
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {

					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		// phraseAnagramCheck("E,B", "S,A");
		String[] fldnam = "a-".split("-");
		System.out.print(fldnam[0] + fldnam[1]);
	}


}
