import Frontend.ASTBuilder;
import Util.Err.Error;
import Util.GlobalScope;
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
            GlobalScope globalScope=new GlobalScope(null);

            MxLexer lexer=new MxLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();

            MxParser parser=new MxParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();

            ParseTree parseTreeRoot=parser.program();

            ASTBuilder astBuilder=new ASTBuilder(globalScope);
        }catch (Error err){
            System.out.println(err.errorMsg());
            System.out.println("-1");
        }



    }
}