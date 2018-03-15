package org.hbhk.aili.client.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReflectionUtils {
	private static Log logger = LogFactory.getLog(ReflectionUtils.class);

	private ReflectionUtils() {
	}

	/**
	 * Read directly the object attribute values, ignoring the private /
	 * protected modifiers, without passing through getter function.
	 */
	public static Object getFieldValue(final Object object, final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			return "";
		}
		// throw new IllegalArgumentException("Could not find field [" +
		// fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("Can not throw exceptions{}", e);
		}
		return result;
	}

	/**
	 * Directly set the object property values, ignoring the private / protected
	 * modifiers, without a setter function.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("Can not throw exceptions:{}", e);
		}
	}

	/**
	 * Directly call the object methods, ignoring private / protected modifiers.
	 */
	public static Object invokeMethod(final Object object, final String methodName, final Class<?>[] parameterTypes,
			final Object[] parameters) throws InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
		}

		method.setAccessible(true);

		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {
			logger.error("Can not throw exceptions:{}", e);
		}

		return null;
	}

	/**
	 * Upward cycle of transformation, to obtain the object DeclaredField.
	 */
	protected static Field getDeclaredField(final Object object, final String fieldName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field is not in the current class definition, the continued
				// transformation of
			}
		}
		return null;
	}

	/**
	 * Upward cycle of transformation, to obtain the object DeclaredField.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * Upward cycle of transformation, to obtain the object DeclaredField.
	 */
	protected static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes) {

		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredMethod(methodName, parameterTypes);
			} catch (NoSuchMethodException e) {
				// Method Not in the current class definition, the continued
				// transformation of
			}
		}
		return null;
	}

	/**
	 * Through reflection, to obtain Class definition statement of the generic
	 * parameters of the parent class type of. eg. public UserDao extends
	 * HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getSuperClassGenricType(@SuppressWarnings("rawtypes") final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return (Class) params[index];
	}

	/**
	 * Will be reflected when the checked exception into unchecked exception.
	 */
	public static IllegalArgumentException convertToUncheckedException(Exception e) {
		if (e instanceof IllegalAccessException || e instanceof IllegalArgumentException
				|| e instanceof NoSuchMethodException) {
			return new IllegalArgumentException("Refelction Exception.", e);
		} else {
			return new IllegalArgumentException(e);
		}
	}

	/**
	 * 返回完全类名+方法名称+方法参数： 例如: "包名.规范化类名.方法名[参数包明.类名,...]"
	 */
	@SuppressWarnings("rawtypes")
	public static String getFullName(Class clazz, Method method) {
		StringBuilder sb = new StringBuilder(8);
		sb.append(clazz.getCanonicalName()).append(".");
		sb.append(method.getName());
		Class[] clazzs = method.getParameterTypes();
		if (clazzs != null && clazzs.length > 0) {
			sb.append("[");
			sb.append(clazzs[0].getClass().getCanonicalName());
			for (int i = 1; i < clazzs.length; i++) {
				sb.append(",").append(clazzs[i].getClass().getCanonicalName());
			}
			sb.append("]");
		}
		return sb.toString();
	}

	/**
	 * 返回完全类名+方法名称+方法参数： 例如: "包名.规范化类名.方法名[参数包明.类名,...]"
	 */
	public static String getFullName(Object object, Method method) {
		return getFullName(object.getClass(), method);
	}
}
