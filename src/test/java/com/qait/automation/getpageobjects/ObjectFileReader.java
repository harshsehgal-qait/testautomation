package com.qait.automation.getpageobjects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.qait.automation.utils.ConfigPropertyReader;

/**
 * This class reads the PageObjectRepository text files. Uses buffer reader.
 *
 * @author prashantshukla
 *
 */
public class ObjectFileReader {

//	static String tier;
	static String platform;
	static String filepath = "src"+File.separator+"test"+File.separator+"resources"+File.separator+"PageObjectRepository"+File.separator;
    
	public static String[] getELementFromFile(String pageName,
			String elementName) {

		setPlatform();
		try {
			FileReader specFile = new FileReader(filepath + platform + pageName + ".spec");
			return getElement(specFile, elementName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPageTitleFromFile(String pageName) {
//		setTier();
		setPlatform();
		BufferedReader br = null;
		String returnElement = "";
		try {
			br = new BufferedReader(new FileReader(filepath + platform + pageName
					+ ".spec"));
			String line = br.readLine();

			while (line != null && !line.startsWith("========")) {
				String titleId = line.split(":", 3)[0];
				System.out.println("titleId is :"+titleId);
				if (titleId.equalsIgnoreCase("pagetitle")
						|| titleId.equalsIgnoreCase("title")
						|| titleId.equalsIgnoreCase("page title")) {
					returnElement = line;
					System.out.println("returnElement is :"+returnElement);
					break;
				}
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return returnElement.split(":", 2)[1].trim();
	}

	private static String[] getElement(FileReader specFile, String elementName)
			throws Exception {

		ArrayList<String> elementLines = getSpecSection(specFile);
		for (String elementLine : elementLines) {
			if (elementLine.startsWith(elementName)) {
				return elementLine.split(" ", 3);
			}
		}
		throw new Exception();
	}

	private static ArrayList<String> getSpecSection(FileReader specfile) {
		String readBuff = null;
		ArrayList<String> elementLines = new ArrayList<String>();

		try {
			BufferedReader buff = new BufferedReader(specfile);
			try {
				boolean flag = false;
				readBuff = buff.readLine();
				while ((readBuff = buff.readLine()) != null) {
					if (readBuff.startsWith("========")) {
						flag = !flag;
					}
					if (flag) {
						elementLines.add(readBuff.trim().replaceAll("[ \t]+",
								" "));
					}
					if (!elementLines.isEmpty() && !flag) {
						break;
					}
				}
			} finally {
				buff.close();
				if (elementLines.get(0).startsWith("========")) {
					elementLines.remove(0);
				}
			}
		} catch (FileNotFoundException e) {
			System.out
					.println("Spec File not found at location :- " + filepath);
		} catch (IOException e) {
			System.out.println("exceptional case");
		}
		return elementLines;
	}

	/*private static void setTier() {
		switch (Tiers.valueOf(getProperty("Config.properties", "tier"))) {
		case production:
		case PROD:
		case PRODUCTION:
		case Production:
		case prod:
			tier = "PROD/";
			break;
		case stage:
		case STAGE:
		case Stage:
			tier = "STAGE/";
			break;
		case Dev:
		case DEV:
		case dev:
			tier = "DEV/";
			break;
		case dev2:
			tier = "DEV2/";
		}
	}*/
	
	private static void setPlatform() {
		if ((ConfigPropertyReader.getProperty("browser").contains("ios"))) {
			platform = "Mobile" + File.separator;

			// System.out.println("mobile spec");
		} else {

			platform = "Desktop" + File.separator;
			// System.out.println("web spec");
		}

	}
}
