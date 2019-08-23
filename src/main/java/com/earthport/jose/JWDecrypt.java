//package com.earthport.jose;
//
//import com.apigee.flow.execution.ExecutionContext;
//import com.apigee.flow.execution.ExecutionResult;
//import com.earthport.jose.Utils;
//import com.apigee.flow.execution.spi.Execution;
//import com.apigee.flow.message.MessageContext;
//
//import static java.lang.System.out;
//import java.util.Map;
//
//public class JWDecrypt {
//
//
//    private static String sharedSecret = "22c30679-e8e4-a9f2-9bf4-1da7612f0d01";
//    private static String encryptedPayload = "eyJraWQiOiIyMmMzMDY3OS1lOGU0LWE5ZjItOWJmNC0xZGE3NjEyZjBkIiwidHlwIjoiSk9TRSIsImNoYW5uZWxTZWN1cml0eUNvbnRleHQiOiJTSEFSRURfU0VDUkVUIiwiZW5jIjoiQTI1NkdDTSIsInRhZyI6IldGdDdheUpULWhSbWFLa3ozVlhwbWciLCJpYXQiOjE1NjUwMTEyNDcsImFsZyI6IkEyNTZHQ01LVyIsIml2IjoiMUdyNFgwNDBpNU1HS0lKdCJ9.9QRIFG7aRqEMes4dMhIQ0qhkiGUhCLyBhVN1M0v1gZA.FbFRiFRVYczO-Uj5.DKmlo04CBQka8xSKHnHMs3kyJBUmKF1ZPlwYh7SYOGevrp5KfszfOA87JXagfz6k3vP8kcqI9nTM_dP6CaQzDoHqeA4CXzxthQyjzKS9oHgBueJMU306h_qBPc3IPbXl6Hp5A-5R7IBEFwqcehg09y-lD1cOzF-SRrVaedUdclj1DW7Wvua1kXXasaw6VE-4KzuRWWjb6PWYxnPoon2z-15K-ju3mcHjhMnlLVmZEu6QivBSe6oT8Lji9gnfSItz11kqTnkQ-IUfz5N23axkEXwnhewhHzsvfc5EFfuQcOyKF9ZN6K0KaBuQO1x3q3wC-4MusGeXmn7_Nd14g0w9dRSr5fakzbcwrsgr9hKpXhvMjiuzS4N_GppnwEeTU1bU815Hxnx6lroFBM7R-xb6UW2DiCRnxSheinlo_3nYcD3SA3zjGS96Rgs43Ow0Kr9cLjIw9wepQnyU3qnJhtxLHX5wbaHWeoq-OWttMRfBxwD7TPNb4fHSL-Ot2Pf1j22EuCYXjTz6S9S-pHcJKXLGfIk0L4OV3l7JGTrbcSzflDHkzXDO_72rxFoZ0xOjYpeV8sVsQLtGwh9OEBKz2kk5ekvZgS4En1s5zTUMLgIruqO7npMpUCESQqeCBRAbCXb65yJ_rAqEUdxmfedB-TW8rI0O6tM78c4S8VWO9Hiv86waOJT51dYcEHC-pqOlAs59PdAHM5fiRjoFOwExDeswExBM_mAaMD_NeJbzyUUO8sXJajiGK9JD1F3d552SaX7OlX9was8bDh8X_6p2ToXZUadDQ2sioY3X_Qei6Skr9gUqY5A3gUmrRU1wKx7tMmzXtiZMrQ_ZET_YSv15426_6d_YAWZ4YnjIeXOj36vpHaWsNR8b7aWIZfkklnTkpfy02QFaE90vkMlUSUI-XlvmR_dk39yozbIu2XVwBj6e_DkzU_l9j7AC3iJDOuvDAHP5YwATEmzx57CsyAfBNNHezUI6_7SaJ79cuKpp1DbvuKRJwrmH3RvKCoIeuF0DLVa3JVki5hZRW2iS8toHU82hQIcnJ5Ou8zuMhrYDC3pRqGfPiHfjS4zejuF7ra3L5xfm3ro8EkYT_C3mdJ-xLIHfHZK3pZX0pWUENR-oEY_f_kt9zs8nRZjLibzZtcyl6zJwmqHByF6Fvi4EgVKq03BVE0b82d0wVljQUIkLgt6X2wTAd3hpwH811EyRMTlLX9cmzVSX0CXvLtQOanKeyoyzPuLaGh0v7OsEKkIMH4UIXeuKOxwxkSGl5SfBwEGJ4_-RNoXeoMpjXzCDdaNEI_ZCWSoUZmXIrjz-jCf2h6MFkhri46eH-l4s30w_oOc-YIhPXO2358DHZDS8Ryp9L6tkcLAILWbEubVJ73uO9KcbG1dnVlXqKy06cyOJF5EjckorJ1Lzw57ldP8G_cIRtN-jaOG2zbLU6WlK0xHcHgdOAm7NrVUZuLUFDiMfV9aMOV4O4MCn0CYOYIgOA9QFgLijBbln19LzLmu45pgcAsNka_iewE05X7SDiL0e5-kH4QoWHqo4aG7F-to52YXjG1qpyg82jBKCweekUuvK1YA17fG412o2pGIz6Ar2gWi5wra58AotH-KwOGYhlQKEPeo6fliwVy7fnzTQ_eAmNpH6gulg8nc5obsqeKNjKL9RMDieMgZo5uYRMd-10U5P2QY4Y_khwU0xsNT6LJRzeWGEpch6Mb8CZugCJv60zevS4FcGt1aZtU3HhJQL3pHznBdNO8Co4WvAJROwHoZnomp1bl0AwG3gsUhjmXjH4n5bvqZhnnJxV4mi28Mkrh3z7ZDQwVnHuRqaCcyxqHcWyG9Euc0Osz6tFyEPXlU96m0_SrxaCdZPod5XjcQkud7mPlJ4CfYoThEsHr9O9CntzKBTtP_fIoOFyLPyO2TGIxkjiU8RIMGqtQv2IzBp7p-knKHdn_yNP7ZprgU0y7OS3nQSVK9_CYYipgzhuj2vcmWCVc_NgMGDhTY30KkGXiHxa0S42rMj6xqOJEK_9sDVgmwUVoiyahdHfwHt46Z6WdzJ6AW2rBdgY46pGcj5bGWmRuKk7fX2ReWuCphXQemd3S8-_mejUhWdYMxI0TB_QGZBtXDRiiIWiEk_wguxNKyjg0fuPYEFcf0T0V8ZO7GmmjBSorxmLPdi9zWziGLryoi8SLFD91gRCplScEnaVOyehhlee9zY-qWVYrBDDD6RUiR42qzv2Aqw6nH5rGRJeGWCGjtJMQ.tb4gZW57U5x5RGglEDOswA";
//
//    public static void main(String...args) throws Exception{
//
//        //Decrypt JWE utilizing Earthport SharedSecret
//        try {
//           sharedSecret="{new.key_id}";
//           sharedSecret = sharedSecret.replaceAll("\\{", "").replaceAll("\\}","");
//           out.println(sharedSecret);
//           String decryptedPayload = Utils.decrypt(encryptedPayload, sharedSecret);
//           out.println(decryptedPayload);  
//        } catch (Exception e) {
//            out.println(e.getMessage());
//        }
//       
//    }
//
//}
