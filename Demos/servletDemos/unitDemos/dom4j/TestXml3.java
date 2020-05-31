package com.beyond.test;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class TestXml3 {
	public static void main(String[] args) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element element = document.addElement("root");
		Element sonElement = element.addElement("son");
		sonElement.addAttribute("name", "jack");
		sonElement.addText("ÖÐ¹úÈË");
		XMLWriter xmlwriter = new XMLWriter(new FileOutputStream("good.xml"));
		xmlwriter.write(document);
	}
}
