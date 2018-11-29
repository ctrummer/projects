package com.trummer.javarestarttraining;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileWriter {

	private static final Logger logger = LogManager.getLogger(FileWriter.class);

	public static void readFile() {
		String current = System.getProperty("user.home");
		logger.info("Home of current user: " + current);
		try {
			current = new java.io.File(".").getCanonicalPath();
			System.out.println("Current directory: " + current);
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e1);
		}

		final FileSystem fs = FileSystems.getDefault();
		System.out.println(System.getProperty("user.home"));
		final Path p = fs.getPath(current, "target", "classes", "MyText.txt");
//		final Path p = fs.getPath("C:\\Users\\chris\\Documents\\temp\\MyText.txt"); // C:\Users\chris\Documents\temp
		writePathInfoToConsole(p);
		try {
			writeFileContentToConsole(p);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void writeFileContentToConsole(Path path) throws IOException {
//		final URI uri = ListAllLines.class.getResource("/lyrics.txt").toURI();
//		final Path path = Paths.get(uri);
//
//		final path.get
		System.out.printf("Datei '%s' mit Länge %d Byte(s) hat folgende Zeilen:%n", path.getFileName(),
				Files.size(path));
		int lineCnt = 1;
		for (final String line : Files.readAllLines(path /* , StandardCharsets.UTF_8 vor Java 8 */)) {
			System.out.println(lineCnt++ + ": " + line);
		}
	}

	private static void writePathInfoToConsole(Path p) {
//		final Path p = Paths.get("C:/Windows/Fonts/");
		System.out.println(p.toString()); // C:\Windows\Fonts
		System.out.println(p.isAbsolute()); // true
		System.out.println(p.getRoot()); // C:\
		System.out.println(p.getParent()); // Fonts
		System.out.println(p.getNameCount()); // 2
		System.out.println(p.getName(p.getNameCount() - 1)); // Fonts

	}

}
