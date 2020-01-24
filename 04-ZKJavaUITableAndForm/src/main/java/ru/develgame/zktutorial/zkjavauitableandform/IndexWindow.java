/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */

package ru.develgame.zktutorial.zkjavauitableandform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.xel.VariableResolver;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.zul.*;
import org.zkoss.zul.impl.GridDataLoader;

import java.util.HashMap;
import java.util.Map;

@Component
@Lazy
@Scope("prototype")
public class IndexWindow {
    @Autowired
    private MailDataModel mailDataModel;

    public void init(Page page) {
        Window window = new Window();
        window.setTitle("My window");
        window.setBorder("normal");

        //GroupsModel<Mail, Object, Object> groupsModel =
//                new SimpleGroupsModel<>(mailDataModel.getData(), mailDataModel.getHeaders());

        SimpleListModel<Mail> mailSimpleListModel = new SimpleListModel<Mail>(mailDataModel.getData().get(0));

        Grid grid = new Grid();
        grid.setModel(mailSimpleListModel);
        grid.setParent(window);
        grid.setTemplate("model", new Template() {
            @Override
            public org.zkoss.zk.ui.Component[] create(org.zkoss.zk.ui.Component component,
                                                      org.zkoss.zk.ui.Component component1,
                                                      VariableResolver variableResolver,
                                                      Composer composer) {
                org.zkoss.zk.ui.Component[] components = new org.zkoss.zk.ui.Component[1];

                Row row = new Row();
                row.setParent(component);

                components[0] = row;

                Mail each = (Mail) variableResolver.resolveVariable("each");

                Label label = new Label();
                label.setValue(Long.toString(each.getId()));
                Label label1 = new Label();
                label1.setValue(each.getSubject());
                Label label2 = new Label();
                label2.setValue(each.getReceived());
                Label label3 = new Label();
                label3.setValue(Integer.toString(each.getSize()));

                label.setParent(row);
                label1.setParent(row);
                label2.setParent(row);
                label3.setParent(row);

                return components;
            }

            @Override
            public Map<String, Object> getParameters() {
                Map<String, Object> map = new HashMap<>();
                return map;
            }
        });

        Columns columns = new Columns();
        columns.setParent(grid);

        Column column = new Column();
        column.setLabel("From");
        column.setParent(columns);

        column = new Column();
        column.setLabel("Subject");
        column.setParent(columns);

        column = new Column();
        column.setLabel("Received");
        column.setParent(columns);

        column = new Column();
        column.setLabel("Size");
        column.setParent(columns);

        window.setPage(page);
    }
}

