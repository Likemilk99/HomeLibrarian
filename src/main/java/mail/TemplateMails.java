package mail;

/**
 * Created by likemilk on 16.03.2016.
 */
public enum TemplateMails {
    MAIL_REG {
        public String getMail() {
            return "\"<p>Добрый день!\" +\n" +
                    "            \" <p>Вы зарегистрировались на HomeLibrarian. \" +\n" +
                    "            \"  \" +\n" +
                    "            \"<p>Для завершения регистрации, пожалуйста, подтвердите ваш электронный адрес.\" +\n" +
                    "            \" \" +\n" +
                    "            \"<p>Код подтверждения: <b>\";";
        }
    },
    MAIL_MESS {
        public String getMail() {
            return "\"<p>Добрый день!\" +\n" +
                    "            \" <p>Вы получили письмо от портала HomeLibrarian. \" +\n" +
                    "            \"  \" +\n" +
                    "            \"<p>Для завершения регистрации, пожалуйста, подтвердите ваш электронный адрес.\" +\n" +
                    "            \" \" +\n" +
                    "            \"<p> С уважением Администрация портала.\";";
        }
    };

    public abstract String getMail();

}
