package com.paypilot.service;

import com.paypilot.model.User;

public class Authentication {
    User user1 = new User(1, "Hari", "hari@gmail.com", "9876543210", "pass123");
        User user2 = new User(2, "Vijay", "vijay@gmail.com", "8765432109", "bpass2");
        User user3 = new User(3, "Chandru", "chandru@gmail.com", "7654321098", "chaie1");
        User user4 = new User(4, "Neha", "neha@gmail.com", "6543210987", "huina123");
        User user5 = new User(5, "Cheera", "cheera@gmail.com", "8432109876", "huinn@123");
//        User user6 = new User(6, "Frank", "frank@example.com", "9999990006", "pass6");
//        User user7 = new User(7, "Grace", "grace@example.com", "9999990007", "pass7");
//        User user8 = new User(8, "Heidi", "heidi@example.com", "9999990008", "pass8");
//        User user9 = new User(9, "Ivan", "ivan@example.com", "9999990009", "pass9");
//        User user10 = new User(10, "Judy", "judy@example.com", "9999990010", "pass10");

//        public User[] users = {user1, user2, user3, user4, user5, user6, user7, user8, user9, user10};
    public User[] users = {user1, user2, user3, user4, user5};
    
    public boolean authenticateUserPassword(int userId, String psssword){

        for (User user : users) {
            if(user.getUserId()==userId && user.getPassword().equals(psssword)) return true;
        }
        return false;


    }
}
