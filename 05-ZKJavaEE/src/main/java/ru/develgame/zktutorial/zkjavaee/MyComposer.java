/*
 *
 * This software is copyright protected (c) 2020 by S-Terra CSP
 *
 * Author:              Ilya Zemskov
 * E-mail:              izemskov@s-terra.com
 *
 * Owner:               Ilya Zemskov
 * E-mail:              izemskov@s-terra.com
 *
 * $Header: $
 *
 */
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
