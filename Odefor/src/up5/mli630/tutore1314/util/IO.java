package up5.mli630.tutore1314.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IO {

	public static void save(Object object, File file) throws IOException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
		} finally {
			if (oos != null)
				oos.close();
			else if (fos != null)
				fos.close();
		}
	}

	public static Object getObject(File file) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			return ois.readObject();
		} finally {
			if (ois != null)
				ois.close();
			else if (fis != null)
				fis.close();
		}
	}

}
