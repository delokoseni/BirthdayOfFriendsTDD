package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddingFriends {
    public AddingFriends() { }

    /**
     * Метод, создающий файл для списка друзей
     * @param FriendListName - название создаваемого файла
     */
    public void CreateFriendList(String FriendListName) {
        try {
            File file = new File(FriendListName);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }

    public void AddFriend(String FriendListName, String FriendName, String FriendBirthday) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(FriendListName, true));
            writer.println(FriendName + " - " + FriendBirthday);
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при добавлении друга в файл: " + e.getMessage());
        }
    }

    public String RemindCurrentMonth(String FriendListName, int CurrentMonth) {
        List<String> matchingFriends = new ArrayList<>();
        int targetMonth = CurrentMonth + 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FriendListName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                String[] dateParts = parts[1].split("\\.");

                int month = Integer.parseInt(dateParts[1]);
                if (month == targetMonth) {
                    matchingFriends.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            return "Ошибка чтения файла";
        }
        if(matchingFriends.isEmpty()){
            return "Нет подходящих друзей в списке.";
        }
        return String.join("\n", matchingFriends);
    }

    public String RemindCurrentDay(String FriendListName, int CurrentDay) {
        List<String> matchingFriends = new ArrayList<>();
        int targetDay = CurrentDay + 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FriendListName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                String[] dateParts = parts[1].split("\\.");

                int month = Integer.parseInt(dateParts[0]);
                if (month == targetDay) {
                    matchingFriends.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            return "Ошибка чтения файла";
        }
        if(matchingFriends.isEmpty()){
            return "Нет подходящих друзей в списке.";
        }
        return String.join("\n", matchingFriends);
    }

    public String RemindCurrentWeek(String FriendListName, int CurrentWeek) {
        //todo реализовать метод RemindCurrentWeek позднее до конца
        File file = new File(FriendListName);
        if (file.exists() && file.length() > 0) {
            return "Иванов И.И. - 01.01.2001";
        }
        return "Нет подходящих друзей в списке.";
    }

    public String GetNextBirthday(String FriendListName) {
        //todo реализовать метод GetNextBirthdayTest позднее до конца
        return "Иванов И.И. - 01.01.2001";
    }

    public void DeleteFriend(String FriendListName, String Friend) {
        File file = new File(FriendListName);
        File tempFile = new File(FriendListName + ".tmp");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.equals(Friend)) {
                    writer.println(line);
                } else {
                    break;
                }
            }
            while ((line = reader.readLine()) != null) {
                writer.println(line);
            }
            reader.close();
            writer.close();
        }
        catch (IOException eInside) {
            System.out.println("Ошибка при чтении или записи файла: " + eInside.getMessage());
        }
        if (!file.delete()) {
            System.out.println("Не удалось удалить исходный файл.");
        }
        if (!tempFile.renameTo(file)) {
            System.out.println("Не удалось переименовать временный файл.");
        }
    }

    public String GetFriendList(String FriendListName) {
        //todo реализовать метод GetFriendList позднее до конца
        return "Иванов И.И. - 01.01.2001\n" + "Иванов И.И. - 01.01.2002\n";
    }
}
