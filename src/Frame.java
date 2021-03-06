import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Frame {
	private static JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					build();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// Frame in which the Panels and grid where added
	private static void build() {
		frame = new JFrame("Base 64 - Caesar - Decoder - Encoder - By 5KR34D3R");
		frame.setBounds(100, 100, 701, 477);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setResizable(false);
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.addTab("Base 64 Decode-Encode", null, tab("Base 64 Encoded","Base 64 Decoded",1), null);
		tabbedPane.addTab("Caesar Decode-Encode", null, tab("Caesar Encoded","Caesar Decoded",2), null);
		tabbedPane.addTab("Base 64 - Caesar Encoded", null, tab("Base 64 - Caesar Encoded","Base 64 - Caesar Decoded",3), null);

		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);
	}
	
	// Grid layout for Panels
	private static JPanel gridLayout() {
		JPanel panBase64 = new JPanel();
		GridBagLayout layoutGridBag = new GridBagLayout();
		layoutGridBag.columnWidths = new int[]{205, 51, 156, 42, 217, 0};
		layoutGridBag.rowHeights = new int[]{41, 0, 90, 41, 110, 41, 0};
		layoutGridBag.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panBase64.setLayout(layoutGridBag);
		return panBase64;
	}
	
	// Panels
	private static JPanel tab(String txtE, String txtD, int mode) {
		JPanel panel = gridLayout();
		JTextArea txtEncoded = new JTextArea();
		JTextArea txtDecoded = new JTextArea();		
		lbl(panel,txtE);	
		scrollTxtarea(panel, txtEncoded,true);
		lbl(panel,txtD);
		scrollTxtarea(panel, txtDecoded,false);
		btns(panel, txtEncoded, txtDecoded, mode);	
		return panel;
	}	
	
	// Label Top and Mid
	private static void lbl(JPanel panel, String lblText) {
		JLabel lblInfo = new JLabel(lblText);
		GridBagConstraints lblGridBag = new GridBagConstraints();
		lblGridBag.gridx = 2;
		panel.add(lblInfo, lblGridBag);		
	}
	
	// Text-Area Top and Mid
	private static void scrollTxtarea(JPanel panel, JTextArea txtArea, Boolean position) {
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints txtareaGirdBag = new GridBagConstraints();
		txtareaGirdBag.gridwidth = 5;
		txtareaGirdBag.fill = GridBagConstraints.BOTH;
		if(position) {
			txtareaGirdBag.gridy = 2;			
		}else {
			txtareaGirdBag.gridx = 0;
			
		}		
		scrollPane.setViewportView(txtArea);
		txtArea.setLineWrap(true);
		panel.add(scrollPane, txtareaGirdBag);
	}	
	
	private static void btns(JPanel panel, JTextArea txtEncoded, JTextArea txtDecoded, int mode) {
		// -------------------------------------- Buttons -----------------------------------
		GridBagConstraints btnDecodeGridBag = new GridBagConstraints();
		GridBagConstraints btnEncodeGridBag = new GridBagConstraints();
		GridBagConstraints btnClearGridBag = new GridBagConstraints();

		// Button Decode
		btnDecodeGridBag.gridx = 0;
		JButton btnDecode = new JButton("Decode");	
		//btnDecode.setEnabled(false);
		btnDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (mode) {
				case 1: txtDecoded.setText(Decoder.decodeString(txtEncoded.getText())); break;
				case 2: 
					for(String s: txtEncoded.getText().split("\n")) {
						for(String outPut: Decoder.ceasarDecoder(s)) {						
							txtDecoded.append(outPut+"\n");
						}					
					}					 
					break;
				case 3: 
					for(String s: txtEncoded.getText().split("\n")) {					
						for(String outPut: Decoder.replaceElements(Decoder.ceasarDecoder(Decoder.decodeString(s)))) {						
							txtDecoded.append(outPut+"\n");
						}					
					} 
					break;				
				}
			}
		});
		
		// Button Encode
		btnEncodeGridBag.gridx = 2;
		JButton btnEncode = new JButton("Encode");	
		//btnEncode.setEnabled(false);
		btnEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (mode) {
				case 1: txtEncoded.setText(Encoder.encodeString(txtDecoded.getText())); break;
				//case 2: break;
				//case 3: break;				
				}				
			}
		});		
		
		// Button Clear		
		btnClearGridBag.gridx = 4;
		JButton btnClear = new JButton("Clear");
		//btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEncoded.setText("");
				txtDecoded.setText("");
			}
		});
		
		panel.add(btnDecode, btnDecodeGridBag);
		panel.add(btnEncode, btnEncodeGridBag);		
		panel.add(btnClear, btnClearGridBag);
	}
}
