package com.beyond.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXml2 {
	public static void main(String[] args) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(TestXml2.class.getResourceAsStream("/test2.xml"));
		Element rootElement = document.getRootElement();
		System.out.println(rootElement.getName());

		double sum = 0;
		double eachSum = 0;
		Map<String, Map<String, Object>> topMap = new HashMap<>();
		String name = null;

		List<Element> userinfo = rootElement.elements();
		for (Element element : userinfo) {
			List<Element> e1 = element.elements();
			Map<String, Object> map = new HashMap<>();
			for (Element e2 : e1) {
				if (e2.getTextTrim() != null && !"".equals(e2.getTextTrim())) {
					sum += Double.parseDouble(e2.getTextTrim());
					eachSum += Double.parseDouble(e2.getTextTrim());
					map.put(name, eachSum);
				} else {
					name = e2.getName();
					eachSum = 0;
				}

			}
			topMap.put(element.getName(), map);

			System.out.println(element.getName());
			System.out.println(sum);
			System.out.println();
			sum = 0;
		}

		System.out.println("list:" + topMap);
	}

}
