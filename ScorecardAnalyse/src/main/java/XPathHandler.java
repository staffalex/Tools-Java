import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathHandler {

  DocumentBuilderFactory factory;
  DocumentBuilder        builder;
  Document               doc;
  XPathFactory           xPathfactory;
  XPath                  xpath;

  public NodeList getTransactions() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

    factory = DocumentBuilderFactory.newInstance();
    builder = factory.newDocumentBuilder();
  //  doc = builder.parse("TxExport_189_201610281106.xml");   // Ikaros_ICD
    doc = builder.parse("TxExport_8089809_201610281307.xml");  // Vodafone_ZV_ICD_RPP
    
    xPathfactory = XPathFactory.newInstance();
    xpath = xPathfactory.newXPath();

    XPathExpression expr = xpath.compile("//*[local-name()='Transaction']");

    NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

    return nodeList;

  }
  
  public String getAttribute(Node node, String attributeName) {
    
    return node.getAttributes().getNamedItem(attributeName).getNodeValue();
  }
  
  public List<List<String>> getTransactionData(Node transactionNode) throws XPathExpressionException {
    
    List<List<String>> transactionData = new ArrayList<>();
    String txId = getAttribute(transactionNode, "id");
    
  //  System.out.println("TransactionId : " + txId);
    
    XPathExpression requestNodeExpr = xpath.compile("*[local-name()='Request']//*[local-name()='Request' and @type='Vodafone_ZV_ICD_RPP']");
    NodeList nodeList = (NodeList) requestNodeExpr.evaluate(transactionNode, XPathConstants.NODESET);
    if (nodeList != null && nodeList.getLength() > 0) {
      
      //System.out.println("matching request nodes : " + nodeList.getLength());

      transactionData.add(getResponseData(transactionNode));
    }
    
    return transactionData;
  }

  private List<String> getResponseData(Node transactionNode) throws XPathExpressionException {

    List<String> responseData = new ArrayList<>();
    
    XPathExpression requestNodeExpr = xpath.compile("*[local-name()='Response']");
    NodeList responses = (NodeList) requestNodeExpr.evaluate(transactionNode, XPathConstants.NODESET);
    Node response = responses.item(0);
    
    String txId = getAttribute(transactionNode, "id");
    
    responseData.add(txId);
    responseData.add("AZ : " + getValue(response, "*//AVFeature"));
    responseData.add("ICD_Score : " + getValue(response, "*//Scores/Score/ScoreValue"));
    responseData.add("ICD_Type : " + getValue(response, "*//Scores/Score/ScoreType"));  
    responseData.add("RPP : " + getValue(response, "*//ValidationResult"));
    responseData.add("Drools : " + getValue(response, "*//LrScore"));
    
    return responseData;
  }

  public String getValue(Node parent, String nodeName) throws XPathExpressionException {
    
    XPathExpression expr = xpath.compile(nodeName);
    NodeList nodeList = (NodeList) expr.evaluate(parent, XPathConstants.NODESET);
    
  //  System.out.println("Value nodes for [" + nodeName + "] : " + nodeList.getLength());
    
    if (nodeList != null && nodeList.getLength() == 1) {
      Node node = nodeList.item(0);
      return node.getTextContent();
    }
    
    return "";
  }
  
  public String getNumberValue(Node parent, String nodeName) throws XPathExpressionException {
    
    XPathExpression expr = xpath.compile(nodeName);
    Double dValue = (Double) expr.evaluate(parent, XPathConstants.NUMBER);
    
    if (dValue != null) {
      return dValue.toString();
    }
    
    return "";
  }
  
}


