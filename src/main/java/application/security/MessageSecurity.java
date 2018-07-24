package application.security;

import application.Properties;
import application.util.PemFile;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Class for signing and verifying data
 * Class - singleton
 */

public class MessageSecurity implements Properties {

    private static MessageSecurity instance;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static MessageSecurity getInstance() {
        if (instance == null)
            instance = new MessageSecurity();
        return instance;
    }

    private MessageSecurity() {
        Security.addProvider(new BouncyCastleProvider());
        KeyFactory factory;
        try {
            factory = KeyFactory.getInstance("RSA");
            this.privateKey = generatePrivateKey(factory);
            this.publicKey = generatePublicKey(factory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param message - string for signature
     * @return sinn in string forman, coded in base64
     * @throws SignatureException when signature length not correct or wrong key
     */

    public String sign(String message) throws SignatureException {
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initSign(this.privateKey);
            sign.update(message.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(sign.sign()), "UTF-8");
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }
    }

    /**
     * Verifies the signature
     *
     * @param message   string for verification
     * @param signature sign in Base64
     * @return true if signature is correct
     * @throws SignatureException when signature length not correct or wrong key
     */
    public boolean verify(String message, String signature) throws SignatureException {
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            sign.initVerify(this.publicKey);
            sign.update(message.getBytes("UTF-8"));
            return sign.verify(Base64.decodeBase64(signature.getBytes("UTF-8")));
        } catch (Exception ex) {
            throw new SignatureException(ex);
        }
    }

    /**
     * Creates a PrivateKey based on the data from the .pem file
     *
     * @param factory use factory instance to generate key
     * @return instance of PrivateKey
     * @throws InvalidKeySpecException if we have not correct key
     * @throws IOException             if there is no this file
     */
    private PrivateKey generatePrivateKey(KeyFactory factory) throws IOException, InvalidKeySpecException {

        PemFile pemFile = new PemFile(RESOURCES_DIR + PRIVATE_KEY_FILE_NAME);
        byte[] content = pemFile.getPemObject().getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privKeySpec);
    }

    /**
     * Creates a PublicKey based on the data from the .pem file
     *
     * @param factory - KeyFactory for generating key
     * @return instance of PublicKey
     */
    private PublicKey generatePublicKey(KeyFactory factory)
            throws InvalidKeySpecException, IOException {
        PemFile pemFile = new PemFile(RESOURCES_DIR + PUBLIC_KEY_FILE_NAME);
        byte[] content = pemFile.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }
}
