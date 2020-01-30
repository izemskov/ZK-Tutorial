/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */

package ru.develgame.zktutorial.zkjavauitableandform;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.xel.VariableResolver;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.zul.*;

import java.util.HashMap;
import java.util.Map;

public abstract class IndexWindow extends Window {
    @Autowired
    private ClientDataModel clientDataModel;

    private SimpleListModel<Client> clientSimpleListModel;
    private Grid grid;

    public void prepare(Page page) {
        this.setTitle("Clients");
        this.setBorder("normal");

        clientSimpleListModel = new SimpleListModel<>(clientDataModel.getData());

        grid = new Grid();
        grid.setModel(clientSimpleListModel);
        grid.setParent(this);
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

                Client each = (Client) variableResolver.resolveVariable("each");

                Label label = new Label();
                label.setValue(each.getName());
                Label label1 = new Label();
                label1.setValue(each.getGroup());
                Label label2 = new Label();
                label2.setValue(Integer.toString(each.getCpuUsage()));
                Label label3 = new Label();
                label3.setValue(Long.toString(each.getDiskFree()));
                Label label4 = new Label();
                label4.setValue(Long.toString(each.getDiskUsage()));
                Label label5 = new Label();
                label5.setValue(Long.toString(each.getMemFree()));
                Label label6 = new Label();
                label6.setValue(Long.toString(each.getMemUsage()));
                Label label7 = new Label();
                label7.setValue(Long.toString(each.getNetInSpeed()));
                Label label8 = new Label();
                label8.setValue(Long.toString(each.getNetOutSpeed()));

                label.setParent(row);
                label1.setParent(row);
                label2.setParent(row);
                label3.setParent(row);
                label4.setParent(row);
                label5.setParent(row);
                label6.setParent(row);
                label7.setParent(row);
                label8.setParent(row);

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
        column.setLabel("Client ID");
        column.setParent(columns);

        column = new Column();
        column.setLabel("Group");
        column.setParent(columns);
        column.setWidth("200px");

        column = new Column();
        column.setLabel("CPUUsage");
        column.setParent(columns);
        column.setWidth("150px");

        column = new Column();
        column.setLabel("DiskFree");
        column.setParent(columns);
        column.setWidth("150px");

        column = new Column();
        column.setLabel("DiskUsage");
        column.setParent(columns);
        column.setWidth("150px");

        column = new Column();
        column.setLabel("MemFree");
        column.setParent(columns);
        column.setWidth("150px");

        column = new Column();
        column.setLabel("MemUsage");
        column.setParent(columns);
        column.setWidth("150px");

        column = new Column();
        column.setLabel("NetInSpeed");
        column.setParent(columns);
        column.setWidth("150px");

        column = new Column();
        column.setLabel("NetOutSpeed");
        column.setParent(columns);
        column.setWidth("150px");

        Div div = new Div();
        div.setStyle("margin-top: 10px;");
        div.setParent(this);

        Button buttonAdd = new Button();
        buttonAdd.setLabel("Add");
        buttonAdd.setAutodisable("self");

        IndexWindow parent = this;
        buttonAdd.addEventListener("onClick", event -> {
            AddEditForm addEditForm = getAddEditForm();
            addEditForm.setPage(page);
            addEditForm.setParent(parent);
            addEditForm.doModal();
        });
        buttonAdd.setParent(div);

        this.setPage(page);
    }

    public void updateDataModel() {
        clientSimpleListModel = new SimpleListModel<>(clientDataModel.getData());
        grid.setModel(clientSimpleListModel);
    }

    protected abstract AddEditForm getAddEditForm();
}

