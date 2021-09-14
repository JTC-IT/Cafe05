package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bean.TimeFormat;

public class Clock extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private JLabel lblTime, lblDate;
	
	Thread t;

	public Clock(){
		lblTime = new JLabel();
		lblTime.setFont(new Font("Tomato", Font.BOLD, 30));
		lblTime.setForeground(Color.white);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setVerticalAlignment(SwingConstants.BOTTOM);
		
		lblDate = new JLabel();
		lblDate.setFont(new Font("Tomato", Font.BOLD, 16));
		lblDate.setForeground(Color.white);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setVerticalAlignment(SwingConstants.TOP);
		lblDate.setPreferredSize(new Dimension(0,40));
		
		setLayout(new BorderLayout());
		setOpaque(false);
		add(lblTime, BorderLayout.CENTER);
		add(lblDate, BorderLayout.SOUTH);
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				setText(TimeFormat.format_time(new Date()), TimeFormat.format_date(new Date()));
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	private void setText(String time, String date) {
		lblTime.setText(time);
		lblDate.setText(date);
	}
}
