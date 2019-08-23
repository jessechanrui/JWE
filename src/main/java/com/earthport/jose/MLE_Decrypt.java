package com.earthport.jose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jose.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.text.ParseException;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lp20
 */
public class MLE_Decrypt {
  
      private static String payload="{\"encData\":\"eyJjdHkiOiJhcHBsaWNhdGlvblwvanNvbjtjaGFyc2V0PVVURi04IiwiZW5jIjoiQTEyOEdDTSIsImlhdCI6MTU2NTYwMTExMjAwNSwiYWxnIjoiUlNBLU9BRVAtMjU2In0.DHB1LWgu6ApxojV13x3LfPb-POf4qBp3jICW-YKZ_dN1b3uq0DVVroGMQcDUoH5Od9iToFhTb1GM2g60p5wP0Om8o4hnbQoI4ZiOSijPWgrczbDeao-rdM3qs0ZTubBC6UQANUYWSRBpmoIKY1cDdA5_ij2Q1Ky3gFQL066WprH4u0v8XmTHytQneup2uq8ceV_Gw21bmT_R3rvPdQbyDYokTHaAFYeYMGkM5OSbcFjb-N_L1xoziSEif5vGNFxIDwqqukB8vooEnMaL_YIwbPedad8wGQqbeOmsrNAq5BE29etuOjuibxdUTdtuBSMWKE-u0OtZGy54dBAilkpcEQ.hQt7gXBLd4uIv8DX.OEASSbdhtdeJ3hIO1Q-DxzFJQk_8XdUopuQSuXIb7Ot5T1zCfIYPUGvemfp8a3tE6Lh5BsbwObglLr7GLQ3Bl7Hrl_FxdqhxTgLYM4pKsjHPPmb7mZOw1UZSdH40qbUttakoROQhv3gHaB2KA2Yd8pEzJRH-I62iHeVBwnhgIE1FzHi9IF3JYQOSjp5pgF_CJFkd2rKh3qo39pwyIkzC6mW_QwJDK5I1XDXK8us-zQIDjDWqPSmf8Ntyu55NYRc2sWaxjaAwKqxKm_zXl96XbE0_QSAm3FRQOuv4GSdjJLcZAK2oo_s9GN8ncl6qjKKQAbZoQjKTjemHEfR6BEbmsX8telhXG-NY54mj7Q1L6_kRQrjt1-JLEW9i5lGEKm6C9tVON_yi.t8bicqUC08xJ4XGYM2ia0A\"}";
      
       private static String enc_payload="eyJjdHkiOiJhcHBsaWNhdGlvblwvanNvbjtjaGFyc2V0PVVURi04IiwiZW5jIjoiQTEyOEdDTSIsImlhdCI6MTU2NTYwMTExMjAwNSwiYWxnIjoiUlNBLU9BRVAtMjU2In0.DHB1LWgu6ApxojV13x3LfPb-POf4qBp3jICW-YKZ_dN1b3uq0DVVroGMQcDUoH5Od9iToFhTb1GM2g60p5wP0Om8o4hnbQoI4ZiOSijPWgrczbDeao-rdM3qs0ZTubBC6UQANUYWSRBpmoIKY1cDdA5_ij2Q1Ky3gFQL066WprH4u0v8XmTHytQneup2uq8ceV_Gw21bmT_R3rvPdQbyDYokTHaAFYeYMGkM5OSbcFjb-N_L1xoziSEif5vGNFxIDwqqukB8vooEnMaL_YIwbPedad8wGQqbeOmsrNAq5BE29etuOjuibxdUTdtuBSMWKE-u0OtZGy54dBAilkpcEQ.hQt7gXBLd4uIv8DX.OEASSbdhtdeJ3hIO1Q-DxzFJQk_8XdUopuQSuXIb7Ot5T1zCfIYPUGvemfp8a3tE6Lh5BsbwObglLr7GLQ3Bl7Hrl_FxdqhxTgLYM4pKsjHPPmb7mZOw1UZSdH40qbUttakoROQhv3gHaB2KA2Yd8pEzJRH-I62iHeVBwnhgIE1FzHi9IF3JYQOSjp5pgF_CJFkd2rKh3qo39pwyIkzC6mW_QwJDK5I1XDXK8us-zQIDjDWqPSmf8Ntyu55NYRc2sWaxjaAwKqxKm_zXl96XbE0_QSAm3FRQOuv4GSdjJLcZAK2oo_s9GN8ncl6qjKKQAbZoQjKTjemHEfR6BEbmsX8telhXG-NY54mj7Q1L6_kRQrjt1-JLEW9i5lGEKm6C9tVON_yi.t8bicqUC08xJ4XGYM2ia0A";
   private static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
        private static final String END_CERT = "-----END CERTIFICATE-----";
        private static final String BEGIN_RSA_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----";
        private static final String END_RSA_PRIVATE_KEY = "-----END RSA PRIVATE KEY-----";
        private static final String ENC_DATA = "encData";
      public static void main(String[] args) throws JOSEException, IOException, CertificateException, ParseException, NoSuchAlgorithmException, InvalidKeySpecException {
       
       
    // headerBuilder.keyID("0d5ab396-ec26-45e6-9929-f1cd94d5d349");

    
   JWEObject jweObject = JWEObject.parse(enc_payload);
                jweObject.decrypt(new RSADecrypter(getRSAPrivateKey()));
            String    response = jweObject.getPayload().toString();
        System.out.println("Encrypted payload "+response);
      }
      
      
        private static PrivateKey getRSAPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
            String pemEncodedKey = IOUtils.readFileToString(new File("D:\\visa_cert\\client_cert.pem"), Charset.forName("UTF-8"));
            Base64 base64 = new Base64(pemEncodedKey.replaceAll(BEGIN_CERT, "").replaceAll(END_RSA_PRIVATE_KEY, ""));
            ASN1Sequence primitive = (ASN1Sequence) ASN1Sequence.fromByteArray(base64.decode());
            Enumeration<?> e = primitive.getObjects();
            BigInteger v = ((ASN1Integer) e.nextElement()).getValue();
            int version = v.intValue();
            if (version != 0 && version != 1) {
                throw new IllegalArgumentException("wrong version for RSA private key");
            }
            BigInteger modulus = ((ASN1Integer) e.nextElement()).getValue();
            ((ASN1Integer) e.nextElement()).getValue();
            BigInteger privateExponent = ((ASN1Integer) e.nextElement()).getValue();
            ((ASN1Integer) e.nextElement()).getValue();
            ((ASN1Integer) e.nextElement()).getValue();
            ((ASN1Integer) e.nextElement()).getValue();
            ((ASN1Integer) e.nextElement()).getValue();
            ((ASN1Integer) e.nextElement()).getValue();
            RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, privateExponent);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (PrivateKey) keyFactory.generatePrivate(privateKeySpec);
        }
        
          public static <T> T getDecryptedPayload(Class<T> returnType)  {
            String response = payload;
            T decryptedResponse = null;
            try {
                JWEObject jweObject = JWEObject.parse(response);
                jweObject.decrypt(new RSADecrypter(getRSAPrivateKey()));
                response = jweObject.getPayload().toString();
                ObjectMapper mapper = new ObjectMapper();
                decryptedResponse = mapper.readValue(response, returnType);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return decryptedResponse;
        }
}

