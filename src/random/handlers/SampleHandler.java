package random.handlers;

import java.awt.AWTException;
import java.awt.Font;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.text.View;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
	}
	
	public Object execute(ExecutionEvent event) throws ExecutionException {

		
		IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		ITextOperationTarget target = (ITextOperationTarget)editorPart.getAdapter(ITextOperationTarget.class);
		if (target instanceof ITextViewer) {
			ITextViewer textViewer = (ITextViewer)target;
		
				
			/* 
			 * Sometimes the top row is not completely displayed so it is not possible to get the exact line number given a pixel value.
			 * The following gets the offset from the top bar for the first line
			 */ 
			//ITextEditor editor;
			
			
			java.awt.Rectangle area = new java.awt.Rectangle();
			Robot robot;

			try {
				robot = new Robot();
				org.eclipse.swt.graphics.Rectangle r = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getBounds();

			System.out.println("Static Features - ");
			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
	        org.eclipse.swt.graphics.Rectangle Part = HandlerUtil.getActiveWorkbenchWindow(event).getShell().getBounds();
	        
	        area.x = window.getShell().getBounds().x ;
		    area.y = window.getShell().getBounds().y ;
		    area.height = window.getShell().getBounds().height;
		    area.width = window.getShell().getBounds().width;
	        BufferedImage Image1 = robot.createScreenCapture(area);
	        ImageIO.write(Image1, "jpg", new File("C:\\image1"+".jpg"));
	        
	        area.x = Part.x;
		    area.y = Part.y;
		    area.height = Part.height;
		    area.width = Part.width;
	        BufferedImage Image2 = robot.createScreenCapture(area);
	        ImageIO.write(Image2, "jpg", new File("C:\\image2"+".jpg"));
	        
			Shell[] comp = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getShells();
			for(int i=0;i<comp.length;i++){
				System.out.println("Bounds for child "+ i +" are "+comp[i].getBounds());
				area.x = comp[i].getBounds().x;
			    area.y = comp[i].getBounds().y;
			    area.height = comp[i].getBounds().height;
			    area.width = comp[i].getBounds().width;
			    BufferedImage bufferedImage = robot.createScreenCapture(area);
			    //ImageIO.write(bufferedImage, "png", new File("/screenshot.png"));
			    //File temp = File.createTempFile("/home/samidha/Desktop/xamppppp", ".jpg");

		        // Use the ImageIO API to write the bufferedImage to a temporary file
		        ImageIO.write(bufferedImage, "jpg", new File("C:\\test"+i+".jpg"));
			} 
			System.out.println("1. Text Viewer Height : " + r.height + " and Width = "+ r.width);
			System.out.println("2. Text Viewer Coordinates : ("+r.x+","+r.y+")");
			System.out.println("3. Active Tab : "+PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPage().getActiveEditor().getEditorInput().getName());
			org.eclipse.swt.graphics.Font ff = textViewer.getTextWidget().getFont();
			FontData f[] = ff.getFontData();
			//FontData[] fData=f.getFontData();
			FontData firstFontData=f[0];
			System.out.println("4. Font Name : "+ firstFontData.getName());
			System.out.println("5. Font height : "+ firstFontData.getHeight());
			int fontStyle = firstFontData.getStyle();
			
			if ((fontStyle & Font.BOLD) == Font.BOLD)
			      System.out.println("6. Font Style : Bold");
			if ((fontStyle & Font.ITALIC) == Font.ITALIC)
			    	System.out.println("6. Font Style : Italic");
			if ((fontStyle & Font.PLAIN) == Font.PLAIN)
			    	System.out.println("6. Font Style : Normal");
			Color col = textViewer.getTextWidget().getBackground();
			System.out.println("7. Background Colour : "+col.toString());	
			
			
			
			
			
		    r = textViewer.getTextWidget().getBounds();
		    Composite s = textViewer.getTextWidget().getParent();
		    org.eclipse.swt.graphics.Rectangle t = s.getBounds();
		    //System.out.println(s.getParent().getBounds());
		    
		    Point absolute = textViewer.getTextWidget().toDisplay(r.x, r.y);
		    r.x = absolute.x;
		    r.y = absolute.y;
		    
		    
		    area.x= r.x;
		    area.y = r.y;
		    area.height = r.height;
		    area.width = r.width;
		    BufferedImage bufferedImage = robot.createScreenCapture(area);
		    //ImageIO.write(bufferedImage, "png", new File("/screenshot.png"));
		    File temp = File.createTempFile("/home/samidha/Desktop/xamppppp", ".jpg");

	        // Use the ImageIO API to write the bufferedImage to a temporary file
	        ImageIO.write(bufferedImage, "jpg", new File("C:\\test.jpg"));
	        System.out.println(temp.getPath());
	        // Delete temp file when program exits.
	        //temp.deleteOnExit();
		
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			         Document doc = dBuilder.newDocument();
			         // root element
			         Element rootElement = doc.createElement("IDE_Details");
			         doc.appendChild(rootElement);

			         //  IDE_Details element
			         Element supercar = doc.createElement("mode");
			         rootElement.appendChild(supercar);

			         // setting attribute to element
			         Attr attr = doc.createAttribute("type");
			         attr.setValue("Static");
			         supercar.setAttributeNode(attr);

			         // carname element
			         Element carname = doc.createElement("data");
			         Attr attrType = doc.createAttribute("category");
			         attrType.setValue("Text_Editor");
			         carname.setAttributeNode(attrType);
			         
			         Element h = doc.createElement("height");
			         h.appendChild(doc.createTextNode(Integer.toString(r.height)));
			         carname.appendChild(h);
			         supercar.appendChild(carname);
			         
			         Element w = doc.createElement("width");			         
			         w.appendChild(doc.createTextNode(Integer.toString(r.width)));
			         carname.appendChild(w);
			         supercar.appendChild(carname);
			         
			         //<XOrigin> ORIGIN : X </XOrigin>
					//	<YOrigin> ORIGIN : Y </YOrigin>
			         Element xorigin = doc.createElement("XOrigin");			         
			         xorigin.appendChild(doc.createTextNode("X ORIGIN : "+Integer.toString(r.x)));
			         carname.appendChild(xorigin);
			         supercar.appendChild(carname);
			         
			         Element yorigin = doc.createElement("YOrigin");			         
			         yorigin.appendChild(doc.createTextNode("Y ORIGIN : "+Integer.toString(r.y)));
			         carname.appendChild(yorigin);
			         supercar.appendChild(carname);
			         
			         Element color = doc.createElement("Background_Color");
			         color.appendChild(doc.createTextNode(""+col.toString()));
			         carname.appendChild(color);
			         supercar.appendChild(carname);
			         
			         Element carname1 = doc.createElement("data");
			         Attr attrType1 = doc.createAttribute("category");
			         attrType1.setValue("Font");
			         carname1.setAttributeNode(attrType1);
			         
			         //<Name> NAME </Name>
					//<Size> SIZE </Size>
					//<Style> STYLE </Style>
			         Element name = doc.createElement("Name");
			         name.appendChild(doc.createTextNode(firstFontData.name));
			         carname1.appendChild(name);
			         supercar.appendChild(carname1);
			         
			         Element size = doc.createElement("Size");
			         size.appendChild(doc.createTextNode(""+firstFontData.height));
			         carname1.appendChild(size);
			         supercar.appendChild(carname1);
			         
			         Element style = doc.createElement("Style");
			         style.appendChild(doc.createTextNode(""+firstFontData.style));
			         carname1.appendChild(style);
			         supercar.appendChild(carname1);
			         // write the content into xml file
			         TransformerFactory transformerFactory = TransformerFactory.newInstance();
			         Transformer transformer;
					
						transformer = transformerFactory.newTransformer();
					
			         DOMSource source = new DOMSource(doc);
			         StreamResult result = new StreamResult(new File("C:\\ide.xml"));
			        
						transformer.transform(source, result);
					
			         // Output to console for testing
			         StreamResult consoleResult = new StreamResult(System.out);
			         
						transformer.transform(source, consoleResult);
					} catch (TransformerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		printLineNumber(); // The main fuction that gets the current line number
		Job job = new Job("Running the job") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				while(true){
					try {
						// We simulate a long running operation here
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					printLineNumber();
				}
			}
		};
		job.setUser(true);
		job.schedule();
		return null;
	}
	public void printLineNumber() {
		Display.getDefault().asyncExec(new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				//System.out.println("File Name: " + PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPage().getActiveEditor().getEditorInput().getName());
				IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
				IWorkbenchPart workbenchPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
				if( workbenchPart instanceof ITextEditor) {
					ITextEditor editor = (ITextEditor) workbenchPart;

					IDocumentProvider provider = editor.getDocumentProvider();
					IDocument document = provider.getDocument(editorPart.getEditorInput());

					ITextSelection textSelection = (ITextSelection) editorPart.getSite().getSelectionProvider().getSelection();
					int offset = textSelection.getOffset();

					int lineNumber = -1;
					try {
						lineNumber = document.getLineOfOffset(offset);
					} catch (org.eclipse.jface.text.BadLocationException e) {
						e.printStackTrace();
					}
					lineNumber += 1; // The Line numbers start from 0
					// In case of selection, you can get the startLine and the endLine, so basically if the startLine == endLine, there is no selection
					int startLine = textSelection.getStartLine() + 1;// The Line numbers start from 0
					int endLine = textSelection.getEndLine() + 1;// The Line numbers start from 0
					int columnNumber = -1;
					try {
						columnNumber = textSelection.getOffset() - document.getLineOffset(lineNumber-1); // the Index starts from 0 i.e. the starting of the line, Here lineNumber-1 is passed because we added 1 before
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					// Getting the ruler displayed indices
					ITextOperationTarget target = (ITextOperationTarget)editorPart.getAdapter(ITextOperationTarget.class);
					if (target instanceof ITextViewer) {
						ITextViewer textViewer = (ITextViewer)target;
						int topIndex = textViewer.getTopIndex() + 1;  // The ruler top index
						int bottomIndex = textViewer.getBottomIndex() + 1; // The ruler bottom index
						System.out.println("Showing - ( " + topIndex + " , " + bottomIndex + " )");
						
						/* 
						 * Sometimes the top row is not completely displayed so it is not possible to get the exact line number given a pixel value.
						 * The following gets the offset from the top bar for the first line
						 */ 
						
						System.out.println("Dynamic Features : ");
						if( startLine == endLine ) {
							// No selection
							System.out.println("1. Cursor Position on Line " + lineNumber + ": "+ "\tColumn: " + columnNumber +"\tFile: "+ PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPage().getActiveEditor().getEditorInput().getName());
						} else {
							System.out.println("1. Lines Selected ( " +startLine+" , "+endLine + " )\tin File: " + PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPage().getActiveEditor().getEditorInput().getName());
						}
						
						System.out.println("2. Pixels Margin : " + textViewer.getTextWidget().getLinePixel(topIndex-1));
						Composite c = textViewer.getTextWidget().getParent();
						
						
						
						ScrollBar s = textViewer.getTextWidget().getHorizontalBar();
						System.out.println("3. Horizontal ScrollBar position : "+ s.getSelection());
						System.out.println("4. Min and max from Horizontal Scrollbar : "+ s.getMinimum() + "and "+s.getMaximum());
						
						ScrollBar t = textViewer.getTextWidget().getVerticalBar();
						System.out.println("5. Vertical ScrollBar position : "+ t.getSelection());
						System.out.println("6. Min and max from Scrollbar : "+ t.getMinimum() + "and "+t.getMaximum());
						
						
						try {
						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						DocumentBuilder documentBuilder;
						documentBuilder = documentBuilderFactory.newDocumentBuilder();
					      
						/* parse existing file to DOM */
						Document doc = documentBuilder.parse(new File("C:\\ide.xml"));
						Element root = doc.getDocumentElement();
						//System.out.println(root);
						Element supercar = doc.createElement("mode");

				         // setting attribute to element
				         Attr attr = doc.createAttribute("type");
				         attr.setValue("Dynamic");
				         supercar.setAttributeNode(attr);
						
				         //line number info
				         Element carname1 = doc.createElement("data");
				         Attr attrType1 = doc.createAttribute("category");
				         attrType1.setValue("Shown_Lines");
				         carname1.setAttributeNode(attrType1);
				         
				         Element startline = doc.createElement("Start_Line");
				         startline.appendChild(doc.createTextNode(""+topIndex));
				         carname1.appendChild(startline);
				         
				         Element endline = doc.createElement("End_Line");
				         endline.appendChild(doc.createTextNode(""+bottomIndex));
				         carname1.appendChild(endline);
				         
				         supercar.appendChild(carname1);
				         
				         //Scrollbar info
				         Element carname2 = doc.createElement("data");
				         Attr attrType2 = doc.createAttribute("category");
				         attrType2.setValue("Scrollbars");
				         carname2.setAttributeNode(attrType2);
				         
				         Element vertical = doc.createElement("VERTICAL");
				         vertical.appendChild(doc.createTextNode("VERTICAL ORIGIN "+t.getSelection()));
				         carname2.appendChild(vertical);
				         
				         Element minvertical = doc.createElement("MIN_VERTICAL");
				         minvertical.appendChild(doc.createTextNode("MINIMUM VERTICAL VALUE "+t.getMinimum()));
				         carname2.appendChild(minvertical);
				         
				         Element maxvertical = doc.createElement("MAX_VERTICAL");
				         maxvertical.appendChild(doc.createTextNode("MAXIMUM VERTICAL VALUE "+t.getMaximum()));
				         carname2.appendChild(maxvertical);
				         
				         Element horizontal = doc.createElement("HORIZONTAL");
				         horizontal.appendChild(doc.createTextNode("HORIZONTAL ORIGIN "+s.getSelection()));
				         carname2.appendChild(horizontal);
				         
				         Element minhorizontal = doc.createElement("MIN_HORIZONTAL");
				         minhorizontal.appendChild(doc.createTextNode("MINIMUM HORIZONTAL VALUE "+s.getMinimum()));
				         carname2.appendChild(minhorizontal);
				         
				         Element maxhorizontal = doc.createElement("MAX_HORIZONTAL");
				         maxhorizontal.appendChild(doc.createTextNode("MAXIMUM HORIZONTAL VALUE "+s.getMaximum()));
				         carname2.appendChild(maxhorizontal);
				         
				         supercar.appendChild(carname2);
				         
				         //Active Tab info
				         Element carname3 = doc.createElement("data");
				         Attr attrType3 = doc.createAttribute("category");
				         attrType3.setValue("Active_Tab");
				         carname3.setAttributeNode(attrType3);
				         
				         carname3.appendChild(doc.createTextNode(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPage().getActiveEditor().getEditorInput().getName()));
				         supercar.appendChild(carname3);
				         
				         //Pixel Offset
				         Element carname4 = doc.createElement("data");
				         Attr attrType4 = doc.createAttribute("category");
				         attrType4.setValue("Offset_From_Editor_Top_Margin");
				         carname4.setAttributeNode(attrType4);
				         
				         carname4.appendChild(doc.createTextNode(""+textViewer.getTextWidget().getLinePixel(topIndex-1)));
				         supercar.appendChild(carname4);
				         
				         //Cursor Position info
				         if( startLine == endLine ) {
								// No selection
				        	 	Element carname5 = doc.createElement("data");
				        	 	Attr attrType5 = doc.createAttribute("category");
						        attrType5.setValue("Cursor_Position");
						        carname5.setAttributeNode(attrType5);
						        
						        Element linenumber = doc.createElement("Line_Number");
						        linenumber.appendChild(doc.createTextNode("Line Number "+lineNumber));
						        carname5.appendChild(linenumber);
						         
						        Element column = doc.createElement("Column_Number");
						        column.appendChild(doc.createTextNode("Column Number "+columnNumber));
						        carname5.appendChild(column);
						        
						        supercar.appendChild(carname5);
						        
								//System.out.println("1. Cursor Position on Line " + lineNumber + ": "+ "\tColumn: " + columnNumber);
							} else {
								Element carname5 = doc.createElement("data");
				        	 	Attr attrType5 = doc.createAttribute("category");
						        attrType5.setValue("Line_Selection");
						        carname5.setAttributeNode(attrType5);
						        
						        Element start = doc.createElement("Start_Line");
						        start.appendChild(doc.createTextNode("Start Line Number "+startLine));
						        carname5.appendChild(start);
						        
						        Element end = doc.createElement("End_Line");
						        end.appendChild(doc.createTextNode("End Line Number "+endLine));
						        carname5.appendChild(end);
						        
						        supercar.appendChild(carname5);
								//System.out.println("1. Lines Selected ( " +startLine+" , "+endLine + " )\tin File: " + PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPage().getActiveEditor().getEditorInput().getName());
							}
				         
						 root.appendChild(supercar);
						 
						 TransformerFactory transformerFactory = TransformerFactory.newInstance();
				         Transformer transformer;
						
							
								transformer = transformerFactory.newTransformer();
							
						
				         DOMSource source = new DOMSource(doc);
				         StreamResult result = new StreamResult(new File("C:\\ide.xml"));
				        
							transformer.transform(source, result);
						
				         // Output to console for testing
				         StreamResult consoleResult = new StreamResult(System.out);
				         
							transformer.transform(source, consoleResult);
						 
						} catch (ParserConfigurationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TransformerConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} 
				}
			}
		});

	}
	/**
	 * the command has been executed, so extract the needed information
	 * from the application context.
	 */
	
}
