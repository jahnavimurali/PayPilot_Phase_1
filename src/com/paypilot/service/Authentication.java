package com.paypilot.service;

import com.paypilot.model.User;

public class Authentication {
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
    
    public boolean authenticateUserPassword(int userId, String psssword){

        for (User user : users) {
            if(user.getUserId()==userId && user.getPassword().equals(psssword)) return true;
        }
        return false;


    }
}
