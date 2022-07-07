package com.work.listener;

import com.parser.CPP14Parser;
import com.parser.CPP14ParserBaseListener;

public class ListenerRewrite extends CPP14ParserBaseListener {
    @Override
    public void exitRefqualifier(CPP14Parser.RefqualifierContext ctx) {
        final String a = ctx.getChild(0).getText();
        final String b = ctx.getChild(1).getText();
        System.out.println(a + " " + b);
    }
}
