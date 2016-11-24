package de.ast.xmlparse;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;

import org.w3c.dom.Document;

public class XmlParse {

  public static void main(String[] args) throws Exception {
    String fileName = "xmlparse/TxExport_189_201610281106.xml";

    long startTime = System.currentTimeMillis();
    long endTime;
    
    Document xmlDoc = XmlService.getXMLFromFile(fileName);
    endTime = System.currentTimeMillis();
    System.out.println("Parsing the xmlDoc took : " + (endTime - startTime) + " msecs");
    
    XmlService xmlService = new XmlService(xmlDoc, true);
    startTime = System.currentTimeMillis();
    XPath xpath = xmlService.getXPath();
    endTime = System.currentTimeMillis();
    System.out.println("Caching namespace definitions took : " + (endTime - startTime) + " msecs");

  }

  private String convertXMLDocToString(Document xmlDoc) throws Exception {
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    StringWriter writer = new StringWriter();
    transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));
    String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

    return output;
  }

}
