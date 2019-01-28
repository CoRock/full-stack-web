package com.corock.mvc.action.user;

import com.corock.mvc.action.AbstractActionFactory;
import com.corock.mvc.action.Action;
import com.corock.mvc.action.main.IndexAction;

public class UserActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("joinform".equals(actionName)) {
			action = new JoinFormAction();
		} else if ("join".equals(actionName)) {
			action = new JoinAction();
		} else if ("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if ("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}