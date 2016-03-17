package cn.by.eform.draft;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JList;

import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import java.awt.Color;
import java.io.File;
import cn.by.eform.model.Form;
import cn.by.eform.print.PrintableForm;
import cn.by.eform.print.Printer;
import cn.by.eform.ui.Global;
import cn.by.eform.ui.PrintPreview;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.IController;
import cn.by.eform.ui.framework.INavBuilder;
import cn.by.eform.ui.framework.IPageBuilder;
import cn.by.eform.ui.framework.ISelectionListener;
import cn.by.eform.ui.impl.ButtonNavBuilder;
import cn.by.eform.ui.impl.Controler;
import cn.by.eform.ui.impl.LabelNavBuilder;
import cn.by.eform.ui.impl.ListNavBuilder;
import cn.by.eform.ui.impl.StepPage;
import cn.by.eform.ui.impl.TablePage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import java.awt.Canvas;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;


public class DraftMain{

	private JFrame frame;
	private JPanel panel;
	
	private Controler controller;
	private CardLayout cl;
	private JPanel top_mgrPanel;
	
	private JComponent formComponent;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 try {
             UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
             UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
             
             UIManager.put("OptionPane.buttonFont", new FontUIResource(FieldFactory.font));
             
         } catch (Exception e) {
             System.err.println("Seaglass LAF not available using Ocean.");
             try {
                 UIManager.setLookAndFeel(new MetalLookAndFeel());
             } catch (Exception e2) {
                 System.err.println("Unable to use Ocean LAF using default.");
             }
         }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DraftMain window = new DraftMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DraftMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("表单打印系统v1.1");
		
		frame.setBounds(Global.getInstance().getAppRect());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("10px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("984px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("10px"),},
			new RowSpec[] {
				RowSpec.decode("53dlu"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("45dlu"),}));
		
		
		JPanel left_listPanel = FieldFactory.createPanel();
		
		frame.getContentPane().add(left_listPanel, "1, 3, left, fill");
		
		panel = FieldFactory.createPanel();
		//panel.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.getContentPane().add(panel, "3, 3, fill, fill");
		cl = new CardLayout(0, 0);
		panel.setLayout(cl);
		
		controller=new Controler();
		controller.getReturnButton().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				//cl.show(panel,"nav");
				if(formComponent!=null){
					cl.removeLayoutComponent(formComponent);
					panel.remove(formComponent);
					formComponent=null;
					
					setBg("./img/bg1.jpg");
				}
			}
		});
		controller.getResetButtion().addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(formComponent!=null){
					cl.removeLayoutComponent(formComponent);
					panel.remove(formComponent);
					formComponent=null;
					
					IPageBuilder pageBuilder = new StepPage();
					formComponent=pageBuilder.buildForm(controller.getForm());
					panel.add(formComponent,"page");
					
					cl.show(panel, "page");
				}
			}
		});
		
		
		INavBuilder  navBuilder=new LabelNavBuilder();//ListNavBuilder();
		ISelectionListener selectionListener=new ISelectionListener(){
			@Override
			public void selected(Form form) {
				
				setBg("./img/bg2.jpg");
				//
				//panel.remove(formComponent);
				IPageBuilder pageBuilder = new StepPage();
				formComponent=pageBuilder.buildForm(form);
				panel.add(formComponent,"page");
				
				cl.show(panel, "page");
				controller.setForm(form);
			}

			@Override
			public void selectedGroup(String path) {
				
			}};
		panel.add(navBuilder.buildNav(new File("./forms"),selectionListener ),"nav");
		
		frame.getContentPane().add(controller, "1, 5, 5, 1, fill, top");
		
		frame.getContentPane().setBackground(SystemColor.activeCaption);
		
		
		
		JPanel rightPanel = FieldFactory.createPanel();
		frame.getContentPane().add(rightPanel, "5, 3, left, fill");
		
		setBg("./img/bg1.jpg");
		
		//frame.setAlwaysOnTop(true);    //总在最前面
		frame.setResizable(false);    //不能改变大小
		frame.setUndecorated(true);    //不要边框

		//SeaGlassTitlePane
	}
	
	public void setBg(String bg){
		if(lblNewLabel1!=null){
			frame.getLayeredPane().remove(lblNewLabel1);
		}
		
		ImageIcon bgImg1 = new ImageIcon(bg);
		lblNewLabel1 = new JLabel(bgImg1);
		lblNewLabel1.setBounds(0, 0, bgImg1.getIconWidth(), bgImg1.getIconHeight());  
		frame.getLayeredPane().setLayout(null); 
		((JPanel)frame.getContentPane()).setOpaque(false);

		
		frame.getLayeredPane().add(lblNewLabel1, new Integer(Integer.MIN_VALUE));
		frame.validate();
		frame.repaint();
	}
	
	private JLabel lblNewLabel1;

}
