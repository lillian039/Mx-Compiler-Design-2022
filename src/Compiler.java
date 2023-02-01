import AST.RootNode;
import Assembly.ASMFn;
import Backend.ASMPrinter;
import Backend.ASMRegAlloc;
import Backend.ASMRegColor;
import Backend.InstructionSelector;
import Frontend.*;
import LLVMIR.RootIR;
import MiddleEnd.IRMem2Reg;
import Util.BuiltinASMPrinter;
import Util.Err.Error;
import Util.Scope.GlobalScope;
import Util.MxErrorListener;
import gen.MxLexer;
import gen.MxParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.*;


public class Compiler {

    public static void main(String[] args) throws Exception {
        String name = "test.mx";
        InputStream input = new FileInputStream(name);
        PrintStream output = new PrintStream("test.ll");
        System.setOut(output);
        //InputStream input = System.in;
        try {
            RootNode root;
            GlobalScope globalScope = new GlobalScope(null);
            globalScope.initializeGlobalScope();

            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());

            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());

            ParseTree parseTreeRoot = parser.program();
            ASTBuilder astBuilder = new ASTBuilder(globalScope);
            root = (RootNode) astBuilder.visit(parseTreeRoot);
            new SymbolCollector(globalScope).visit(root);
            new SemanticChecker(globalScope).visit(root);

            RootIR rootIR = new RootIR();
            new IRCollector(globalScope).visit(root);
            new IRBuilder(globalScope, rootIR).visit(root);
            // new IRMem2Reg(rootIR).visit(rootIR);
            new IRPrinter(rootIR).print();

            ASMFn asmFn = new ASMFn();
            new InstructionSelector(asmFn).visit(rootIR);
            // new ASMPrinter(asmFn).printOrigin();
            //new ASMRegAlloc(asmFn).alloc();
            new ASMRegColor().visit(asmFn);
            // new ASMPrinter(asmFn).printAlloc();
            //BuiltinASMPrinter builtinASMPrinter = new BuiltinASMPrinter("builtin.s");
        } catch (Error err) {
//            System.out.println(err.errorMsg());
            throw err;
        }
    }
}