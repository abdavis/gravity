import javax.swing.*;

public class Frame {
	JFrame jfrm=new JFrame();

	public Frame(){
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setLayout(null);
		jfrm.setSize(1000,1000);

		Panel panel=new Panel();
		panel.setBounds(0,0,1000,1000);
		jfrm.add(panel);

		jfrm.setVisible(true);



	}
	public static void main(String args[]){
		new Frame();
	}
}
