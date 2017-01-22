package task2;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Air on 22/01/2017.
 */
public class Questions {

    private ResourceBundle questions;
    private ResourceBundle answers;
    private Locale localeEn = new Locale("en");
    private Locale localeRu = new Locale("ru");
    private Locale locale;

    public Questions() {
        locale = Locale.getDefault();
        questions = ResourceBundle.getBundle("Questions", locale);
        answers = ResourceBundle.getBundle("Answers", locale);
    }

    private void changeLocale() {
        locale = locale.getLanguage().equalsIgnoreCase("en") ? localeRu : localeEn;
        questions = ResourceBundle.getBundle("Questions", locale);
        answers = ResourceBundle.getBundle("Answers", locale);
    }

    public void startApp() {

        try (Scanner scanner = new Scanner(System.in)) {
            String input = "";
            int i = 0;
            while (true) {
                Enumeration<String> keys = answers.getKeys();
                for (i = 1; keys.hasMoreElements(); i++) {
                    System.out.println(i + ": " + questions.getString(keys.nextElement()));
                }
                System.out.println("9: " + questions.getString("lang"));
                System.out.println("0: " + questions.getString("exit"));
                input = scanner.nextLine();
                if (input.length() == 1 && input.matches("[\\d]")) {
                    switch (input) {
                        case "0":
                            return;
                        case "9":
                            changeLocale();
                            continue;
                        default:
                            if (Integer.parseInt(input) <= i) {
                                System.out.println("--------------------------");
                                System.out.println(answers.getString("q" + input));
                                System.out.println("--------------------------");
                            } else {
                                System.out.println(questions.getString("err"));
                            }
                            break;
                    }
                } else {
                    System.out.println(questions.getString("err"));
                }
            }
        }
    }

    public static void main(String[] args) {
        Questions q = new Questions();
        q.startApp();
    }
}
