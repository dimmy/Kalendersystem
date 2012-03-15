package no.ntnu.fp.gui;
	import java.awt.GridBagLayout;
	import javax.swing.BorderFactory;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;

public class editmeetingBoxGUI extends JPanel {

		private JOptionPane text;
		
		public static void main (String []args){
			JFrame editmeeting = new JFrame("Endre m�te?");
			editmeeting.setSize(260,140);
			editmeeting.getContentPane().add(new editmeetingBoxGUI());
			editmeeting.setLocationRelativeTo(null);
			editmeeting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			editmeeting.setVisible(true);
	}

		public editmeetingBoxGUI(){
			setLayout(new GridBagLayout());
			setBorder(BorderFactory.createEmptyBorder(7, 7, 7, 7));
			
			text = new JOptionPane("Er du sikker du vil endre m�tet?", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);
			add(text);
		
		}
	

}
