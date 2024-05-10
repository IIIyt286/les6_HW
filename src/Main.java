import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные: Фамилия Имя Отчество ДатаРождения (в формате dd.MM.yyyy) НомерТелефона Пол (разделенные пробелом):");
            String input = scanner.nextLine().trim();
            scanner.close();

            String[] data = input.split(" ");

            if (data.length < 6) {
                throw new IllegalArgumentException("Вы ввели меньше данных, чем требуется.");
            } else if (data.length > 6) {
                throw new IllegalArgumentException("Вы ввели больше данных, чем требуется.");
            }

            String surname = data[0];
            String firstName = data[1];
            String patronymic = data[2];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate;
            try {
                birthDate = dateFormat.parse(data[3]);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Неверный формат даты рождения.");
            }

            long phoneNumber;
            try {
                phoneNumber = Long.parseLong(data[4]);
                if (phoneNumber < 0) {
                    throw new IllegalArgumentException("Номер телефона не может быть отрицательным.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Неверный формат номера телефона.");
            }

            char gender;
            if (data[5].length() != 1 || (data[5].charAt(0) != 'm' && data[5].charAt(0) != 'f')) {
                throw new IllegalArgumentException("Неверный формат пола.");
            }
            gender = data[5].charAt(0);

            String fileName = surname + ".txt";
            FileWriter writer = new FileWriter(fileName, true); // Открываем файл для добавления данных в конец файла
            writer.write(surname + " " + firstName + " " + patronymic + " " + dateFormat.format(birthDate) + " " + phoneNumber + " " + gender + "\n");
            writer.close();

            System.out.println("Данные введены корректно.");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
