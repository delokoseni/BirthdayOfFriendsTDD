package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    public String RemindCurrentDay(String FriendListName, int CurrentMonth) {
        //todo реализовать метод RemindCurrentDay позднее до конца
        return "Иванов И.И. - 01.01.2001";
    }
}
