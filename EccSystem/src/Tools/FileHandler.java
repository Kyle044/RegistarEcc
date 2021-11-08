package Tools;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class FileHandler {
	public  BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	public BufferedImage scaleimage(int wid, int hei, BufferedImage img){
	    Image im = img;
	    double scale;
	    double imw = img.getWidth();
	    double imh = img.getHeight();
	    if (wid > imw && hei > imh){
	        im = img;
	    }else if(wid/imw < hei/imh){
	        scale = wid/imw;
	        im = img.getScaledInstance((int) (scale*imw), (int) (scale*imh), Image.SCALE_SMOOTH);
	    }else if (wid/imw > hei/imh){
	        scale = hei/imh;
	        im = img.getScaledInstance((int) (scale*imw), (int) (scale*imh), Image.SCALE_SMOOTH);
	    }else if (wid/imw == hei/imh){
	        scale = wid/imw;
	        im = img.getScaledInstance((int) (scale*imw), (int) (scale*imh), Image.SCALE_SMOOTH);
	    } 
	    return this.toBufferedImage(im);
	}
	
	 public BufferedImage crop(double amount,BufferedImage originalImage) throws IOException {
	     
	        int height = originalImage.getHeight();
	        int width = originalImage.getWidth();

	        int targetWidth = (int)(width * amount);
	        int targetHeight = (int)(height * amount);
	        // Coordinates of the image's middle
	        int xc = (width - targetWidth) / 2;
	        int yc = (height - targetHeight) / 2;

	        // Crop
	        BufferedImage croppedImage = originalImage.getSubimage(
	                        xc, 
	                        yc,
	                        targetWidth, // widht
	                        targetHeight // height
	        );
	        return croppedImage;
	    }

	

	
	public boolean imagesOnly(File file) {
		boolean isIt=false;
		if(this.getFileExtension(file).equals(".png")||this.getFileExtension(file).equals(".jpg")) {
			isIt=true;
		}
		else {
			isIt=false;
		}
		return isIt;
	}
	private String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	
	
	
	
	
	public File getSingleFile() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setMultiSelectionEnabled(true);
		int response = filechooser.showSaveDialog(null);
		File file;
		if (response == JFileChooser.APPROVE_OPTION) {
			file = filechooser.getSelectedFile();
				System.out.println("You chose file " + file.getName());
			return file;

		} else {
			System.out.println("You did not choose a file.");
			return null;
		}
	}
	
	
	
	
	
	public File[] getAttachment() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setMultiSelectionEnabled(true);
		int response = filechooser.showSaveDialog(null);
		File[] file;
		if (response == JFileChooser.APPROVE_OPTION) {
			file = filechooser.getSelectedFiles();
			for (File f : file) {
				System.out.println("You chose file " + f.getName());
			}
			return file;

		} else {
			System.out.println("You did not choose a file.");
			return null;
		}
	}
	
	public void deleteDirectory(File file) {
		// store all the paths of files and folders present
		// inside directory
		for (File subfile : file.listFiles()) {

			// if it is a subfolder,e.g Rohan and Ritik,
			// recursiley call function to empty subfolder
			if (subfile.isDirectory()) {
				deleteDirectory(subfile);
			}

			// delete files and empty subfolders
			subfile.delete();
		}

		if (file.delete()) {
			System.out.println("Successfully Deleted The File Also");
		} else {
			System.out.println("Did not Deleted the file");
		}

	}
	
	
	public void readFile(File file) {

		if (!Desktop.isDesktopSupported()) {
			System.out.println("Desktop is not supported");
		} else {
			Desktop desktop = Desktop.getDesktop();
			if (file.exists()) {
				try {
					desktop.open(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
