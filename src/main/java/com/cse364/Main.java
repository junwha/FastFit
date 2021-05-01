package com.cse364;

import com.cse364.infra.*;

public class Main {
    public static void main(String args[]) {
        Config config = new Config();
        Controller controller = new Controller(config);
        controller.main(args);
    }
}
