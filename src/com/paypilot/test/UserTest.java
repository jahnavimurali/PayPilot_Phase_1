package com.paypilot.test;

import com.paypilot.model.User;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testManualUserData() {
        User user1 = new User(1, "Alice", "alice@example.com", "9999990001", "pass1");
        User user2 = new User(2, "Bob", "bob@example.com", "9999990002", "pass2");
        User user3 = new User(3, "Charlie", "charlie@example.com", "9999990003", "pass3");
        User user4 = new User(4, "David", "david@example.com", "9999990004", "pass4");
        User user5 = new User(5, "Eve", "eve@example.com", "9999990005", "pass5");
        User user6 = new User(6, "Frank", "frank@example.com", "9999990006", "pass6");
        User user7 = new User(7, "Grace", "grace@example.com", "9999990007", "pass7");
        User user8 = new User(8, "Heidi", "heidi@example.com", "9999990008", "pass8");
        User user9 = new User(9, "Ivan", "ivan@example.com", "9999990009", "pass9");
        User user10 = new User(10, "Judy", "judy@example.com", "9999990010", "pass10");

        User[] users = {user1, user2, user3, user4, user5, user6, user7, user8, user9, user10};

        for (User user : users) {
            assertNotNull(user.getUserId());
            assertNotNull(user.getName());
            assertNotNull(user.getEmail());
            assertNotNull(user.getPhone());
            assertNotNull(user.getPassword());

            user.display();
        }

        // Equality check for same data
        User duplicateUser1 = new User(1, "Alice", "alice@example.com", "9999990001", "pass1");
        assertEquals(user1, duplicateUser1);
        assertEquals(user1.hashCode(), duplicateUser1.hashCode());

        // Negative check
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
    @Test
    public void testEquals_SameObject_ReturnsTrue() {
        User user = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        assertTrue(user.equals(user));
    }

    @Test
    public void testEquals_NullObject_ReturnsFalse() {
        User user = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        assertFalse(user.equals(null));
    }

    @Test
    public void testEquals_DifferentClass_ReturnsFalse() {
        User user = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        assertFalse(user.equals("Not a User object"));
    }

    @Test
    public void testEquals_EqualObjects_ReturnsTrue() {
        User user1 = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        User user2 = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        assertTrue(user1.equals(user2));
    }

    @Test
    public void testEquals_DifferentObjects_ReturnsFalse() {
        User user1 = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        User user2 = new User(2, "Astha Singh", "singh.k.astha@gmail.com", "0987654321", "password123");
        assertFalse(user1.equals(user2));
    }

    @Test
    public void testHashCode_EqualObjects_SameHashCode() {
        User user1 = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        User user2 = new User(1, "Ayush Melty", "ayushmelty@gmail.com", "1234567890", "password");
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
