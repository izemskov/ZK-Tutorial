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
package ru.develgame.zktutorial.zkjavauitableandform;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.event.ListDataListener;

import java.util.ArrayList;
import java.util.List;

@Component
@Lazy
@Scope("prototype")
public class MailDataModel {
    private List<List<Mail>> data = new ArrayList<>();
    private List<String> headers = new ArrayList<>();

    public MailDataModel() {
        List<Mail> today = new ArrayList<>();
        today.add(new Mail(1, "[zk1 - Help] RE: Bandbox Autocomplete Problem", "2012/11/18 10:16:27", 16));
        today.add(new Mail(2, "[zk1 - Help] RE: Bandbox Autocomplete Problem", "2012/11/18 10:16:27", 16));

        data.add(today);
        headers.add("Date: Today");
    }

    public List<List<Mail>> getData() {
        return data;
    }

    public List<String> getHeaders() {
        return headers;
    }
}