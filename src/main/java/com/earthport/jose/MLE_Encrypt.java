/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.earthport.jose;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import sun.net.www.http.HttpClient;


/**
 *
 * @author lp20
 */
public class MLE_Encrypt {
    
    private static String payload="{ \"serviceProviderDetail\": { \"routingId\": 4957030013852001, \"financialInstitutionId\": \"232323\" }, \"originatorDetail\": { \"acquiringBIN\": 495702 }, \"transactionDetail\": { \"systemTraceAuditNumber\": 114679, \"transmissionDateTime\": \"2015-02-03T18:35:13.485+00:00\", \"returnReasonCode\": \"RE203\", \"transactionAmount\": 5832, \"returningPaymentNarrative\": \"156450378198879 111 Account Address invalid . BE04\", \"transactionCurrencyCode\": \"978\", \"transactionIdentifier\": 156450378198879, \"retrievalReferenceNumber\": 925410114679 } }";
    
    public static void main(String[] args) throws JOSEException, IOException, CertificateException {
       
       
        
        JWEHeader.Builder headerBuilder = new JWEHeader.Builder(

        JWEAlgorithm.RSA_OAEP_256,

        EncryptionMethod.A128GCM);

     //Get the keyId from developer portal and substitute in the place holder <keyId from the developer portal> below:

     headerBuilder.keyID("0d5ab396-ec26-45e6-9929-f1cd94d5d349");

    //Add token issued at timestamp (iat)

    headerBuilder.customParam("iat", System.currentTimeMillis());

    //Substitute the actual payload (in clear text form) in the place holder <payload>

    JWEObject jweObject = new JWEObject(headerBuilder.build(), new Payload(payload));

    //Substitute the path where the server encryption certificate presents in the place holder <path where server side encryption certificate is present>

    String pemEncodedPublicKey = IOUtils.readFileToString(new File("C:\\mykeys\\server.pem"), Charset.forName("UTF-8"));
    
     CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
    Certificate cf = (X509Certificate) certificateFactory.generateCertificate(

              new ByteArrayInputStream(pemEncodedPublicKey.getBytes()));

    jweObject.encrypt(new RSAEncrypter((RSAPublicKey) cf.getPublicKey()));

    String encryptedPayload = "{\"encData\":\""+jweObject.serialize()+"\"}";
    
        System.out.println("Encrypted payload "+encryptedPayload);
    }
    
}
