package com.cc.addressbook.util;

import com.vaadin.ui.Button;
import com.vaadin.ui.Table;

public class DeleteColumnGenerator implements Table.ColumnGenerator {
        private final Button.ClickListener deleteListener;
        private Object lazyGeneratedColumnId;

        public DeleteColumnGenerator(Button.ClickListener deleteListener) {
            this.deleteListener = deleteListener;
        }

        @Override
        public Object generateCell(Table source, Object itemId, Object columnId) {
            this.lazyGeneratedColumnId = columnId;

            final Button deleteButton = new Button("Delete");
            deleteButton.addListener(deleteListener);

            return deleteButton;
        }

        public Object getLazyGeneratedColumnId() {
            return lazyGeneratedColumnId;
        }
    }
