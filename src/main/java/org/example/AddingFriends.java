package org.example;

import java.io.File;
import java.io.IOException;

public class AddingFriends {
    public AddingFriends() { }

    public void CreateFriendList(String FriendListName) {
        try {
            File file = new File(FriendListName);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла: " + e.getMessage());
        }
    }
}
