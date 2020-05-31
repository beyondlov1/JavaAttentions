package com.beyond.hello;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PackageScanner {
    public static List<String> scanForClassName(String packageName) throws IOException {
        ArrayList<String> fileNameList = new ArrayList<String>();
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName.replaceAll("\\.", "/"));
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if (url != null) {
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    String packagePath = url.getPath();
                    scanDir(packagePath,packageName,fileNameList);
                }
            }
        }
        return fileNameList;
    }

    private static void scanDir(String path, String packageName, List<String> fileNameList){
        File[] childFiles = new File(path).listFiles();
        for (File childFile : childFiles != null ? childFiles : new File[0]) {
            if (childFile.isFile()){
                fileNameList.add(getClassName(packageName, childFile));
            }else if (childFile.isDirectory()){
                scanDir(childFile.getPath(), getClassName(packageName, childFile),fileNameList);
            }
        }
    }

    private static String getClassName(String packageName, File childFile) {
        String className =  packageName + "." + childFile.getName();
        if (className.endsWith(".class")){
            className = className.substring(0,className.lastIndexOf(".class"));
        }
        return className;
    }

    public static void main(String[] args) {
        try {
            scanForClassName("com.beyond.hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
