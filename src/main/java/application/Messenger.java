package application;

import application.security.MessageSecurity;
import application.util.RequestGenerator;
import application.util.XmlParsing;
import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.SignatureException;

class Messenger implements Properties {

    private static final Logger LOGGER =Logger.getLogger(Messenger.class);
    private static MessageSecurity security = MessageSecurity.getInstance();
    private static URLConnection connection;

    private Messenger() {
    }

    /**
     * @param operation
     */
    static void operation(MainOperations operation) {
        String xmlMessage;
        System.out.println("\n--------" + operation.name() + "--------");
        if (operation==MainOperations.VERIFY) {
            xmlMessage = RequestGenerator.generateVerify();
        }else if(operation==MainOperations.PAYMENT){
            xmlMessage = RequestGenerator.generatePayment();
        }else{
            xmlMessage = RequestGenerator.generateStatus();
        }
        setConnectionHeaders(xmlMessage);
        sendRequest(xmlMessage);
        getResponce();
    }


    /**
     * @param message - POST body
     */
    public static void sendRequest(String message) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            LOGGER.error("an exception in sendRequest() : "+e);
            e.printStackTrace();
        }

    }


    /**
     *
     */
    public static void getResponce() {
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
            LOGGER.error("an exception in getResponce() : "+e);
            e.printStackTrace();
        }
    }

    /**
     * create and configure a connection
     *
     * @return instance of URLConnection
     */
    private static URLConnection createConjection() {
        URL url;
        URLConnection urlConnection = null;
        try {
            url = new URL(URL);
            urlConnection = url.openConnection();
        } catch (Exception e) {
            LOGGER.error("an exception in createConjection() : "+e);
            e.printStackTrace();
        }
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setConnectTimeout(20000);
        urlConnection.setReadTimeout(20000);
        urlConnection.setUseCaches(false);
        urlConnection.setDefaultUseCaches(false);
        return urlConnection;
    }

    /**
     * set headers of the connection
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
        connection = createConjection();
        connection.setRequestProperty("Content-Type", "text/xml");
        connection.setRequestProperty(HEADER_NAME, requestSign);
        LOGGER.info("request xml   : \n" + "\n" + XmlParsing.prettyFormat(message) + "\n");
        LOGGER.info("request sign  : " + requestSign);
    }
}
