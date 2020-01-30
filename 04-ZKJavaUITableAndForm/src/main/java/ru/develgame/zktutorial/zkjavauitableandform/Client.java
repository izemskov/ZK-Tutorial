/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */

package ru.develgame.zktutorial.zkjavauitableandform;

import java.util.Random;

public class Client {
    private String name;
    private String group;
    private int cpuUsage;
    private int diskFree;
    private int diskUsage;
    private int memFree;
    private int memUsage;
    private int netInSpeed;
    private int netOutSpeed;

    private Random random = new Random();

    public Client() {
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getCpuUsage() {
        return cpuUsage;
    }

    public long getDiskFree() {
        return diskFree;
    }

    public long getDiskUsage() {
        return diskUsage;
    }

    public long getMemFree() {
        return memFree;
    }

    public long getMemUsage() {
        return memUsage;
    }

    public int getNetInSpeed() {
        return netInSpeed;
    }

    public int getNetOutSpeed() {
        return netOutSpeed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Client(String name, String group) {
        this.name = name;
        this.group = group;

        cpuUsage = random.nextInt(100);
        diskFree = random.nextInt(100000);
        diskUsage = random.nextInt(100000);
        memFree = random.nextInt(100000);
        memUsage = random.nextInt(100000);
        netInSpeed = random.nextInt(10000);
        netOutSpeed = random.nextInt(10000);
    }
}
