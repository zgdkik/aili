package org.hbhk.test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

	private static final long serialVersionUID = 7758689434195492602L;

	private Image image;

	public BackgroundPanel() {

		super();

		setOpaque(false);

		setLayout(null);

	}

	public void setImage(Image image) {

		this.image = image;

	}

	@Override
	protected void paintComponent(Graphics g) {

		if (image != null) {

			int width = getWidth();

			int height = getHeight();

			g.drawImage(image, 0, 0, width, height, this);

		}

		super.paintComponent(g);

	}

}