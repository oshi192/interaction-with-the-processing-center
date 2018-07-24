package application.util;

import application.Properties;
import application.model.request.Request;
import application.model.response.Response;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * class for working with xml format strings and parsing xml
 */

public class XmlParsing implements Properties {
    private static final Logger LOGGER = Logger.getLogger(XmlParsing.class);

    private XmlParsing() {
    }

    /**
     * parse .xml file from resources folder to String
     *
     * @param fileName - name of file with xml
     * @return string with xml
     */
    public static String XmlToString(String fileName) {
        String output = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(RESOURCES_DIR + fileName));
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            output = writer.getBuffer().toString();
        } catch (Exception e) {
            LOGGER.error("an exception in XmlToString() : " + e);
            e.printStackTrace();
        }
        return output;
    }

    /**
     * converting XML string to response object
     *
     * @param xmlString xml that needed convert
     * @return converted Response object
     */
    public static Response convertToResponse(String xmlString) {
        Response response = null;
        JAXBContext jaxbContext;
        try {
            StringReader sr = new StringReader(xmlString);
            jaxbContext = JAXBContext.newInstance(Response.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            response = (Response) unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Convert Request object to string xml
     *
     * @param request object for conversion
     * @return String with xml formated data from request object
     */
    static String convertToString(Request request) {
        JAXBContext jaxbContext;
        String xmlString = null;
        try {
            jaxbContext = JAXBContext.newInstance(request.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(request, sw);
            xmlString = sw.toString();
        } catch (JAXBException e) {
            LOGGER.error("cannot convert [" + request + "] to string: " + e);
            e.printStackTrace();
        }
        return xmlString;
    }

    /**
     * formats a one-line string xml into a habitual appearance
     *
     * @param input - one-line string with xml to be changed
     * @return formatted string with xml
     */
    public static String prettyFormat(String input) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", INDENT);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
