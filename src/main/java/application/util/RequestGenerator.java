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
 * util class for generating requests string xml
 */
public class RequestGenerator implements Properties {

    private RequestGenerator() {
    }

    private static final Random random = new Random();

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
    public static Payment generateSinglePayment() {
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
