package application;

/**
 *
 */
public class Main {

    public static void main(String[] args) {
        Messenger.operation(MainOperations.VERIFY);
        Messenger.operation(MainOperations.PAYMENT);
        Messenger.operation(MainOperations.STATUS);
    }


}
