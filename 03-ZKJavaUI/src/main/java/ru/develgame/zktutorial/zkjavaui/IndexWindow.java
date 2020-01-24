/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */

package ru.develgame.zktutorial.zkjavaui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import java.util.Random;

@Component
@Lazy
@Scope("prototype")
public class IndexWindow {
    private int randomInt = new Random().nextInt(100);

    @Value("${somevalue}")
    private String someValue;

    public void init(Page page) {
        Window window = new Window();
        window.setTitle("My window");
        window.setBorder("normal");

        Div div = new Div();
        div.setParent(window);

        Label label = new Label();
        label.setValue("Hello zk javaUI! Random value: " + randomInt);
        label.setParent(div);

        Label label1 = new Label();
        label1.setValue("Value passed via spring boot configuration: " + someValue);
        label1.setParent(window);

        window.setPage(page);
    }
}
