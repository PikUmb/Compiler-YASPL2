/* Specifica JFlex */

import java_cup.runtime.*;

%%

%class Lexer
%unicode
%cupsym LexerSym
%cup
%line
%column


%{
	//conta i Token restituiti
	private int nToken;

	StringBuffer string = new StringBuffer();

	private Symbol symbol(int type)
	{
		return new Symbol(type);
	}

	private Symbol symbol(int type, String value)
	{
		Symbol toReturn = null;
		if(type == LexerSym.ID){
			toReturn = new Symbol(type, SymbolTable.addIdentifiers(value));
		}else{
			toReturn = new Symbol(type, value);
		}
		return toReturn;
	}

%}

%init{
	nToken = 0;
%init}

%eof{
	System.out.println("Numero di Token individuati: " + nToken + " Token");
%eof}

digit = [0-9]
digits = {digit}+
letter = [a-zA-Z]
letter_ = {letter} | "_" | "."
number = {digits} ("." {digits})? ("E" ("+-")? {digits})?
identificatore = {letter_} ({letter} | {digit})*

separatore = ";" | "(" | ")" | "[" | "]" | "{" | "}" | ","
relop = "<" | ">" | "<>" | ">=" | "<=" | "!" | "?"
assegnazione = "=" | ":=" | "<-" | "->"
operatore = "-" | "+" | "*" | "/"

/* A separatore_istr is a \r (carriage return), \n (line feed), or \r\n. */
separatore_istr = \r|\n|\r\n
/* White space is a, or more, separatore_istr, space, tab, or line feed. */
spazi_bianchi = {separatore_istr}+ | [ \t\f]

%state STRING
%state COMMENT
%state LINECOMMENT


%%


<YYINITIAL> {

	/* keywords */
	"if" { nToken++; return symbol(LexerSym.IF, yytext()); }
	"then" { nToken++; return symbol(LexerSym.THEN, yytext()); }
	"else" { nToken++; return symbol(LexerSym.ELSE, yytext()); }
	"endif" { nToken++; return symbol(LexerSym.ENDIF, yytext()); }
	"new" { nToken++; return symbol(LexerSym.NEW, yytext()); }
	"for" { nToken++; return symbol(LexerSym.FOR, yytext()); }
	"while" { nToken++; return symbol(LexerSym.WHILE, yytext()); }
	"do" { nToken++; return symbol(LexerSym.DO, yytext()); }
	"return" { nToken++; return symbol(LexerSym.RETURN, yytext()); }
	
	/* literals */
	{number} { nToken++; return symbol(LexerSym.NUMBER, yytext()); }
	\" { string.setLength(0); yybegin(STRING); }
	
	/* separators */
	{separatore} { nToken++; return symbol(LexerSym.SEP, yytext()); }
	
	/* operators */
	{operatore} { nToken++; return symbol(LexerSym.OP, yytext()); }
	
	/* relop */
	{relop} { nToken++; return symbol(LexerSym.RELOP, yytext()); }
	
	/* assignment operators */
	{assegnazione} { nToken++; return symbol(LexerSym.ASS, yytext()); }
	
	/* identifiers */
	{identificatore} { nToken++; return symbol(LexerSym.ID, yytext()); }
	
	/* whitespace */
	{spazi_bianchi} { /* ignore */ }
	
	/* comments */
	\/\* { string.setLength(0); yybegin(COMMENT); }
	\/\/ { string.setLength(0); yybegin(LINECOMMENT); }

}

<STRING>{

	<<EOF>>	{ System.err.println("Error: End of File (EOF) in String!" + " At line: " + yyline + ", column: " + yycolumn);
			  return symbol(LexerSym.EOF);
			 }
	\" {	 nToken++;
		 yybegin(YYINITIAL); 
		 return symbol(LexerSym.STRING_LITERAL, string.toString());
		}
	. { string.append(yytext()); }
	\\\\ { string.append('\\'); }
	\\\" { string.append('\"'); }
	{spazi_bianchi} { string.append(yytext()); }

}

<COMMENT>{

	<<EOF>>	{ System.err.println("Error: End of File (EOF) in Comment!" + " At line: " + yyline + ", column: " + yycolumn);
			  return symbol(LexerSym.EOF);
			 }
	\*\/		{ nToken++;
			  yybegin(YYINITIAL); 
		 	  return symbol(LexerSym.COMMENT, string.toString());
			}
	.	{ string.append(yytext()); }
	\/\*		{ string.append(yytext()); }
	\/\*\*	{ string.append(yytext()); }
	{spazi_bianchi}	{ string.append(yytext()); }

}

<LINECOMMENT>{
	
	<<EOF>>	{ yybegin(YYINITIAL);
			  return symbol(LexerSym.LINECOMMENT, string.toString());
			 }
	\n	{nToken++; 
		 yybegin(YYINITIAL);
		 return symbol(LexerSym.LINECOMMENT, string.toString()); 
		 }
	[^\n]	{ string.append(yytext()); }

}

/* error fallback */
[^]		{ throw new Error("Illegal character <"+yytext()+"> at line "+yyline+", column "+yycolumn); }
<<EOF>>	{ return symbol(LexerSym.EOF); }


