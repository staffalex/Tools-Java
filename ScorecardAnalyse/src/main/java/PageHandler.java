import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PageHandler extends DefaultHandler {

  private final PageProcessor processor;
  private StringBuilder       stringBuilder;
  private boolean             idSet = false;

  public PageHandler(PageProcessor processor) {
    this.processor = processor;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    System.out.println("uri : " + uri);
    System.out.println("localName : " + localName);
    System.out.println("qname : " + qName);
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    // Unchanged from your implementation
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equals("page")) {

//      processor.process(page);
//      page = null;

    }
  }

}