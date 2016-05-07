package mail;

/**
 * Created by likemilk on 16.03.2016.
 */
public enum TemplateMails {
    MAIL_REG {
        public String getMail() {
            return "<p>Добрый день!" +
                    "            <p>Вы зарегистрировались на HomeLibrarian." +
                    "            <p>Для завершения регистрации, пожалуйста, подтвердите ваш электронный адрес." +
                    "            <p>Код подтверждения: <b>";
        }
    },
    MAIL_MESS {
        public String getMail() {
            return "<p>Добрый день!" +
                    "            <p>Вы получили письмо от портала HomeLibrarian. " +
                    "            <p>Для завершения регистрации, пожалуйста, подтвердите ваш электронный адрес." +
                    "            <p> С уважением Администрация портала.;";
        }
    },

    MAIL_FORGOT {
        public String getMail() {
            return "<p>Добрый день!" +
                    "             <p>Вы получили письмо от портала HomeLibrarian."+
                    "            <p>Для восстановления пароля введите код." +
                    "            <p>Код подтверждения: <b>";
        }
    };

    public abstract String getMail();

}
