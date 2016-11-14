package de.ast.xmlparse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.Charset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParse {

    private String workdir = "C:/work/git/AIDA/ims-ng/work/dbunit/";

    public static void main(String[] args) throws Exception {
        String xml = FileUtils.readFileToString(new File("C:/StrategyOne/engine43/DPR/SKYIPT.xml"), Charsets.UTF_8);

        NodeList nodeList = getSomeNodes(xml);
        System.out.println("Found : " + nodeList.getLength() + " nodes");
        
        for(int i = 0; i < nodeList.getLength(); i++)
        {
            String nodeValue = null;
            Node node = nodeList.item(i);
            
            nodeValue = node.getFirstChild().getNodeValue();
            if (nodeValue != null)
            {
                if (nodeValue.equals("CONST_SYSTEM_VERSION"))
                {
                    System.out.println("found");
                }
            }
        }
    }
    
    private static NodeList getSomeNodes(String xmlString) throws Exception {
        Document doc = getXMLFromString(xmlString);

        return doc.getElementsByTagName("ID");
    }

    private static Node getAuthToken(String xml) throws Exception {
        Document doc = getXMLFromString(xml);
        NodeList authTokenNodeList = doc.getElementsByTagName("PAX_BLK_ROW");
        return authTokenNodeList.item(0);
    }

    public static Document getXMLFromString(String xmlString) throws Exception {
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        builder = factory.newDocumentBuilder();
        doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes(Charset.forName("UTF-8"))));
        return doc;
    }

    //      private String convertXMLDocToString(Document xmlDoc) throws Exception {
    //        TransformerFactory tf = TransformerFactory.newInstance();
    //        Transformer transformer = tf.newTransformer();
    //        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    //        StringWriter writer = new StringWriter();
    //        transformer.transform(new DOMSource(xmlDoc), new StreamResult(writer));
    //        String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
    //
    //        return output;
    //    }

}
