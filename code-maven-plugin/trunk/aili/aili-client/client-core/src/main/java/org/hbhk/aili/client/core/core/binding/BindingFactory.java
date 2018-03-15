package org.hbhk.aili.client.core.core.binding;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hbhk.aili.client.core.commons.binding.IBindingArgsAware;
import org.hbhk.aili.client.core.commons.binding.validation.IAnnotationAdapter;
import org.hbhk.aili.client.core.commons.binding.validators.AbstractConvertValidator;
import org.hbhk.aili.client.core.commons.binding.validators.SimpleConvertValidator;
import org.hbhk.aili.client.core.commons.binding.validators.StringToIntConvertValidator;
import org.hbhk.aili.client.core.commons.binding.validators.StringToIntegerConvertValidator;
import org.hbhk.aili.client.core.commons.conversion.ConversionException;
import org.hbhk.aili.client.core.commons.conversion.Converter;
import org.hbhk.aili.client.core.commons.conversion.IConverter;
import org.hbhk.aili.client.core.commons.validation.BeanGlobalValidator;
import org.hbhk.aili.client.core.commons.validation.IConvertValidator;
import org.hbhk.aili.client.core.commons.validation.IValidationListener;
import org.hbhk.aili.client.core.commons.validation.IValidator;
import org.hbhk.aili.client.core.commons.validation.ValidationError;
import org.hbhk.aili.client.core.commons.validation.ValidationErrorEvent;
import org.hbhk.aili.client.core.commons.validators.CompositeValidator;
import org.hbhk.aili.client.core.commons.validators.IValueAcceptor;
import org.hbhk.aili.client.core.commons.validators.ValueAcceptedValidator;
import org.hbhk.aili.client.core.core.binding.annotation.Bind;
import org.hbhk.aili.client.core.core.binding.annotation.BindingArg;
import org.hbhk.aili.client.core.core.binding.annotation.BindingArgs;
import org.hbhk.aili.client.core.core.binding.utils.BindingUtils;
import org.hbhk.aili.client.core.core.binding.validation.BeanValidatorFactory;
import org.hbhk.aili.client.core.core.binding.validation.IBeanValidatorFactory;

import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.AbstractValueModel;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.Trigger;
import com.jgoodies.binding.value.ValueModel;

/**
 * 
 * :这是和DecoratedValueModel平行的接口，实际上用来辅助后者工作。实际效果是创建一个相应的后者，并且用后者去装饰ValueModel,
 * <li>绑定框架的核心功能都是由平行的几组实现去定义的，请仔细阅读这两个接口的所有实现。 <li>
 * 如果需要给绑定框架定制新的功能（如果注解和监听器都无法搞定
 * ），那么就需要实现平行的这两者：一个IValueModelDecorator，和一个相应的DecoratedValueModel
 */
public class BindingFactory {
	private static BindingFactory instance;
	private Map<TypePair, Class<? extends IConvertValidator>> convertValidators;
	private Map<Class<?>, IAnnotationAdapter<?>> validatorAnnotationAdapters;
	@SuppressWarnings("unused")
	private Map<Class<?>, List<Field>> containerFieldsCache;
	private Map<Component, String> componentProperty;

	private static final Log log = LogFactory.getLog(BindingFactory.class);

	private BindingFactory() {
		convertValidators = new HashMap<TypePair, Class<? extends IConvertValidator>>();
		validatorAnnotationAdapters = new HashMap<Class<?>, IAnnotationAdapter<?>>();
		containerFieldsCache = new HashMap<Class<?>, List<Field>>();
		componentProperty = new HashMap<Component, String>();
		registerConverters();
	}

	/**
	 * 注册默认的转换校验器 registerConverters void
	 * 
	 * @since:0.6
	 */
	private void registerConverters() {
		registerConverter(new TypePair(JTextField.class, Integer.class), StringToIntegerConvertValidator.class);
		registerConverter(new TypePair(JTextField.class, int.class), StringToIntConvertValidator.class);
		registerConverter(new TypePair(JTextArea.class, Integer.class), StringToIntegerConvertValidator.class);
		registerConverter(new TypePair(JTextArea.class, int.class), StringToIntConvertValidator.class);
		registerConverter(new TypePair(JPasswordField.class, Integer.class), StringToIntegerConvertValidator.class);
		registerConverter(new TypePair(JPasswordField.class, int.class), StringToIntConvertValidator.class);
		registerConverter(new TypePair(JFormattedTextField.class, Integer.class), StringToIntegerConvertValidator.class);
		registerConverter(new TypePair(JFormattedTextField.class, int.class), StringToIntConvertValidator.class);
	}

	/**
	 * 注册校验器的适配器。校验器的获取就是通过适配器的api方法返回的。
	 * 并且每一个适配器都会与一个校验器相关联，简单来说就是每个校验器都与某个注解关联，
	 * 因为这里适配器的作用就是生成一个校验器，在生成校验器的方法参数中，框架会把具体的注解独享传递给使用者。
	 * registerValidatorAnnotation
	 * 
	 * @param <T>
	 * @param annotation
	 * @param adapter
	 *            void
	 * @since:0.6
	 */
	public <T extends Annotation> void registerValidatorAnnotation(Class<T> annotation, IAnnotationAdapter<T> adapter) {
		validatorAnnotationAdapters.put(annotation, adapter);
	}

	/**
	 * 卸载某个指定注解的校验器 unregisterValidatorAnnotation
	 * 
	 * @param annotation
	 *            void
	 * @since:0.6
	 */
	public void unregisterValidatorAnnotation(Class<? extends Annotation> annotation) {
		validatorAnnotationAdapters.remove(annotation);
	}

	/**
	 * 静态方法获取绑定工厂实例，这里运用双重检查机制。 保证了多线程访问的时候对象创建的唯一性。 getIntsance
	 * 
	 * @return BindingFactory
	 * @since:0.6
	 */
	public static BindingFactory getIntsance() {
		if (instance == null) {
			synchronized (log) {
				if (instance == null) {
					instance = new BindingFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * 注册框架中默认的转换器，折现默认数据转换器包括 JTextField <-> Integer JTextField <-> int
	 * JTextArea <-> Integer JTextArea <-> int JPasswordField <-> Integer
	 * JPasswordField <-> int JFormattedTextField <-> Integer
	 * JFormattedTextField <-> int
	 * 
	 * registerConverter
	 * 
	 * @param <K>
	 * @param <V>
	 * @param typePair
	 *            TypePair对象封装了两个相互转换的类类型
	 * @param convertValidatorClass
	 *            void 一个转换校验器。其实从某种角度来说转换失败也是一种校验失败， 只是这种校验规则是一种固定校验规则，就是转换异常。
	 *            简单来说它就是一个类型转换的校验器。
	 * @since:0.6
	 */
	public <K, V> void registerConverter(TypePair typePair, Class<? extends IConvertValidator> convertValidatorClass) {
		convertValidators.put(typePair, convertValidatorClass);
	}

	/**
	 * 卸载转换校验器 unregisterConverter
	 * 
	 * @param typePair
	 *            void
	 * @since:0.6
	 */
	public void unregisterConverter(TypePair typePair) {
		convertValidators.remove(typePair);
	}

	/**
	 * 采用立即绑定策略绑定。 指定绑定bean类型；绑定的组件集合和绑定参数集合，它们用来共同去指定一个绑定目标集合， 两个集合应该有相同的size。
	 * 就目前而言，绑定参数是字符串，代表对应的组件所绑定到的bean的属性名。
	 * 由于直接指定组件而不是从一个UI容器上扫描需要绑定的组件集合，所有绑定目标的绑定规则都为空，
	 * 此时，将通过默认的规则进行绑定。因为没有指定bindingDescriptor，因此bindingDescriptor为null
	 * 
	 * @param <T>
	 * @param beanType
	 * @param components
	 * @param bindingArgs
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> violentBind(Class<T> beanType, JComponent[] components, Object[] bindingArgs) {
		return violentBind(beanType, components, bindingArgs, null);
	}

	/**
	 * 和violentBind(Class<T> beanType,JComponent[] components, Object[]
	 * bindingArgs)方法相同， 但指定了一个bindingDescriptor。 violentBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param components
	 * @param bindingArgs
	 * @param bindingDescriptor
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> violentBind(Class<T> beanType, JComponent[] components, Object[] bindingArgs, Object bindingDescriptor) {
		return violentBind(beanType, BindingUtils.createAssociations(components, bindingArgs), bindingDescriptor);
	}

	/**
	 * 采用立即绑定策略绑定。指定绑定bean类型；
	 * 这个方法从Container上扫描绑定目标和收集绑定规则。扫描的方法是，查找所有component类型的字段，
	 * 如果上面有Bind注解，则作为为一个绑定目标， Bind注解的value属性就是绑定的bean属性名，
	 * 而绑定规则为字段上的注解集合；若没有Bind注解，那么当此字段为一Container时，
	 * 继续在此子容器上扫描绑定目标。指定bindingDescriptor。 violentBind
	 * 
	 * @param <T>
	 * @param beanType
	 *            JavaBean类类型
	 * @param root
	 *            需要搜索的顶层面板容器。
	 * @param bindingDescriptor
	 * @return IBinder<T> 返回一个IBinder对象可以操作
	 * @since:0.6
	 */
	public <T> IBinder<T> violentBind(Class<T> beanType, Container root, Object bindingDescriptor) {
		T bean = createBean(beanType);

		Map<BindingAssociation, Annotation[]> bindingRules = createBindingRules(bean, root);
		return bind(bean, bindingRules, bindingDescriptor, new ViolentBindingStrategyFactory<T>(bean, bindingRules, bindingDescriptor), false);
	}

	/**
	 * 创建Bean对象 createBean
	 * 
	 * @param <T>
	 * @param beanType
	 * @return T
	 * @since:0.6
	 */
	private <T> T createBean(Class<T> beanType) {
		try {
			return beanType.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Binding bean type must have a null constructor.");
		}
	}

	/**
	 * 创建绑定规则，首先是扫描顶层容器中所有的需要绑定控件对象。 然后这层扫描顶层容器中所有子容器中需要绑定的控件对象。
	 * 最后把这些控件与与之绑定JavaBean的属性包转到一个BindingAssociation对象中。
	 * 这个BindingAssociation对象是绑定的关键点，把它作为key，
	 * component属性上的标注作为值存放到一个map对象中就组成了绑定规则。 createBindingRules
	 * 
	 * @param bean
	 *            需要绑定的JavaBean对象
	 * @param root
	 *            与JavaBean对象有绑定关系的顶层面板
	 * @return Map<BindingAssociation,Annotation[]>
	 * @since:0.6
	 */
	private Map<BindingAssociation, Annotation[]> createBindingRules(Object bean, Container root) {
		BindingScanner scanner = new BindingScanner(root);
		scanner.scan();
		Map<BindingAssociation, Annotation[]> bindingRules = scanner.getBindingRules();

		return bindingRules;
	}

	/**
	 * 
	 ******************************************* 
	 * <b style="font-family:微软雅黑"> <small>Description: 一个控件扫描类，用来扫描当前容器中需要绑定的控件
	 * </small> </b></br> <b
	 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
	 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
	 ******************************************** 
	 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-3-24 rogger 新增 </div>
	 ******************************************** 
	 */
	private class BindingScanner {
		private Container root;
		private Map<BindingAssociation, Annotation[]> bindingRules;

		public BindingScanner(Container root) {
			this.root = root;
			bindingRules = new HashMap<BindingAssociation, Annotation[]>();
		}

		/**
		 * 扫描root容器，首先获取root容器包含的属性。这些属性可能打了需要绑定属性的标注。 接下来就是逐层扫描root中包括的控件或者容器。
		 * 
		 * @since:0.6
		 */
		public void scan() {
			Map<Component, Field> componentAndFields = new HashMap<Component, Field>();

			componentAndFields.putAll(scanComponentField(root));

			buildBindingRules(componentAndFields);

		}

		/**
		 * 获取Component及子Component中所有包含了Bind标注的字段，并且获取字段实际对象。 其中 if
		 * (field.getType() == JLabel.class) continue;
		 * 表示忽略JLabel,因为一般情况下不需要绑定JLabel。
		 * 如果，传入的component是Container的话还是需要扫描Container容器里其他组件。 scanComponentField
		 * 
		 * @param component
		 *            需要扫描的控件
		 * @return Map<Component,Field> 返回Component容器中所有打了Bind标注的字段，并且存放到Map里返回
		 * @since:0.6
		 */
		private Map<Component, Field> scanComponentField(Component component) {
			
			Map<Component, Field> componentAndFields = new HashMap<Component, Field>();

			Field[] fields = component.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (Component.class.isAssignableFrom(field.getType())) {
					if (field.getType() == JLabel.class)
						continue;

					Bind bind = field.getAnnotation(Bind.class);
					if (bind == null) {
						continue;
					}
					Component componentField = null;
					boolean oldAccessible = field.isAccessible();
					field.setAccessible(true);
					try {
						componentField = (Component) field.get(component);
						if (componentField != null && bind.component() != null && !"".equals(bind.component())) {
							String fieldName = bind.component();

							Field innerField = componentField.getClass().getDeclaredField(fieldName);
							boolean innerFieldOldAccess = innerField.isAccessible();
							try {
								innerField.setAccessible(true);
								Object innerComponent = innerField.get(componentField);

								if (innerComponent instanceof Component) {
									componentAndFields.put((Component) innerComponent, field);
								}
							} finally {
								innerField.setAccessible(innerFieldOldAccess);
							}

						} else if (componentField != null && bind.property() != null && !"".equals(bind.property())) {
							componentProperty.put(componentField, bind.property());
							componentAndFields.put(componentField, field);
						} else {
							componentAndFields.put(componentField, field);
						}

					} catch (Exception e) {
						if (log.isWarnEnabled()) {
							log.warn("Container scanner can't get field value.", e);
						}
					} finally {
						field.setAccessible(oldAccessible);
					}
				}
			}
			if (component instanceof Container) {
				for (Component componentChild : ((Container) component).getComponents()) {
					componentAndFields.putAll(scanComponentField(componentChild));
				}
			}

			return componentAndFields;
		}

		/**
		 * 创建绑定规则，把需要绑定的控件与需要绑定的字段名称关联起来。
		 * 首先是把需要绑定的Bean对象的属性名字封装成为IBindingArgWrapper对象。
		 * 然后把控件component及IBindingArgWrapper对象封装为BindingAssociation对象。
		 * 最后，把BindingAssociation作为key，把打在component上的所有标注座位值保存到map里面
		 * buildBindingRules
		 * 
		 * @param componentAndFields
		 *            void
		 * @since:0.6
		 */
		private void buildBindingRules(Map<Component, Field> componentAndFields) {
			Set<Component> components = componentAndFields.keySet();
			for (Component component : components) {
				//System.out.println("~~~~~~~~~~~~~~~"+component.getParent().getClass().getName());
				Field field = componentAndFields.get(component);
				if (field != null) {
					Bind bind = field.getAnnotation(Bind.class);

					Annotation[] annotations = field.getAnnotations();

					String sBindingArg = bind.value();
					Object bindingArg = null;
					if (component instanceof JRadioButton) {
						String[] args = sBindingArg.split("#");
						if (args.length != 2) {
							throw new IllegalArgumentException("Illegal radio button binding argument. The format must be property_name#choice.");
						}
						bindingArg = new RadioButtonBindingArg(args[0], args[1]);
					} else {
						bindingArg = sBindingArg;
					}

					BindingAssociation association = new BindingAssociation(component, BindingUtils.createBindingArgWrapper(bindingArg));
					bindingRules.put(association, annotations);
				}
			}

		}

		/**
		 * 
		 * getBindingRules
		 * 
		 * @return Map<BindingAssociation,Annotation[]> 以map对象形式返回绑定规则
		 * @since:0.6
		 */
		public Map<BindingAssociation, Annotation[]> getBindingRules() {
			return bindingRules;
		}
	}

	/**
	 * 向外提供的立即绑定策略方法，没有指定绑定校验描述类。 此方法只是简单的调用了后续的绑定方法 violentBind
	 * 
	 * @param <T>
	 * @param beanType
	 *            需要绑定的JavaBean对象的类类型
	 * @param root
	 *            需要搜索绑定控件的顶层容器
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> violentBind(Class<T> beanType, Container root) {
		return violentBind(beanType, root, null);
	}

	/**
	 * 向外提供的立即绑定策略方法，指定了绑定校验描述类。 此方法除了调用真正的绑定方法外还做了绑定控件扫描工作。
	 * 这种类型的绑定是没有提供注解形式的校验规则的。 violentBind
	 * 
	 * @param <T>
	 * @param beanType
	 *            需要绑定的JavaBean对象的类类型
	 * @param root
	 *            需要搜索绑定控件的顶层容器
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> violentBind(Class<T> beanType, List<BindingAssociation> associations, Object bindingDescriptor) {
		Map<BindingAssociation, Annotation[]> bindingRules = createBindingRules(associations);
		T bean = createBean(beanType);

		return bind(bean, bindingRules, bindingDescriptor, new ViolentBindingStrategyFactory<T>(bean, bindingRules, bindingDescriptor), false);
	}

	/**
	 * 创建绑定规则 此方法只是针对<T> IBinder<T> violentBind(Class<T> beanType,JComponent[]
	 * components, Object[] bindingArgs,Object bindingDescriptor)
	 * 方法有用，因为这种类型的绑定传入的是控件对象无法获取此对象在容器中定义时候的所持有的规则校验注解。 createBindingRules
	 * 
	 * @param associations
	 * @return Map<BindingAssociation,Annotation[]>
	 * @since:0.6
	 */
	private Map<BindingAssociation, Annotation[]> createBindingRules(List<BindingAssociation> associations) {
		Map<BindingAssociation, Annotation[]> associationRules = new HashMap<BindingAssociation, Annotation[]>();

		for (BindingAssociation association : associations) {
			associationRules.put(association, new Annotation[0]);
		}

		return associationRules;
	}

	/**
	 * 指定组件失去焦点时绑定的策略。参数的意思和violentBind方法完全一样 moderateBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param root
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> moderateBind(Class<T> beanType, Container root, boolean isViolentLostfocus) {
		return moderateBind(beanType, root, null, isViolentLostfocus);
	}

	/**
	 * 指定组件失去焦点时绑定的策略。参数的意思和violentBind方法完全一样。 moderateBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param components
	 * @param bindingArgs
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> moderateBind(Class<T> beanType, JComponent[] components, Object[] bindingArgs, boolean isViolentLostfocus) {
		return moderateBind(beanType, components, bindingArgs, null, isViolentLostfocus);
	}

	/**
	 * 指定组件失去焦点时绑定的策略。参数的意思和violentBind方法完全一样。
	 * 增加了一个bindingDescriptor对象，描述了额外的校验规则。 moderateBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param components
	 * @param bindingArgs
	 * @param bindingDescriptor
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> moderateBind(Class<T> beanType, JComponent[] components, Object[] bindingArgs, Object bindingDescriptor, boolean isViolentLostfocus) {
		return moderateBind(beanType, BindingUtils.createAssociations(components, bindingArgs), bindingDescriptor, isViolentLostfocus);
	}

	/**
	 * 指定组件失去焦点时绑定的策略。参数的意思和violentBind方法完全一样。
	 * 添加了一个bindingDescriptor对象表示额外的校验规则。 moderateBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param root
	 * @param bindingDescriptor
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> moderateBind(Class<T> beanType, Container root, Object bindingDescriptor, boolean isViolentLostfocus) {
		T bean = createBean(beanType);

		Map<BindingAssociation, Annotation[]> bindingRules = createBindingRules(bean, root);
		return bind(bean, bindingRules, bindingDescriptor, new ModerateBindingStrategyFactory<T>(bean, bindingRules, bindingDescriptor), isViolentLostfocus);
	}

	/**
	 * 指定组件失去焦点时绑定的策略。参数的意思和violentBind方法完全一样。
	 * 这种方式的绑定，已经提供了绑定控件对象，无法从控件字段定义中获取校验规则标注。 moderateBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param associations
	 * @param bindingDescriptor
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> moderateBind(Class<T> beanType, List<BindingAssociation> associations, Object bindingDescriptor, boolean isViolentLostfocus) {
		Map<BindingAssociation, Annotation[]> bindingRules = createBindingRules(associations);

		T bean = createBean(beanType);
		return bind(bean, bindingRules, bindingDescriptor, new ModerateBindingStrategyFactory<T>(bean, bindingRules, bindingDescriptor), isViolentLostfocus);
	}

	/**
	 * 指定延迟绑定的策略。 通过指定控件与JavaBean属性名字，来创建延迟绑定。
	 * 其中component数组与bindingArgs数组需要一一对应。 分别表示了控件与需要绑定的JavaBean对象的属性。
	 * bufferedBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param components
	 * @param bindingArgs
	 * @return IBufferedBinder<T>
	 * @since:0.6
	 */
	public <T> IBufferedBinder<T> bufferedBind(Class<T> beanType, JComponent[] components, Object[] bindingArgs) {
		return bufferedBind(beanType, components, bindingArgs, null);
	}

	/**
	 * 与方法<T> IBufferedBinder<T> bufferedBind(Class<T> beanType,JComponent[]
	 * components, Object[] bindingArgs) 一样，只是多了一个bindingDescriptor参数表示更多的校验规则。
	 * bufferedBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param components
	 * @param bindingArgs
	 * @param bindingDescriptor
	 * @return IBufferedBinder<T>
	 * @since:0.6
	 */
	public <T> IBufferedBinder<T> bufferedBind(Class<T> beanType, JComponent[] components, Object[] bindingArgs, Object bindingDescriptor) {
		return bufferedBind(beanType, BindingUtils.createAssociations(components, bindingArgs), bindingDescriptor);
	}

	/**
	 * 指定延迟绑定的策略。参数的意思和violentBind方法完全一样。
	 * 此种类型的绑定需要通过IBufferedBinder对象的flush方式来刷新绑定效果。 bufferedBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param root
	 * @return IBufferedBinder<T>
	 * @since:0.6
	 */
	public <T> IBufferedBinder<T> bufferedBind(Class<T> beanType, Container root) {
		return bufferedBind(beanType, root, null);
	}

	/**
	 * 指定延迟绑定的策略。参数的意思和violentBind方法完全一样。 此方法比IBufferedBinder<T>
	 * bufferedBind(Class<T> beanType, Container root)
	 * 多了一个bindingDescriptor对象，表示更多的校验规则。 bufferedBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param root
	 * @param bindingDescriptor
	 * @return IBufferedBinder<T>
	 * @since:0.6
	 */
	public <T> IBufferedBinder<T> bufferedBind(Class<T> beanType, Container root, Object bindingDescriptor) {
		T bean = createBean(beanType);

		Map<BindingAssociation, Annotation[]> bindingRules = createBindingRules(bean, root);
		return (IBufferedBinder<T>) bind(bean, bindingRules, bindingDescriptor, new BufferedBindingStrategyFactory<T>(bean, bindingRules, bindingDescriptor), false);
	}

	/**
	 * 
	 * bufferedBind
	 * 
	 * @param <T>
	 * @param beanType
	 * @param associations
	 * @param bindingDescriptor
	 * @return IBufferedBinder<T>
	 * @since:0.6
	 */
	public <T> IBufferedBinder<T> bufferedBind(Class<T> beanType, List<BindingAssociation> associations, Object bindingDescriptor) {
		Map<BindingAssociation, Annotation[]> bindingRules = createBindingRules(associations);

		T bean = createBean(beanType);
		return (IBufferedBinder<T>) bind(bean, bindingRules, bindingDescriptor, new BufferedBindingStrategyFactory<T>(bean, bindingRules, bindingDescriptor), false);
	}

	/**
	 * 
	 ******************************************* 
	 * <b style="font-family:微软雅黑"> <small>Description: 一个 </small> </b></br> <b
	 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
	 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
	 ******************************************** 
	 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-3-24 rogger 新增 </div>
	 ******************************************** 
	 */
	private class BidirectionalBindingSupportInterceptor implements MethodInterceptor {
		private Class<?> beanClass;
		private Map<Method, BidirectionalBindingValueModel<?>> valueModels;
		private Map<Method, Method> readMethods;

		public BidirectionalBindingSupportInterceptor(Object bean) {
			beanClass = bean.getClass();
			valueModels = new HashMap<Method, BidirectionalBindingValueModel<?>>();
			readMethods = new HashMap<Method, Method>();
		}

		public void addValueModel(String propertyName, BidirectionalBindingValueModel<?> valueModel) {
			try {
				PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, beanClass);
				Method writeMethod = descriptor.getWriteMethod();
				Method readMethod = descriptor.getReadMethod();

				valueModels.put(writeMethod, valueModel);
				readMethods.put(writeMethod, readMethod);
			} catch (IntrospectionException e) {
				return;
			}
		}

		// 为了找到与空间真正绑定的valueModel，可以完成从bean到控件的值变化
		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			BidirectionalBindingValueModel<?> valueModel = valueModels.get(method);
			Method readMethod = readMethods.get(method);

			Object oldValue = null;
			Object newValue = null;

			if (valueModel != null) {
				oldValue = readMethod.invoke(obj, new Object[0]);
			}

			Object ret = proxy.invokeSuper(obj, args);

			if (valueModel != null) {
				newValue = readMethod.invoke(obj, new Object[0]);
				
				
				//BigDecimal精度不一致时，PropertyChangeEvent事件重复触发  ;
				//例：vo中原值为125,重新设置为125.0,BindingListener不停工作 
				if (newValue != null && newValue instanceof BigDecimal) {
					
					if(oldValue != null && oldValue instanceof BigDecimal){
						BigDecimal newBg =(BigDecimal) newValue;
						BigDecimal oldBg =(BigDecimal) oldValue;
						if(newBg.doubleValue() == oldBg.doubleValue()){
							return ret;
						}
					}
					
				}

				
				if ((oldValue != newValue) || (oldValue != null && !oldValue.equals(newValue))) {
					
						
					
					valueModel.getLastValueModel().fireValueChange(oldValue, newValue);
				}
			}

			return ret;
		}
	}

	/**
	 * 绑定bean，所有其它绑定方法都只是简单的调用这个方法。
	 * bindingRules：绑定规则和目标已经在bindingRules中定义、每个绑定规则指定了一个绑定目标，
	 * 即绑定的组件，绑定bean的属性名，以及一些其它附加信息（如果存在的话）， 和与这个目标相关联的规则，即一个注解的数组。
	 * 实现绑定步骤，需要通过CGLIB的API创建一个Bean类型对象的代理对象。
	 * 接下来绑定过程中会判断绑定参数是否是RadioButtonBindingArg对象或者String，
	 * 如果两者皆不是将会IllegalArgumentException异常。如代码中所示，此绑定中只能支持如下Swing控件
	 * JTextField.class
	 * 、JPasswordField、JFormattedTextField、JTextArea、JList、JComboBox
	 * 、JColorChooser
	 * 
	 * bind
	 * 
	 * @param <T>
	 * @param bean
	 *            需要绑定JavaBean对象
	 * @param bindingRules
	 *            绑定规则，关联了BindingAssociation与Annotation数组
	 * @param bindingDescriptor
	 *            一个关于JavaBean对象的校验类对象。 用来指定更多绑定规则，可以为null。
	 *            如果bindingDescriptor不为null，绑定过程会去扫描bindingDescriptor对象类型上的方法，
	 *            以获得更多的绑定规则，这个机制提供更多的便利性，而某些绑定规则（例如后绑定），
	 *            甚至只能通过bindingDescriptor来指定。
	 * @param factory
	 *            绑定策略工厂，用来创建具体的绑定策略。
	 * @return IBinder<T>
	 * @since:0.6
	 */
	public <T> IBinder<T> bind(T bean, Map<BindingAssociation, Annotation[]> bindingRules, 
			Object bindingDescriptor, BindingStrategyFactory<T> factory, boolean isViolentLostfocus) {
		BidirectionalBindingSupportInterceptor bidirectionalBindingSupportInterceptor = new BidirectionalBindingSupportInterceptor(bean);
		try {
			bean = createBidirectionalBindingProxy(bean, bidirectionalBindingSupportInterceptor);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add bidirectional binding support.", e);
		}

		BeanAdapter<T> beanAdapter = new BeanAdapter<T>(bean);

		for (BindingAssociation association : bindingRules.keySet()) {
			Component component = association.getComponent();
			  
			  
			if (component instanceof JRadioButton && !(association.getArgWrapper().getArg() instanceof RadioButtonBindingArg)) {
				throw new IllegalArgumentException("Binding argument of javax.swing.JRadioButton must be com.deppon.foss.client.framework.binding.RadioButtonBindingArg");
			} else if (!(component instanceof JRadioButton) && !(association.getArgWrapper().getArg() instanceof String)) {
				throw new IllegalArgumentException(String.format("Binding argument of %s must be java.lang.String", component.getClass().getName()));
			}

			IBindingStrategy<T> strategy = factory.createBindingStrategy(association);
			strategy.setBean(bean);
			
			ValueModel valueModel = beanAdapter.getValueModel(association.getArgWrapper().getPropertyName());

			valueModel = strategy.getDecorator().decorate(valueModel);

			valueModel = 
				addBidirectionalBindingSupport(component, bean, association.getArgWrapper().getPropertyName(), 
						valueModel, bidirectionalBindingSupportInterceptor);
			
			strategy.bindToComponent(valueModel, isViolentLostfocus);
		}

		if (bindingDescriptor != null) {
			Method[] methods = bindingDescriptor.getClass().getDeclaredMethods();
			for (Method method : methods) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof BeanGlobalValidator) {
						if (method.getReturnType() != String.class && method.getReturnType() != List.class) {
							continue;
						}

						Class<?>[] paramTypes = method.getParameterTypes();
						if (paramTypes.length != 1 || !paramTypes[0].isAssignableFrom(bean.getClass())) {
							throw new IllegalArgumentException(String.format("The bean global validator method %s must accept a parameter %s.", method, bean.getClass().getName()));
						}

						IValidator beanGlobalValidator = new BeanGlobalValidatorImpl(method, bean, bindingDescriptor);
						factory.getBinder().getBeanValidatorFactory().addGlobalValidator(beanGlobalValidator);
					}
				}
			}
		}

		return factory.getBinder();
	}

	/**
	 * 创建bean的proxy，使proxy属性改变时能通知ValueModel。
	 * 
	 * @param <T>
	 * @param bean
	 * @param bidirectionalBindingSupportInterceptor
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private <T> T createBidirectionalBindingProxy(T bean, BidirectionalBindingSupportInterceptor bidirectionalBindingSupportInterceptor) throws Exception {
		Enhancer e = new Enhancer();
		e.setSuperclass(bean.getClass());
		e.setCallback(bidirectionalBindingSupportInterceptor);

		return (T) e.create();
	}

	/**
	 * 装饰ValueModel，加上反向绑定的能力。
	 * 
	 * @param <T>
	 * @param component
	 * @param bean
	 * @param propertyName
	 * @param valueModel
	 * @param handler
	 * @return
	 */
	private <T> ValueModel addBidirectionalBindingSupport(Component component, T bean, String propertyName, ValueModel valueModel, BidirectionalBindingSupportInterceptor handler) {
		BidirectionalBindingValueModel<T> bidirectionalBindingValueModel = new BidirectionalBindingValueModel<T>(valueModel);
		handler.addValueModel(propertyName, bidirectionalBindingValueModel);

		return bidirectionalBindingValueModel;
	}

	@SuppressWarnings("serial")
	private class BidirectionalBindingValueModel<T> extends DecoratedValueModel<T> {
		private AbstractValueModel last;

		public BidirectionalBindingValueModel(ValueModel next) {
			super(next);
		}

		@Override
		public Object getValue() {
			return next.getValue();
		}

		@Override
		public void setValue(Object newValue) {
			next.setValue(newValue);
		}

		public AbstractValueModel getLastValueModel() {
			if (last == null) {
				last = getLastValueModel(this);
			}

			return last;
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			super.propertyChange(evt);
		}

		private AbstractValueModel getLastValueModel(ValueModel valueModel) {
			ValueModel next = null;

			if (valueModel instanceof BufferedValueModel) {
				next = ((BufferedValueModel) valueModel).getSubject();
			} else if (valueModel instanceof DecoratedValueModel) {
				next = ((DecoratedValueModel<?>) valueModel).getNext();
			}

			if (next == null)
				return null;

			if (next instanceof BeanAdapter.SimplePropertyAdapter) {
				return (AbstractValueModel) next;
			}

			return getLastValueModel(next);
		}
	}

	private class BeanGlobalValidatorImpl extends ValueAcceptedValidator {
		private Object bean;
		private Method method;
		private Object bindingDescriptor;

		public BeanGlobalValidatorImpl(Method method, Object bean, Object bindingDescriptor) {
			this.method = method;
			this.bean = bean;
			this.bindingDescriptor = bindingDescriptor;
		}

		@Override
		public List<ValidationError> validate() {
			Object result = null;
			try {
				result = method.invoke(bindingDescriptor, new Object[] { bean });
			} catch (Exception e) {
				throw new RuntimeException("Can't call bean global validator method " + method, e);
			}

			List<ValidationError> errors = new ArrayList<ValidationError>();
			if (result == null) {
				return errors;
			}

			if (result instanceof String) {
				errors.add(new ValidationError((String) result));
			} else {
				List<?> errorsList = (List<?>) result;
				for (Object message : errorsList) {
					if (message instanceof String) {
						errors.add(new ValidationError((String) message));
					} else {
						errors.add(new ValidationError(message.toString()));
					}
				}
			}

			return errors;
		}

	}

	@SuppressWarnings("unused")
	private boolean isComponent(Component component, Class<? extends JComponent> type) {
		return type.isAssignableFrom(component.getClass());
	}

	/**
	 * 
	 ******************************************* 
	 * <b style="font-family:微软雅黑"> <small>Description:
	 * 绑定策略工厂抽象类，主要是用来创建IBinder对象。 </small> </b></br> <b
	 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
	 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
	 ******************************************** 
	 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-3-24 rogger 新增 </div>
	 ******************************************** 
	 */
	private abstract class BindingStrategyFactory<T> {
		protected T bean;
		protected Object bindingDescriptor;
		protected Binder<T> binder;
		protected Map<BindingAssociation, Annotation[]> bindingRules;

		public BindingStrategyFactory(T bean, Map<BindingAssociation, Annotation[]> bindingRules, Object bindingDescriptor) {
			this.bean = bean;
			this.bindingDescriptor = bindingDescriptor;
			this.bindingRules = bindingRules;
			binder = createBinder();
		}

		public Binder<T> getBinder() {
			return binder;
		}

		protected abstract Binder<T> createBinder();

		public abstract IBindingStrategy<T> createBindingStrategy(BindingAssociation association);
	}

	private class ViolentBindingStrategyFactory<T> extends BindingStrategyFactory<T> {
		public ViolentBindingStrategyFactory(T bean, Map<BindingAssociation, Annotation[]> bindingRules, Object bindingDescriptor) {
			super(bean, bindingRules, bindingDescriptor);
		}

		@Override
		public IBindingStrategy<T> createBindingStrategy(BindingAssociation association) {
			return new ViolentBindingStrategy<T>(association, bindingRules.get(association), bindingDescriptor, binder);
		}

		@Override
		public Binder<T> createBinder() {
			return new Binder<T>();
		}
	}

	private class ModerateBindingStrategyFactory<T> extends ViolentBindingStrategyFactory<T> {
		public ModerateBindingStrategyFactory(T bean, Map<BindingAssociation, Annotation[]> bindingRules, Object bindingDescriptor) {
			super(bean, bindingRules, bindingDescriptor);
		}

		@Override
		public IBindingStrategy<T> createBindingStrategy(BindingAssociation association) {
			return new ModerateBindingStragey<T>(bean, association, bindingRules.get(association), bindingDescriptor, binder);
		}
	}

	private class BufferedBindingStrategyFactory<T> extends BindingStrategyFactory<T> {
		public BufferedBindingStrategyFactory(T bean, Map<BindingAssociation, Annotation[]> bindingRules, Object bindingDescriptor) {
			super(bean, bindingRules, bindingDescriptor);
		}

		@Override
		public IBindingStrategy<T> createBindingStrategy(BindingAssociation association) {
			return new BufferedBindingStragey<T>(association, bindingRules.get(association), bindingDescriptor, binder);
		}

		@Override
		public Binder<T> createBinder() {
			return new BufferedBinder<T>();
		}
	}

	private interface IBindingStrategy<T> {
		IValueModelDecorator getDecorator();

		void setBean(T bean);

		void bindToComponent(ValueModel valueModel, boolean isViolentLostfocus);
	}

	private class BufferedBindingStragey<T> extends ViolentBindingStrategy<T> {
		public BufferedBindingStragey(BindingAssociation association, Annotation[] componentAnnotations, Object bindingDescriptor, Binder<T> binder) {
			super(association, componentAnnotations, bindingDescriptor, binder);
		}

		@Override
		protected boolean isCommitOnFocusLost() {
			return true;
		}


		
		
		@Override
		public IValueModelDecorator getDecorator() {
			IValueModelDecorator decorator = new ConverterAdapter<T>(binder, bean, association, args, getConverter(bean, association.getArgWrapper().getPropertyName(), componentAnnotations, bindingDescriptor), new ValidationDecorator<T>(bean, association, componentAnnotations, bindingDescriptor, args, (Binder<T>) binder, new BufferedDecorator<T>(((BufferedBinder<T>) binder).getTrigger(), new AfterBindedValueModelDecoraor<T>(bean, association, bindingDescriptor, new PropertyChangeSupportDecorator<T>(bean, association.getArgWrapper().getPropertyName(), (Binder<T>) binder, null)))));

			return decorator;
		}
	}

	/**
	 * 缓冲绑定的绑定器。
	 * 
	 * @param <T>
	 */
	private class BufferedBinder<T> extends Binder<T> implements IBufferedBinder<T> {
		private Trigger trigger;

		public BufferedBinder() {
			trigger = new Trigger();
		}

		@Override
		public void flush() {
			trigger.triggerCommit();
		}

		@Override
		public void reset() {
			trigger.triggerFlush();
		}

		public Trigger getTrigger() {
			return trigger;
		}

	}

	private class BufferedDecorator<T> extends AbstractValueModelDecorator<T> {
		public Trigger trigger;

		public BufferedDecorator(Trigger trigger, IValueModelDecorator next) {
			super(next);
			this.trigger = trigger;
		}

		@Override
		public ValueModel doDecorate(ValueModel valueModel) {
			return new BufferedValueModel(valueModel, trigger);
		}

	}

	private class ModerateBindingStragey<T> extends ViolentBindingStrategy<T> {
		public ModerateBindingStragey(T bean, BindingAssociation association, Annotation[] componentAnnotations, Object bindingDescriptor, Binder<T> binder) {
			super(association, componentAnnotations, bindingDescriptor, binder);
		}

		@Override
		protected boolean isCommitOnFocusLost() {
			return true;
		}
	}

	/**
	 * 获取转换器注解，此注解可以放在控件字段上或者 Descriptor类的字段上或者打在方法上 getConverter
	 * 
	 * @param bean
	 * @param propertyName
	 * @param annotations
	 * @param bindingDescriptor
	 * @return Converter
	 * @since:0.6
	 */
	public Converter getConverter(Object bean, String propertyName, Annotation[] annotations, Object bindingDescriptor) {
		for (Annotation annotation : annotations) {
			if (annotation instanceof Converter) {
				return (Converter) annotation;
			}
		}

		if (bindingDescriptor == null)
			return null;

		try {
			Field field = bindingDescriptor.getClass().getDeclaredField(propertyName);
			if (field != null) {
				Converter converter = field.getAnnotation(Converter.class);
				if (converter != null)
					return converter;
			}
		} catch (Exception e) {
			// ignore
		}

		List<Method> validateMethods = getBindingMethods("validate", bean, propertyName, bindingDescriptor);
		for (Method method : validateMethods) {
			Converter converter = method.getAnnotation(Converter.class);
			if (converter != null)
				return converter;
		}

		return null;
	}

	private String getBindingMethodName(String prefix, String propertyName) {
		String bindingMethodName = prefix + Character.toUpperCase(propertyName.charAt(0)) + propertyName.substring(1);
		return bindingMethodName;
	}

	private List<Method> getBindingMethods(String prefix, Object bean, String propertyName, Object bindingDescriptor) {
		if (bindingDescriptor == null)
			return new ArrayList<Method>(0);

		Method[] methods = bindingDescriptor.getClass().getDeclaredMethods();
		List<Method> bindingMethods = new ArrayList<Method>();

		String bindingMethodName = getBindingMethodName(prefix, propertyName);
		for (Method method : methods) {
			if (method.getName().equals(bindingMethodName)) {
				bindingMethods.add(method);
			}
		}

		return bindingMethods;
	}

	private Class<?> getValueParamType(Object bean, String propertyName) {
		try {
			PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, bean.getClass());
			return propertyDescriptor.getPropertyType();
		} catch (IntrospectionException e) {
			return null;
		}
	}

	private Map<String, String> getArgs(Object bean, String propertyName, Annotation[] componentAnnotations, Object bindingDescriptor) {
		for (Annotation annotation : componentAnnotations) {
			if (annotation instanceof BindingArgs) {
				return getArgs((BindingArgs) annotation);
			}
		}

		if (bindingDescriptor == null)
			return new HashMap<String, String>();

		try {
			Field propertyField = bindingDescriptor.getClass().getDeclaredField(propertyName);
			return getArgs(propertyField.getAnnotation(BindingArgs.class));
		} catch (Exception e) {
			// ignore
		}

		List<Method> validateMethods = getBindingMethods("validate", bean, propertyName, bindingDescriptor);
		for (Method validateMethod : validateMethods) {
			BindingArgs args = validateMethod.getAnnotation(BindingArgs.class);
			if (args != null) {
				return getArgs(args);
			}
		}

		return new HashMap<String, String>();
	}

	private Map<String, String> getArgs(BindingArgs args) {
		Map<String, String> argsMap = new HashMap<String, String>();
		if (args == null)
			return argsMap;

		for (BindingArg arg : args.value()) {
			argsMap.put(arg.name(), arg.value());
		}

		return argsMap;
	}

	private class ViolentBindingStrategy<T> implements IBindingStrategy<T> {
		protected T bean;
		protected BindingAssociation association;
		protected Object bindingDescriptor;
		protected Binder<T> binder;
		protected Annotation[] componentAnnotations;
		protected Map<String, String> args;

		public ViolentBindingStrategy(BindingAssociation association, Annotation[] componentAnnotations, Object bindingDescriptor, Binder<T> binder) {
			this.bindingDescriptor = bindingDescriptor;
			this.binder = binder;
			this.association = association;
			this.componentAnnotations = componentAnnotations;
			args = getArgs(bean, association.getArgWrapper().getPropertyName(), componentAnnotations, bindingDescriptor);
		}

		@Override
		public void setBean(T bean) {
			this.bean = bean;
			if (binder != null) {
				binder.setBean(bean);
			}
		}

		@Override
		public IValueModelDecorator getDecorator() {
			IValueModelDecorator decorator = new ConverterAdapter<T>(binder, bean, association, args, getConverter(bean, association.getArgWrapper().getPropertyName(), componentAnnotations, bindingDescriptor), new ValidationDecorator<T>(bean, association, componentAnnotations, bindingDescriptor, args, (Binder<T>) binder, new AfterBindedValueModelDecoraor<T>(bean, association, bindingDescriptor, new PropertyChangeSupportDecorator<T>(bean, association.getArgWrapper().getPropertyName(), (Binder<T>) binder, null))));

			return decorator;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void bindToComponent(final ValueModel valueModel, boolean isViolentLostfocus) {
			Component component = association.getComponent();

			if (component instanceof JTextField) {

				if (isCommitOnFocusLost()) {

					if (isViolentLostfocus) {

						Bindings.bind((JTextField) component, valueModel, isCommitOnFocusLost());
						/**
						 * @处理在没有insertDocument的情况，焦点失去也会触发校验
						 * @修改人：linws
						 * @时间：2012-05-09
						 */
						new FocusLostHandle((JTextField) component, valueModel);

					} else {
						Bindings.bind((JTextField) component, valueModel, isCommitOnFocusLost());
					}

				} else {

					Bindings.bind((JTextField) component, valueModel);
				}
			} else if (component instanceof JCheckBox) {
				Bindings.bind((JCheckBox) component, valueModel);
			} else if (component instanceof JColorChooser) {
				Bindings.bind((JColorChooser) component, valueModel);
			} else if (component instanceof JComboBox) {
				Bindings.bind((JComboBox) component, new SelectionInList(((JComboBox) component).getModel(), valueModel));
			} else if (component instanceof JFormattedTextField) {
				Bindings.bind((JFormattedTextField) component, valueModel, isCommitOnFocusLost());
			} else if (component instanceof JList) {
				Bindings.bind((JComboBox) component, new SelectionInList(((JList) component).getModel(), valueModel));
			} else if (component instanceof JRadioButton) {
				RadioButtonBindingArg arg = (RadioButtonBindingArg) association.getArgWrapper().getArg();
				Bindings.bind((JRadioButton) component, valueModel, arg.getChoice());
			} else if (component instanceof JTextArea) {
				Bindings.bind((JTextArea) component, valueModel, isCommitOnFocusLost());
			} else if (component instanceof JPasswordField) {
				Bindings.bind((JPasswordField) component, valueModel, isCommitOnFocusLost());
			} else if (component instanceof JComponent) {

				component.addPropertyChangeListener(componentProperty.get(component), new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {

						valueModel.setValue(evt.getNewValue());
					}
				});

			} else {
				throw new IllegalArgumentException(String.format("Unsupported binding component %s", component.getClass()));
			}
		}

		protected boolean isCommitOnFocusLost() {
			return false;
		}
	}

	private class AfterBindedValueModelDecoraor<T> extends AbstractValueModelDecorator<T> {
		private T bean;
		private String propertyName;
		private Object bindingDescriptor;

		public AfterBindedValueModelDecoraor(T bean, BindingAssociation association, Object bindingDescriptor, IValueModelDecorator next) {
			super(next);
			this.bean = bean;
			this.propertyName = association.getArgWrapper().getPropertyName();
			this.bindingDescriptor = bindingDescriptor;
		}

		@Override
		protected ValueModel doDecorate(ValueModel valueModel) {
			return new AfterBindedValueModel<T>(bean, propertyName, bindingDescriptor, valueModel);
		}

	}

	/**
	 * 
	 ******************************************* 
	 * <b style="font-family:微软雅黑"> <small>Description:
	 * 绑定后后的校验，只能通过bindingDescriptor来实现 </small> </b></br> <b
	 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
	 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
	 ******************************************** 
	 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-3-24 rogger 新增 </div>
	 ******************************************** 
	 */
	@SuppressWarnings("serial")
	private class AfterBindedValueModel<T> extends DecoratedValueModel<T> {
		private T bean;
		private String propertyName;
		private Object bindingDescriptor;

		public AfterBindedValueModel(T bean, String propertyName, Object bindingDescriptor, ValueModel next) {
			super(next);
			this.bean = bean;
			this.propertyName = propertyName;
			this.bindingDescriptor = bindingDescriptor;
		}

		@Override
		public Object getValue() {
			return next.getValue();
		}

		@Override
		public void setValue(Object newValue) {
			next.setValue(newValue);
		}

		/**
		 * 此功能只是触发bindingDescriptor类对象的以afterBinder开头的方法，
		 * 表示对某个JavaBean属性进行绑定后校验。
		 * 
		 * @see org.hbhk.aili.client.core.core.binding.BindingFactory.DecoratedValueModel#propertyChange(java.beans.PropertyChangeEvent)
		 *      propertyChange
		 * @param evt
		 * @since:
		 */
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			List<Method> afterBindedMethods = getBindingMethods("afterBinded", bean, propertyName, bindingDescriptor);
			for (Method method : afterBindedMethods) {
				if (!isBindingMethod(bean, propertyName, method)) {
					continue;
				}

				try {
					callBindingMethod(bean, evt.getNewValue(), bindingDescriptor, method);
				} catch (Exception e) {
					if (log.isWarnEnabled()) {
						log.warn(String.format("Can't invoke binded method %s", method), e);
					}
				}

			}

			//修改重复校验bug
			Object oldValue = evt.getOldValue();
			Object newValue = evt.getNewValue();
			
			if(oldValue != null && newValue != null 
				&& oldValue.equals(newValue)) {
				return;
			}
			
			
			//BigDecimal精度不一致时，PropertyChangeEvent事件重复触发  ;
			//例：vo中原值为125,重新设置为125.0,BindingListener不停工作 
			if (newValue != null && newValue instanceof BigDecimal) {
				
				if(oldValue != null && oldValue instanceof BigDecimal){
					BigDecimal newBg =(BigDecimal) newValue;
					BigDecimal oldBg =(BigDecimal) oldValue;
					if(newBg.doubleValue() == oldBg.doubleValue()){
						return;
					}
				}
				
			}
			
			super.propertyChange(evt);
		}

	}

	private boolean isBindingMethod(Object bean, String propertyName, Method bindingMethod) {
		Class<?>[] paramTypes = bindingMethod.getParameterTypes();
		if (paramTypes.length != 1 && paramTypes.length != 2)
			return false;

		Class<?> valueParamType = getValueParamType(bean, propertyName);
		if (valueParamType == null) {
			if (log.isWarnEnabled()) {
				log.warn(String.format("Can't get value parameter type. Property name is %s.%s", bean.getClass().getName(), propertyName));
			}
			return false;
		}
		Class<?> beanParamType = bean.getClass();

		// 第一个参数是bean的话就不是绑定方法而是全局校验方法
		if (paramTypes.length == 1 && !matchType(paramTypes[0], valueParamType) && paramTypes[0].isAssignableFrom(beanParamType)) {
			return false;
		}

		if (paramTypes.length == 2 && !matchType(paramTypes[0], valueParamType) && !paramTypes[1].isAssignableFrom(beanParamType)) {
			return false;
		}

		return true;
	}

	private class ValidationDecorator<T> extends AbstractValueModelDecorator<T> {
		private BindingAssociation association;
		private Object bindingDescriptor;
		private Binder<T> binder;
		private Annotation[] componentAnnotations;
		private Map<String, String> args;
		private Object bean;

		public ValidationDecorator(T bean, BindingAssociation association, Annotation[] componentAnnotations, Object bindingDescriptor, Map<String, String> args, Binder<T> binder, IValueModelDecorator next) {
			super(next);
			this.association = association;
			this.bindingDescriptor = bindingDescriptor;
			this.binder = binder;
			this.componentAnnotations = componentAnnotations;
			this.args = args;
			this.bean = bean;
		}

		@Override
		public ValueModel doDecorate(ValueModel valueModel) {
			/*
			 * if (bindingDescriptor == null) return valueModel;
			 */

			String propertyName = association.getArgWrapper().getPropertyName();
			CompositeValidator propertyValidator = new CompositeValidator();

			addAnnotationValidators(componentAnnotations, args, propertyValidator, association);

			if (bindingDescriptor != null) {

				Field validatorField = null;
				try {
					validatorField = bindingDescriptor.getClass().getDeclaredField(propertyName);
				} catch (Exception e) {
					// ignore
				}

				if (validatorField != null) {
					Annotation[] annotations = validatorField.getAnnotations();
					addAnnotationValidators(annotations, args, propertyValidator, association);
				}

				List<Method> validateMethods = getBindingMethods("validate", bean, propertyName, bindingDescriptor);
				if (validateMethods.size() != 0) {
					for (Method validateMethod : validateMethods) {
						Annotation[] annotations = validateMethod.getAnnotations();
						addAnnotationValidators(annotations, args, propertyValidator, association);

						if (!isBindingMethod(bean, propertyName, validateMethod)) {
							continue;
						}

						if (validateMethod.getReturnType() != String.class && validateMethod.getReturnType() != List.class) {
							continue;
						}

						MethodValidator validator = new MethodValidator(bean, bindingDescriptor, association);
						validator.setMethod(validateMethod);

						propertyValidator.addValidator(validator);
					}
				}
			}

			if (!propertyValidator.isNullValidator())
				binder.getBeanValidatorFactory().addPropertyValidator(propertyName, propertyValidator);

			//此处注册PropertyChange事件，调用SetValue方法
			return new ValidationValueModel<T>(binder, valueModel, propertyValidator);
		}

		//该方法是将componet中注册所有的adaptor
		@SuppressWarnings({ "unchecked" })
		private void addAnnotationValidators(Annotation[] annotations, Map<String, String> args, CompositeValidator propertyValidator, Object errorKey) {
			for (Annotation annotation : annotations) {
				Class<?> annotationType = annotation.annotationType();
				@SuppressWarnings("rawtypes")
				IAnnotationAdapter adapter = validatorAnnotationAdapters.get(annotationType);
				if (adapter == null) {
					continue;
				}

				if (adapter instanceof IBindingArgsAware) {
					((IBindingArgsAware) adapter).setArgs(args);
				}

				IValidator validator = adapter.adapt(annotation, errorKey);
				propertyValidator.addValidator(validator);
			}
		}
	}

	private boolean matchType(Class<?> type1, Class<?> type2) {
		if ((type1 == int.class && type2 == Integer.class) || (type1 == Integer.class && type2 == int.class)) {
			return true;
		} else if ((type1 == float.class && type2 == Float.class) || (type1 == Float.class && type2 == float.class)) {
			return true;
		} else if ((type1 == double.class && type2 == Double.class) || (type1 == Double.class && type2 == double.class)) {
			return true;
		} else {
			return type1 == type2;
		}
	}

	@SuppressWarnings("serial")
	private class ValidationValueModel<T> extends DecoratedValueModel<T> {
		private ValueAcceptedValidator validator;
		private Binder<T> binder;

		public ValidationValueModel(Binder<T> binder, ValueModel next, ValueAcceptedValidator validator) {
			super(next);
			this.validator = validator;
			this.binder = binder;
			//解决重复监听Bug
//			next.addValueChangeListener(this);
		}

		@Override
		public Object getValue() {
			return next.getValue();
		}

		@Override
		public void setValue(Object newValue) {
			validator.setValue(newValue);
			List<ValidationError> errors = null;
			
			//bufferedBinder需要在提交的时候检查 在onchange和focusLost的时候不检查
			if(!(binder instanceof BufferedBinder)){
				errors = validator.validate();
			}
			
			if (errors == null || errors.size() == 0) {
				next.setValue(newValue);
			} else {
				binder.notifyValidationErrors(errors);
				for(ValidationError vaildError : errors) {
					if(vaildError.getMessage().equals("success")){
						next.setValue(newValue);
					}
				}
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			setValue(evt.getNewValue());
			super.propertyChange(evt);
		}

	}

	private class MethodValidator extends ValueAcceptedValidator {
		private Object bean;
		private Method method;
		private Object errorKey;
		private Object bindingDescriptor;

		public MethodValidator(Object bean, Object bindingDescriptor, Object errorKey) {
			this.bean = bean;
			this.bindingDescriptor = bindingDescriptor;
			this.errorKey = errorKey;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		@Override
		public List<ValidationError> validate() {
			try {
				Object result = callBindingMethod(bean, getValue(), bindingDescriptor, method);
				List<ValidationError> errors = new ArrayList<ValidationError>();

				if (result == null)
					return errors;

				if (result instanceof String) {
					errors.add(new ValidationError((String) result, errorKey));
				} else {
					for (Object message : (List<?>) result) {
						if (message instanceof String) {
							errors.add(new ValidationError((String) message, errorKey));
						} else {
							errors.add(new ValidationError(message.toString(), errorKey));
						}
					}
				}

				return errors;
			} catch (Exception e) {
				if (log.isWarnEnabled()) {
					log.warn(String.format("Can't invoke validate method %s", method), e);
				}

				return null;
			}
		}
	}

	/**
	 * 调用某个绑定对象的某个绑定方法。就是bindingDescriptor的某个方法，
	 * 而这个方法就是针对某个JavaBean属性的校验的。通常情况下，bindingDescriptor
	 * 的方法一帮可以包括两个参数，分别是JavaBean某个绑定属性的对象，另一个就是JavaBean 对象本身。 callBindingMethod
	 * 
	 * @param bean
	 *            绑定成功后，绑定框架创建的绑定的JavaBean对象
	 * @param value
	 *            JavaBean中某个绑定属性
	 * @param bindingDescriptor
	 *            JavaBean对象的描述校验器，对某个属性绑定前校验，方法名称为validate+JavaBean的属性名构成。
	 * @param method
	 *            将要调用的bindingDescriptor对象的方法
	 * @return
	 * @throws Exception
	 *             Object
	 * @since:0.6
	 */
	@SuppressWarnings("rawtypes")
	private Object callBindingMethod(Object bean, Object value, Object bindingDescriptor, Method method) throws Exception {
		Class<?>[] paramTypes = method.getParameterTypes();
		Object[] params = new Object[paramTypes.length];
		for (int i = 0; i < params.length; i++) {
			if (paramTypes[i].isAssignableFrom(bean.getClass())) {
				params[i] = bean;
			} else {
				Class cls = null;
				if (value == null) {
					params[i] = value;
					continue;
				}

				if (Enum.class.isAssignableFrom(value.getClass())) {
					cls = ((Enum) value).getDeclaringClass();
				} else {
					cls = value.getClass();
				}

				if (matchType(cls, paramTypes[i])) {
					params[i] = value;
				}
			}
		}

		return method.invoke(bindingDescriptor, params);
	}

	/**
	 * 
	 ******************************************* 
	 * <b style="font-family:微软雅黑"> <small>Description:
	 * 用来组织框架功能的ValueModel，同时它也是一个PropertyChangeListener对象。
	 * DecoratedValueModel是利用装饰模式组织ValueModel的链式响应，即当DecoratedValueModel
	 * 所装饰的对象ValueModel发生变化时就会导致DecoratedValueModel这个监听器触发，接着就会导致
	 * DecoratedValueModel上注册的监听器触发，从而形成了链式相应。 </small> </b></br> <b
	 * style="font-family:微软雅黑"><small>HISTORY</small></b></br> <b
	 * style="font-family:微软雅黑"><small> ID DATE PERSON REASON</small></b><br>
	 ******************************************** 
	 * <div style="font-family:微软雅黑,font-size:70%"> 1 2011-3-24 rogger 新增 </div>
	 ******************************************** 
	 */
	@SuppressWarnings("serial")
	private abstract class DecoratedValueModel<T> extends AbstractValueModel implements PropertyChangeListener {
		protected ValueModel next;

		/**
		 * 
		 * 创建一个新的实例 DecoratedValueModel. 为了实现链式响应，必须把当前对象作为next的监听器去注册。
		 * 
		 * @param next
		 * @since
		 */
		public DecoratedValueModel(ValueModel next) {
			this.next = next;
			next.addValueChangeListener(this);
		}

		/**
		 * 获取当前ValueModel所装饰的ValueModel对象。 getNext
		 * 
		 * @return ValueModel
		 * @since:0.6
		 */
		public ValueModel getNext() {
			return next;
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			firePropertyChange(PROPERTYNAME_VALUE, evt.getOldValue(), evt.getNewValue());
		}
	}

	private class Binder<T> implements IBinder<T>, PropertyChangeListener {
		protected T bean;
		protected List<IBindingListener> bindingListeners;
		protected List<IValidationListener> validationListeners;
		protected IBeanValidatorFactory beanValidatorFactory;

		protected Map<String, List<IPropertyBindingListener>> propertyListeners;

		public Binder() {
			bindingListeners = new ArrayList<IBindingListener>();
			validationListeners = new ArrayList<IValidationListener>();
			propertyListeners = new HashMap<String, List<IPropertyBindingListener>>();
			beanValidatorFactory = new BeanValidatorFactory(this);
		}

		public void notifyValidationErrors(List<ValidationError> errors) {
			ValidationErrorEvent event = new ValidationErrorEvent(errors);
			for (IValidationListener listener : validationListeners) {
				listener.validationError(event);
			}
		}

		@Override
		public void addBindingListener(IBindingListener listener) {
			bindingListeners.add(listener);
		}

		@Override
		public void removeBindingListener(IBindingListener listener) {
			bindingListeners.remove(listener);
		}

		public void notifyBindingEvent(List<BindingEvent> event, boolean isFromVoEvent) {

			for (BindingEvent bindingEvent : event) {
				String propertyName = bindingEvent.getPropertyName();

				List<IPropertyBindingListener> listeners = propertyListeners.get(propertyName);
				if (listeners != null) {
					for (IPropertyBindingListener listener : listeners) {
						if(!isFromVoEvent){
							//从页面过来都触发事件
							listener.bindingTriggered(bindingEvent);
						}else if(listener.isFromVoTargetEnable()){
							//VO双向事件仅在isFromVoTargetEnable打开后才触发
							listener.bindingTriggered(bindingEvent);
						}
					}
				}
			}

			for (IBindingListener listener : bindingListeners) {
				if(!isFromVoEvent){
					//从页面过来都触发事件
					listener.bindingTriggered(event);
				}else if(listener.isFromVoTargetEnable()){
					//VO双向事件仅在isFromVoTargetEnable打开后才触发
					listener.bindingTriggered(event);
				}

			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String propertyName = evt.getPropertyName();

			BindingEvent event = new BindingEvent();
			event.setSource(bean);
			event.setPropertyName(propertyName);
			event.setOldValue(evt.getOldValue());
			event.setNewValue(evt.getNewValue());

			List<BindingEvent> events = new ArrayList<BindingEvent>();
			events.add(event);
			notifyBindingEvent(events, (Boolean)evt.getPropagationId());
		}

		@Override
		public void addValidationListener(IValidationListener listener) {
			validationListeners.add(listener);
		}

		@Override
		public void removeValidationListener(IValidationListener listener) {
			validationListeners.remove(listener);

		}

		public IBeanValidatorFactory getBeanValidatorFactory() {
			return beanValidatorFactory;
		}

		@Override
		public List<ValidationError> validate() {
			return beanValidatorFactory.getBeanValidator().validate();
		}

		@Override
		public T getBean() {
			return bean;
		}

		public void setBean(T bean) {
			this.bean = bean;
		}

		private ReentrantLock lock = new ReentrantLock();

		@Override
		public void addPropertyBindingListener(String property, IPropertyBindingListener listener) {
			List<IPropertyBindingListener> propListeners = propertyListeners.get(property);
			if (propListeners == null) {
				lock.lock();
				if (propListeners == null) {
					propListeners = new ArrayList<IPropertyBindingListener>();
					propertyListeners.put(property, propListeners);
				}
				lock.unlock();
			}

			propListeners.add(listener);

		}

		@Override
		public void removePropertyBindingListener(String property, IPropertyBindingListener listener) {
			List<IPropertyBindingListener> propListeners = propertyListeners.get(property);
			if (propListeners == null) {
				lock.lock();
				if (propListeners == null) {
					propListeners = new ArrayList<IPropertyBindingListener>();
				}
				lock.unlock();
			}
			propListeners.remove(listener);
		}

		@Override
		public void removePropertyBindingListener(String property) {
			propertyListeners.remove(property);
		}
		
	}

	/**
	 * ValueModel的装饰器，负责组装ValueModel。 这是和DecoratedValueModel平行的接口，实际上用来辅助后者工作。
	 * 实际效果是创建一个相应的后者，并且用后者去装饰ValueModel, 从而达到组装的目的。
	 * 如果需要给绑定框架定制新的功能（如果注解和监听器都无法搞定），那么就需要实现
	 * 平行的这两者：一个IValueModelDecorator，和一个相应的DecoratedValueModel， 并且把前者组装到绑定策略里面。
	 */
	private interface IValueModelDecorator {
		ValueModel decorate(ValueModel valueModel);
	}

	private abstract class AbstractValueModelDecorator<T> implements IValueModelDecorator {
		protected IValueModelDecorator next;

		public AbstractValueModelDecorator(IValueModelDecorator next) {
			this.next = next;
		}

		public ValueModel decorate(ValueModel valueModel) {
			if (next != null) {
				valueModel = next.decorate(valueModel);
			}

			return doDecorate(valueModel);
		}

		protected abstract ValueModel doDecorate(ValueModel valueModel);
	}

	private class PropertyChangeSupportDecorator<T> extends AbstractValueModelDecorator<T> {
		private T bean;
		private String propertyName;
		private PropertyChangeListener listener;

		public PropertyChangeSupportDecorator(T bean, String propertyName, PropertyChangeListener listener, IValueModelDecorator next) {
			super(next);

			this.bean = bean;
			this.propertyName = propertyName;
			this.listener = listener;
		}

		@Override
		protected ValueModel doDecorate(ValueModel valueModel) {
			return new PropertyChangeSupportValueModel<T>(bean, propertyName, valueModel, listener);
		}

	}

	@SuppressWarnings("serial")
	private class PropertyChangeSupportValueModel<T> extends DecoratedValueModel<T> {
		private String propertyName;
		private PropertyChangeListener listener;
		private Object bean;
		private Object oldValueFromEvent;
		//调用vo.setxxx 连带掉firePropertyChange就会一次
		private int isFromSetObjectEvent = 0;
		private boolean isFromEvent;

		public PropertyChangeSupportValueModel(Object bean, String propertyName, ValueModel next, PropertyChangeListener listener) {
			super(next);
			this.bean = bean;
			this.propertyName = propertyName;
			this.listener = listener;
			this.isFromEvent = false;
		}

		@Override
		public Object getValue() {
			return next.getValue();
		}

		@Override
		public void setValue(Object newValue) {
			Object oldValue = null;
			
			//通过PropertyChangeEvent的扩展属性记录事件是否从VO过来
			Object eventPropagationId = new Boolean(isFromEvent);

			if(isFromEvent){
				oldValue = oldValueFromEvent;
			}else{
				oldValue = next.getValue();
			}
			
			oldValueFromEvent = null;
			isFromEvent = false;
			
			if (newValue == null && oldValue == null) {
				
				return;
			}
			

			if (newValue != null && newValue.equals(oldValue)) {
				return;
			}
			
			//BigDecimal精度不一致时，PropertyChangeEvent事件重复触发  ;
			//例：vo中原值为125,重新设置为125.0,BindingListener不停工作 
			if (newValue != null && newValue instanceof BigDecimal) {
				
				if(oldValue != null && oldValue instanceof BigDecimal){
					BigDecimal newBg =(BigDecimal) newValue;
					BigDecimal oldBg =(BigDecimal) oldValue;
					if(newBg.doubleValue() == oldBg.doubleValue()){
						return;
					}
				}
				
			}

			try {
				//记录setValue栈次数
				isFromSetObjectEvent ++;
				next.setValue(newValue);
				isFromSetObjectEvent --;
			} catch (Exception e) {
				// throw exception will corrupt ui. so ignore it.
				if (log.isWarnEnabled()) {
					log.warn(String.format("Can't set value to %s", newValue), e);
				}
				return;
			}
			//仅在完全出栈后才触发BindingListener
            if(isFromSetObjectEvent==0){
            	PropertyChangeEvent event = new PropertyChangeEvent(bean, propertyName, oldValue, newValue);
            	event.setPropagationId(eventPropagationId);
    			listener.propertyChange(event);
            }
			
		}
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			isFromEvent = true;
			oldValueFromEvent = evt.getOldValue();
			super.propertyChange(evt);
		}

	}

	private class ConverterAdapter<T> extends AbstractValueModelDecorator<T> {
		private T bean;
		private BindingAssociation association;
		private Converter converterAnnotation;
		private Binder<T> binder;
		private Map<String, String> args;

		public ConverterAdapter(Binder<T> binder, T bean, BindingAssociation association, Map<String, String> args, Converter converterAnnotation, IValueModelDecorator next) {
			super(next);
			this.bean = bean;
			this.association = association;
			this.converterAnnotation = converterAnnotation;
			this.binder = binder;
			this.args = args;
		}

		@Override
		public ValueModel doDecorate(ValueModel valueModel) {
			IConvertValidator validator = null;
			if (converterAnnotation != null) {
				try {
					SimpleConvertValidator convertValidator = new SimpleConvertValidator();
					convertValidator.setConverter((IConverter) converterAnnotation.type().newInstance());
					convertValidator.setErrorMessage(converterAnnotation.errorMsg());

					validator = convertValidator;
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
			}

			if (validator == null) {
				Field field = null;
				try {
					field = bean.getClass().getSuperclass().getDeclaredField(association.getArgWrapper().getPropertyName());
					Class<?> type = field.getType();

					TypePair typePair = new TypePair(association.getComponent().getClass(), type);
					Class<? extends IConvertValidator> validatorClass = BindingFactory.this.convertValidators.get(typePair);
					if (validatorClass != null) {
						validator = validatorClass.newInstance();
					}
				} catch (Exception e) {
					return valueModel;
				}
			}

			if (validator == null) {
				return valueModel;
			}

			if (validator instanceof AbstractConvertValidator) {
				((AbstractConvertValidator) validator).setErrorKey(association);
			}

			if (validator instanceof IBindingArgsAware) {
				((IBindingArgsAware) validator).setArgs(args);
			}

			if (validator.getConverter() instanceof IBindingArgsAware) {
				((IBindingArgsAware) validator.getConverter()).setArgs(args);
			}

			binder.getBeanValidatorFactory().addConvertValidator(association.getArgWrapper().getPropertyName(), validator);
			return new TypeConverter<T>(binder, valueModel, validator);
		}
	}

	@SuppressWarnings("serial")
	private class TypeConverter<T> extends DecoratedValueModel<T> {
		private IConvertValidator convertValidator;
		private Binder<T> binder;

		public TypeConverter(Binder<T> binder, ValueModel next, IConvertValidator convertValidator) {
			super(next);
			this.convertValidator = convertValidator;
			this.binder = binder;
		}

		@Override
		public Object getValue() {
			try {
				return convertValidator.getConverter().from(next.getValue());
			} catch (ConversionException e) {
				throw new RuntimeException("Convertion exception", e);
			}
		}

		@Override
		public void setValue(Object newValue) {
			if (convertValidator instanceof IValueAcceptor) {
				((IValueAcceptor) convertValidator).setValue(newValue);
			}

			List<ValidationError> errors = convertValidator.validate();
			if (errors != null && errors.size() > 0) {
				binder.notifyValidationErrors(errors);
				return;
			}

			next.setValue(convertValidator.getToResult());
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			IConverter converter = convertValidator.getConverter();
			Object oldValue = null;
			Object newValue = null;

			try {
				oldValue = converter.from(evt.getOldValue());
				newValue = converter.from(evt.getNewValue());
				//修改页面Converter初始化时Value为Null Bug				
				setValue(newValue);
			} catch (ConversionException e) {
				// ignore
			}

			super.firePropertyChange(PROPERTYNAME_VALUE, oldValue, newValue);
		}

	}

	private class FocusLostHandle {
		JTextField textField;

		ValueModel valueModel;

		boolean isDo = true;

		public FocusLostHandle(JTextField textField, final ValueModel valueModel) {
			this.textField = textField;
			this.valueModel = valueModel;

			textField.addFocusListener(new FocusListener() {
				@Override
				public void focusLost(FocusEvent e) {
					JTextField source = (JTextField) e.getSource();

					if (isDo) {
						valueModel.setValue(source.getText());
					}
					isDo = true;
				}

				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
				}
			});

			textField.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					isDo = false;
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub

				}
			});

		}

	}
}
