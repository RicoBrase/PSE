package de.rico_brase.pse.utils;

import java.io.File;
import java.io.FileFilter;

public class PSEFilter implements FileFilter{

	String description = "";
    String fileExt = "";

    public PSEFilter(String extension) {
        fileExt = extension;
    }

    public PSEFilter(String extension, String typeDescription) {
        fileExt = extension;
        this.description = typeDescription;
    }

    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    public String getDescription() {
        return description;
    }
}