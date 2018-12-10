package com.nialljude.dev.files;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import javax.net.ssl.HttpsURLConnection;

import java.io.*;

import static org.junit.Assert.*;

public class FileManagerTest {

    HttpsURLConnection httpsURLConnection = mock(HttpsURLConnection.class);
    String testText;
    FileManager fileManager;

    @Before
    public void setUp() throws Exception {
        testText = "test";
        fileManager = new FileManager();
    }

    @Test
    public void writeRequestWorksWithMockedConnection() throws IOException {
        OutputStream testOutputStream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        };

        when(httpsURLConnection.getOutputStream()).thenReturn(testOutputStream);
        Boolean actual = fileManager.writeRequest(httpsURLConnection, testText);
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void writeRequestWorksWithSimulatedException() throws IOException {
        when(httpsURLConnection.getOutputStream()).thenThrow(IOException.class);
        Boolean actual = fileManager.writeRequest(httpsURLConnection, testText);
        Boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void readResponseWorksWithMockedConnection() throws IOException {
        InputStream testStream = new ByteArrayInputStream("test".getBytes());
        when(httpsURLConnection.getInputStream()).thenReturn(testStream);
        String actual = fileManager.readResponse(httpsURLConnection);
        String expected = "test\n";
        assertEquals(expected, actual);
    }

    @Test
    public void readResponseReturnsAndEmptyStringWithIOException() throws IOException {
        InputStream testStream = new ByteArrayInputStream("test".getBytes());
        when(httpsURLConnection.getInputStream()).thenThrow(IOException.class);
        String actual = fileManager.readResponse(httpsURLConnection);
        String expected = "";
        assertEquals(expected, actual);
    }

}