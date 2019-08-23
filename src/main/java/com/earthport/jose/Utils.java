package com.earthport.jose;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.AESEncrypter;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String encrypt(String request, String keyId, String sharedSecret) throws JOSEException, NoSuchAlgorithmException {
        try {
            long iat = System.currentTimeMillis() / 1000;
            JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.A256GCMKW, EncryptionMethod.A256GCM)
                    .keyID(keyId)
                    .type(JOSEObjectType.JOSE)
                    //.iv()
                    //.authTag()
                    .customParam("channelSecurityContext", "SHARED_SECRET")
                    .customParam("iat", iat)
                    .build();

            Payload payload = new Payload(request);
            JWEObject jweObject = new JWEObject(jweHeader, payload);

            byte[] keyByte = getKeyBytes(sharedSecret);
            jweObject.encrypt(new AESEncrypter(keyByte));
            return jweObject.serialize();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String decrypt(String encryptedRequest, String sharedSecret) throws Exception {
     
            JWEObject jweObject = JWEObject.parse(encryptedRequest);
            byte[] keyByte = getKeyBytes(sharedSecret);

            jweObject.decrypt(new AESDecrypter(keyByte));
            return jweObject.getPayload().toString();
      
    }

    /**
     * Convert SharedSecret to secret key
     *
     * @param sharedSecret - The sharedSecret generated for AES based encryption
     *
     * @return - The secret key bytes
     *
     * @throws NoSuchAlgorithmException
     */
    private static byte[] getKeyBytes(String sharedSecret) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(sharedSecret.getBytes(Charset.forName("UTF-8")));
        return md.digest();
    }

}
