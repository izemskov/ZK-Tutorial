/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */

package ru.develgame.zktutorial.zkjavauitableandform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.zkoss.zk.ui.Page;

@SpringBootApplication
public class Application {
    private static ConfigurableApplicationContext context;

    public static void initIndexWindow(Page page) {
        IndexWindow indexWindow = context.getBean(IndexWindow.class);
        indexWindow.init(page);
    }

    public static void main(String[] args) {
        context = SpringApplication.run(Application.class);
    }
}
