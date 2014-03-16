package tpnote;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.io.File;

import tp4.*;
import tp5.FiltreGaussien;
import tp5.Masque;
import tp5.Outils;

public class Main  {

	public static ImagePlus postTraitement(ImagePlus imp) {
		ImageProcessor ip = imp.getProcessor();
		new FiltreGaussien(ip, 17);
		return imp;
	}
	
	public static void main(String[] args) {
		String foldername = "source"+'/';
		File dossier = new File(foldername);
		File[] images = dossier.listFiles();
		
		ImagePlus imp = IJ.openImage(images[2].toString());
		imp = tp4.Image.createGrayImage(imp);
		postTraitement(imp);
		imp = tp4.Image.createOTSUImage(imp);
		imp.show();
	}
}
