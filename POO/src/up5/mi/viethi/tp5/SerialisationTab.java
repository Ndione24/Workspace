package up5.mi.viethi.tp5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * Question a
 * Ecrire un programme permettant d'enregistrer dans un fichier un tableau d'entiers sérialisé
 * Question b
 * Ecrire un autre programme permettant de récupérer ce tableau sérialisé et d'afficher 
 * les éléments de ce tableau
 */
public class SerialisationTab {

	public static void writeSTabInt(int[] tabInt, File file) throws IOException {
		if (file.exists() && !file.canWrite()) {
			throw new IOException("Impossible d'écrire dans le fichier destination");
		}
		// serialisation
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(tabInt);
		os.close();
	}

	public static void readSTabInt(File file) throws ClassNotFoundException, FileNotFoundException, IOException {
		if (!file.canRead()) {
			throw new IOException("Impossible d'ouvrir le fichier source");
		}
		// deserialisation
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
		int[] tabInt = (int[]) is.readObject();
		
		System.out.print("{ " + tabInt[0]);
		for (int i = 1; i < tabInt.length; ++i) {
			System.out.print(", " + tabInt[i]);
		}
		System.out.print(" }");
		
		is.close();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		int[] tabInt = { 1, 2, 3, 4 };
		File file = new File("donnees2.txt");
		System.out.println("Ecriture du tableau d'entier dans le fichier");
		writeSTabInt(tabInt, file);
		System.out.println("Lecture du fichier");
		readSTabInt(file);
	}

}
