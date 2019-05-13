package com.corock.mysite.action.guestbook;

import com.corock.mvc.action.AbstractActionFactory;
import com.corock.mvc.action.Action;

public class GuestbookActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if ("insert".equals(actionName)) {
			action = new InsertAction();
		} else if ("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("ajax".equals(actionName)) {
			action = new AjaxAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
