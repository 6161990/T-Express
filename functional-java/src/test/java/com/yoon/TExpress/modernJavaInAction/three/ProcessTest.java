package com.yoon.TExpress.modernJavaInAction.three;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ProcessTest {

    Process process = new Process();

    @Test
    void before() throws IOException {
        process.processFile();
    }

    @Test
    void after() throws IOException {
        String oneLines = process.processFile(BufferedReader::readLine);
        String twoLines = process.processFile((BufferedReader br) -> br.readLine() + br.readLine());

        assertThat(oneLines).isEqualTo("aaa=111");
        assertThat(twoLines).isEqualTo("aaa=111bbb=222");
    }
}