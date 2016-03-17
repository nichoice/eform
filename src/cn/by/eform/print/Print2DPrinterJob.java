package cn.by.eform.print;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.awt.image.*;
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import cn.by.eform.ui.factory.FieldFactory;

public class Print2DPrinterJob implements Printable {

        public Print2DPrinterJob() {

                /* Construct the print request specification.
                * The print data is a Printable object.
                * the request additonally specifies a job name, 2 copies, and
                * landscape orientation of the media.
                */
                PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
                //aset.add(OrientationRequested.LANDSCAPE);
                aset.add(new Copies(1));
                aset.add(new JobName("My job", null));
                
                aset.add(MediaSizeName.ISO_A4);
                aset.add(new MediaPrintableArea(0,0,210,297,Size2DSyntax.MM));
                

                /* Create a print job */
                PrinterJob pj = PrinterJob.getPrinterJob();       
                pj.setPrintable(this);
                /* locate a print service that can handle the request */
                PrintService[] services =
                        PrinterJob.lookupPrintServices();
                
                PrintService defaultService = PrintServiceLookup
        				.lookupDefaultPrintService();

                if (services.length > 0) {
                        System.out.println("selected printer " + defaultService.getName());
                        try {
                                pj.setPrintService(defaultService);
                                //pj.pageDialog(aset);
                                if(pj.printDialog(aset)) {
                                        pj.print(aset);
                                }
                        } catch (PrinterException pe) { 
                                System.err.println(pe);
                        }
                }
        }

        public int print(Graphics g,PageFormat pf,int pageIndex) {

                if (pageIndex == 0) {
                        Graphics2D g2d= (Graphics2D)g;
                        /*
                        g2d.translate(pf.getImageableX(), pf.getImageableY());
                        g2d.setColor(Color.black);
                        g2d.drawString("example string", 250, 250);
                        g2d.fillRect(0, 0, 200, 200);
                        */
                       
                        int weight=1000;
                        int height=1000;
                        
                        int netSize=20;
                        
                		g.setFont(new Font("宋体",0,10));
                		
                		for(int i=0;i<weight;i+=netSize){
                			g.drawLine(0, i, height, i);
                			
                			if(i%netSize==0){
                				g.drawString(String.valueOf(i), 0, i+10);
                			}
                		}
                		
                		for(int i=0;i<height;i+=netSize){
                			g.drawLine(i, 0, i, weight);
                			
                			if(i%netSize==0){
                				g.drawString(String.valueOf(i), i, 10);
                			}
                		}
                        
                        return Printable.PAGE_EXISTS;                                   
                } else {
                        return Printable.NO_SUCH_PAGE;
                }
        }

        public static void main(String arg[]) {
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

                Print2DPrinterJob sp = new Print2DPrinterJob();
        }
}