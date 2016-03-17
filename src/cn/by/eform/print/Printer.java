package cn.by.eform.print;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.Size2DSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.MediaName;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JOptionPane;

public class Printer {
	public static void printReport(Printable print) {
		callSwingDialog(print);
		//callSystemDialog(print);
	}

	public static void callSwingDialog(Printable print) {
		 /* Construct the print request specification.
         * The print data is a Printable object.
         * the request additonally specifies a job name, 2 copies, and
         * landscape orientation of the media.
         */
         PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
         //aset.add(OrientationRequested.LANDSCAPE);
         aset.add(new Copies(1));
         aset.add(new JobName("表单打印系统", null));
         
         aset.add(MediaSizeName.ISO_A4);
         aset.add(new MediaPrintableArea(0,0,210,297,Size2DSyntax.MM));
         

         /* Create a print job */
         PrinterJob pj = PrinterJob.getPrinterJob();       
         pj.setPrintable(print);
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

	public static void callCustomDialog(Printable print) {
		int selected = JOptionPane.showConfirmDialog(null, "是否进行打印", "打印",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

		if (selected == JOptionPane.NO_OPTION)
			return;

		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);
		PrintService defaultService = PrintServiceLookup
				.lookupDefaultPrintService();

		PrintService service = defaultService;
		if (service != null) {
			try {
				DocPrintJob job = service.createPrintJob();
				DocAttributeSet das = new HashDocAttributeSet();
				Doc doc = new SimpleDoc(print, flavor, das);
				job.print(doc, pras);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void callSystemDialog(Printable print) {
		PrinterJob pj = PrinterJob.getPrinterJob();
		PageFormat pf = PrinterJob.getPrinterJob().defaultPage();
		Paper paper = pf.getPaper();

		double pageWidth = 1024;
		double pageHeight = 1024;
		paper.setSize(pageWidth, pageHeight);

		paper.setImageableArea(0, 0, pageWidth, pageHeight);
		paper.setSize(1024, 1024);
		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(paper);

		pj.setPrintable(print, pf);

		if (pj.printDialog()) {

			try {
				pj.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
	}
}
