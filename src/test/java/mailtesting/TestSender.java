package mailtesting;

import mail.Sender;
import org.junit.Test;

/**
 * Created by likemilk on 22.02.2016.
 */
public class TestSender {

    @Test
    public void TestSender() {
        Sender sender = new Sender("smirnovivan944", "Iround!1");
        sender.send("64358", "iround2@yandex.ru");
    }
}
