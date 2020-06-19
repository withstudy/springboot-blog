package com.xhb.blog.utils;

import java.io.File;
import java.util.ArrayList;

public class GetFileList {
    public static void getAllFileName(String path, ArrayList<String> fileNameList) {
        //ArrayList<String> files = new ArrayList<String>();
        boolean flag = false;
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                fileNameList.add(tempList[i].getName());
            }
            if (tempList[i].isDirectory()) {
                getAllFileName(tempList[i].getAbsolutePath(), fileNameList);
            }
        }
        return;
    }
}
