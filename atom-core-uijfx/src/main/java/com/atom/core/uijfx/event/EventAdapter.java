/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.event;

import javafx.event.ActionEvent;

/**
 * @author obullxl@gmail.com
 */
public class EventAdapter {
private static final EventAdapter DEFAULT = new EventAdapter();

    public static EventAdapter get() {
        return DEFAULT;
    }

    public void handle(ActionEvent evt) {
    }

}
