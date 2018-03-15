package org.hbhk.aili.client.core.widget.calender;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.calendar.SingleDaySelectionModel;
import org.jdesktop.swingx.plaf.basic.BasicDatePickerUI;

public class JXDateTimePicker extends JXDatePicker implements ActionListener {
    private JSpinner timeSpinner;
    private JPanel timePanel;
    private DateFormat timeFormat;
 
    public JXDateTimePicker() {
        super();
        getMonthView().setSelectionModel(new SingleDaySelectionModel());
    }
 
    public JXDateTimePicker( Date d ) {
        this();
        setDate(d); 
        
    }
 
    public void commitEdit() throws ParseException {
        
        super.commitEdit();
        commitTime();
       
    }
 
    public void cancelEdit() {
        super.cancelEdit();
        setTimeSpinners();
    }
 
    @Override
    public JPanel getLinkPanel() {
        super.getLinkPanel();
        if( timePanel == null ) {
            timePanel = createTimePanel();
        }
        setTimeSpinners();
        return timePanel;
    }
 
    private JPanel createTimePanel() {
        JPanel newPanel = new JPanel();
        newPanel.setLayout(new FlowLayout());
 
        SpinnerDateModel dateModel = new SpinnerDateModel();
        timeSpinner = new JSpinner(dateModel);
        if( timeFormat == null ) 
        	timeFormat = DateFormat.getTimeInstance( DateFormat.MEDIUM );
        updateTextFieldFormat();
        newPanel.add(new JLabel( "时间选择:" ) );
        newPanel.add(timeSpinner);
        JButton okBtn = new JButton("确认");
        okBtn.addActionListener(this);
        newPanel.add(okBtn);
        newPanel.setBackground(Color.WHITE);
        return newPanel;
    }
 
    private void updateTextFieldFormat() {
        if( timeSpinner == null ) return;
        JFormattedTextField tf = ((JSpinner.DefaultEditor) timeSpinner.getEditor()).getTextField();
        DefaultFormatterFactory factory = (DefaultFormatterFactory) tf.getFormatterFactory();
        DateFormatter formatter = (DateFormatter) factory.getDefaultFormatter();
        // Change the date format to only show the hours
        formatter.setFormat( timeFormat );
    }
 
    private void commitTime() {
        Date date = getDate();
        if (date != null) {
            Date time = (Date) timeSpinner.getValue();
            GregorianCalendar timeCalendar = new GregorianCalendar();
            timeCalendar.setTime( time );
 
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get( Calendar.HOUR_OF_DAY ) );
            calendar.set(Calendar.MINUTE, timeCalendar.get( Calendar.MINUTE ) );
            calendar.set(Calendar.SECOND, timeCalendar.get( Calendar.SECOND ));
            calendar.set(Calendar.MILLISECOND, 0);
 
            Date newDate = calendar.getTime();
            //System.out.println(newDate);
            setDate(newDate);
        }
 
    }
 
    private void setTimeSpinners() {
        Date date = getDate();
        if (date != null) {
            timeSpinner.setValue( date );
        }
    }
 
    public DateFormat getTimeFormat() {
        return timeFormat;
    }
 
    public void setTimeFormat(DateFormat timeFormat) {
        this.timeFormat = timeFormat;
        updateTextFieldFormat();
    }
 
    public static void main(String[] args) {
        Date date = new Date();
        JFrame frame = new JFrame();
        frame.setTitle("Date Time Picker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JXDateTimePicker dateTimePicker = new JXDateTimePicker();
        dateTimePicker.setFormats( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        dateTimePicker.setTimeFormat( DateFormat.getTimeInstance( DateFormat.MEDIUM ) );
 
        dateTimePicker.setDate(date);
 
        frame.getContentPane().add(dateTimePicker);
        frame.pack();
        frame.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			commitEdit();
			((BasicDatePickerUI)getUI()).hidePopup();
		} catch (ParseException e1) {
		}
	}
}

