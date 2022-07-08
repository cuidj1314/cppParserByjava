package com.work.listener;

import com.parser.CPP14Parser;
import com.parser.CPP14ParserBaseListener;

public class ListenerCpp extends CPP14ParserBaseListener {

    @Override
    public void enterClassHead(CPP14Parser.ClassHeadContext ctx) {
        ctx.classKey();
        super.enterClassHead(ctx);
    }
}
