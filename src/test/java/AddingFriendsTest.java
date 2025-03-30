import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddingFriendsTest {
    @Test
    public void AddingFriendsClassCreationTest() {
        AddingFriends addingFriends = new AddingFriends();
        Assertions.assertNotNull(addingFriends);
    }
}
