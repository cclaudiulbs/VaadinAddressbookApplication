package com.cc.addressbook.presenters;

import com.cc.addressbook.constants.CustomerEditViewOperations;
import com.cc.addressbook.entities.PersonEntity;
import com.cc.addressbook.models.CustomerModel;
import com.cc.addressbook.views.CustomerEditView;

/**
 * @author cclaudiu
 *
 * There are 3 operations which the presenter needs to handle:
 *  save -> commit()
 *  reset -> getCurrentItem
 *  cancel -> discard()
 */

public class CustomerEditViewPresenter implements CustomerEditView.CustomerEditViewListener {

    private CustomerEditView editView;
    private CustomerModel customerModel;

    public CustomerEditViewPresenter(CustomerEditView editView, CustomerModel customerModel) {
        this.editView = editView;
        this.customerModel = customerModel;

        editView.addListener(this);
    }

    @Override
    public void changeCustomer(PersonEntity customer, CustomerEditViewOperations operationTypes) {

        switch (operationTypes) {
            case SAVE:      // delegate to save view
                PersonEntity savedCustomer = customerModel.saveCustomer(customer);
                editView.setDisplay(editView.getSaveView(), savedCustomer);
                break;
            case CLEAR:     // delegate to clear view
                editView.setDisplay(editView.getClearView(), customer);
                break;
            case CANCEL:    // delegate to cancel view
                editView.setDisplay(editView.getCancelView(), null);
                break;
        }
    }
}