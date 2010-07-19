package com.inepex.projectcontext.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileUtil {

	public static String readFile(String path) throws Exception {
		BufferedReader breader = new BufferedReader(new FileReader(new File(
				path)));
		String content = "";
		String strLine = "";
		while ((strLine = breader.readLine()) != null) {
			content += strLine + "\n";
		}
		breader.close();
		return content;
	}

	public static void writeFile(String path, String content) throws Exception {
		File f = new File(path);
		if (f.exists())
			f.delete();
		f.createNewFile();
		FileWriter fstream = new FileWriter(f);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(content);;
		out.close();
	}

}
