package com.hz.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;



public class BaseController {

	// 获取request,response,session
	
	/**
	 * 线程局部变量
	 */
	private static final ThreadLocal<HttpServletRequest> requestContainer = new ThreadLocal<HttpServletRequest>();

	/**
	 * 初始化request
	 * 
	 * @param request
	 */
	@ModelAttribute
	private final void initRequest(HttpServletRequest request) {
		requestContainer.set(request);
	}

	/**
	 * 获取当前线程的request对象
	 * @return
	 */
	protected final HttpServletRequest getRequest() {
		return requestContainer.get();
	}

	/**
	 * 当前线程的session
	 * @return
	 */
	public HttpSession getSession() {
		 HttpSession session = getRequest().getSession();
		return session;
	}

	/**
	 * 获取请求的字符串
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String str = getRequest().getParameter(key);
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * 获取请求的Int值
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		String str = getRequest().getParameter(key);
		int i = 0;
		try {
			if (str != null && !("".equals(str))) {
				i = Integer.parseInt(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 获取请求的byte
	 * @param key
	 * @return
	 */
	public byte getByte(String key) {
		String str = getRequest().getParameter(key);
		byte i = 0;
		try {
			if (str != null && !("".equals(str))) {
				i = Byte.parseByte(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 获取请求的double
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
		String str = getRequest().getParameter(key);
		double i = 0;
		try {
			if (str != null && !("".equals(str))) {
				i = Double.parseDouble(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 转换数据库时间格式
	 * @param key
	 * @return
	 */
	public Timestamp getTimeM(String key) {
		String str = getRequest().getParameter(key);
		Timestamp i = null;
		if (str != null && !("".equals(str))) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				i = new Timestamp(sdf.parse(str).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	/**
	 * 获取请求中的字符串数组
	 * @param key
	 * @return
	 */
	public String[] getArrayString(String key) {
		String[] str = getRequest().getParameterValues(key);
		return str;
	}

	/**
	 * 获取请求的整数数组
	 * @param key
	 * @return
	 */
	public int[] getArrayInt(String key) {
		String[] str = getRequest().getParameterValues(key);
		if (str == null || str.length == 0) {
			return null;
		}
		int[] array = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			try {
				array[i] = Integer.parseInt(str[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return array;
	}

	/**
	 * 获取double数组
	 * 
	 * @param str
	 * @return
	 */
	public double[] getArrayDouble(String str) {
		String[] strAr = getRequest().getParameterValues(str);
		if (strAr == null || (strAr != null && strAr.length == 0)) {
			return null;
		}
		double[] array = new double[strAr.length];
		for (int i = 0; i < strAr.length; i++) {
			if (!strAr[i].equals("")) {
				array[i] = Double.parseDouble(strAr[i]);
			} else {
				array[i] = 0;
			}
		}
		return array;
	}

	/**
	 * 获取请求中的map
	 * @param model
	 * @return
	 */
	public Map<String, Object> getModel(Map<String, Object> model) {
		Enumeration<?> pNames = getRequest().getParameterNames();
		while (pNames.hasMoreElements()) {
			String name = (String) pNames.nextElement();
			String value = getRequest().getParameter(name);
			model.put(name, value);
		}
		return model;
	}

}
