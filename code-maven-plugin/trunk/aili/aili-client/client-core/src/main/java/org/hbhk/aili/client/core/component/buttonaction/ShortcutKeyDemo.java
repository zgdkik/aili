package org.hbhk.aili.client.core.component.buttonaction;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hbhk.aili.client.core.component.buttonaction.annotation.ButtonAction;
import org.hbhk.aili.client.core.component.buttonaction.factory.ButtonActionFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * <b style="font-family:微软雅黑"><small>Description:用于展示快捷键的demo</small></b> </br>
 */
public class ShortcutKeyDemo extends JPanel {
    @ButtonAction(icon = "", shortcutKey = "alt Q", type = org.hbhk.aili.client.core.component.buttonaction.ButtonAction.class)
	private JButton btnSave;
    @ButtonAction(icon = "", shortcutKey = "alt W", type = org.hbhk.aili.client.core.component.buttonaction.ButtonAction.class)
    private JButton btnQuery;
    @ButtonAction(icon = "", shortcutKey = "alt E", type = org.hbhk.aili.client.core.component.buttonaction.ButtonAction.class)
    private JButton btnUpdate;
    @ButtonAction(icon = "", shortcutKey = "ctrl R", type = org.hbhk.aili.client.core.component.buttonaction.ButtonAction.class)
    private JButton btnDelete;

	public ShortcutKeyDemo() {

		init();
		setLayout();
	}

	private void init() {
		btnSave = new JButton("Save _alt+q");
		btnQuery = new JButton("Query _alt+w");
		btnUpdate = new JButton("Update _alt+e");
		btnDelete = new JButton("Delete _alt+r");
		
		ButtonActionFactory.getIntsance().bindButtonAction(this);
	}

	private void setLayout() {
		FormLayout formLayout = new FormLayout("left:pref",
				"pref,pref,pref,pref,pref,pref");
		DefaultFormBuilder formBuilder = new DefaultFormBuilder(formLayout);
		formBuilder.append(btnSave);
		formBuilder.nextLine();
		formBuilder.append(btnQuery);
		formBuilder.nextLine();
		formBuilder.append(btnUpdate);
		formBuilder.nextLine();
		formBuilder.append(btnDelete);
		JPanel indexPanel = formBuilder.getPanel();
		JPanel scrollPane = new JPanel();
		scrollPane.add(indexPanel);
		add(scrollPane);
	}

	public static void main(String[] agrs) {
		JFrame frame = new JFrame();
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new ShortcutKeyDemo());
		frame.setVisible(true);
		frame.pack();
	}

}


