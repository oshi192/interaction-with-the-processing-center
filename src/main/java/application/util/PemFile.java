package application.util;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.*;

/**
 * class for reading and writing .pem files
 */
public class PemFile {

	private PemObject pemObject;

	public PemFile(String filePath) throws  IOException {
		PemReader pemReader = new PemReader(new InputStreamReader(
				new FileInputStream(filePath)));
		try {
			this.pemObject = pemReader.readPemObject();
		} finally {
			pemReader.close();
		}
	}

	public void write(String filePath) throws IOException {
		PemWriter pemWriter = new PemWriter(new OutputStreamWriter(
				new FileOutputStream(filePath)));
		try {
			pemWriter.writeObject(this.pemObject);
		} finally {
			pemWriter.close();
		}
	}

	public PemObject getPemObject() {
		return pemObject;
	}

}
