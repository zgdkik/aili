package com.deppon.esb.management.web.deploy.struts.result.json;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.json.JSONException;

import com.deppon.esb.management.web.deploy.struts.result.json.annotation.DateFormat;

/**
 * @Description 绑定Map中的键值对到指定Action
 * @author HuangHua
 * @Date 2012-4-12
 * 
 */
public class JSONPopulator {

	private static final Log LOG = LogFactory.getLog(JSONPopulator.class);

	public JSONPopulator() {
	}

	public static void populateObject(Object object,
			@SuppressWarnings("rawtypes") final Map elements)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, IntrospectionException,
			IllegalArgumentException, JSONException, InstantiationException {
		Class<?> clazz = object.getClass();

		BeanInfo info = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] props = info.getPropertyDescriptors();

		// iterate over class fields
		for (int i = 0; i < props.length; ++i) {
			PropertyDescriptor prop = props[i];
			String name = prop.getName();

			if (elements.containsKey(name)) {
				Object value = elements.get(name);
				Method method = prop.getWriteMethod();
				if (method != null) {
					// use only public setters
					if (Modifier.isPublic(method.getModifiers())) {
						Class<?>[] paramTypes = method.getParameterTypes();
						Type[] genericTypes = method.getGenericParameterTypes();

						if (paramTypes.length == 1) {
							Object convertedValue = convert(paramTypes[0],
									genericTypes[0], value, method);
							method.invoke(object,
									new Object[] { convertedValue });
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static Object convert(Class<?> clazz, Type type, Object value,
			Method method) throws IllegalArgumentException, JSONException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException,
			IntrospectionException {

		if (value == null) {
			// if it is a java primitive then get a default value, otherwise
			// leave it as null
			return clazz.isPrimitive() ? convertPrimitive(clazz, null, method) : null;
		} else if (isJSONPrimitive(clazz)) {
			return convertPrimitive(clazz, value, method);
		} else if (Collection.class.isAssignableFrom(clazz)) {
			return convertToCollection(clazz, type, value, method);
		} else if (Map.class.isAssignableFrom(clazz)) {
			return convertToMap(clazz, type, value, method);
		} else if (clazz.isArray()) {
			return convertToArray(clazz, type, value, method);
		} else if (value instanceof Map) {
			// nested bean
			Object convertedValue = clazz.newInstance();
			populateObject(convertedValue, (Map) value);
			return convertedValue;
		} else if (BigDecimal.class.equals(clazz)) {
//			return new BigDecimal(value != null ? value.toString() : "0");
			return new BigDecimal(value.toString());
		} else if (BigInteger.class.equals(clazz)) {
//			return new BigInteger(value != null ? value.toString() : "0");
			return new BigInteger(value.toString());
		} else {
			throw new JSONException("Incompatible types for property "
					+ method.getName());
		}
	}

	private static boolean isJSONPrimitive(Class<?> clazz) {
		return clazz.isPrimitive() || clazz.equals(String.class)
				|| clazz.equals(Date.class) || clazz.equals(Boolean.class)
				|| clazz.equals(Byte.class) || clazz.equals(Character.class)
				|| clazz.equals(Double.class) || clazz.equals(Float.class)
				|| clazz.equals(Integer.class) || clazz.equals(Long.class)
				|| clazz.equals(Short.class) || clazz.equals(Locale.class)
				|| clazz.isEnum();
	}

	@SuppressWarnings("rawtypes")
	private static Object convertToArray(Class<?> clazz, Type type,
			Object value, Method accessor) throws JSONException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException,
			NoSuchMethodException, IntrospectionException {
		if (value == null){
			return null;
		}
		else if (value instanceof List) {
			Class<?> arrayType = clazz.getComponentType();
			List values = (List) value;
			Object newArray = Array.newInstance(arrayType, values.size());

			// create an object for each element
			for (int j = 0; j < values.size(); j++) {
				Object listValue = values.get(j);

				if (arrayType.equals(Object.class)) {
					// Object[]
					Array.set(newArray, j, listValue);
				} else if (isJSONPrimitive(arrayType)) {
					// primitive array
					Array.set(newArray, j,
							convertPrimitive(arrayType, listValue, accessor));
				} else if (listValue instanceof Map) {
					// array of other class
					Object newObject = null;
					if (Map.class.isAssignableFrom(arrayType)) {
						newObject = convertToMap(arrayType, type, listValue,
								accessor);
					} else if (List.class.isAssignableFrom(arrayType)) {
						newObject = convertToCollection(arrayType, type,
								listValue, accessor);
					} else {
						newObject = arrayType.newInstance();
						populateObject(newObject, (Map) listValue);
					}

					Array.set(newArray, j, newObject);
				} else{
					throw new JSONException("Incompatible types for property "
							+ accessor.getName());
				}
			}

			return newArray;
		} else{
			throw new JSONException("Incompatible types for property "
					+ accessor.getName());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object convertToCollection(Class<?> clazz, Type type,
			Object value, Method accessor) throws JSONException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, InstantiationException,
			NoSuchMethodException, IntrospectionException {
		if (value == null){
			return null;
		}
		else if (value instanceof List) {
			Class<?> itemClass = Object.class;
			Type itemType = null;
			if ((type != null) && (type instanceof ParameterizedType)) {
				ParameterizedType ptype = (ParameterizedType) type;
				itemType = ptype.getActualTypeArguments()[0];
				if (itemType.getClass().equals(Class.class)) {
					itemClass = (Class<?>) itemType;
				} else {
					itemClass = (Class<?>) ((ParameterizedType) itemType)
							.getRawType();
				}
			}
			List values = (List) value;

			Collection newCollection = null;
			try {
				newCollection = (Collection) clazz.newInstance();
			} catch (InstantiationException ex) {
				// fallback if clazz represents an interface or abstract class
				if (Set.class.isAssignableFrom(clazz)) {
					newCollection = new HashSet();
				} else {
					newCollection = new ArrayList();
				}
			}

			// create an object for each element
			for (int j = 0; j < values.size(); j++) {
				Object listValue = values.get(j);

				if (itemClass.equals(Object.class)) {
					// Object[]
					newCollection.add(listValue);
				} else if (isJSONPrimitive(itemClass)) {
					// primitive array
					newCollection.add(convertPrimitive(itemClass, listValue,
							accessor));
				} else if (Map.class.isAssignableFrom(itemClass)) {
					Object newObject = convertToMap(itemClass, itemType,
							listValue, accessor);
					newCollection.add(newObject);
				} else if (List.class.isAssignableFrom(itemClass)) {
					Object newObject = convertToCollection(itemClass, itemType,
							listValue, accessor);
					newCollection.add(newObject);
				} else if (listValue instanceof Map) {
					// array of beans
					Object newObject = itemClass.newInstance();
					populateObject(newObject, (Map) listValue);
					newCollection.add(newObject);
				} else{
					throw new JSONException("Incompatible types for property "
							+ accessor.getName());
				}
			}

			return newCollection;
		} else{
			throw new JSONException("Incompatible types for property "
					+ accessor.getName());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object convertToMap(Class<?> clazz, Type type, Object value,
			Method accessor) throws JSONException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException, NoSuchMethodException,
			IntrospectionException {
		if (value == null){
			return null;
		}
		else if (value instanceof Map) {
			Class<?> itemClass = Object.class;
			Type itemType = null;
			if ((type != null) && (type instanceof ParameterizedType)) {
				ParameterizedType ptype = (ParameterizedType) type;
				itemType = ptype.getActualTypeArguments()[1];
				if (itemType.getClass().equals(Class.class)) {
					itemClass = (Class<?>) itemType;
				} else {
					itemClass = (Class<?>) ((ParameterizedType) itemType)
							.getRawType();
				}
			}
			Map values = (Map) value;

			Map newMap = null;
			try {
				newMap = (Map) clazz.newInstance();
			} catch (InstantiationException ex) {
				// fallback if clazz represents an interface or abstract class
				newMap = new HashMap();
			}

			// create an object for each element
			Iterator iter = values.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Object v = entry.getValue();

				if (itemClass.equals(Object.class)) {
					// String, Object
					newMap.put(key, v);
				} else if (isJSONPrimitive(itemClass)) {
					// primitive map
					newMap.put(key, convertPrimitive(itemClass, v, accessor));
				} else if (Map.class.isAssignableFrom(itemClass)) {
					Object newObject = convertToMap(itemClass, itemType, v,
							accessor);
					newMap.put(key, newObject);
				} else if (List.class.isAssignableFrom(itemClass)) {
					Object newObject = convertToCollection(itemClass, itemType,
							v, accessor);
					newMap.put(key, newObject);
				} else if (v instanceof Map) {
					// map of beans
					Object newObject = itemClass.newInstance();
					populateObject(newObject, (Map) v);
					newMap.put(key, newObject);
				} else {
					throw new JSONException("Incompatible types for property "
							+ accessor.getName());
				}
			}

			return newMap;
		} else {
			throw new JSONException("Incompatible types for property "
					+ accessor.getName());
		}
	}

	/**
	 * Converts numbers to the desired class, if possible
	 * 
	 * @throws JSONException
	 */
	@SuppressWarnings("unchecked")
	private static Object convertPrimitive(
			@SuppressWarnings("rawtypes") Class clazz, Object value,
			Method method) throws JSONException {
		if (value == null) {
			if (Short.TYPE.equals(clazz) || Short.class.equals(clazz)){
				return (short) 0;
			}
			else if (Byte.TYPE.equals(clazz) || Byte.class.equals(clazz)){
				return (byte) 0;
			}
			else if (Integer.TYPE.equals(clazz) || Integer.class.equals(clazz)){
				return 0;
			}
			else if (Long.TYPE.equals(clazz) || Long.class.equals(clazz)){
				return 0L;
			}
			else if (Float.TYPE.equals(clazz) || Float.class.equals(clazz)){
				return 0f;
			}
			else if (Double.TYPE.equals(clazz) || Double.class.equals(clazz)){
				return 0d;
			}
			else if (Boolean.TYPE.equals(clazz) || Boolean.class.equals(clazz)){
				return Boolean.FALSE;
			}
			else{
				return null;
			}
		} else if (value instanceof Number && !Date.class.equals(clazz)) {
			Number number = (Number) value;

			if (Short.TYPE.equals(clazz)){
				return number.shortValue();
			}
			else if (Short.class.equals(clazz)){
				return Short.valueOf(number.shortValue());
			}
			else if (Byte.TYPE.equals(clazz)){
				return number.byteValue();
			}
			else if (Byte.class.equals(clazz)){
				return Byte.valueOf(number.byteValue());
			}
			else if (Integer.TYPE.equals(clazz)){
				return number.intValue();
			}
			else if (Integer.class.equals(clazz)){
				return Integer.valueOf(number.intValue());
			}
			else if (Long.TYPE.equals(clazz)){
				return number.longValue();
			}
			else if (Long.class.equals(clazz)){
				return Long.valueOf(number.longValue());
			}
			else if (Float.TYPE.equals(clazz)){
				return number.floatValue();
			}
			else if (Float.class.equals(clazz)){
				return Float.valueOf(number.floatValue());
			}
			else if (Double.TYPE.equals(clazz)){
				return number.doubleValue();
			}
			else if (Double.class.equals(clazz)){
				return Double.valueOf(number.doubleValue());
			}
			else if (String.class.equals(clazz)){
				return value.toString();
			}
		} else if (clazz.equals(Date.class)) {
			if (Long.class.equals(value.getClass())) {
				Long time = (Long) value;
				return new Date(time);
			} else {
				if (String.class.equals(value.getClass())) {
					DateFormat formate = method.getAnnotation(DateFormat.class);
					if (formate != null) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								formate.formate());
						try {
							return sdf.parse((String) value);
						} catch (ParseException e) {
							LOG.debug(value
									+ " Could not be parsed into Date type", e);
							throw new RuntimeException(e);
						}
					} else {
						Locale locale = Locale.US;
						SimpleDateFormat sdf = new SimpleDateFormat(
								"EEE MMM dd yyyy HH:mm:ss",
								new DateFormatSymbols(locale));
						try {
							return sdf.parse((String) value);
						} catch (ParseException e) {
							LOG.debug(value
									+ " Could not be parsed into Date type", e);
							throw new RuntimeException(e);
						}
					}
				}
			}
		} else if (clazz.isEnum()) {
			String sValue = (String) value;
			return Enum.valueOf(clazz, sValue);
		} else if (value instanceof String) {
			String sValue = (String) value;
			if (Boolean.TYPE.equals(clazz)){
				return Boolean.parseBoolean(sValue);
			}
			else if (Boolean.class.equals(clazz)){
				return Boolean.valueOf(sValue);
			}
			else if (Short.TYPE.equals(clazz)){
				return Short.parseShort(sValue);
			}
			else if (Short.class.equals(clazz)){
				return Short.valueOf(sValue);
			}
			else if (Byte.TYPE.equals(clazz)){
				return Byte.parseByte(sValue);
			}
			else if (Byte.class.equals(clazz)){
				return Byte.valueOf(sValue);
			}
			else if (Integer.TYPE.equals(clazz)){
				return Integer.parseInt(sValue);
			}
			else if (Integer.class.equals(clazz)){
				return Integer.valueOf(sValue);
			}
			else if (Long.TYPE.equals(clazz)){
				return Long.parseLong(sValue);
			}
			else if (Long.class.equals(clazz)){
				return Long.valueOf(sValue);
			}
			else if (Float.TYPE.equals(clazz)){
				return Float.parseFloat(sValue);
			}
			else if (Float.class.equals(clazz)){
				return Float.valueOf(sValue);
			}
			else if (Double.TYPE.equals(clazz)){
				return Double.parseDouble(sValue);
			}
			else if (Double.class.equals(clazz)){
				return Double.valueOf(sValue);
			}
			else if (Character.TYPE.equals(clazz)
					|| Character.class.equals(clazz)) {
				char charValue = 0;
				if (sValue.length() > 0) {
					charValue = sValue.charAt(0);
				}
				if (Character.TYPE.equals(clazz)){
					return charValue;
				}
				else{
					return Character.valueOf(charValue);
				}
			} else if (clazz.equals(Locale.class)) {
				String[] components = sValue.split("_", 2);
				if (components.length == 2) {
					return new Locale(components[0], components[1]);
				} else {
					return new Locale(sValue);
				}
			} else if (Enum.class.isAssignableFrom(clazz)) {
				return Enum.valueOf(clazz, sValue);
			}
		}

		return value;
	}

}