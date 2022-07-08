package com.work.visitor;

import com.parser.CPP14Lexer;
import com.parser.CPP14Parser;
import com.work.readfile.ReadFiles;
import com.work.visitor.parsercpp.GetNameSpace;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class VisitorMain {

    public static void main(String[] args) {

        ReadFiles readFile = new ReadFiles();
        String path = "C:\\Users\\cuidj\\Desktop\\test";
        List<File> files = readFile.getFiles(path);
        files.forEach(x -> {
            try {
                String strCpp = Files.readString(x.toPath(), Charset.forName("Shift-jis"));
                // �����񂩂�CharStream�𐶐�
                CharStream cs = CharStreams.fromString(strCpp);
                // CharStream��lexer�ɓn��
                CPP14Lexer lexer = new CPP14Lexer(cs);
                // lexer�Ńg�[�N����ɕ���
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                // �g�[�N�����parser�ɓn���AAST�����
                CPP14Parser parser = new CPP14Parser(tokens);

                GetNameSpace.className(parser);
                GetNameSpace.getMethodName(parser);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
