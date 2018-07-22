package application.util;

import application.Properties;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

public class XmlParsing implements Properties {

    private XmlParsing() {
    }

    /**
     * parse xml from file
     * @param fileName - name of file with xml
     * @return string with xml
     */
    public static String XmlToString(String fileName)  {
        String output=null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(RESOURCES_DIR+"/xml/" + fileName));
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            output = writer.getBuffer().toString();
        }catch (Exception e){
            LOGGER.error("an exception in XmlToString() : "+e);
            e.printStackTrace();
        }
        return output;
    }
}
