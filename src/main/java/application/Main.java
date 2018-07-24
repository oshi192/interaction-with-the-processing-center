package application;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        HttpsClient.operation(MainOperations.VERIFY);
        HttpsClient.operation(MainOperations.PAYMENT);
        HttpsClient.operation(MainOperations.STATUS);
    }

}
