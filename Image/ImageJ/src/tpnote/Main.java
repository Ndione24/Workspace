package tpnote;

import ij.ImagePlus;
import ij.process.ImageProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import tp5.FiltreGaussien;
import tp5.Outils;

public class Main  {

	public static ImagePlus postTraitement(ImagePlus imp) {
		imp = tp4.Image.createGrayImage(imp);
		ImageProcessor ip = imp.getProcessor();
		new FiltreGaussien(ip, 17);
		imp = tp4.Image.createOTSUImage(imp);
		return imp;
	}
	
	public static void main(String[] args) {
		String foldername = "imagesOK/";
		File dossier = new File(foldername);
		File[] images = dossier.listFiles();
		Arrays.sort(images);
		for (File image : images) {
			ImagePlus imp = Outils.openImage(image.getName());
			imp = postTraitement(imp);
			imp.show();
		}
	}
}
