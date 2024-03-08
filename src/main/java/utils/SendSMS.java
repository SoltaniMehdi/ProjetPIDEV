package utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SendSMS {
    public static final String ACCOUNT_SID = "AC90cc6a07f41214f51978a1fd0bc2a61c";
    public static final String AUTH_TOKEN = "5cb317ced6ff957034040aa5dd7d9c96";

    public static void SendSMS(String msg, String number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(number),//To phone number
                new PhoneNumber("+18333224894"),msg).create();

        System.out.println(message.getSid());
    }
}
