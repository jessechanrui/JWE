package com.earthport.jose;

import com.earthport.jose.Utils;

import static java.lang.System.out;


public class JWEncrypt {


    private static String keyId = "22c30679-e8e4-a9f2-9bf4-1da7612f0d";
    private static String sharedSecret = "22c30679-e8e4-a9f2-9bf4-1da7612f0d";
    private static String payload = "{\"originatorDetail\":{\"acquiringBIN\":123456,\"acquirerCountryCode\":\"012\",\"merchantId\":\"123456789012345\",\"terminalId\":\"12345678\",\"name\":\"caname\",\"address\":{\"country\":\"84\"},\"merchantCategoryCode\":\"1234\"},\"serviceProviderDetail\":{\"routingId\":\"4957030013852001\"},\"transactionDetail\":{\"retrievalReferenceNumber\":\"906456123463\",\"systemTraceAuditNumber\":\"123456\",\"amount\":12345,\"transactionCurrencyCode\":\"840\",\"transmissionDateTime\":\"2019-04-12T14:58:00\",\"localTransactionDateTime\":\"2019-04-12T14:58:00\",\"statementNarrative\":\"dono\",\"purposeOfPayment\":\"dono\",\"settlementAmount\":12345,\"settlementCurrencyCode\":\"840\",\"settlementDate\":\"0412\",\"transactionIdentifier\":\"123456780912345\",\"destinationAmount\":12345,\"destinationCurrencyCode\":\"840\",\"businessApplicationId\":\"PP\"},\"recipientDetail\":{\"type\":\"1\",\"idType\":\"I\",\"idNumber\":\"\",\"idIssueCountry\":\"IND\",\"firstName\":\"XXXX\",\"lastName\":\"XXXX\",\"dateOfBirth\":\"2100-11-01\",\"countryOfBirth\":\"USA\",\"cityOfBirth\":\"Austin\",\"companyName\":\"ASAS\",\"address\":{\"addressLine1\":\"XXXX\",\"addressLine2\":\"XXXX\",\"addressLine3\":\"XXXX\",\"city\":\"x1e21e21e2\",\"country\":\"123\",\"postalCode\":\"1234567\",\"province\":\"1231k223ej23\"},\"bank\":{\"bankName\":\"bankname\",\"accountName\":\"accountname\",\"accountNumber\":\"00000020312420312\",\"accountType\":\"22\",\"countryCode\":\"123\",\"bankCode\":\"123456789012\",\"branchCode\":\"1234\",\"currencyCode\":\"123\"}},\"senderDetail\":{\"type\":\"1\",\"firstName\":\"XXYoXX\",\"lastName\":\"XXXX\",\"dateOfBirth\":\"1993-05-05\",\"countryOfBirth\":\"000\",\"cityOfBirth\":\"\",\"companyName\":\"\",\"idType\":\"1\",\"idNumber\":\"123456789012345678901234567890\",\"address\":{\"addressLine1\":\"XXXX\",\"addressLine2\":\"XXXX\",\"addressLine3\":\"XXXX\",\"city\":\"bla\",\"country\":\"bla\",\"postalCode\":\"\",\"province\":\"\"}}}\n";

    public static void main(String...args) throws Exception{
        JWEncrypt jWEncrypt = new JWEncrypt();

        //Encrypt JSON utilizing Earthport SharedSecret & KeyId
        String encryptedPayload = Utils.encrypt(payload, keyId, sharedSecret);
        out.println(encryptedPayload);

    }

}
