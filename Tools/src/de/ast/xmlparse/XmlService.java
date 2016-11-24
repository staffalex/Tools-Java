package de.ast.xmlparse;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlService {
  private static final Logger logger                  = LogManager.getLogger(XmlService.class);

  private Document            xmlDoc                  = null;
  private XPath               xpath                   = null;
  private DocumentBuilder     builder                 = null;
  private boolean             recursiveNamespaceCache = false;

  public XmlService(Document xmlDoc, boolean recursiveNamespaceCache) {
    this.xmlDoc = xmlDoc;
    this.recursiveNamespaceCache = recursiveNamespaceCache;
  }

  private XPath getXPath() {
    if (xpath == null) {
      xpath = XPathFactory.newInstance().newXPath();
      xpath.setNamespaceContext(new UniversalNamespaceCache(xmlDoc, recursiveNamespaceCache));
    }
    return xpath;
  }

  /**
   * Get node value for specified xpathExpr.
   * 
   * @param xmlDoc
   *          the xml doc to parse
   * @param xpathExpr
   *          the xpath for node value.
   * @return the node value
   * @throws IMSBusinessException
   */
  public String getNodeValue(Node xmlDoc, String xpathExpr) {

    String nodeValue = null;
    Node node = getNode(xmlDoc, xpathExpr);

    if (node != null && node.getFirstChild() != null) {
      nodeValue = node.getFirstChild().getNodeValue();
      if (nodeValue != null) {
        // log.debug("xpath: get nodevalue :"+nodeValue);
      }
      else {
        logger.debug("xpath: no nodevalue found for xpath:" + xpathExpr);
      }
    }
    else {
      logger.debug("xpath: nodevalue is null for xpath:" + xpathExpr);
    }
    return nodeValue;
  }

  public Node getNode(Node xmlDoc, String xpathExpr) {
    try {
      XPathExpression expr = getXPath().compile(xpathExpr);
      return (Node) expr.evaluate(xmlDoc, XPathConstants.NODE);
    }
    catch (XPathExpressionException e) {
      logger.error("failure evaluating xpath", e);
      System.out.println("sis sucks");
      return null;
    }
  }

  /**
   * Get nodelist of specified xpath.
   * 
   * @param xmlDoc
   * @param xpath
   * @return
   * @throws XPathExpressionException
   */
  public NodeList getNodeList(Node xmlDoc, String xpathExpr) throws XPathExpressionException {
    XPathExpression expr = getXPath().compile(xpathExpr);
    return (NodeList) expr.evaluate(xmlDoc, XPathConstants.NODESET);
  }

  /**
   * Removes node from document.
   * 
   * @param xmlDoc
   * @param xpathExpr
   * @return
   * @throws XPathExpressionException
   */
  public Node removeNode(Node xmlDoc, String xpathExpr) throws XPathExpressionException {
    Node node = null;

    if (xmlDoc != null && xpathExpr != null) {
      node = getNode(xmlDoc, xpathExpr);
      if (node != null && node.getParentNode() != null) {
        node = node.getParentNode().removeChild(node);
      }
    }
    return node;
  }

  /**
   * Modify the node.
   * 
   * @param xmlDoc
   * @param xpathExpr
   * @param value
   *          Value to set in Node.
   */
  public void modifyNodeValue(Document xmlDoc, Node nod, String xpathExpr, String value) {
    Node n = getNode(nod, xpathExpr);
    if (n != null) {
      Node child = n.getFirstChild();
      if (child != null) {
        child.setNodeValue(value);
      }
      else {
        Node nodT = xmlDoc.createTextNode(value);
        n.appendChild(nodT);
      }
    }
  }

  /**
   * Modify node attribute. Set attribute, when doesnt exists.
   * 
   * @param nod
   * @param attrName
   * @param attrValue
   */
  public void modifyNodeAttribute(Document xmlDoc, Element nod, String attrName, String attrValue) {
    Attr att = xmlDoc.createAttribute(attrName);
    att.setValue(attrValue);
    nod.setAttributeNode(att);
  }

  public Document getDocument(String xmlSource) throws SAXException, ParserConfigurationException, IOException {
    return getDocumentBuilder().parse(new InputSource(new StringReader(xmlSource)));
  }

  public Document getDocument(File xmlFileSource) throws SAXException, ParserConfigurationException, IOException {
    return getDocumentBuilder().parse(xmlFileSource);
  }

  public DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
    if (builder == null) {
      builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    return builder;
  }

  public void addNodeValue(Document xmlDoc, String xpath, String nodeName, String nodeValue)
      throws XPathExpressionException {

    Node root = getNode(xmlDoc, xpath);
    if (root != null) {
      Element element = xmlDoc.createElement(nodeName);
      Node textNode = xmlDoc.createTextNode(nodeValue);
      element.appendChild(textNode);
      root.appendChild(element);
    }
  }

  /**
   * @param xmlDoc
   * @param string
   * @param docType
   * @throws XPathExpressionException
   */
  public void setNodeValue(Document xmlDoc, String xpath, String value) {

    Node n = getNode(xmlDoc, xpath);
    if (n != null) {
      Node child = n.getFirstChild();
      if (child != null) {
        child.setNodeValue(value);
      }
      else {
        Node nodT = xmlDoc.createTextNode(value);
        n.appendChild(nodT);
      }
    }
  }

  /**
   * add a new element to a xml document
   * 
   * @param xml
   *          - the document
   * @param nodeName
   *          - name of the node to add
   * @param nodeValue
   *          - value of node to add
   * @param xpath
   *          - path where to add the node
   */
  public void addElement(Document xml, String nodeName, String nodeValue, String xpath) {
    Element child = xml.createElement(nodeName);
    Node typeValue = xml.createTextNode(nodeValue);
    child.appendChild(typeValue);
    getNode(xml, xpath).appendChild(child);
  }

}
