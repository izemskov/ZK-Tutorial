package ru.develgame.zktutorial.zkjavaee;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class MyViewModel {

    private int count;
    
    private String testValue = "Test";

    @Init
    public void init() {
        count = 100;
    }

    @Command
    @NotifyChange("count")
    public void cmd() {
        ++count;
    }

    public int getCount() {
        return count;
    }

    public String getTestValue() {
        return testValue;
    }
}
