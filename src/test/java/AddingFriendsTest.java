import org.example.AddingFriends;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    @Test
    public void AddFriendTest() {
        AddingFriends addingFriends = new AddingFriends();
        String FriendListName = "FriendList.txt";
        addingFriends.CreateFriendList(FriendListName);
        String FriendName = "Иванов И.И.";
        String FriendBirthday = "01.01.2001";
        addingFriends.AddFriend(FriendListName, FriendName, FriendBirthday);
        File file = new File(FriendListName);
        Assertions.assertTrue(file.exists(), "Файл должен быть успешно создан.");
        try {
            Scanner scanner = new Scanner(file);
            String expectedContent = FriendName + " - " + FriendBirthday;
            String actualContent = scanner.nextLine();
            Assertions.assertEquals(expectedContent, actualContent,
                    "Содержимое файла должно соответствовать ожидаемому: Иванов И.И. - 01.01.2001");
            scanner.close();
        } catch (FileNotFoundException e) {
            Assertions.fail("Файл не найден");
        }
    }

    @Test
    public void RemindCurrentMonthTest() {
        AddingFriends addingFriends = new AddingFriends();
        String FriendListName = "FriendList.txt";
        addingFriends.CreateFriendList(FriendListName);
        String FriendName = "Иванов И.И.";
        String FriendBirthday = "01.01.2001";
        addingFriends.AddFriend(FriendListName, FriendName, FriendBirthday);
        int CurrentMonth = 0;
        Assertions.assertEquals("Иванов И.И. - 01.01.2001",
                addingFriends.RemindCurrentMonth(FriendListName, CurrentMonth),
                "Имя и дата рождения должны быть Иванов И.И. - 01.01.2001");
    }

    @Test
    public void RemindCurrentDayTest() {
        AddingFriends addingFriends = new AddingFriends();
        String FriendListName = "FriendList.txt";
        addingFriends.CreateFriendList(FriendListName);
        String FriendName = "Иванов И.И.";
        String FriendBirthday = "01.01.2001";
        addingFriends.AddFriend(FriendListName, FriendName, FriendBirthday);
        int CurrentDay = 0;
        Assertions.assertEquals("Иванов И.И. - 01.01.2001",
                addingFriends.RemindCurrentDay(FriendListName, CurrentDay),
                "Имя и дата рождения должны быть Иванов И.И. - 01.01.2001");
    }

    @Test
    public void RemindCurrentWeekTest() {
        AddingFriends addingFriends = new AddingFriends();
        String FriendListName = "FriendList.txt";
        addingFriends.CreateFriendList(FriendListName);
        String FriendName = "Иванов И.И.";
        String FriendBirthday = "01.01.2001";
        addingFriends.AddFriend(FriendListName, FriendName, FriendBirthday);
        int CurrentWeek = 0;
        Assertions.assertEquals("Иванов И.И. - 01.01.2001",
                addingFriends.RemindCurrentWeek(FriendListName, CurrentWeek),
                "Имя и дата рождения должны быть Иванов И.И. - 01.01.2001");
    }

    @Test
    public void GetNextBirthdayTest() {
        AddingFriends addingFriends = new AddingFriends();
        String FriendListName = "FriendList.txt";
        addingFriends.CreateFriendList(FriendListName);
        String FriendName = "Иванов И.И.";
        String FriendBirthday = "01.01.2001";
        addingFriends.AddFriend(FriendListName, FriendName, FriendBirthday);
        Assertions.assertEquals("Иванов И.И. - 01.01.2001",
                addingFriends.GetNextBirthday(FriendListName),
                "Имя и дата рождения должны быть Иванов И.И. - 01.01.2001");
    }

    @Test
    public void DeleteFriendTest() {
        AddingFriends addingFriends = new AddingFriends();
        String FriendListName = "FriendList.txt";
        addingFriends.CreateFriendList(FriendListName);
        String FriendName = "Иванов И.И.";
        String FriendBirthday = "01.01.2001";
        addingFriends.AddFriend(FriendListName, FriendName, FriendBirthday);
        FriendName = "Иванов И.И.";
        FriendBirthday = "01.01.2002";
        addingFriends.AddFriend(FriendListName, FriendName, FriendBirthday);
        File file = new File(FriendListName);
        Assertions.assertTrue(file.exists(), "Файл должен быть успешно создан.");
        String TargetLine = "Иванов И.И. - 01.01.2002";
        addingFriends.DeleteFriend(FriendListName, TargetLine);
        try {
            Scanner scanner = new Scanner(file);
            boolean Found = false;
            while (scanner.hasNextLine()) {
                String Line = scanner.nextLine();
                if (Line.equals(TargetLine)) {
                    Found = true;
                }
            }
            scanner.close();
            Assertions.assertFalse(Found, "Запись \"Иванов И.И. - 01.01.2002\" должна быть удалена");
        } catch (FileNotFoundException e) {
            Assertions.fail("Файл не найден");
        }
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
