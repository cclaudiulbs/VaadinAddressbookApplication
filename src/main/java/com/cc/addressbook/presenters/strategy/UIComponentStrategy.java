package com.cc.addressbook.presenters.strategy;

import com.cc.addressbook.constants.DefaultMenuBar;


/**
 * @author cclaudiu
 * 
 * Strategy should be an abstract-class/interface from which the concrete impl derive
 *
 */
public interface UIComponentStrategy {
	class Decide{
//		CustomComponent createInstance(DefaultMenuBar pressedMenu) {
//			if(pressedMenu instanceOf )
//		}
	}
	
	void dispatch(DefaultMenuBar pressedMenu);
}
