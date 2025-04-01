package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FriendListName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                String[] dateParts = parts[1].split("\\.");
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                LocalDate birthDate = LocalDate.of(LocalDate.now().getYear(), month, day);
                int birthDayOfYear = birthDate.getDayOfYear() - 1;
                if (birthDayOfYear == CurrentDay) {
                    matchingFriends.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            return "Ошибка чтения файла";
        }
        if(matchingFriends.isEmpty()) {
            return "Нет подходящих друзей в списке.";
        }
        return String.join("\n", matchingFriends);
    }

    public String RemindCurrentWeek(String FriendListName, int CurrentWeek) {
        List<String> matchingFriends = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalDate weekStart = LocalDate.of(today.getYear(), 1, 1)
                .with(WeekFields.ISO.weekOfWeekBasedYear(), CurrentWeek + 1)
                .with(java.time.DayOfWeek.MONDAY);
        for (int i = 0; i < 7; i++) {
            LocalDate date = weekStart.plusDays(i);
            try {
                BufferedReader reader = new BufferedReader(new FileReader(FriendListName));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" - ");
                    String[] dateParts = parts[1].split("\\.");
                    int day = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    LocalDate birthDate = LocalDate.of(today.getYear(), month, day);
                    if (birthDate.getDayOfMonth() == date.getDayOfMonth() && birthDate.getMonthValue() == date.getMonthValue()) {
                        matchingFriends.add(line);
                    }
                }
                reader.close();
            } catch (IOException e) {
                return "Ошибка чтения файла";
            }
        }
        if(matchingFriends.isEmpty()) {
            return "Нет подходящих друзей в списке.";
        }
        return String.join("\n", matchingFriends);
    }

    public String GetNextBirthday(String FriendListName) {
        List<String> closestBirthdays = new ArrayList<>();
        LocalDate today = LocalDate.now();
        long minDiff = Long.MAX_VALUE;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FriendListName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length != 2) continue;
                String[] dateParts = parts[1].split("\\.");
                if (dateParts.length != 3) continue;
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                LocalDate nextBirthday = LocalDate.of(today.getYear(), month, day);
                if (nextBirthday.isBefore(today)) {
                    nextBirthday = nextBirthday.plusYears(1);
                }
                long diff = ChronoUnit.DAYS.between(today, nextBirthday);
                if (diff < minDiff) {
                    minDiff = diff;
                    closestBirthdays.clear();
                    closestBirthdays.add(line);
                }
                else if (diff == minDiff) {
                    closestBirthdays.add(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            return "Ошибка чтения файла";
        }
        if (closestBirthdays.isEmpty()) {
            return "Нет подходящих друзей в списке.";
        }
        return String.join("\n", closestBirthdays);
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
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FriendListName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Ошибка чтения файла";
        }
        return content.toString();
    }
}
