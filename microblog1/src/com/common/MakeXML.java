package com.common;

public class MakeXML {

	public static String makeXml(String message, boolean flag) throws Exception {
		StringBuffer bf = new StringBuffer();
		bf.append("<?xml version='1.0' encoding='UTF-8'?>");
		bf.append("<response>");
		if (message != null) {
			if (flag) { // 返回正确信息
				bf.append("<message>");
				bf.append(message);
				bf.append("</message>");
			} else { // 返回错误信息
				bf.append("<error>");
				bf.append(message);
				bf.append("</error>");
			}
		}
		bf.append("</response>");
		return bf.toString();
	}
}