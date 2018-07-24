package application;


import application.security.MessageSecurity;
import application.util.RequestGenerator;
import application.util.XmlParsing;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.SignatureException;
/**
 * Class to sends requests and receiving a response
 */
public class HttpsClient implements Properties {
    private static final Logger LOGGER = Logger.getLogger(HttpsClient.class);
    private static MessageSecurity security = MessageSecurity.getInstance();
    private static HttpsURLConnection connection;

    private HttpsClient() {
    }

    /**
     * performs one of the operations
     *
     * @param operation - Operation type
     */
    public static void operation(MainOperations operation) {
        String xmlMessage;
        System.out.println("\n--------" + operation.name() + "--------");
        if (operation == MainOperations.VERIFY) {
            xmlMessage = RequestGenerator.generateVerify();
        } else if (operation == MainOperations.PAYMENT) {
            xmlMessage = RequestGenerator.generatePayment();
        } else {
            xmlMessage = RequestGenerator.generateStatus();
        }
        connection = configureConnection();
        setConnectionHeaders(xmlMessage);
        sendRequest(xmlMessage);
        getResponce();
    }

    /**
     * create and configure a connection
     *
     * @return instance of URLConnection
     */
    private static HttpsURLConnection configureConnection() {
        URL url;
        HttpsURLConnection connection = null;
        try {
            url = new URL(URL);
            connection = (HttpsURLConnection) url.openConnection();
        } catch (Exception e) {
            LOGGER.error("an exception in createConnection() : " + e);
            e.printStackTrace();
        }
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setDefaultUseCaches(false);
        connection.setConnectTimeout(20000);
        connection.setReadTimeout(20000);
        return connection;
    }

    /**
     * set headers of the connection
     * and signs a String
     *
     * @param message - string for signature
     */
    private static void setConnectionHeaders(String message) {
        String requestSign = null;
        try {
            requestSign = security.sign(message);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty(HEADER_NAME, requestSign);
        LOGGER.info("request xml   : \n" + "\n" + XmlParsing.prettyFormat(message) + "\n");
        LOGGER.info("request sign  : " + requestSign);
    }

    /**
     * sends POST request
     *
     * @param message - POST body
     */
    private static void sendRequest(String message) {
        OutputStreamWriter writer;
        try {
            writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            LOGGER.error("an exception in sendRequest() : " + e);
            e.printStackTrace();
        }
    }

    /**
     * receiving a response and printing it into logs
     */
    private static void getResponce() {
        try {
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            StringBuilder buf = new StringBuilder();
            char[] cbuf = new char[2048];
            int num;
            while (-1 != (num = reader.read(cbuf))) {
                buf.append(cbuf, 0, num);
            }
            String responceXml = buf.toString();
            String responceSign = connection.getHeaderField(HEADER_NAME);
            LOGGER.info("response xml  : \n" + "\n" + XmlParsing.prettyFormat(responceXml) + "\n");
            LOGGER.info("response sign : " + responceSign);
            LOGGER.info("verify sign   : " + security.verify(responceXml, responceSign));
        } catch (Exception e) {
            LOGGER.error("an exception in getResponce() : " + e);
            e.printStackTrace();
        }
    }
}