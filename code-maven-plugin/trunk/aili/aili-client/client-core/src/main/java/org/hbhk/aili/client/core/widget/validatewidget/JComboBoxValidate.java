package org.hbhk.aili.client.core.widget.validatewidget;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

public class JComboBoxValidate extends JComboBox implements IBallValidateWidget {

	private BalloonTip balloonTip;
	private Color color;
	private boolean isEnable = true;
	private boolean isPassed = true;

	public JComboBoxValidate() {
		super();
		init();

	}

	private void init() {
		RoundedBalloonStyle balloonStyle = new RoundedBalloonStyle(10, 5,
				new Color(16777154), Color.BLACK);
		balloonTip = new BalloonTip(this, "", balloonStyle, true);

		balloonTip.setVisible(false);
		balloonTip.setIconTextGap(10);
		
		this.addAncestorListener(new AncestorListener() {
			
			public void ancestorAdded(AncestorEvent event) {}
			public void ancestorRemoved(AncestorEvent event) {
				balloonTip.setVisible(false);
			}
			public void ancestorMoved(AncestorEvent event) {
				balloonTip.refreshLocation();
			}
		});
	}

	public void verifyWrong(String message) {
		this.setBackground(Color.PINK);

		this.balloonTip.setText(message);
		if(this.isDisplayable()){
			this.balloonTip.setVisible(true);
		}
		setIsEnable(false);
		setIsPassed(false);
	}

	public void verifyPass() {
		this.setBackground(Color.WHITE);
		this.balloonTip.setVisible(false);
		setIsEnable(true);
		setIsPassed(true);
	}

	public void setIsEnable(boolean isEnable) {
		boolean oldValue = this.isEnable;
		this.isEnable = isEnable;

		firePropertyChange("isEnable", Boolean.valueOf(oldValue),
				Boolean.valueOf(this.isEnable));
	}

	public void setIsPassed(boolean isEnable) {
		this.isPassed = isPassed;
	}

	public BalloonTip getBalloonTip() {
		return balloonTip;
	}

	public void setBalloonTip(BalloonTip balloonTip) {
		this.balloonTip = balloonTip;
	}

}
