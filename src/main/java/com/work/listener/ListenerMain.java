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
                // ?建?法分析器
                CPP14Lexer lexer = new CPP14Lexer(CharStreams.fromString(str));
                // 新建一个?法符号的?冲区，用于存??法分析器生成的?法符号
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                // 新建一个?法分析器，?理符号?冲区内容
                CPP14Parser parser = new CPP14Parser(tokens);
                // ????，?始?法分析
                CPP14Parser.RefqualifierContext context = parser.refqualifier();
                // ?建?听器
                ListenerRewrite listener = new ListenerRewrite();
                // 使用?听器初始化??法分析?的遍?
                ParseTreeWalker.DEFAULT.walk(listener, context);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
