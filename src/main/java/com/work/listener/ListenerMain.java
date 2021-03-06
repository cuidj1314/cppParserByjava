package com.work.listener;

import com.parser.CPP14Lexer;
import com.parser.CPP14Parser;
import com.work.readfile.ReadFiles;
import com.work.visitor.VisitorCpp;
import org.antlr.v4.runtime.CharStream;
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

        // 一部完了しましたので、利用するとき、改修が必要です。
        files.forEach(x -> {
            try {
                // ソースの内容を読み
                String strCpp = Files.readString(x.toPath());
                // 文字列からCharStreamを生成
                CharStream cs = CharStreams.fromString(strCpp);
                // CharStreamをlexerに渡す
                CPP14Lexer lexer = new CPP14Lexer(cs);
                // lexerでトークン列に分解
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                // トークン列をparserに渡し、ASTを作る
                CPP14Parser parser = new CPP14Parser(tokens);
                // コードを分析する
//                CPP14Parser.RefqualifierContext context = parser.refqualifier();
                // リスナーを新規する
                ListenerCpp listener = new ListenerCpp();
                // listenerでASTをトラバース
                ParseTreeWalker walker = ParseTreeWalker.DEFAULT;
                // リスナーの初期化で遍歴する
                walker.walk(listener, parser.classHead());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
