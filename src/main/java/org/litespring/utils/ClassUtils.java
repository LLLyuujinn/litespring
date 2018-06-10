package org.litespring.utils;

/**
 * @Author: lzy
 * @Date: Created in 18:20 2018/6/10
 */
public abstract class ClassUtils {

	/**
	 * 获取默认的类加载器
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {

		}
		if (cl == null) {
			cl = ClassUtils.class.getClassLoader();
			if (cl == null) {
				try {
					cl = ClassLoader.getSystemClassLoader();
				} catch (Throwable ex) {

				}
			}
		}
		return cl;
	}
}
