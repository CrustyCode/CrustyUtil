
package org.crusty.util;

import java.util.ArrayList;
import java.util.Random;

public class StringUtil {
	
	/**
	 * Converts a String list in the format: 35, 32, 12, 64
	 * into a String[] of all the entities in the list
	 * @param s 
	 * @return
	 */
	public static ArrayList<String> stringListToArrayList(String s) {
		ArrayList<String> entities = new ArrayList<String>();
		s = s.replace(" ", "");
		int i = 0;
		int lastPos = 0;
		while (i < s.length()) {
			if (s.charAt(i) == ',') {
				entities.add(s.substring(lastPos, i));
				lastPos = i + 1;
			}
			i++;
		}
		entities.add(s.substring(lastPos, s.length()));
		if (entities.size() == 0)
			return null;
		return entities;
	}
	
	/**
	 * Converts a String list in the format: 35, 32, 12, 64
	 * into a String[] of all the entities in the list
	 * @param s 
	 * @return
	 */
	public static String[] stringListToArray(String s) {
		ArrayList<String> entities = stringListToArrayList(s);
		if (entities == null)
			return null;
		String[] ent = new String[entities.size()];
		for (int i1 = 0; i1 < entities.size(); i1++) {
			ent[i1] = entities.get(i1);
			MiscUtil.p("> " + ent[i1]);
		}
		return ent;
	}
	
	public static String randomString(int maxLength) {
		Random r = new Random();
		int len = r.nextInt(maxLength);
		String s = "";
		for (int i = 0; i < len; i++) {
			s += (char) r.nextInt(255);
		}
		return s;
	}
}
