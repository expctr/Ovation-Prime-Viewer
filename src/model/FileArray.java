package model;

import java.io.*;

public class FileArray {
    public final String path;

    private int length = 0;

    public FileArray(String path) throws IOException {
        this.path = path;
        clear();
    }

    public void add(String string) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true));
        bufferedWriter.write(string + "\n");

        bufferedWriter.close();
        ++length;
    }

    public int size() {
        return length;
    }

    public void clear() throws IOException {
        FileWriter fileWriter = new FileWriter(path, false);
        length = 0;
    }

    public String get(int index) throws IOException {
        if ((index < 0) || (length <= index)) {
            throw new IndexOutOfBoundsException();
        }

        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader(path));

        for (int i = 0; i < index; ++i) {
            bufferedReader.readLine();
        }

        String result = bufferedReader.readLine();
        bufferedReader.close();

        return result;
    }
}
