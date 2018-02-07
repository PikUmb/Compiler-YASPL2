/* Specifica JFlex per il linguaggio YASPL2*/

import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cupsym YASPL2Sym
%cup
%line
%column

%{

	StringBuffer string = new StringBuffer(); 

	private Symbol symbol(int type)
	{ 
		return new Symbol(type, yyline, yycolumn);
	}

	private Symbol symbol(int type, String value)
	{
		Symbol toReturn = null;
		toReturn = new Symbol(type, yyline, yycolumn, value);
		return toReturn;
	} 

%}

digit = [0-9]
name = [:jletter:] [:jletterdigit:]*
int_const = {digit}+
double_const = {int_const} ("." {int_const})? ("E" ("+-")? {int_const})?

semi = ";"
comma = ","
lpar = "("
rpar = ")"
colon = ":"
lgpar = "{"
rgpar = "}"
read = "<-"
write = "->"
plus = "+"
minus = "-"
times = "*"
div = "/"
assign = "="
gt = ">"
ge = ">="
lt = "<"
le = "<="
eq = "=="
or = "||"
and = "&&"

/* White space is a, or more, separatore_istr, space, tab, or line feed. */
spazi_bianchi = [\r|\n|\r\n]+ | [ \t\f]

%state STRING_CONST
%state COMMENT
%state LINECOMMENT


%%

<YYINITIAL> {

	/* keywords */
	"head" { return symbol(YASPL2Sym.HEAD, yytext()); }
	"start" { return symbol(YASPL2Sym.START, yytext()); }
	"int" { return symbol(YASPL2Sym.INT, yytext()); }
	"bool" { return symbol(YASPL2Sym.BOOL, yytext()); }
	"double" { return symbol(YASPL2Sym.DOUBLE, yytext()); }
	"def" { return symbol(YASPL2Sym.DEF, yytext()); }
	"if" { return symbol(YASPL2Sym.IF, yytext()); }
	"then" { return symbol(YASPL2Sym.THEN, yytext()); }
	"else" { return symbol(YASPL2Sym.ELSE, yytext()); }
	"while" { return symbol(YASPL2Sym.WHILE, yytext()); }
	"do" { return symbol(YASPL2Sym.DO, yytext()); }
	"true" { return symbol(YASPL2Sym.TRUE, yytext()); }
	"false" { return symbol(YASPL2Sym.FALSE, yytext()); }
	"not" { return symbol(YASPL2Sym.NOT, yytext()); }
	
	/* literals */
	{int_const} { return symbol(YASPL2Sym.NUMBER_INT, yytext()); }
	{double_const} { return symbol(YASPL2Sym.NUMBER_DOUBLE, yytext()); }
	
	/* separators */
	{semi} { return symbol(YASPL2Sym.SEMI, yytext()); }
	{comma} { return symbol(YASPL2Sym.COMMA, yytext()); }
	
	/* operators */
	{lpar} { return symbol(YASPL2Sym.LPAR, yytext()); }
	{rpar} { return symbol(YASPL2Sym.RPAR, yytext()); }
	{colon} { return symbol(YASPL2Sym.COLON, yytext()); }
	{lgpar} { return symbol(YASPL2Sym.LGPAR, yytext()); }
	{rgpar} { return symbol(YASPL2Sym.RGPAR, yytext()); }
	{plus} { return symbol(YASPL2Sym.PLUS, yytext()); }
	{minus} { return symbol(YASPL2Sym.MINUS, yytext()); }
	{times} { return symbol(YASPL2Sym.TIMES, yytext()); }
	{div} { return symbol(YASPL2Sym.DIV, yytext()); }
	{gt} { return symbol(YASPL2Sym.GT, yytext()); }
	{ge} { return symbol(YASPL2Sym.GE, yytext()); }
	{lt} { return symbol(YASPL2Sym.LT, yytext()); }
	{le} { return symbol(YASPL2Sym.LE, yytext()); }
	{eq} { return symbol(YASPL2Sym.EQ, yytext()); }
	{or} { return symbol(YASPL2Sym.OR, yytext()); }
	{and} { return symbol(YASPL2Sym.AND, yytext()); }
	
	/* assignment operators */
	{assign} { return symbol(YASPL2Sym.ASSIGN, yytext()); }
	
	/* identifiers */
	{name} { return symbol(YASPL2Sym.NAME, yytext()); }
	
	/* whitespace */
	{spazi_bianchi} { /* ignore */ }
	
	/* read and write */
	{read} { return symbol(YASPL2Sym.READ, yytext()); }
	{write} { return symbol(YASPL2Sym.WRITE, yytext()); }
	
	/* states */
	\" { string.setLength(0); yybegin(STRING_CONST); }
	\/\* { string.setLength(0); yybegin(COMMENT); }
	\/\/ { string.setLength(0); yybegin(LINECOMMENT); }

}

<STRING_CONST>{

	<<EOF>>	{ System.err.println("Error: End of File (EOF) in String!" + " At line: " + yyline + ", column: " + yycolumn);
			  return symbol(YASPL2Sym.EOF);
			 }
	\" {	
		 yybegin(YYINITIAL); 
		 return symbol(YASPL2Sym.STRING_CONST, string.toString());
		}
	. { string.append(yytext()); }
	\\\\ { string.append('\\'); }
	\\\" { string.append('\"'); }
	{spazi_bianchi} { string.append(yytext()); }

}

<COMMENT>{

	<<EOF>>	{ System.err.println("Error: End of File (EOF) in Comment!" + " At line: " + yyline + ", column: " + yycolumn);
			  return symbol(YASPL2Sym.EOF);
			 }
	\*\/		{ ;
			  yybegin(YYINITIAL); 
			}
	.	{ string.append(yytext()); }
	\/\*		{ string.append(yytext()); }
	\/\*\*	{ string.append(yytext()); }
	{spazi_bianchi}	{ string.append(yytext()); }

}

<LINECOMMENT>{
	
	<<EOF>>	{ yybegin(YYINITIAL);
			 }
	\n	{; 
		 yybegin(YYINITIAL);
		 }
	[^\n]	{ /* ignore */ }

}

/* error fallback */
[^]		{ throw new Error("Illegal character <"+yytext()+"> at line "+yyline+", column "+yycolumn);
		}

<<EOF>>	{ return symbol(YASPL2Sym.EOF); }
