package com.cc.addressbook.views;

import com.cc.addressbook.entities.PersonEntity;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;

/**
 * @author cclaudiu
 *
 */

public interface CustomerTableView extends Component {

    void addListener(CustomerTableViewListener listener);

    public interface CustomerTableViewListener {
        void populateContainer(BeanItemContainer<PersonEntity> container);
    }

    BeanItemContainer<PersonEntity> getPersonContainer();

    void displayEditView(Component component);
}