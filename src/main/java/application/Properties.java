package application;

import org.apache.log4j.Logger;

public interface Properties {
    String URL="https://test.lgaming.net/external/extended";
    String RESOURCES_DIR = "src/main/resources/";
    String HEADER_NAME="paylogic-signature";
    String PUBLIC_KEY_FILE_NAME="public.pem";
    String PRIVATE_KEY_FILE_NAME="private.pem";
    int POINT=327;
    int SERVICE=4390;
    int SUM_MIN=1;
    int SUM_MAX=1599900;
    int ACCOUNT_LENGTH=50;
    int ATTRIBUTE_MAX_NUMBER=5;
    int MAX_NUMBER_OF_ELEMENTS=5;

}
