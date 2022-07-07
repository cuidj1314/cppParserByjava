package com.work.listener;

import com.parser.CPP14Lexer;
import com.parser.CPP14Parser;
import com.work.readfile.ReadFiles;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ListenerMain {

    public static void main(String[] args) {
        ReadFiles readFile = new ReadFiles();
        String path = "C:\\Users\\cuidj\\Desktop\\test";
        List<File> files = readFile.getFiles(path);

        files.forEach(x -> {
            try {
                String str = Files.readString(x.toPath());
                // ?��?�@���͊�
                CPP14Lexer lexer = new CPP14Lexer(CharStreams.fromString(str));
                // �V���꘢?�@�����I?�t��C�p����??�@���͊퐶���I?�@����
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                // �V���꘢?�@���͊�C?������?�t����e
                CPP14Parser parser = new CPP14Parser(tokens);
                // ????�C?�n?�@����
                CPP14Parser.RefqualifierContext context = parser.refqualifier();
                // ?��?�ߊ�
                ListenerRewrite listener = new ListenerRewrite();
                // �g�p?�ߊ평�n��??�@����?�I��?
                ParseTreeWalker.DEFAULT.walk(listener, context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
