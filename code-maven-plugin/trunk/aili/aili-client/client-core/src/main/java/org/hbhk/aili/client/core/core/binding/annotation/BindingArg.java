package org.hbhk.aili.client.core.core.binding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *	绑定参数，在进行绑定的时候可以自己传入参数在需要的时候领用自己的参数。
 * 比如在绑定某个控件的时候打上BindingArgs标注
 * @Bind("name")
 * @BindingArgs({@BindingArg(name="fieldName", value="Name")})
 * private JTextField tfName;
 * 这样框架会把这些参数包装成为一个map对象。
 * 在需要的时候实现IBindingArgsAware接口。
 * 框架就会把参数参数设置给实现类。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface BindingArg {
	/**
	 * 参数名
	 * name
	 * @return String
	 * @since:0.6
	 */
	String name();
	/**
	 * 参数值
	 * value
	 * @return String
	 * @since:0.6
	 */
	String value();
}
