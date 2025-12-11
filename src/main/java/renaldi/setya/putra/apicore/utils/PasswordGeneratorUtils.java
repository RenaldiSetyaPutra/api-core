package renaldi.setya.putra.apicore.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PasswordGeneratorUtils {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijkmnopqrstuvwxyz";
    private static final String NUMBER = "123456789";
    private static final String SYMBOL = "!@#$%^&*()-_=+";
    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword() {
        // Random length 8–12
        int length = 8 + random.nextInt(5);

        char first = UPPER.charAt(random.nextInt(UPPER.length()));

        char number = NUMBER.charAt(random.nextInt(NUMBER.length()));
        char symbol = SYMBOL.charAt(random.nextInt(SYMBOL.length()));

        int remaining = length - 3; // 1 uppercase + 1 number + 1 symbol
        List<Character> chars = new ArrayList<>();

        for (int i = 0; i < remaining; i++) {
            String letters = LOWER + UPPER;
            chars.add(letters.charAt(random.nextInt(letters.length())));
        }

        chars.add(number);
        chars.add(symbol);

        Collections.shuffle(chars);

        StringBuilder password = new StringBuilder();
        password.append(first);
        for (char c : chars) {
            password.append(c);
        }

        return password.toString();
    }
}

