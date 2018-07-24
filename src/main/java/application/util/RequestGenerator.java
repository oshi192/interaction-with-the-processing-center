package application.util;

import application.Properties;
import application.model.Attribute;
import application.model.request.Payment;
import application.model.request.Request;
import application.model.request.Status;
import application.model.request.Verify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * util class for generating requests xml formats string
 */
public class RequestGenerator implements Properties {

    private RequestGenerator() {
    }

    private static final Random random = new Random();

    /**
     * Generating Verify request
     * @return one line string with xml verify request
     */
    public static String generateVerify() {
        Verify verify = new Verify();
        verify.setAccount(generateNumberString(ACCOUNT_LENGTH));
        verify.setService(SERVICE);
        verify.setAttributes(generateAtributes(50));
        List<Verify> data = new ArrayList();
        data.add(verify);
        Request request = new Request(POINT, data);
        return XmlParsing.convertToString(request);
    }

    /**
     * Generating Payment request
     * The request may include several Payments
     * @return one line string with xml payment request
     */
    public static String generatePayment() {
        Verify verify = new Verify();
        verify.setAccount(generateNumberString(ACCOUNT_LENGTH));
        verify.setService(SERVICE);
        List<Payment> data = new ArrayList();
        int maxPeyments = random.nextInt(MAX_NUMBER_OF_ELEMENTS) + 1;
        for (int i = 0; i < maxPeyments; i++) {
            Payment payment = generateSinglePayment();
            data.add(payment);
        }
        Request request = new Request(POINT, data);
        return XmlParsing.convertToString(request);
    }

    /**
     * Generating Payment object
     * @return generated Payment object
     */
    private static Payment generateSinglePayment() {
        Payment payment=new Payment();
        payment.setAccount(RequestGenerator.generateNumberString(ACCOUNT_LENGTH));
        payment.setId(random.nextLong());
        payment.setSum(random.nextInt(SUM_MAX+SUM_MIN)+SUM_MIN);
        payment.setCheck(random.nextInt(Integer.MAX_VALUE));
        payment.setService(SERVICE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");
        payment.setDate(dateFormat.format(new Date()));
        payment.setAttributes(generateAtributes(50));
        return payment;
    }

    /**
     * Generating Status request
     * The request may include several Statuses
     * @return one line string with xml status request
     */
    public static String generateStatus() {
        List<Status> data = new ArrayList();
        int maxPeyments = random.nextInt(MAX_NUMBER_OF_ELEMENTS) + 1;
        for (int i = 0; i < maxPeyments; i++) {
            Status status = new Status(random.nextLong());
            data.add(status);
        }
        Request request = new Request(POINT, data);
        return XmlParsing.convertToString(request);
    }


    /**
     * Generating list with attributes
     * @param percentChanсe = chance that we will have attributes
     * if we will have attributes we can create several instances
     * @return List with attributes
     */
    private static List<Attribute> generateAtributes(int percentChanсe) {
        List<Attribute> attributes = new ArrayList<>();
        if (random.nextInt(100) < percentChanсe % 101) {
            int numberOfAtributes = random.nextInt(ATTRIBUTE_MAX_NUMBER);
            for (int i = 0; i < numberOfAtributes; i++) {
                int number = random.nextInt(AttributeExample.values().length);
                String attributeName = AttributeExample.values()[number].name;
                String attributeValue = AttributeExample.values()[number].value;
                attributes.add(new Attribute(attributeName, attributeValue));
            }
        }
        return attributes;
    }

    private static String generateNumberString(int maxLength) {
        StringBuilder builder = new StringBuilder();
        int length = random.nextInt(maxLength);
        for (int i = 0; i < length; i++) {
            int character = random.nextInt(10);
            builder.append(character);
        }
        return builder.toString();
    }

}
