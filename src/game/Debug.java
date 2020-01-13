package game;

import java.util.ArrayList;

public class Debug {

	private static String string = "";
	private static ArrayList<String> hist = new ArrayList<String>();

	public static void add(String s) {
		if (!hist.contains(s)) {
			if(! string.equals(""))
				string = "\t" + string;
			string = s + string;
		}
		hist.add(s);
	}

	public static void print(boolean d) {
		if (d) {
			System.out.println(string);
			string = "";
			hist.clear();
		}
	}
}
