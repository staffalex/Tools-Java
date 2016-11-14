package de.ast.xmlparse;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class XmlService
{
  private static final Logger logger = LogManager.getLogger(XmlService.class);

  private XPath xpath = null;
  private DocumentBuilder builder = null;
  private String DATE_PATTERN = "dd-MM-yyyy";
  private DateFormat df = new SimpleDateFormat(DATE_PATTERN);
  private DateFormat dfOld = new SimpleDateFormat("dd/MM/yyyy");

  private XPath getXPath()
  {
    if (xpath == null)
    {
      xpath = XPathFactory.newInstance().newXPath();
    }
    return xpath;
  }
  
  /**
   * Get node value for specified xpath.
   * 
   * @param xmlDoc
   *          the xml doc to parse
   * @param xpath
   *          the xpath for node value.
   * @return the node value
   * @throws IMSBusinessException
   */
  public String getNodeValue(Node xmlDoc, String xpath)
  {

    String nodeValue = null;
    Node node = getNode(xmlDoc, xpath);

    if (node != null && node.getFirstChild() != null)
    {
      nodeValue = node.getFirstChild().getNodeValue();
      if (nodeValue != null)
      {
        // log.debug("xpath: get nodevalue :"+nodeValue);
      }
      else
      {
        logger.debug("xpath: no nodevalue found for xpath:" + xpath);
      }
    }
    else
    {
      logger.debug("xpath: nodevalue is null for xpath:" + xpath);
    }
    return nodeValue;
  }

  public Node getNode(Node xmlDoc, String xpathExpr)
  {
    try
    {
      XPathExpression expr = getXPath().compile(xpathExpr);
      return (Node) expr.evaluate(xmlDoc, XPathConstants.NODE);
    }
    catch (XPathExpressionException e)
    {
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
  public NodeList getNodeList(Node xmlDoc, String xpathExpr) throws XPathExpressionException
  {
    XPathExpression expr = getXPath().compile(xpathExpr);
    return (NodeList) expr.evaluate(xmlDoc, XPathConstants.NODESET);
  }

  /**
   * Removes node from document.
   * 
   * @param xmlDoc
   * @param xpath
   * @return
   * @throws XPathExpressionException
   */
  public Node removeNode(Node xmlDoc, String xpath) throws XPathExpressionException
  {
    Node node = null;

    if (xmlDoc != null && xpath != null)
    {
      node = getNode(xmlDoc, xpath);
      if (node != null && node.getParentNode() != null)
      {
        node = node.getParentNode().removeChild(node);
      }
    }
    return node;
  }

  /**
   * Modify the node.
   * 
   * @param xmlDoc
   * @param xpath
   * @param value
   *          Value to set in Node.
   */
  public void modifyNodeValue(Document xmlDoc, Node nod, String xpath, String value)
  {
    Node n = getNode(nod, xpath);
    if (n != null)
    {
      Node child = n.getFirstChild();
      if (child != null)
      {
        child.setNodeValue(value);
      }
      else
      {
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
  public void modifyNodeAttribute(Document xmlDoc, Element nod, String attrName, String attrValue)
  {
    Attr att = xmlDoc.createAttribute(attrName);
    att.setValue(attrValue);
    nod.setAttributeNode(att);
  }

  public Document getDocument(String xmlSource) throws SAXException, ParserConfigurationException, IOException
  {
    return getDocumentBuilder().parse(new InputSource(new StringReader(xmlSource)));
  }
  
  public Document getDocument(File xmlFileSource) throws SAXException, ParserConfigurationException, IOException
  {
    return getDocumentBuilder().parse(xmlFileSource);
  }
  
  public DocumentBuilder getDocumentBuilder() throws ParserConfigurationException
  {
    if (builder == null)
    {
      builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    return builder;
  }

  /**
   * Get the booking Id from given xml-Document.
   * 
   * @param xmlDoc
   * @return
   */
  public String getZipcodeFromXML(Document xmlDoc)
  {
    return getNodeValue(xmlDoc, "//PAY_ZIP_CD");
  }

  /**
   * Get the booking Id from given xml-Document.
   * 
   * @param xmlDoc
   * @return -1 if no booking number is found
   */
  public int getBookingId(Document xmlDoc) throws XPathExpressionException
  {
    String xpath = "/*/HEADER/BKGNO";
    String bookId = getNodeValue(xmlDoc, xpath);
    if (bookId != null)
    {
      return Integer.valueOf(bookId).intValue();
    }
    return -1;
  }
  
  public String getBookingStatus(Document xmlDoc) throws XPathExpressionException
  {
    return getNodeValue(xmlDoc, "/*/HEADER/BKG_STAT");
  }

  /**
   * Get the documentType from the given xml-Document.
   * 
   * @param xmlDoc
   * @return
   */
  public String getDocType(Document xmlDoc) throws XPathExpressionException
  {
    String xpath = "//DOC_TYP";
    return getNodeValue(xmlDoc, xpath);

  }

  /**
   * 
   * @param xmlDoc
   * @return the earliest departure date from CRU, HTL, or AIR item
   */
  public Date getEarliestDepDate(Document xmlDoc)
  {

    String xpath = "//ITM_BLK_ROW[(PROD_TYPE='CRU' and (boolean(ITEM_TYPE=//BKG_STAT and NEW_CXL='true') or boolean(ITEM_TYPE=//BKG_STAT and not(//NEW_CXL)) or ITEM_TYPE='BKD')) "
        + " or (PROD_TYPE='HTL' and (boolean(ITEM_TYPE=//BKG_STAT or ITEM_TYPE='BKD'))) "
        + " or (PROD_TYPE='AIR' and (boolean(ITEM_TYPE=//BKG_STAT and //NEW_AIR_CXL='true') or boolean(ITEM_TYPE=//BKG_STAT and not(//NEW_AIR_CXL)) "
        + " or boolean(ITEM_TYPE='CRQ' and //BKG_STAT='CXL' and //NEW_AIR_CXL='true') or ITEM_TYPE='BKD' or ITEM_TYPE='POS' or ITEM_TYPE='REQ'))]";

    Date startDate = null;
    Date itemDate = null;

    try
    {
      NodeList itemList = getNodeList(xmlDoc, xpath);

      if (itemList != null)
      {
        for (int i = 0; i < itemList.getLength(); i++)
        {

          Node item = itemList.item(i);
          String prodType = getNodeValue(item, "PROD_TYPE");

          if (prodType != null)
          {

            String dateString = null;

            if (prodType.equals("CRU"))
            {
              dateString = getNodeValue(item, "CRU_BLK/ITIN_DT");

            }
            else if (prodType.equals("HTL"))
            {
              dateString = getNodeValue(item, "HTL_BLK/ARR_DT");

            }

            if (dateString != null)
            {
              // parse date
              try
              {
                itemDate = df.parse(dateString);
              }
              catch (ParseException e)
              {
                try
                {
                  itemDate = dfOld.parse(dateString);
                }
                catch (ParseException e2)
                {
                  logger.error("failure parsing date: " + dateString, e2);
                  itemDate = null;
                }
              }

              if (itemDate != null && (startDate == null || startDate.after(itemDate)))
              {
                startDate = itemDate;
              }
            }
            // extract flight dates from air rows
            if (prodType.equals("AIR"))
            {
              try
              {
                itemDate = calculateFirstAirDate(item);

              }
              catch (ParseException e2)
              {
                logger.error("failure parsing date: " + dateString, e2);
                itemDate = null;
              }

              if (itemDate != null && (startDate == null || startDate.after(itemDate)))
              {
                startDate = itemDate;
              }
            }
          }
        }
      }
      else
      {
        logger.warn("no items found in entry ");
      }
    }
    catch (XPathExpressionException e)
    {
      logger.warn("INVALID_DOCUMENT", e);
    }

    return startDate;
  }

  private Date calculateFirstAirDate(Node n) throws XPathExpressionException, ParseException
  {
    Date startDate = null;
    NodeList nl = getNodeList(n, "AIR_BLK/AIR_ROW");
    if (nl != null)
    {
      int l = nl.getLength();
      for (int i = 0; i < l; i++)
      {
        Node nn = nl.item(i);
        Date itemDate = null;
        try
        {
          itemDate = df.parse(getNodeValue(nn, "FS_DEP_DT"));
        }
        catch (ParseException e)
        {
          itemDate = dfOld.parse(getNodeValue(nn, "FS_DEP_DT"));
        }
        if (itemDate != null && (startDate == null || itemDate.before(startDate)))
        {
          startDate = itemDate;
        }
      }
    }
    return startDate;
  }
  
  public void addNodeValue(Document xmlDoc, String xpath, String nodeName, String nodeValue)
      throws XPathExpressionException
  {

    Node root = getNode(xmlDoc, xpath);
    if (root != null)
    {
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
  public void setNodeValue(Document xmlDoc, String xpath, String value)
  {

    Node n = getNode(xmlDoc, xpath);
    if(n != null)
    {
      Node child = n.getFirstChild();
      if(child != null)
      {
        child.setNodeValue(value);
      }
      else
      {
        Node nodT = xmlDoc.createTextNode(value);
        n.appendChild(nodT);
      }
    }
  }
  
  /**
   * add a new element to a xml document
   * @param xml - the document
   * @param nodeName - name of the node to add
   * @param nodeValue - value of node to add
   * @param xpath - path where to add the node
   */
  public void addElement(Document xml, String nodeName, String nodeValue, String xpath)
  {
    Element child = xml.createElement(nodeName);
    Node typeValue = xml.createTextNode(nodeValue);
    child.appendChild(typeValue);
    getNode(xml, xpath).appendChild(child);
  }
  
}
