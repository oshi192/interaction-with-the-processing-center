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
    Logger LOGGER =Logger.getLogger(Main.class);

}
