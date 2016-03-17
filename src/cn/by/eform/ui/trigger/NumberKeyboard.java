package cn.by.eform.ui.trigger;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.text.JTextComponent;

import cn.by.eform.draft.DraftMain;
import cn.by.eform.model.Field;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.ITrigger;

import java.awt.Event;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NumberKeyboard extends JDialog implements FocusListener,MouseListener,ITrigger{
	
	private Field field;
	
	/*private static NumberKeyboard keyboard=new NumberKeyboard();
	
	public static NumberKeyboard getInstance(){
		return keyboard;
	}
	*/
	private JTextComponent text;

	/**
	 * Create the panel.
	 */
	public NumberKeyboard() {
		setLayout(new GridLayout(0, 3, 20, 20));
		
		String[]buttonNames={
				"7","8","9",
				"4","5","6",
				"1","2","3",
				"0",".","<-",
		};
		
		
		for(int i=0;i<buttonNames.length;i++){
			final JButton button=FieldFactory.createButton(" "+buttonNames[i]+" ");
			button.setFont(new Font("黑体",Font.BOLD,30));
			button.addMouseListener(new MouseAdapter(){
				private String buttonText=button.getText().replace(" ", "");
				public void mouseClicked(MouseEvent e) {
					
					if(text!=null){
						if(buttonText.equals("<-")){
							if(!text.getText().isEmpty()){
								//text.setText(text.getText().substring(0,text.getText().length()-1));
								text.dispatchEvent(new KeyEvent(text,KeyEvent.KEY_PRESSED,System.currentTimeMillis(),0,KeyEvent.VK_BACK_SPACE,'\b'));
								text.dispatchEvent(new KeyEvent(text,KeyEvent.KEY_TYPED,System.currentTimeMillis(),0,0,'\b'));
								text.dispatchEvent(new KeyEvent(text,KeyEvent.KEY_RELEASED,System.currentTimeMillis(),0,KeyEvent.VK_BACK_SPACE,'\b'));
							}
						}else{
							//text.setText(text.getText()+buttonText);
							char ch=buttonText.charAt(0);
							text.dispatchEvent(new KeyEvent(text,KeyEvent.KEY_PRESSED,System.currentTimeMillis(),0,ch,ch));
							text.dispatchEvent(new KeyEvent(text,KeyEvent.KEY_TYPED,System.currentTimeMillis(),0,0,ch));
							text.dispatchEvent(new KeyEvent(text,KeyEvent.KEY_RELEASED,System.currentTimeMillis(),0,ch,ch));
						}

					}
				}
			});
			add(button);
			
			//this.setAlwaysOnTop(true);    //总在最前面
			this.setResizable(false);    //不能改变大小
			this.setUndecorated(true);    //不要边框
		}
	}
	/*
	public void changeEditor(JTextComponent text){
		this.text=text;
	}*/

	
	protected void showMe(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(true);
					Rectangle rect=new Rectangle(350,350);
					
					Point location=new Point(337,280);//text.getLocationOnScreen();
					//location.x=312;
					//location.y+=FieldFactory.font.getSize()*1.3;
					rect.setLocation(location);
					setBounds(rect);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Invoked when a component gains the keyboard focus.
	 */
	@Override
	public void focusGained(FocusEvent e) {
		//showMe();
		Object oSrc=e.getSource();
		if(oSrc instanceof JTextComponent){
			this.text=(JTextComponent)oSrc;
		}
	}
	
	/**
	 * Invoked when a component loses the keyboard focus.
	 */
	@Override
	public void focusLost(FocusEvent e) {
		//text=null;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		showMe();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		if(!event.getAncestor().getBounds().equals(new Rectangle(0,0,0,0))){
			showMe();
		}
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setField(Field field) {
		// TODO Auto-generated method stub
		this.field=field;
	}

	@Override
	public Field getField() {
		// TODO Auto-generated method stub
		return field;
	}
}
