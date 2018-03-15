package org.hbhk.aili.client.core.widget.validatewidget;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.styles.RoundedBalloonStyle;

public class JTextFieldValidate extends JTextField implements IBallValidateWidget, AdjustmentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8862320299952670201L;
	private BalloonTip balloonTip;
	private boolean isEnable = true;
	private boolean isPassed = true;
	
	public JTextFieldValidate(){
		super();
		init();
		
	}
	
	public void init(){
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
	
	public void verifyWrong(String message){
		init();
		if(message.equals("success")) {
			verifyPass();
		}else{ 
			if(message.startsWith("warning-")){
				this.setBackground(Color.yellow);
				message = message.substring(message.indexOf("warning-"), message.length());
				this.balloonTip.setText(message);
				if(this.isDisplayable()){
					this.balloonTip.setVisible(true);
				}
				setIsEnable(true);
				setIsPassed(true);
			}else{
				this.setBackground(Color.PINK);
				this.balloonTip.setText(message);
				if(this.isDisplayable()){
					this.balloonTip.setVisible(true);
				}
				setIsEnable(false);
				setIsPassed(false);
			}
		}
	}
	
	public void verifyPass(){
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
		this.isPassed = isEnable;
	}

	public BalloonTip getBalloonTip() {
		return balloonTip;
	}

	public void setBalloonTip(BalloonTip balloonTip) {
		this.balloonTip = balloonTip;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {

		balloonTip.refreshLocation();
		
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	
}
