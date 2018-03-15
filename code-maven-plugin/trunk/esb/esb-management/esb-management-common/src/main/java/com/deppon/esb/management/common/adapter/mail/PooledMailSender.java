package com.deppon.esb.management.common.adapter.mail;


import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * 一期移植
 * 
 * 对Springframework MailSender和JavaMailSender的装饰, 实现装饰器模式。
 * <p>
 * <p>
 * 
 * 客户程序按照MailSender或JavaMailSender接口的方式向PooledMailSender表示要发送邮件，
 * 然后立即返回(PooledMailSender没有保证此时邮件马上发送)。PooledMailSender将要发送
 * 邮件进行排队，按FIFO方式发送得到的邮件消息。
 * <p>
 * <p>
 * 
 * 邮件如果发送失败，将被废弃，不重复发送。
 * <p>
 * <p>
 * 
 * 使用PooledMailSender，需要注入一个真正发送邮件的MailSender或JavaMailSender，当PooledMailSender
 * 认为该发送邮件时，实际调用注入的MailSender或JavaMailSender处理。
 * <p>
 * <p>
 * 
 * 当注入的是MailSender时，仅可以使用MailSender的两个邮件发送方法，当注入的是JavaMailSender时，可以
 * 使用JavaMailSender额外声明的邮件发送方法。
 * <p>
 * <p>
 * 
 * 邮件发送详细规定，请参考SpringFramework的规定。
 * <p>
 * <p>
 * 
 * 本类的职责在于排队邮件，将邮件代理给实际的MailSender发送。
 * <p>
 * <p>
 * 
 * 在Spring Context中配置本类时，"最好"配置destroy-method="close"，不过这不是必须的。
 * <p>
 * <p>
 * 
 * 
 * @see org.springframework.mail.MailSender
 * @see org.springframework.mail.javamail.JavaMailSender
 * 
 */
public class PooledMailSender implements MailSender, JavaMailSender {
	/**
	 * 日志
	 */
	protected static final Log log = LogFactory.getLog(PooledMailSender.class);

	/**
	 * 实际发送邮件的邮件发送器，可以是MailSender，或JavaMailSender
	 */
	private MailSender mailSender;
	private EmailSenderExecutor emailSenderExecutor;


	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public EmailSenderExecutor getEmailSenderExecutor() {
		return emailSenderExecutor;
	}

	public void setEmailSenderExecutor(EmailSenderExecutor emailSenderExecutor) {
		this.emailSenderExecutor = emailSenderExecutor;
	}


	// MailSender接口实现-将邮件放入排队机

	public void send(SimpleMailMessage simpleMessage) throws MailException {
		doSend(simpleMessage);
	}

	public void send(SimpleMailMessage[] simpleMessages) throws MailException {
		for (SimpleMailMessage message : simpleMessages) {
			doSend(message);
		}
	}

	// JavaMailSender接口实现-将邮件放入排队机

	public void send(MimeMessage mimeMessage) throws MailException {
		doSend(mimeMessage);
	}

	public void send(MimeMessage[] mimeMessages) throws MailException {
		for (MimeMessage message : mimeMessages) {
			doSend(message);
		}
	}

	public void send(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
		doSend(mimeMessagePreparator);
	}

	public void send(MimeMessagePreparator[] mimeMessagePreparators)
			throws MailException {
		for (MimeMessagePreparator preparator : mimeMessagePreparators) {
			doSend(preparator);
		}
	}

	public MimeMessage createMimeMessage() {
		return ((JavaMailSender) mailSender).createMimeMessage();
	}

	public MimeMessage createMimeMessage(InputStream contentStream)
			throws MailException {
		return ((JavaMailSender) mailSender).createMimeMessage(contentStream);
	}

	// 实际发送代理方法

	public void doSend(Object object) {
		if (object instanceof SimpleMailMessage) {
			doSend((SimpleMailMessage) object);
		} else if (object instanceof MimeMessage) {
			doSend((MimeMessage) object);
		} else if (object instanceof MimeMessagePreparator) {
			doSend((MimeMessagePreparator) object);
		}
	}

	private void doSend(final SimpleMailMessage simpleMessage) throws MailException {
		getEmailSenderExecutor().getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				mailSender.send(simpleMessage);
			}
		});
	}

	private void doSend(final MimeMessage mimeMessage) throws MailException {
		log.debug(emailSenderExecutor == null);
		emailSenderExecutor.getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				((JavaMailSender) mailSender).send(mimeMessage);
			}
		});
	}

	private void doSend(final MimeMessagePreparator mimeMessagePreparator) throws MailException {
		emailSenderExecutor.getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				((JavaMailSender) mailSender).send(mimeMessagePreparator);
			}
		});
	}

}