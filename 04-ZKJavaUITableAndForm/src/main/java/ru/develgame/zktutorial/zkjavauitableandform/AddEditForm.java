/* This Source Code Form is subject to the terms of the Mozilla
 * Public License, v. 2.0. If a copy of the MPL was not distributed
 * with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * Copyright 2020 Ilya Zemskov */

package ru.develgame.zktutorial.zkjavauitableandform;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zul.*;

import javax.annotation.PostConstruct;

public class AddEditForm extends Window {
    @Autowired
    private ClientDataModel clientDataModel;

    private Client client = new Client();

//    private IndexWindow parent;

    @PostConstruct
    public void init() {
        this.setWidth("700px");
        this.setTitle("Add window");
        this.setBorder("normal");

        Grid grid = new Grid();
        grid.setParent(this);

        Rows rows = new Rows();
        rows.setParent(grid);

        Row row = new Row();
        row.setParent(rows);

        Label label = new Label();
        label.setValue("Name:");
        label.setParent(row);

        Textbox textbox = new Textbox();
        textbox.setParent(row);

        Row row1 = new Row();
        row1.setParent(rows);

        Label label1 = new Label();
        label1.setValue("Group:");
        label1.setParent(row1);

        Textbox textbox1 = new Textbox();
        textbox1.setParent(row1);

        Row row2 = new Row();
        row2.setParent(rows);
        row2.setAlign("right");

        Cell cell = new Cell();
        cell.setColspan(2);
        cell.setParent(row2);

        Window thisWindow = this;
        Button button = new Button();
        button.setLabel("Close");
        button.addEventListener("onClick", event -> {
            thisWindow.onClose();
        });
        button.setStyle("margin-right: 5px;");
        button.setParent(cell);

        Button button1 = new Button();
        button1.setLabel("Ok");
        button1.addEventListener("onClick", event -> {
            if (textbox.getValue().isEmpty()) {
                Messagebox.show("Name cannot be empty");
                return;
            }

            client.setName(textbox.getValue());
            client.setGroup(textbox1.getValue());
            clientDataModel.getData().add(client);

            ((IndexWindow) getParent()).updateDataModel();

            thisWindow.onClose();
        });
        button1.setParent(cell);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
