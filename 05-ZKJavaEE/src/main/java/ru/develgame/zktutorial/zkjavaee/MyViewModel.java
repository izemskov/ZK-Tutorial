/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */
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
