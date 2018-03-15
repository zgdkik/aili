package org.hbhk.aili.client.core.component.focuspolicy;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.hbhk.aili.client.core.component.focuspolicy.annotation.ContainerSeq;
import org.hbhk.aili.client.core.component.focuspolicy.annotation.FocusSeq;
import org.hbhk.aili.client.core.component.focuspolicy.factory.FocusPolicyFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

@ContainerSeq(seq = 1)
public class FocusPolicyDemo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3141746138903596235L;
	
	@FocusSeq(seq = 1)
	private JTextField txta;
	@FocusSeq(seq = 2)
	private JTextField txtb;
	@FocusSeq(seq = 3)
	private JCheckBox cbka;
	@FocusSeq(seq = 4)
	private JCheckBox cbkb;
	@FocusSeq(seq = 5)
	private JButton btna;
	@FocusSeq(seq = 6)
	private JButton btnb;
	private ButtonGroup bg;
	private JRadioButton rba;
	private JRadioButton rbb;
	@FocusSeq(seq = 7)
	private JComboBox combotransProperty;

	/**
	 * 
	 * @功能：创建一个新的实例 FocusPolicyDemo.
	 */
	public FocusPolicyDemo() {

		init();
		setLayout();
	}

	/**
	 * 
	 * 功能：setLayout
	 */
	private void setLayout() {
		FormLayout layout = new FormLayout(
				"20dlu,2dlu,50dlu,2dlu,20dlu,2dlu,50dlu", ""); // add rows
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.appendSeparator("Segment");
		// builder.append("Identifier", idField);
		builder.append("a:", txta);
		builder.append("b:", txtb);
		builder.append("a:", cbka);
		builder.append("b", cbkb);
		builder.append("", btna);
		builder.append("", btnb);
		builder.append(rba, rbb);
		builder.append(combotransProperty, 3);
		add(builder.getPanel());
	}

	/**
	 * 
	 * 功能：init
	 */
	private void init() {
		txta = new JTextField();
		txtb = new JTextField();
		cbka = new JCheckBox("JCheckBox1");
		cbkb = new JCheckBox("JCheckBox2");
		btna = new JButton("btna");
		btnb = new JButton("btnb");
		bg = new ButtonGroup();
		rba = new JRadioButton();
		rbb = new JRadioButton();
		bg.add(rba);
		bg.add(rbb);
		txtb.setToolTipText("9879879879");

		combotransProperty = new JComboBox(new DefaultComboBoxModel(
				new AssemblyType[] { AssemblyType.DANDUKAIDAN,
						AssemblyType.HEDAPIAO, AssemblyType.KONGYUN,
						AssemblyType.PIANXIAN, AssemblyType.ZHUANXIAN }));

		/*Component[] c = new Component[] { txta, txtb, cbka, cbkb, btna, btnb,
				rba, rbb, combotransProperty };
		com.deppon.foss.framework.client.component.focuspolicy.FocusTraversalPolicy
				.setFocusTraversalPolicy(c);
		FocusTraversalPolicy focusTraversalPolicy = new Custom_FocusTraversalPolicy(
				c);
		setFocusTraversalPolicy(focusTraversalPolicy);*/
		
		FocusPolicyFactory.getIntsance().setFocusTraversalPolicy(this);

	}

	public static void main(String[] agrs) {
		JFrame frame = new JFrame();
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new FocusPolicyDemo());
		frame.setVisible(true);
		frame.pack();
	}

}
