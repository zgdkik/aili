package org.hbhk.aili.jms.server.ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

public class AiliTextField extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -507458103277288325L;

	private JLabel label;

	private JTextField textField;

	private GridBagLayout gridbag = new GridBagLayout();

	private GridBagConstraints c = new GridBagConstraints();

	private boolean validate = true;
	public static List<AiliTextField> validateList = new ArrayList<AiliTextField>();

	public AiliTextField(String text, int cols) {
		label = new JLabel(text);
		textField = new JTextField(cols);
		addComp(this, label, 0, 0);
		addComp(this, textField, 0, 1);
		validateList.add(this);
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (StringUtils.isEmpty(getText())) {
					getTextField().setBorder(BorderFactory.createLineBorder(Color.red));
				} else {
					getTextField().setBorder(BorderFactory.createLineBorder(Color.green));
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (StringUtils.isEmpty(getText())) {
					getTextField().setBorder(BorderFactory.createLineBorder(Color.red));
				}
			}
		});
	}

	public AiliTextField(String text, int cols, boolean validate) {
		label = new JLabel(text);
		textField = new JTextField(cols);
		addComp(this, label, 0, 0);
		addComp(this, textField, 0, 1);
		this.validate = validate;
		if (validate) {
			validateList.add(this);
			textField.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (StringUtils.isEmpty(getText())) {
						getTextField().setBorder(
								BorderFactory.createLineBorder(Color.red));
					} else {
						getTextField().setBorder(
								BorderFactory.createLineBorder(Color.green));
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (StringUtils.isEmpty(getText())) {
						getTextField().setBorder(
								BorderFactory.createLineBorder(Color.red));
					}
				}
			});
		}
	}

	public AiliTextField(String text) {
		label = new JLabel(text);
		textField = new JTextField(10);
		addComp(this, label, 0, 0);
		addComp(this, textField, 0, 1);
		if (validate) {
			validateList.add(this);
		}
		textField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (StringUtils.isEmpty(getText())) {
					getTextField().setBorder(BorderFactory.createLineBorder(Color.red));
				} else {
					getTextField().setBorder(BorderFactory.createLineBorder(Color.green));
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (StringUtils.isEmpty(getText())) {
					getTextField().setBorder(
							BorderFactory.createLineBorder(Color.red));
				}
			}
		});
	}

	public void validateError() {
		if (this.isVisible() && this.isEnabled() && getTextField().isVisible()
				&& getTextField().isEnabled()) {
			if (StringUtils.isEmpty(getText())) {
				getTextField().requestFocus();
				getTextField().setBorder(
						BorderFactory.createLineBorder(Color.red));
				throw new RuntimeException("必填项");
			}
		}
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public JTextField getTextField() {
		return textField;
	}

	public String getText() {
		return textField.getText();
	}

	public void setText(String text) {
		textField.setText(text);
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void addComp(JComponent cont, JComponent comp, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		gridbag.setConstraints(comp, c);
		c.fill = GridBagConstraints.BOTH;
		cont.add(comp);
	}

}
