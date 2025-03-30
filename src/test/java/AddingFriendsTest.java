import org.example.AddingFriends;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class AddingFriendsTest {
    @Test
    public void AddingFriendsClassCreationTest() {
        AddingFriends addingFriends = new AddingFriends();
        Assertions.assertNotNull(addingFriends, "Экземпляр класса должен быть успешно создан.");
    }

    @Test
    public void CreateFriendListTest() {
        AddingFriends addingFriends = new AddingFriends();
        String FriendListName = "FriendList.txt";
        addingFriends.CreateFriendList(FriendListName);
        File file = new File(FriendListName);
        Assertions.assertTrue(file.exists(), "Файл должен быть успешно создан.");
    }

    @AfterEach
    public void Clean() {
        String FriendListName = "FriendList.txt";
        File file = new File(FriendListName);
        if(file.exists()) {
            file.delete();
        }
    }
}
