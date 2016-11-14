import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class XMLManager {

  public static void load(PageProcessor processor) {
    SAXParserFactory factory = SAXParserFactory.newInstance();

    try {

      SAXParser parser = factory.newSAXParser();
      File file = new File("TxExport_189_201610281106.xml");
      PageHandler pageHandler = new PageHandler(processor);

      parser.parse(file, pageHandler);

    }
    catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    catch (SAXException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }

  }
}
