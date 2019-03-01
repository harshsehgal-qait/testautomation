/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation.utils;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import org.testng.Reporter;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class YamlReader {

	public static String yamlFilePath = null;
	public static String tier;

	@SuppressWarnings("resource")
	public static String setYamlFilePath() {
		String tierFromConfigFile = getProperty("Config.properties", "tier").trim();
		tier = System.getProperty("environment", tierFromConfigFile);

		System.out.println("*******Tier********* : " + tier);

		if (tier.equalsIgnoreCase("dev")) {
			yamlFilePath = "src/test/resources/testdata/DEV_TestData.yml";
		} else if (tier.equalsIgnoreCase("qa")) {
			yamlFilePath = "src/test/resources/testdata/QA_TestData.yml";
		} else if (tier.equalsIgnoreCase("dev2")) {
			yamlFilePath = "src/test/resources/testdata/DEV2_TestData.yml";
		} else if (tier.equalsIgnoreCase("stage")) {
			yamlFilePath = "src/test/resources/testdata/STAGE_TestData.yml";
		} else if (tier.equalsIgnoreCase("perf")) {
			yamlFilePath = "src/test/resources/testdata/PERF_TestData.yml";
		} else if (tier.equalsIgnoreCase("dwlp2")) {
			yamlFilePath = "src/test/resources/testdata/DWLP2_TestData.yml";
		} else if (tier.equalsIgnoreCase("stage2")) {
			yamlFilePath = "src/test/resources/testdata/STAGE2_TestData.yml";
		} else if (tier.equalsIgnoreCase("prod") || tier.equalsIgnoreCase("production")) {
			yamlFilePath = "src/test/resources/testdata/PROD_TestData.yml";
		} else {
			Reporter.log("YOU HAVE PROVIDED WRONG TIER IN CONFIG!!! using dev test data", true);
		}

		System.out.println("YAML file path :: " + yamlFilePath);
		try {
			new FileReader(yamlFilePath);
		} catch (FileNotFoundException e) {
			Reporter.log("Wrong Tier.", true);
			System.exit(0);
		}
		return yamlFilePath;
	}

	public static String getYamlValue(String token) {
		try {
			return getValue(token);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	public static String getData(String token) {
		return getYamlValue(token);
	}

	public static Map<String, Object> getYamlValues(String token) {
		Reader doc;
		try {
			doc = new FileReader(yamlFilePath);
		} catch (FileNotFoundException ex) {
			System.out.println("File not valid or missing!!!");
			ex.printStackTrace();
			return null;
		}
		Yaml yaml = new Yaml();
		// TODO: check the type casting of object into the Map and create
		// instance in one place
		Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
		return parseMap(object, token + ".");
	}

	private static String getValue(String token) throws FileNotFoundException {
		Reader doc = new FileReader(yamlFilePath);
		Yaml yaml = new Yaml();
		Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
		return getMapValue(object, token);

	}

	public static String getMapValue(Map<String, Object> object, String token) {
		// TODO: check for proper yaml token string based on presence of '.'
		String[] st = token.split("\\.");
		return parseMap(object, token).get(st[st.length - 1]).toString();
	}

	private static Map<String, Object> parseMap(Map<String, Object> object, String token) {
		if (token.contains(".")) {
			String[] st = token.split("\\.");
			object = parseMap((Map<String, Object>) object.get(st[0]), token.replace(st[0] + ".", ""));
		}
		return object;
	}

	public static int generateRandomNumber(int MinRange, int MaxRange) {
		int randomNumber = MinRange + (int) (Math.random() * ((MaxRange - MinRange) + 1));
		return randomNumber;
	}
}
