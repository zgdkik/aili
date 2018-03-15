package org.hbhk.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class JCustomFrame extends JFrame {
	
	/**
	 * gloomy fish
	 */
	private static final long serialVersionUID = -523336873755438297L;
    private Shape shape;
    private float alpha = 1f;
    private Dimension arcSize = new Dimension(50, 50);
    protected static final int CIRCLE_TYPE = 1;
    protected static final int RECTANGEL_TYPE = 0;
    protected static final int AREA_TYPE = 2;
    
	public JCustomFrame() {
        setUndecorated(true);
        setVisible(true);
        setListenersForEffects();
	}
	
	public JCustomFrame(int width, int height) {
        this();
        setSize(width, height);
    }

    public JCustomFrame(Shape shape, int width, int height) {
        this(width, height);
        setShape(shape);
    }

    public void setShape(Shape shape) {
		this.shape = shape;
	}

	public JCustomFrame(float alpha, Shape shape, int width, int height) {
        this(shape, width, height);
        setAlpha(alpha);
    } 
	
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	private void setListenersForEffects() {
        //It is important to upadate visual effect on form resize.
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent evt) {
                updateFrameEffects();
            }
        });
    }

    /**
     * This updates visual effects like SHAPE form and transparency. You have to
     * update also <b>shape</b> property or it paints old shape ( if you resize
     * frame without resize shape .. )
     */
    public void updateFrameEffects() {
        updateShape();
        try {
        } catch (Exception ex) {
            Logger.getLogger(JCustomFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
	public void updateShape() {
		if(shape == null) {
			shape = new RoundRectangle2D.Double(0d, 0d, getWidth(), getHeight(), arcSize.width, arcSize.height);
		}
		
	}
	
	public void updateShape(int type) {
		if(type == RECTANGEL_TYPE) {
			shape = new RoundRectangle2D.Double(0d, 0d, getWidth(), getHeight(), arcSize.width, arcSize.height);
		} else if(type == CIRCLE_TYPE) {
			shape = new Ellipse2D.Double(0, 0,400, 400);
		} else if(type == AREA_TYPE) {		
			Shape circle1 = new Ellipse2D.Double(0, 0,400, 400);
			Shape circle2 = new Ellipse2D.Double(200, 100,400, 400);
			Area area1 = new Area(circle1);
			Area area2 = new Area(circle2);
			area1.subtract(area2);
			shape = area1;
		}
	}
	
	public void center() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
    }
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                	// com.sun.java.swing.plaf.windows.WindowsLookAndFeel
                	UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                } catch (Exception e) {
                }

                /*These are simple custom panel generated with vidual editor of Netbeans
                  don't care about it, take a look only to ImagePanel inherit ( why?...)
                 */
                
                // Cool transparent Frame
                final JCustomFrame customFrame = new JCustomFrame();
                customFrame.setLayout(new BorderLayout());
                
                // create custom JPanel
                final JPanel panel = new JPanel();
                java.net.URL image1 = this.getClass().getResource("ball.jpg");

                customFrame.add(panel, BorderLayout.CENTER);
                customFrame.setTitle("Ttranslucency JFrame");
                customFrame.setSize(400, 300);
                customFrame.setAlpha(0.8f);
                customFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                customFrame.center();

            }
        });

	}

}

