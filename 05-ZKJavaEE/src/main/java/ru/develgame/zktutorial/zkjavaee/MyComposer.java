/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */
package ru.develgame.zktutorial.zkjavaee;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;

public class MyComposer extends SelectorComposer<Component> {
    @Wire
    private Label testLabel;

    @Override
    public void doAfterCompose(Component component) throws Exception {
        super.doAfterCompose(component);
        testLabel.setValue("Test");
    }

    @Listen("onClick = #someButton")
    public void some() {
        testLabel.setValue("Test1");
    }
}
