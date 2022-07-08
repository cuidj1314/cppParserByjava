package com.work.visitor.parsercpp;

import com.parser.CPP14Parser;
import com.work.visitor.VisitorCpp;
import org.antlr.v4.runtime.tree.ParseTree;

public class GetNameSpace {

    public static void className(CPP14Parser parser) {
        ParseTree tree = parser.simpleDeclaration();
        VisitorCpp visitor = new VisitorCpp();
        System.out.println(visitor.visit(tree));
    }

    public static void getMethodName(CPP14Parser parser) {
        // visitorでASTをトラバース
        ParseTree tree = parser.declarationseq();
        VisitorCpp visitor = new VisitorCpp();
        System.out.println(visitor.visit(tree));
    }
}
