package application;


import application.util.RandomModelCreator;
import application.security.MessageSecurity;
import application.util.XmlParsing;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.SignatureException;

class Messenger implements Properties {

    private static MessageSecurity security = MessageSecurity.getInstance();
    private static URLConnection connection;

    private Messenger() {
    }

    /**
     * @param operationName the name of the operation and the file xml for it
     */
    static void operation(String operationName) {
        String xmlMessage;
        System.out.println("\n--------" + operationName + "--------");
        xmlMessage = XmlParsing.XmlToString(operationName + ".xml");
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
            LOGGER.info("response xml  : \n" + "\n" + responceXml + "\n");
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
        LOGGER.info("request xml   : \n" + "\n" + message + "\n");
        LOGGER.info("request sign  : " + requestSign);
    }
}
