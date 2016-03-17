package cn.by.eform.ui.impl;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import cn.by.eform.draft.FormParser;
import cn.by.eform.model.Form;
import cn.by.eform.print.PrintableForm;
import cn.by.eform.print.Printer;
import cn.by.eform.ui.factory.FieldFactory;
import cn.by.eform.ui.framework.IController;
import cn.by.eform.ui.framework.INavBuilder;
import cn.by.eform.ui.framework.IPageBuilder;

public class Controler extends JPanel implements IController{
	
	//protected IPageBuilder pb;
	//protected INavBuilder nb;
	protected Form form;
	protected File formFile;
	
	private int currentFieldIndex=0;
	private JButton homeButton;
	private JButton resetBtn;

	/**
	 * Create the panel.
	 */
	public Controler() {
		FlowLayout fl_controller = new FlowLayout(FlowLayout.RIGHT);
		fl_controller.setVgap(10);
		fl_controller.setHgap(20);
		this.setLayout(fl_controller);
		//controller.setBorder(BorderFactory.createLineBorder(Color.black));
		/*
		JButton btnPrintPreview = FieldFactory.createButton("打印预览");
		btnPrintPreview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(form!=null){
					JFrame previewFrame=new JFrame();
					PrintPreview printPreview=new PrintPreview(form);
					previewFrame.setTitle("打印预览");
					previewFrame.getContentPane().add(printPreview);
					previewFrame.setBounds(0, 0, 800, 800);
					previewFrame.setVisible(true);
				}
			}
		});
		controller.add(btnPrintPreview);*/
		
		JButton printBtn = FieldFactory.createButton("打印");
		printBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				print();
			}
		});
		
		homeButton = FieldFactory.createButton("返回");
		this.add(homeButton);
		this.add(printBtn);
		
		resetBtn = FieldFactory.createButton("清除");
		resetBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetForm();
			}
		});
		this.add(resetBtn);
		this.setOpaque(false);
	}

	@Override
	public Form getForm() {
		// TODO Auto-generated method stub
		return form;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		if(form!=null){
			Printer.printReport(new PrintableForm(form));
		}
	}

	@Override
	public void setForm(Form form) {
		// TODO Auto-generated method stub
		this.form=form;
	}

	@Override
	public void resetForm() {
		// TODO Auto-generated method stub
		if(form!=null){
			form.resetData();
		}
	}

	@Override
	public void resetField() {
		// TODO Auto-generated method stub
		form.getFields().get(currentFieldIndex).resetData();
	}

	@Override
	public void reflash() {
		// TODO Auto-generated method stub
	}
	
	public JButton getReturnButton(){
		return homeButton;
	}
	
	public JButton getResetButtion() {
		return resetBtn;
	}

}
