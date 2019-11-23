package app.note.utils;

import android.graphics.Color;
import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Classe de validação.
 */
public class Util {

    public static boolean isValidName(String name) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(name).matches();
    }

    public static boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String format(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
        return sdf.format(date);
    }

    public static String formatMin(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM", Locale.US);
        return sdf.format(date);
    }

    public static int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
