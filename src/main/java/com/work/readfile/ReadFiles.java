package com.work.readfile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * フォルダにすべてファイルを読む
 */
public class ReadFiles {

    private final List<File> list = new ArrayList<>();

    public List<File> getFiles(String path) {

        File dirAndFile = new File(path);
        if (dirAndFile.isFile()) {
            list.add(dirAndFile.getAbsoluteFile());
        } else if (dirAndFile.isDirectory()) {
            String[] fileList = dirAndFile.list();
            if (null != fileList) {
                for (String fileName : fileList) {
                    getFiles(path + "/" + fileName);
                }
            }
        }
        return list;
    }
}
