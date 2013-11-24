package up5.mi.viethi.tp5;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CopieFichier {

	
	private static void bCopy(final File fSource, final File fDest) throws IOException {
		if (! fSource.canRead()) {
			throw new IOException("Impossible d'ouvrir le fichier source");
		} else if ( fDest.exists() && !fDest.canWrite() ) {
			throw new IOException("Impossible d'écrire dans le fichier destination");
		}
		// Reader du fichier source
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(fSource));
		// Writer du fichier
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fDest));

		int value = 0;
		// Lecture du fichier du fichier source et écriture du fichier destination
		while ( (value = in.read() ) != -1) 
			out.write(value);
		
		in.close(); out.close();
	}
	
	private static void copy(final File fSource, final File fDest) throws IOException {
		if (! fSource.canRead()) {
			throw new IOException("Impossible d'ouvrir le fichier source");
		} else if ( fDest.exists() && !fDest.canWrite() ) {
			throw new IOException("Impossible d'écrire dans le fichier destination");
		}
		
		InputStreamReader in = new InputStreamReader(new FileInputStream(fSource));
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fDest));
		
		int value = 0;
		// Lecture du fichier du fichier source et écriture du fichier destination
		while ( (value = in.read() ) != -1) 
			out.write(value);
		
		in.close(); out.close();
	}
	
	private static void copyDir(final File repSource, final File repDest) throws IOException {
		if (!repDest.exists()) {
			repDest.mkdir();
		}
		
		final File[] listFiles = repSource.listFiles();
		File fSource;
		for (int i = 0; i < listFiles.length; ++i) {
			fSource = listFiles[i];
			System.out.println(fSource);
			bCopy(fSource, new File(repDest, fSource.getName()));
		}
	}
	

	public static void main(String[] args) throws IOException {
		/*
		final File fSource = new File("exemple.txt");
		final File fDest = new File("exemple(copie).txt");
		System.out.println("Copie du fichier " + fSource.getAbsolutePath() + " dans " + fDest.getName());
		bCopy(fSource, fDest);
		copy(fSource, fDest);
		System.out.println("Copie du fichier terminé");
		*/
		final File dSource = new File("repSource");
		final File dDest = new File("repDestination");
		System.out.println("Copie du répertoire " + dSource.getAbsolutePath() + " dans " + dDest.getName());
		copyDir(dSource, dDest);
		System.out.println("Copie du répertoire terminé");
	}

}
