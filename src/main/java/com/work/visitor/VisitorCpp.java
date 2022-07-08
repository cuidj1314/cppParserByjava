package com.work.visitor;

import com.parser.CPP14Parser;
import com.parser.CPP14ParserBaseVisitor;

public class VisitorCpp extends CPP14ParserBaseVisitor<Object> {

    @Override
    public Object visitSimpleDeclaration(CPP14Parser.SimpleDeclarationContext ctx) {
        // TForm1‚ðŽæ“¾‚·‚é
        Object tform = ctx.declSpecifierSeq().declSpecifier(0).typeSpecifier().trailingTypeSpecifier().simpleTypeSpecifier().theTypeName().className().Identifier();
        // *‚ðŽæ“¾‚·‚é
        Object ho = ctx.initDeclaratorList().initDeclarator(0).declarator().pointerDeclarator().pointerOperator(0).Star();
        // form1‚ðŽæ“¾‚·‚é
        Object form1 = ctx.initDeclaratorList().initDeclarator(0).declarator().pointerDeclarator().noPointerDeclarator().declaratorid().idExpression().unqualifiedId().Identifier();
        Object semi = ctx.Semi();
        StringBuffer sb = new StringBuffer();
        return sb.append(tform).append(" ").append(ho).append(form1).append(semi);
    }

    @Override
    public Object visitDeclaration(CPP14Parser.DeclarationContext ctx) {
        return super.visitDeclaration(ctx);
    }
}