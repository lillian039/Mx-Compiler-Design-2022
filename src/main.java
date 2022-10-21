import AST.RootNode;
import Frontend.ASTBuilder;
import Util.Err.Error;
import Util.GlobalScope;
import Util.MxErrorListener;
import gen.MxLexer;
import gen.MxParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;


public class main {

    public static void main(String[] args) throws Exception{
        String name="test.mx";
        InputStream input=new FileInputStream(name);
        try {
            RootNode ASTRoot;
            GlobalScope globalScope=new GlobalScope(null);

            MxLexer lexer=new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());

            MxParser parser=new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());

            ParseTree parseTreeRoot=parser.program();
            ASTBuilder astBuilder=new ASTBuilder(globalScope);
            ASTRoot=(RootNode) astBuilder.visit(parseTreeRoot);
        }catch (Error err){
            System.out.println(err.errorMsg());
            System.out.println("-1");
        }



    }
}