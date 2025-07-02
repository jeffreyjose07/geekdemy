package com.example.geektrust;

import com.example.geektrust.command.CommandProcessor;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleIOIntegrationTest {

    @Test
    void input1ProducesExpectedOutput() throws Exception {
        assertOutputMatches("sample_input/input1.txt", "sample_input/output1.txt");
    }

    @Test
    void input2ProducesExpectedOutput() throws Exception {
        assertOutputMatches("sample_input/input2.txt", "sample_input/output2.txt");
    }

    @Test
    void input3ProducesExpectedOutput() throws Exception {
        assertOutputMatches("sample_input/input3.txt", "sample_input/output3.txt");
    }

    @Test
    void input4ProducesExpectedOutput() throws Exception {
        assertOutputMatches("sample_input/input4.txt", "sample_input/output4.txt");
    }

    private void assertOutputMatches(String inputFile, String outputFile) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));

        new CommandProcessor().run(inputFile);

        System.setOut(original);

        String expected = Files.readString(Path.of(outputFile)).replace("\r", "").trim();
        String actual = out.toString().replace("\r", "").trim();
        assertEquals(expected, actual);
    }
}
