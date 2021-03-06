package com.corock.mvc.action.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corock.mvc.action.Action;
import com.corock.mysite.repository.UserDAO;
import com.corock.mysite.vo.UserVO;

import net.sf.json.JSONObject;

public class AjaxCheckEmailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		UserDAO dao = new UserDAO();
		UserVO vo = dao.get(email);
		
		boolean bExist = vo != null;
		Map<String, Object> map = new HashMap<>();
		map.put("exist", bExist);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		String jsonString = jsonObject.toString();
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(jsonString);
	}

}
