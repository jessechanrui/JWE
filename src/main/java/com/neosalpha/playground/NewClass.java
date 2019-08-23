/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neosalpha.playground;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.text.ParseException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.openssl.*;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

/**
 *
 * @author lp26
 */
public class NewClass {
   public static void main(String[] args) throws JOSEException, IOException, ParseException {
    String enc_payload="eyJlbmMiOiJBMTI4R0NNIiwiaWF0IjoxNTY1NzkzMTQwNjg3LCJhbGciOiJSU0EtT0FFUC0yNTYiLCJraWQiOiIwZDVhYjM5Ni1lYzI2LTQ1ZTYtOTkyOS1mMWNkOTRkNWQzNDkifQ.CQNSq8vxcmd1ICkfr8D-dzdUkTfRa6ECYFCx2rMBDlE6l16ubiDVdWzr41DByDxL6nr_XK6-P-ia7ZTg0o07spXwz9togLiXUaCgBZAJaeXGlNJeIpmt-llyqPflbPw24D6wznISSOEb7dqZgqSKupmsm7gEHJfIBnie8mMhOMpo_XarO83LGY0dpVBS8TVJfHdoTxZpCipumeYJK8XYyLta83YCjGvZiNtKa_zGdWdUVI0kWVW0B5epTDWfj_aePymHjS3yCMoDwy8iggb_x9GufGCYc8Gcx7aRe12eii0Mo12C2t368wCxKHqe5IdzLR4xd9M03lS0mYOOVeClOg.YXWcdUc7-kjLVZEt.khtTvKpSo8pE2pFMSKo5Hxh0dekCdGIfLeGmf0buOptg_7wEXmdv5rifqORxBWamQnkAmY0O7k3sPmlcZXeD_4BXiz1TcJeBZ_-qlNpXl_s-hvVJOTIMwQNhdxKHcyMhm22XHXttpoeI7l8tjWvjzq0e-hNJGEgtoGJEUghGAbYoPS4FNfcYSSgamZLi6OQbpQHorNeAeCBks5XtXTZYoC-cWKbiqTFxbxzZFIjdYHShsI3E0_Yf1Tp83GZ9EpoucEpldOO7HVsVEP2N4Cz1sqieBrCwKglOic6fJ-WwVJu08BnTqzaCEBsd3xi2H6GhM8Qwl6lSUcq3afYsxcomXUTXtkFaFDlCEJYnphqVgnAJWoKtNwWDyZHjZZM1OKnqaM8uQQdTRsZYtrAQKDU-1BNMjejlHQUb_YwoN38CzVQrFC5_hOGlU_KzsnGIwD_o2btLhKolml7SJCt9s60sTjV_KKFnwThyvZZ_qhO4817Kz4-5XV5-nAbTGk5LpPEmF04TDPyHHV3QW70ftESDR4veZAR6kWUAjx_vHj_u0bmqDaJRNf5fDqCmcJewdSwFWh1BXjw6VPjobd7hyboz2puV6eVgD4nlSnRCl3fUxfmL3kE6ToBWL5ptYucsTfZGf_y0m7P5o91_ZhszvOlTW_uXreBPdAxCIM2Lu89-1U1A6PqoODkV.vm9X54igjy97cpqfB5vOqA";
    String keyPath="C:\\mykeys\\servern.key";
    Security.addProvider(new BouncyCastleProvider());
    JWEObject jweObject = JWEObject.parse(enc_payload);
    PrivateKey key=getPemKey(keyPath);
    jweObject.decrypt(new RSADecrypter(key));
    String    response = jweObject.getPayload().toString();
    System.out.println("Decrypted payload: "+response);
}


private static PrivateKey getPemKey(String keyPath) throws PEMException, FileNotFoundException,IOException {
    PEMParser pemParser = new PEMParser(new FileReader(keyPath));
    JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
    Object object = pemParser.readObject();
    KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
    PrivateKey privateKey = kp.getPrivate();
    return privateKey;
}
}
