package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

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
        //todo реализовать метод RemindCurrentMonth позднее до конца
        return "Иванов И.И. - 01.01.2001";
    }

    public String RemindCurrentDay(String FriendListName, int CurrentDay) {
        //todo реализовать метод RemindCurrentDay позднее до конца
        return "Иванов И.И. - 01.01.2001";
    }

    public String RemindCurrentWeek(String FriendListName, int CurrentWeek) {
        //todo реализовать метод RemindCurrentWeek позднее до конца
        return "Иванов И.И. - 01.01.2001";
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
