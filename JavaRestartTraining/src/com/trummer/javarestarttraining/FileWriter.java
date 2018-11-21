package com.trummer.javarestarttraining;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriter {

	private static void writeFileContentToConsole(Path path) throws IOException {
//		final URI uri = ListAllLines.class.getResource("/lyrics.txt").toURI();
//		final Path path = Paths.get(uri);
//
//		final path.get
		System.out.printf("Datei '%s' mit L�nge %d Byte(s) hat folgende Zeilen:%n", path.getFileName(),
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

	public static void readFile() {

		final FileSystem fs = FileSystems.getDefault();
		final Path p = fs.getPath("C:\\Users\\chris\\Documents\\temp\\MyText.txt"); // C:\Users\chris\Documents\temp
		writePathInfoToConsole(p);
		try {
			writeFileContentToConsole(p);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
