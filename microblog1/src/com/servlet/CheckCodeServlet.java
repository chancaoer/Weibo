package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.MakeXML;

public class CheckCodeServlet extends HttpServlet {

	public CheckCodeServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 防止浏览器缓存
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();
		// 获得值
		String code = request.getParameter("code");
		String xml = null;
		try {
			if (code.equals(request.getSession().getAttribute(
					"validationCode"))) {
				xml = MakeXML.makeXml("验证码输入正确", true);
			} else {
				xml = MakeXML.makeXml("验证码输入错误", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(xml);
		// 将xml加到response中,一定要加进去
		out.println(xml);
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
