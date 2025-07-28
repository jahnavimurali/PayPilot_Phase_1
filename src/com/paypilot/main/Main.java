package com.paypilot.main;


import com.paypilot.model.*;
import com.paypilot.service.*;
import com.paypilot.util.*;

import com.paypilot.service.Authentication;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        Authentication auth = new Authentication();
        System.out.println(auth.authenticateUserPassword(1, "pass11"));
    }
}