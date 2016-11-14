import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ScorecardAnalyse {

  public static void main(String[] args) {

    XPathHandler xph = new XPathHandler();
    try {
      NodeList nodeList = xph.getTransactions();

  //    for (int i = 0; i < nodeList.getLength(); i++) {
      for (int i = 0; i < 30; i++) {
        Node transactionNode = nodeList.item(i);

        List<List<String>> data = xph.getTransactionData(transactionNode);

        for (List<String> dat : data) {
          for(String da : dat) {
            System.out.print(da + " ");
          }
          System.out.println("");
        }
      }

    }
    catch (XPathExpressionException | ParserConfigurationException | SAXException |

        IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
