package org.jbake.app;

import org.jbake.parser.Engines;

import java.io.File;
import java.io.FileFilter;
import java.net.URLDecoder;

/**
 * Provides File related functions
 * 
 * @author Jonathan Bullock <jonbullock@gmail.com>
 *
 */
public class FileUtil {
	
	/**
	 * Filters files based on their file extension.
	 * 
	 * @return	Object for filtering files
	 */
	public static FileFilter getFileFilter() {
		return new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return !pathname.isFile()
						|| Engines.getRecognizedExtensions().contains(fileExt(pathname));
			}
		};
	}

    public static boolean isExistingFolder(File f) {
        return null != f && f.exists() && f.isDirectory();
    }
    
    /**
     * Works out the folder where JBake is running from.
     * 
     * @return File referencing folder JBake is running from
     * @throws Exception
     */
    public static File getRunningLocation() throws Exception {
    	// work out where JBake is running from
		String codePath = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(codePath, "UTF-8");
		File codeFile = new File(decodedPath);
		if (!codeFile.exists()) {
			throw new Exception("Cannot locate running location of JBake!");
		}
		File codeFolder = codeFile.getParentFile();
		if (!codeFolder.exists()) {
			throw new Exception("Cannot locate running location of JBake!");
		}
		
		return codeFolder;
    }

    public static String fileExt(File src) {
        String name = src.getName();
        int idx = name.lastIndexOf('.');
        if (idx > 0) {
            return name.substring(idx + 1);
        } else {
            return "";
        }
    }
}
