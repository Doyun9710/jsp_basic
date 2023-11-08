import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ZipcodeSearchEx03 extends JFrame {

	private JPanel contentPane;
	private JComboBox cbBoxSido;
	private JComboBox cbBoxGugun;
	private JComboBox cbBoxDong;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZipcodeSearchEx03 frame = new ZipcodeSearchEx03();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ZipcodeSearchEx03() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cbBoxSido = new JComboBox();
		cbBoxSido.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbBoxGugun.setModel( new CustomModelZipcodeGugun( (String)cbBoxSido.getSelectedItem() ) );
				
				textArea.setText( "" );
				ZipcodeDAO dao = new ZipcodeDAO();
				ArrayList<String> datas = dao.searchSido( (String)cbBoxSido.getSelectedItem() );
				for( String data : datas ) {
					textArea.append( data + System.lineSeparator() );
				}
			}
		});
		cbBoxSido.setModel( new CustomModelZipcodeSido() );
		cbBoxSido.setBounds(31, 59, 133, 23);
		contentPane.add(cbBoxSido);
		
		cbBoxGugun = new JComboBox();
		cbBoxGugun.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cbBoxDong.setModel( new CustomModelZipcodeDong( (String)cbBoxSido.getSelectedItem(), 
																(String)cbBoxGugun.getSelectedItem() ) );
				
				textArea.setText( "" );
				ZipcodeDAO dao = new ZipcodeDAO();
				ArrayList<String> datas = dao.searchGugun( (String)cbBoxSido.getSelectedItem(), 
														(String)cbBoxGugun.getSelectedItem() );
				for( String data : datas ) {
					textArea.append( data + System.lineSeparator() );
				}
			}
		});
		cbBoxGugun.setBounds(220, 59, 133, 23);
		contentPane.add(cbBoxGugun);
		
		cbBoxDong = new JComboBox();
		cbBoxDong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				textArea.setText( "" );
				ZipcodeDAO dao = new ZipcodeDAO();
				ArrayList<String> datas = dao.searchDong( (String)cbBoxSido.getSelectedItem(), 
														(String)cbBoxGugun.getSelectedItem(), 
														(String)cbBoxDong.getSelectedItem() );
				for( String data : datas ) {
					textArea.append( data + System.lineSeparator() );
				}
			}
		});
		cbBoxDong.setBounds(405, 59, 133, 23);
		contentPane.add(cbBoxDong);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 110, 500, 641);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

}
