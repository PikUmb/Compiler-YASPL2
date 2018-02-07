/* The following code was generated by JFlex 1.4.3 on 07/02/18 15.10 */

/* Specifica JFlex per il linguaggio YASPL2*/

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 07/02/18 15.10 from the specification file
 * <tt>/Users/umbertopicariello/eclipse-workspace/UP_Esercitazione_4_Completa/src/Lexer.lex</tt>
 */
class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING_CONST = 2;
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 4;
  public static final int LINECOMMENT = 6;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\3\1\27\1\50\1\0\1\27\1\26\16\3\4\0\1\27\1\0"+
    "\1\47\1\0\1\2\1\0\1\25\1\0\1\12\1\13\1\21\1\6"+
    "\1\11\1\7\1\4\1\22\12\1\1\14\1\10\1\17\1\23\1\20"+
    "\2\0\4\2\1\5\25\2\1\0\1\51\2\0\1\2\1\0\1\32"+
    "\1\41\1\2\1\33\1\31\1\45\1\2\1\30\1\37\2\2\1\43"+
    "\1\2\1\40\1\42\2\2\1\36\1\34\1\35\1\44\1\2\1\46"+
    "\3\2\1\15\1\24\1\16\1\0\41\3\2\0\4\2\4\0\1\2"+
    "\2\0\1\3\7\0\1\2\4\0\1\2\5\0\27\2\1\0\37\2"+
    "\1\0\u01ca\2\4\0\14\2\16\0\5\2\7\0\1\2\1\0\1\2"+
    "\21\0\160\3\5\2\1\0\2\2\2\0\4\2\10\0\1\2\1\0"+
    "\3\2\1\0\1\2\1\0\24\2\1\0\123\2\1\0\213\2\1\0"+
    "\5\3\2\0\236\2\11\0\46\2\2\0\1\2\7\0\47\2\7\0"+
    "\1\2\1\0\55\3\1\0\1\3\1\0\2\3\1\0\2\3\1\0"+
    "\1\3\10\0\33\2\5\0\3\2\15\0\5\3\6\0\1\2\4\0"+
    "\13\3\5\0\53\2\37\3\4\0\2\2\1\3\143\2\1\0\1\2"+
    "\10\3\1\0\6\3\2\2\2\3\1\0\4\3\2\2\12\3\3\2"+
    "\2\0\1\2\17\0\1\3\1\2\1\3\36\2\33\3\2\0\131\2"+
    "\13\3\1\2\16\0\12\3\41\2\11\3\2\2\4\0\1\2\5\0"+
    "\26\2\4\3\1\2\11\3\1\2\3\3\1\2\5\3\22\0\31\2"+
    "\3\3\104\0\1\2\1\0\13\2\67\0\33\3\1\0\4\3\66\2"+
    "\3\3\1\2\22\3\1\2\7\3\12\2\2\3\2\0\12\3\1\0"+
    "\7\2\1\0\7\2\1\0\3\3\1\0\10\2\2\0\2\2\2\0"+
    "\26\2\1\0\7\2\1\0\1\2\3\0\4\2\2\0\1\3\1\2"+
    "\7\3\2\0\2\3\2\0\3\3\1\2\10\0\1\3\4\0\2\2"+
    "\1\0\3\2\2\3\2\0\12\3\4\2\7\0\1\2\5\0\3\3"+
    "\1\0\6\2\4\0\2\2\2\0\26\2\1\0\7\2\1\0\2\2"+
    "\1\0\2\2\1\0\2\2\2\0\1\3\1\0\5\3\4\0\2\3"+
    "\2\0\3\3\3\0\1\3\7\0\4\2\1\0\1\2\7\0\14\3"+
    "\3\2\1\3\13\0\3\3\1\0\11\2\1\0\3\2\1\0\26\2"+
    "\1\0\7\2\1\0\2\2\1\0\5\2\2\0\1\3\1\2\10\3"+
    "\1\0\3\3\1\0\3\3\2\0\1\2\17\0\2\2\2\3\2\0"+
    "\12\3\1\0\1\2\17\0\3\3\1\0\10\2\2\0\2\2\2\0"+
    "\26\2\1\0\7\2\1\0\2\2\1\0\5\2\2\0\1\3\1\2"+
    "\7\3\2\0\2\3\2\0\3\3\10\0\2\3\4\0\2\2\1\0"+
    "\3\2\2\3\2\0\12\3\1\0\1\2\20\0\1\3\1\2\1\0"+
    "\6\2\3\0\3\2\1\0\4\2\3\0\2\2\1\0\1\2\1\0"+
    "\2\2\3\0\2\2\3\0\3\2\3\0\14\2\4\0\5\3\3\0"+
    "\3\3\1\0\4\3\2\0\1\2\6\0\1\3\16\0\12\3\11\0"+
    "\1\2\7\0\3\3\1\0\10\2\1\0\3\2\1\0\27\2\1\0"+
    "\12\2\1\0\5\2\3\0\1\2\7\3\1\0\3\3\1\0\4\3"+
    "\7\0\2\3\1\0\2\2\6\0\2\2\2\3\2\0\12\3\22\0"+
    "\2\3\1\0\10\2\1\0\3\2\1\0\27\2\1\0\12\2\1\0"+
    "\5\2\2\0\1\3\1\2\7\3\1\0\3\3\1\0\4\3\7\0"+
    "\2\3\7\0\1\2\1\0\2\2\2\3\2\0\12\3\1\0\2\2"+
    "\17\0\2\3\1\0\10\2\1\0\3\2\1\0\51\2\2\0\1\2"+
    "\7\3\1\0\3\3\1\0\4\3\1\2\10\0\1\3\10\0\2\2"+
    "\2\3\2\0\12\3\12\0\6\2\2\0\2\3\1\0\22\2\3\0"+
    "\30\2\1\0\11\2\1\0\1\2\2\0\7\2\3\0\1\3\4\0"+
    "\6\3\1\0\1\3\1\0\10\3\22\0\2\3\15\0\60\2\1\3"+
    "\2\2\7\3\4\0\10\2\10\3\1\0\12\3\47\0\2\2\1\0"+
    "\1\2\2\0\2\2\1\0\1\2\2\0\1\2\6\0\4\2\1\0"+
    "\7\2\1\0\3\2\1\0\1\2\1\0\1\2\2\0\2\2\1\0"+
    "\4\2\1\3\2\2\6\3\1\0\2\3\1\2\2\0\5\2\1\0"+
    "\1\2\1\0\6\3\2\0\12\3\2\0\4\2\40\0\1\2\27\0"+
    "\2\3\6\0\12\3\13\0\1\3\1\0\1\3\1\0\1\3\4\0"+
    "\2\3\10\2\1\0\44\2\4\0\24\3\1\0\2\3\5\2\13\3"+
    "\1\0\44\3\11\0\1\3\71\0\53\2\24\3\1\2\12\3\6\0"+
    "\6\2\4\3\4\2\3\3\1\2\3\3\2\2\7\3\3\2\4\3"+
    "\15\2\14\3\1\2\17\3\2\0\46\2\1\0\1\2\5\0\1\2"+
    "\2\0\53\2\1\0\u014d\2\1\0\4\2\2\0\7\2\1\0\1\2"+
    "\1\0\4\2\2\0\51\2\1\0\4\2\2\0\41\2\1\0\4\2"+
    "\2\0\7\2\1\0\1\2\1\0\4\2\2\0\17\2\1\0\71\2"+
    "\1\0\4\2\2\0\103\2\2\0\3\3\40\0\20\2\20\0\125\2"+
    "\14\0\u026c\2\2\0\21\2\1\0\32\2\5\0\113\2\3\0\3\2"+
    "\17\0\15\2\1\0\4\2\3\3\13\0\22\2\3\3\13\0\22\2"+
    "\2\3\14\0\15\2\1\0\3\2\1\0\2\3\14\0\64\2\40\3"+
    "\3\0\1\2\3\0\2\2\1\3\2\0\12\3\41\0\3\3\2\0"+
    "\12\3\6\0\130\2\10\0\51\2\1\3\1\2\5\0\106\2\12\0"+
    "\35\2\3\0\14\3\4\0\14\3\12\0\12\3\36\2\2\0\5\2"+
    "\13\0\54\2\4\0\21\3\7\2\2\3\6\0\12\3\46\0\27\2"+
    "\5\3\4\0\65\2\12\3\1\0\35\3\2\0\13\3\6\0\12\3"+
    "\15\0\1\2\130\0\5\3\57\2\21\3\7\2\4\0\12\3\21\0"+
    "\11\3\14\0\3\3\36\2\15\3\2\2\12\3\54\2\16\3\14\0"+
    "\44\2\24\3\10\0\12\3\3\0\3\2\12\3\44\2\122\0\3\3"+
    "\1\0\25\3\4\2\1\3\4\2\3\3\2\2\11\0\300\2\47\3"+
    "\25\0\4\3\u0116\2\2\0\6\2\2\0\46\2\2\0\6\2\2\0"+
    "\10\2\1\0\1\2\1\0\1\2\1\0\1\2\1\0\37\2\2\0"+
    "\65\2\1\0\7\2\1\0\1\2\3\0\3\2\1\0\7\2\3\0"+
    "\4\2\2\0\6\2\4\0\15\2\5\0\3\2\1\0\7\2\16\0"+
    "\5\3\32\0\5\3\20\0\2\2\23\0\1\2\13\0\5\3\5\0"+
    "\6\3\1\0\1\2\15\0\1\2\20\0\15\2\3\0\33\2\25\0"+
    "\15\3\4\0\1\3\3\0\14\3\21\0\1\2\4\0\1\2\2\0"+
    "\12\2\1\0\1\2\3\0\5\2\6\0\1\2\1\0\1\2\1\0"+
    "\1\2\1\0\4\2\1\0\13\2\2\0\4\2\5\0\5\2\4\0"+
    "\1\2\21\0\51\2\u0a77\0\57\2\1\0\57\2\1\0\205\2\6\0"+
    "\4\2\3\3\2\2\14\0\46\2\1\0\1\2\5\0\1\2\2\0"+
    "\70\2\7\0\1\2\17\0\1\3\27\2\11\0\7\2\1\0\7\2"+
    "\1\0\7\2\1\0\7\2\1\0\7\2\1\0\7\2\1\0\7\2"+
    "\1\0\7\2\1\0\40\3\57\0\1\2\u01d5\0\3\2\31\0\11\2"+
    "\6\3\1\0\5\2\2\0\5\2\4\0\126\2\2\0\2\3\2\0"+
    "\3\2\1\0\132\2\1\0\4\2\5\0\51\2\3\0\136\2\21\0"+
    "\33\2\65\0\20\2\u0200\0\u19b6\2\112\0\u51cd\2\63\0\u048d\2\103\0"+
    "\56\2\2\0\u010d\2\3\0\20\2\12\3\2\2\24\0\57\2\1\3"+
    "\4\0\12\3\1\0\31\2\7\0\1\3\120\2\2\3\45\0\11\2"+
    "\2\0\147\2\2\0\4\2\1\0\4\2\14\0\13\2\115\0\12\2"+
    "\1\3\3\2\1\3\4\2\1\3\27\2\5\3\20\0\1\2\7\0"+
    "\64\2\14\0\2\3\62\2\21\3\13\0\12\3\6\0\22\3\6\2"+
    "\3\0\1\2\4\0\12\3\34\2\10\3\2\0\27\2\15\3\14\0"+
    "\35\2\3\0\4\3\57\2\16\3\16\0\1\2\12\3\46\0\51\2"+
    "\16\3\11\0\3\2\1\3\10\2\2\3\2\0\12\3\6\0\27\2"+
    "\3\0\1\2\1\3\4\0\60\2\1\3\1\2\3\3\2\2\2\3"+
    "\5\2\2\3\1\2\1\3\1\2\30\0\3\2\2\0\13\2\5\3"+
    "\2\0\3\2\2\3\12\0\6\2\2\0\6\2\2\0\6\2\11\0"+
    "\7\2\1\0\7\2\221\0\43\2\10\3\1\0\2\3\2\0\12\3"+
    "\6\0\u2ba4\2\14\0\27\2\4\0\61\2\u2104\0\u016e\2\2\0\152\2"+
    "\46\0\7\2\14\0\5\2\5\0\1\2\1\3\12\2\1\0\15\2"+
    "\1\0\5\2\1\0\1\2\1\0\2\2\1\0\2\2\1\0\154\2"+
    "\41\0\u016b\2\22\0\100\2\2\0\66\2\50\0\15\2\3\0\20\3"+
    "\20\0\7\3\14\0\2\2\30\0\3\2\31\0\1\2\6\0\5\2"+
    "\1\0\207\2\2\0\1\3\4\0\1\2\13\0\12\3\7\0\32\2"+
    "\4\0\1\2\1\0\32\2\13\0\131\2\3\0\6\2\2\0\6\2"+
    "\2\0\6\2\2\0\3\2\3\0\2\2\3\0\2\2\22\0\3\3"+
    "\4\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17"+
    "\1\20\1\21\1\22\1\1\2\22\12\3\1\23\2\24"+
    "\1\25\3\24\1\26\2\0\1\27\1\30\1\31\1\32"+
    "\1\33\1\34\1\35\1\36\1\37\3\3\1\40\4\3"+
    "\1\41\4\3\1\42\1\43\1\44\1\24\2\45\1\0"+
    "\2\3\1\46\4\3\1\47\1\50\3\3\1\0\1\51"+
    "\1\52\2\3\1\53\1\54\1\55\3\3\1\56\1\57"+
    "\1\60\1\61";

  private static int [] zzUnpackAction() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\52\0\124\0\176\0\250\0\322\0\374\0\250"+
    "\0\u0126\0\250\0\250\0\250\0\250\0\250\0\250\0\250"+
    "\0\u0150\0\u017a\0\250\0\u01a4\0\u01ce\0\u01f8\0\u0222\0\u024c"+
    "\0\250\0\u0276\0\u02a0\0\u02ca\0\u02f4\0\u031e\0\u0348\0\u0372"+
    "\0\u039c\0\u03c6\0\u03f0\0\250\0\250\0\u041a\0\250\0\u0444"+
    "\0\u046e\0\u0498\0\250\0\u04c2\0\u04ec\0\250\0\250\0\250"+
    "\0\250\0\250\0\250\0\250\0\u024c\0\250\0\u0516\0\u0540"+
    "\0\u056a\0\u0594\0\u05be\0\u05e8\0\u0612\0\u063c\0\374\0\u0666"+
    "\0\u0690\0\u06ba\0\u06e4\0\250\0\250\0\250\0\u070e\0\u0738"+
    "\0\u0762\0\u078c\0\u07b6\0\u07e0\0\374\0\u080a\0\u0834\0\u085e"+
    "\0\u0888\0\374\0\374\0\u08b2\0\u08dc\0\u0906\0\u0762\0\374"+
    "\0\374\0\u0930\0\u095a\0\374\0\374\0\374\0\u0984\0\u09ae"+
    "\0\u09d8\0\374\0\374\0\374\0\374";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\2\5\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32"+
    "\1\33\1\7\1\34\1\35\1\36\1\7\1\37\1\40"+
    "\1\41\3\7\1\42\1\43\1\44\1\30\1\5\24\45"+
    "\1\46\1\45\1\46\20\45\1\47\1\46\1\50\21\45"+
    "\1\51\1\52\1\45\1\46\1\45\1\46\21\45\1\46"+
    "\1\45\50\31\1\53\1\31\53\0\1\6\2\0\1\54"+
    "\1\55\45\0\3\7\1\0\1\7\22\0\17\7\23\0"+
    "\1\56\40\0\1\57\13\0\1\60\51\0\1\61\47\0"+
    "\1\62\1\63\52\0\1\64\52\0\1\65\1\0\1\30"+
    "\21\0\1\30\26\0\1\66\50\0\1\30\1\0\1\30"+
    "\21\0\1\30\2\0\3\7\1\0\1\7\22\0\1\7"+
    "\1\67\15\7\4\0\3\7\1\0\1\7\22\0\13\7"+
    "\1\70\3\7\4\0\3\7\1\0\1\7\22\0\1\7"+
    "\1\71\10\7\1\72\4\7\4\0\3\7\1\0\1\7"+
    "\22\0\5\7\1\73\11\7\4\0\3\7\1\0\1\7"+
    "\22\0\1\74\5\7\1\75\10\7\4\0\3\7\1\0"+
    "\1\7\22\0\10\7\1\76\4\7\1\77\1\7\4\0"+
    "\3\7\1\0\1\7\22\0\12\7\1\100\4\7\4\0"+
    "\3\7\1\0\1\7\22\0\12\7\1\101\4\7\4\0"+
    "\3\7\1\0\1\7\22\0\2\7\1\102\14\7\4\0"+
    "\3\7\1\0\1\7\22\0\1\103\16\7\27\0\1\46"+
    "\1\0\1\46\21\0\1\46\50\0\1\104\1\0\1\105"+
    "\22\0\1\106\50\0\1\107\31\0\1\110\51\0\1\111"+
    "\4\0\1\112\44\0\3\7\1\0\1\7\22\0\2\7"+
    "\1\113\14\7\4\0\3\7\1\0\1\7\22\0\4\7"+
    "\1\114\12\7\4\0\3\7\1\0\1\7\22\0\15\7"+
    "\1\115\1\7\4\0\3\7\1\0\1\7\22\0\14\7"+
    "\1\116\2\7\4\0\3\7\1\0\1\7\22\0\2\7"+
    "\1\117\14\7\4\0\3\7\1\0\1\7\22\0\1\7"+
    "\1\120\15\7\4\0\3\7\1\0\1\7\22\0\14\7"+
    "\1\121\2\7\4\0\3\7\1\0\1\7\22\0\5\7"+
    "\1\122\11\7\4\0\3\7\1\0\1\7\22\0\5\7"+
    "\1\123\11\7\4\0\3\7\1\0\1\7\22\0\12\7"+
    "\1\124\4\7\4\0\3\7\1\0\1\7\22\0\13\7"+
    "\1\125\3\7\4\0\3\7\1\0\1\7\22\0\7\7"+
    "\1\126\7\7\24\0\1\45\31\0\1\110\3\0\1\55"+
    "\45\0\1\111\57\0\1\127\43\0\3\7\1\0\1\7"+
    "\22\0\3\7\1\130\13\7\4\0\3\7\1\0\1\7"+
    "\22\0\1\7\1\131\15\7\4\0\3\7\1\0\1\7"+
    "\22\0\11\7\1\132\5\7\4\0\3\7\1\0\1\7"+
    "\22\0\6\7\1\133\10\7\4\0\3\7\1\0\1\7"+
    "\22\0\10\7\1\134\6\7\4\0\3\7\1\0\1\7"+
    "\22\0\1\7\1\135\15\7\4\0\3\7\1\0\1\7"+
    "\22\0\13\7\1\136\3\7\4\0\3\7\1\0\1\7"+
    "\22\0\4\7\1\137\12\7\4\0\3\7\1\0\1\7"+
    "\22\0\13\7\1\140\3\7\4\0\3\7\1\0\1\7"+
    "\22\0\13\7\1\141\3\7\4\0\3\7\1\0\1\7"+
    "\22\0\5\7\1\142\11\7\4\0\3\7\1\0\1\7"+
    "\22\0\1\7\1\143\15\7\4\0\3\7\1\0\1\7"+
    "\22\0\1\7\1\144\15\7\4\0\3\7\1\0\1\7"+
    "\22\0\1\7\1\145\15\7\3\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2562];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\1\11\2\1\1\11\1\1\7\11\2\1\1\11"+
    "\5\1\1\11\12\1\2\11\1\1\1\11\3\1\1\11"+
    "\2\0\7\11\1\1\1\11\15\1\3\11\3\1\1\0"+
    "\14\1\1\0\16\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[101];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

	StringBuffer string = new StringBuffer();

	private Symbol symbol(int type)
	{
		return new Symbol(type);
	}

	private Symbol symbol(int type, String value)
	{
		Symbol toReturn = null;
		if(type == YASPL2Sym.NAME){
			toReturn = new Symbol(type, yyline, yycolumn, SymbolTable.addIdentifiers(value));
		}else{
			toReturn = new Symbol(type, value);
		}
		return toReturn;
	} 



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2242) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 18: 
          { /* ignore */
          }
        case 50: break;
        case 6: 
          { return symbol(YASPL2Sym.SEMI, yytext());
          }
        case 51: break;
        case 21: 
          { yybegin(YYINITIAL); 
		 return symbol(YASPL2Sym.STRING_CONST, string.toString());
          }
        case 52: break;
        case 22: 
          { ; 
		 yybegin(YYINITIAL);
          }
        case 53: break;
        case 10: 
          { return symbol(YASPL2Sym.COLON, yytext());
          }
        case 54: break;
        case 41: 
          { return symbol(YASPL2Sym.HEAD, yytext());
          }
        case 55: break;
        case 17: 
          { return symbol(YASPL2Sym.ASSIGN, yytext());
          }
        case 56: break;
        case 7: 
          { return symbol(YASPL2Sym.COMMA, yytext());
          }
        case 57: break;
        case 14: 
          { return symbol(YASPL2Sym.GT, yytext());
          }
        case 58: break;
        case 46: 
          { return symbol(YASPL2Sym.START, yytext());
          }
        case 59: break;
        case 36: 
          { ;
			  yybegin(YYINITIAL);
          }
        case 60: break;
        case 2: 
          { return symbol(YASPL2Sym.NUMBER_INT, yytext());
          }
        case 61: break;
        case 4: 
          { return symbol(YASPL2Sym.PLUS, yytext());
          }
        case 62: break;
        case 35: 
          { string.append('\\');
          }
        case 63: break;
        case 49: 
          { return symbol(YASPL2Sym.DOUBLE, yytext());
          }
        case 64: break;
        case 23: 
          { return symbol(YASPL2Sym.WRITE, yytext());
          }
        case 65: break;
        case 45: 
          { return symbol(YASPL2Sym.BOOL, yytext());
          }
        case 66: break;
        case 25: 
          { return symbol(YASPL2Sym.LE, yytext());
          }
        case 67: break;
        case 9: 
          { return symbol(YASPL2Sym.RPAR, yytext());
          }
        case 68: break;
        case 12: 
          { return symbol(YASPL2Sym.RGPAR, yytext());
          }
        case 69: break;
        case 5: 
          { return symbol(YASPL2Sym.MINUS, yytext());
          }
        case 70: break;
        case 42: 
          { return symbol(YASPL2Sym.ELSE, yytext());
          }
        case 71: break;
        case 19: 
          { string.setLength(0); yybegin(STRING_CONST);
          }
        case 72: break;
        case 43: 
          { return symbol(YASPL2Sym.THEN, yytext());
          }
        case 73: break;
        case 34: 
          { string.append('\"');
          }
        case 74: break;
        case 38: 
          { return symbol(YASPL2Sym.DEF, yytext());
          }
        case 75: break;
        case 1: 
          { throw new Error("Illegal character <"+yytext()+"> at line "+yyline+", column "+yycolumn);
          }
        case 76: break;
        case 32: 
          { return symbol(YASPL2Sym.DO, yytext());
          }
        case 77: break;
        case 8: 
          { return symbol(YASPL2Sym.LPAR, yytext());
          }
        case 78: break;
        case 11: 
          { return symbol(YASPL2Sym.LGPAR, yytext());
          }
        case 79: break;
        case 15: 
          { return symbol(YASPL2Sym.TIMES, yytext());
          }
        case 80: break;
        case 16: 
          { return symbol(YASPL2Sym.DIV, yytext());
          }
        case 81: break;
        case 29: 
          { return symbol(YASPL2Sym.EQ, yytext());
          }
        case 82: break;
        case 30: 
          { return symbol(YASPL2Sym.OR, yytext());
          }
        case 83: break;
        case 47: 
          { return symbol(YASPL2Sym.FALSE, yytext());
          }
        case 84: break;
        case 27: 
          { string.setLength(0); yybegin(COMMENT);
          }
        case 85: break;
        case 20: 
          { string.append(yytext());
          }
        case 86: break;
        case 26: 
          { return symbol(YASPL2Sym.GE, yytext());
          }
        case 87: break;
        case 40: 
          { return symbol(YASPL2Sym.NOT, yytext());
          }
        case 88: break;
        case 24: 
          { return symbol(YASPL2Sym.READ, yytext());
          }
        case 89: break;
        case 28: 
          { string.setLength(0); yybegin(LINECOMMENT);
          }
        case 90: break;
        case 31: 
          { return symbol(YASPL2Sym.AND, yytext());
          }
        case 91: break;
        case 37: 
          { return symbol(YASPL2Sym.NUMBER_DOUBLE, yytext());
          }
        case 92: break;
        case 13: 
          { return symbol(YASPL2Sym.LT, yytext());
          }
        case 93: break;
        case 48: 
          { return symbol(YASPL2Sym.WHILE, yytext());
          }
        case 94: break;
        case 33: 
          { return symbol(YASPL2Sym.IF, yytext());
          }
        case 95: break;
        case 39: 
          { return symbol(YASPL2Sym.INT, yytext());
          }
        case 96: break;
        case 3: 
          { return symbol(YASPL2Sym.NAME, yytext());
          }
        case 97: break;
        case 44: 
          { return symbol(YASPL2Sym.TRUE, yytext());
          }
        case 98: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
            switch (zzLexicalState) {
            case STRING_CONST: {
              System.err.println("Error: End of File (EOF) in String!" + " At line: " + yyline + ", column: " + yycolumn);
			  return symbol(YASPL2Sym.EOF);
            }
            case 102: break;
            case COMMENT: {
              System.err.println("Error: End of File (EOF) in Comment!" + " At line: " + yyline + ", column: " + yycolumn);
			  return symbol(YASPL2Sym.EOF);
            }
            case 103: break;
            case LINECOMMENT: {
              yybegin(YYINITIAL);
            }
            case 104: break;
            default:
              {
                return symbol(YASPL2Sym.EOF);
              }
            }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
