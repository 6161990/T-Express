package com.yoon.TExpress.modernJavaInAction.three;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Process {

    public String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/6161990src/Documents/GitHub/T-Express/functional-java/src/test/java/com/yoon/TExpress/modernJavaInAction/data.txt"))){
            return br.readLine();
        }
    }

    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/6161990src/Documents/GitHub/T-Express/functional-java/src/test/java/com/yoon/TExpress/modernJavaInAction/data.txt"))){
            return p.process(br);
        }
    }
}
