package com.beyond.utils;

import java.io.File;
import java.util.UUID;

public class DirUtils {
    public static String getDir(String filename) {
        int hashCode = filename.hashCode();
        int dir1 = hashCode & 0xf;
        int dir2 = (hashCode >>> 4) & 0xf;

        return "/" + dir1 + "/" + dir2;
    }

    public static String getAbsoluteDir(String root, String filename) {

        String path = RequestResponseBox.getRequest().getServletContext().getRealPath("/" + root) + getDir(filename);
        File parent = new File(path);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        return path;
    }

    public static String getAbsolutePath(String root, String filename) {

        String path = RequestResponseBox.getRequest().getServletContext().getRealPath("/" + root) + getDir(filename);
        File parent = new File(path);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        return path + "/" + filename;
    }

    public static String getUri(String root, String storageFileName) {
        return root + getDir(storageFileName) + "/" + storageFileName;
    }

    public static String getParentUri(String root, String storageFileName) {
        return root + getDir(storageFileName) + "/";
    }

    public static String getUniqueFileName(String oldFileName) {
        return UUID.randomUUID().toString() + "_" + oldFileName;
    }
}
