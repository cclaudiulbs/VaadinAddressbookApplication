package com.cc.addressbook.util;

import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * @author cclaudiu
 *
 */
public final class ContactNotificationUtil {
    private ContactNotificationUtil() { }

    public static void showTrayNotification(String message, Application mainAppInstance) {
        mainAppInstance.getMainWindow().showNotification(message, Window.Notification.TYPE_TRAY_NOTIFICATION);
    }

    public static void showStandardNotification(String message, Application mainAppInstance) {
        mainAppInstance.getMainWindow().showNotification(message);
    }
}
