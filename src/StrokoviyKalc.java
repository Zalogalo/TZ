import java.util.Scanner;

public class StrokoviyKalc {
    public static void main(String[] args) throws Exception {
        Scanner console = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = console.nextLine();
        System.out.println("\"" + Osnova.razbiv(input) + "\"");
    }
}


class Osnova {
    static String razbiv(String input) throws Exception { // (x"home" */ 3)   // (x"home" +- "home")
        String[] firstRazbiv = input.split("\"");
        String firstString = firstRazbiv[1]; // индекс = 1 т.к. деление по кавычке, а слева от первой кавычки - ничто
        String operation;
        String secondString;
        if (!firstRazbiv[0].equals("")) {
            throw new Exception("Первым операндом должна быть строка в кавычках");
        }

        if (firstRazbiv[2].contains("+") || firstRazbiv[2].contains("-")) {
            operation = firstRazbiv[2];
            secondString = firstRazbiv[3];
        } else {
            String[] secondRazbiv = firstRazbiv[2].split(" ");
            operation = secondRazbiv[1];
            secondString = secondRazbiv[2];
        }
        if (operation.equals("/") || operation.equals("*")) {
            if (Integer.parseInt(secondString) < 1 || Integer.parseInt(secondString) > 10)
                throw new Exception("Числа должны быть от 1 до 10");
        }

        if (firstString.length() > 10 || secondString.length() > 10) {
            throw new Exception("Длина строки должна быть не больше 10 символов");
        }

        if (calculate(firstString, secondString, operation).length() > 40) {
            return (calculate(firstString, secondString, operation).substring(0, 40) + "...");
        }


        return calculate(firstString, secondString, operation);
    }

    static String calculate(String firstString, String secondString, String operation) throws Exception {
        String result;
        if (operation.equals(" + ")) {
            result = firstString + secondString;
        } else if (operation.equals("*")) {
            result = firstString.repeat(Integer.parseInt(secondString));
        } else if (operation.equals(" - ")) {
            result = firstString.replace(secondString, "");
        } else if (operation.equals("/")) {
            result = firstString.substring(0, firstString.length() / Integer.parseInt(secondString));
        } else throw new Exception("Введенная операция не соответствует условию");
        return result;
    }
}