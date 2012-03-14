package jpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Arrays;
import java.util.Iterator;
import java.io.File;
import java.util.regex.Pattern;


public class Path {
	public static String join(String... args) {
		if (args.length == 0)
			return "";

		File file = new File(args[0]);
		for (int i = 1; i < args.length; i++)
			file = new File(file, args[i]);

		return file.getPath();
	}

    private static String _join(Collection s, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

	public static String abspath(String path) {
		return new File(path).getAbsolutePath();
	}

	public static String dirname(String path) {
		File file = new File(path);
		if (file.isDirectory())
			return path;

		String []items = path.split(Pattern.quote(File.separator));
		if (items.length == 0)
			return path;

		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < items.length - 1; i++)
			res.add(items[i]);

		return _join(res, File.separator);
	}

	public static void main(String []args) {
		String[] path = {"the", "path", "to", "file.txt"};
		System.out.println("Path: " + Path.join(path));
		System.out.println("Dirname: " + Path.abspath("build.xml"));
	}
}
