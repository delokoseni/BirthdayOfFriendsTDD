package org.example;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddingFriends addingFriends = new AddingFriends();
        String CurrentFriendList = "FriendList1.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Программа для напоминания о днях рождения друзей:");
        while (true) {
            System.out.println();
            System.out.print("""
                    1) Создать список друзей
                    2) Добавить друга в существующий список
                    3) Дни рождения в этом месяце
                    4) Дни рождения на неделе
                    5) У кого сегодня день рождения
                    6) Следующий день рождения
                    7) Удалить друга из существующего списка
                    8) Изменить текущий список друзей
                    9) Отобразить список друзей
                    Введите номер действия или 0 для выхода:\s""");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("0")) {
                break;
            }

            switch (input) {
                case "1":
                    System.out.print("Введите имя файла для списка друзей: ");
                    CurrentFriendList = scanner.nextLine();
                    addingFriends.CreateFriendList(CurrentFriendList);
                    System.out.println("Список друзей создан.");
                    break;
                case "2":
                    System.out.print("Введите имя друга: ");
                    String friendName = scanner.nextLine();
                    System.out.print("Введите дату рождения друга (дд.мм.гггг): ");
                    String friendBirthday = scanner.nextLine();
                    addingFriends.AddFriend(CurrentFriendList, friendName, friendBirthday);
                    System.out.println("Друг добавлен в список.");
                    break;
                case "3":
                    System.out.print("Введите месяц (0-11): ");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.println("Результат:\n" +
                            addingFriends.RemindCurrentMonth(CurrentFriendList, month));
                    break;
                case "4":
                    System.out.print("Введите неделю (1 - следующая, 2 - текущая): ");
                    int week = Integer.parseInt(scanner.nextLine());
                    Calendar calendar = Calendar.getInstance();
                    if(week == 1) {
                        week = calendar.get(Calendar.WEEK_OF_YEAR) + 1;
                    }
                    else {
                        week = calendar.get(Calendar.WEEK_OF_YEAR);
                    }
                    System.out.println("Результат:\n" +
                            addingFriends.RemindCurrentMonth(CurrentFriendList, week));
                    break;
                case "5":
                    System.out.println("День рождения сегодня:" +
                            addingFriends.RemindCurrentDay(CurrentFriendList,
                                    LocalDate.now().getDayOfYear()));
                    break;
                case "6":
                    System.out.println("Следующий день рождения:" +
                            addingFriends.GetNextBirthday(CurrentFriendList));
                    break;
                case "7":
                    System.out.print("Введите имя друга для удаления: ");
                    friendName = scanner.nextLine();
                    String friendBirthdayForDelete = friendName + " - " + "дд.мм.гггг";
                    addingFriends.DeleteFriend(CurrentFriendList, friendBirthdayForDelete);
                    System.out.println("Друг удален из списка.");
                    break;
                case "8":
                    System.out.print("Введите название списка друзей: ");
                    CurrentFriendList = scanner.nextLine();
                    break;
                case "9":
                    System.out.print("Список ваших друзей:\n" + addingFriends.GetFriendList(CurrentFriendList));
                    break;
                default:
                    System.out.println("Недопустимый выбор. Пожалуйста, выберите действие из списка.");
            }
        }
        scanner.close();
    }
}