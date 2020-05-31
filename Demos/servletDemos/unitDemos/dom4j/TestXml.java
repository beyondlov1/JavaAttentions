package com.beyond.test;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXml {
	public static void main(String[] args) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(TestXml.class.getResourceAsStream("/test.xml"));
		Element rootElement = document.getRootElement();
		System.out.println(rootElement.getName());

		List<Element> userinfo = rootElement.elements("userinfo");
		for (Element element : userinfo) {
			System.out.println(element.getName());
			Element age = element.element("age");
			System.out.print(age.getName());
			System.out.println(" " + age.getTextTrim());

			Element address = element.element("address");
			System.out.print(address.getName() + " ");
			List<Attribute> addressAttrs = address.attributes();
			for (Attribute attr : addressAttrs) {
				System.out.print(attr.getName());
				System.out.print("=" + attr.getValue());
				System.out.print(" ");
			}
			System.out.println("");
			System.out.println("");
		}

	}
}
