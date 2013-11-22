package com.lifeix;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class TestUtil {
	/**
	 * 找出所有实现测试
	 * 
	 * @param packageName 搜索的饭锅
	 * @param interfaceTarget 搜索的目标接口
	 * @param methodName 测试方法名
	 * @param invoker 测试方法的宿主
	 */
	public static void searchAndTest(String packageName, Class<?> interfaceTarget, String methodName, Object invoker) {
		Method method = Util.getMethod(invoker.getClass(), methodName, interfaceTarget);

		Set<Class<?>> classes = null;
		try {
			classes = Util.getClasses(packageName);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		for (Class<?> clz : classes) {
			try {
				if (clz.isInterface()) {
					continue;
				}
				Object o = clz.newInstance();
				if (interfaceTarget.isInstance(o)) {
					method.invoke(invoker, interfaceTarget.cast(o));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
