/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.atom.core.uijfx.views;

import java.text.Format;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

/**
 * 表格单元格格式化展示
 * 
 * @author obullxl@gmail.com
 * @version $Id: FormattedTableCellFactory.java, V1.0.1 2013-2-15 下午4:45:58 $
 */
public class FormattedTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {
    private TextAlignment alignment;
    private Format        format;

    public TextAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment(TextAlignment alignment) {
        this.alignment = alignment;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    /** 
     * @see javafx.util.Callback#call(java.lang.Object)
     */
    public final TableCell<S, T> call(TableColumn<S, T> arg) {
        TableCell<S, T> cell = new TableCell<S, T>() {
            @SuppressWarnings("unchecked")
            public void updateItem(Object item, boolean empty) {
                if (item == getItem()) {
                    return;
                }

                super.updateItem((T) item, empty);

                if (item == null) {
                    super.setText(null);
                    super.setGraphic(null);
                } else if (item instanceof Node) {
                    super.setText(null);
                    super.setGraphic((Node) item);
                } else {
                    String text = item.toString();
                    if (format != null) {
                        text = format.format(text);
                    }
                    super.setText(text);
                    super.setGraphic(null);
                }
            }
        };

        cell.setTextAlignment(alignment);

        switch (alignment) {
            case CENTER:
                cell.setAlignment(Pos.CENTER);
                break;
            case RIGHT:
                cell.setAlignment(Pos.CENTER_RIGHT);
                break;
            default:
                cell.setAlignment(Pos.CENTER_LEFT);
                break;
        }

        return cell;
    }
}
