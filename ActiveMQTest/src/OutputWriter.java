
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class OutputWriter {

	private final String fn;
	private final Writer fw;

	public OutputWriter(String fn) throws IOException {
		this.fn = fn;
		fw = new FileWriter(fn);
	}

	public void close() {
		if (fw != null) {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeln(String text) {
		try {
			fw.write((new Date()) + " : ");
			fw.write(text);
			fw.append(System.getProperty("line.separator"));
			fw.flush();
		} catch (IOException e) {
			System.err.println("Error while writing to: " + fn);
			close();
		}
	}

}
