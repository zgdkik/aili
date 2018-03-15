package org.hbhk.aili.client.core.commons.binding.validators;

import org.hbhk.aili.client.core.commons.conversion.IConverter;
/**
*******************************************
* 一般的转换校验器，通过注解声明一个转换器和错误信息来实例化。
* 框架中把转换器包装在校验器中使用。对于一般的转换器，用这个校验器来包装
 */
public class SimpleConvertValidator extends AbstractConvertValidator {
	// 转换器
	private IConverter converter;
	
	// 错误信息
	private String errorMessage;
	
	/**
	 * 
	 * <p>Title: setErrorMessage</p>
	 * <p>Description: 设置错误信息</p>
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * 
	 * <p>Title: setConverter</p>
	 * <p>Description: 设置转换器</p>
	 * @param converter
	 */
	public void setConverter(IConverter converter) {
		this.converter = converter;
	}

	@Override
	protected String getErrorMessage() {
		if (errorMessage == null) {
			return errorMessage;
		}
		
		PlaceholderSeeker seeker = new PlaceholderSeeker();
		while (true) {
			seeker.setString(errorMessage);
			seeker.seek();
			int start = seeker.getStartPosition();
			if (start == -1){
				break;
			}
			
			String placeholder = seeker.getPlaceholder();
				
			errorMessage = errorMessage.substring(0, start) + getArgs().get(placeholder) +
				errorMessage.substring(start + placeholder.length() + 3, errorMessage.length());
		}
		
		return errorMessage;
	}
	
	/**
	 * 
	 * <p>Title: PlaceholderSeeker</p>
	 * <p>Description: 占位符引导类</p>
	 * <p>Company: DEPPON</p>
	 * @author Polo Yuan
	 * @date 2011-6-13
	 *
	 */
	private class PlaceholderSeeker {
		private String string;
		private String placeholder;
		private int startPosition;
		
		public void setString(String string) {
			this.string = string;
		}
		
		public void seek() {
			startPosition = -1;
			placeholder = null;

			startPosition = string.indexOf("#{");
			if (startPosition == -1 || startPosition >= string.length() - 4) {
				return;
			}
			
			int end = string.indexOf('}', startPosition + 3);
			if (end != -1) {
				placeholder = string.substring(startPosition + 2, end);
			} else {
				startPosition = -1;
			}
		}
		
		/**
		 * 
		 * <p>Title: getPlaceholder</p>
		 * <p>Description: 获取占位符</p>
		 * @return
		 */
		public String getPlaceholder() {
			return placeholder;
		}
		
		/**
		 * 
		 * <p>Title: getStartPosition</p>
		 * <p>Description: 获取起始位置</p>
		 * @return
		 */
		public int getStartPosition() {
			return startPosition;
		}
	}

	@Override
	public IConverter getConverter() {
		return converter;
	}
}
