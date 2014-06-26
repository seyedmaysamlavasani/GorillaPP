grammar GorillaPP_grammar_fp;

options {
  backtrack=true;
  memoize=true;
  k=2;
}

scope Symbols {
  FpCodeGen cg;
}

@header {
  import java.util.Set;
  import java.util.HashSet;
  import java.util.Iterator;
  import java.io.*;
}

@members {

}

translation_unit
scope Symbols; // entire file is a scope
@init {
  $Symbols::cg =  new FpCodeGen();
}
    : external_declaration {$Symbols::cg.writeOutputs();};


external_declaration 
  : ({$Symbols::cg.setIsHeaderFile();}
    (type_definition|define)*) 
    {$Symbols::cg.handlePrePragmas();} |
    (in_pragma out_pragma 
    off_pragma* other_pragma* 
    {$Symbols::cg.handlePostPragmas();}
    global_declaration* {$Symbols::cg.handlePreInstrs();}
    magilla_instr+ {$Symbols::cg.handlePostInstrs();})
	;

global_declaration
    :  define | global_variable_definition 
    ;
    
magilla_instr 
    : MAGILLA_INSTR_NAME {$Symbols::cg.handlePreInstrBody($MAGILLA_INSTR_NAME.text);}
      ('('')') '{' instr_body'}' {
       $Symbols::cg.
        handlePostInstrBody($MAGILLA_INSTR_NAME.text);
      } 
	;

instr_body 
    : local_variable_definition* 
      statement* {$Symbols::cg.unsetIsPreOff();} 
      offload_statement* {$Symbols::cg.setIsPostOff(); } 
      statement* finish_statement?
	; 
        

local_variable_definition
    : ((type_specifier 
      {$Symbols::cg.setTypeName($type_specifier.text);} 
      variablelist) | 
      ('bit_vector' array_index ID)) ';' ; 

global_variable_definition
    : ((type_specifier { 
        $Symbols::cg.
         handlePostTypeSpecifier($type_specifier.text);
      } variablelist) 
      (array_index)? | ('bit_vector' array_index ID)) 
      ({$Symbols::cg.setGlobalInitialized();} '=' 
      constant)? ';' {
        $Symbols::cg.handleGlobalVariableDefinition($constant.text);
      }
	;

type_definition 
    :  'typedef' type_specifier_struct ID {
         $Symbols::cg.handleTypeDefinition($ID.text);
       }
     ';' 
	; 

pragma
    : off_pragma | in_pragma | out_pragma | other_pragma;

other_pragma
	: '#pragma' ('CONCURRENT_SAFE' | 'PIPELINABLE')
	;

in_pragma
    : '#pragma' 'INPUT'   inType=ID  {    
      $Symbols::cg.handleInPragma($inType.text);
    }
	;

out_pragma
    : '#pragma' 'OUTPUT'  outType=ID  {    
   	  $Symbols::cg.handleOutPragma($outType.text);   
    }
	;

off_pragma 
    : '#pragma' 'OFFLOAD' '(' offPort=ID ',' 
      reqType=ID ',' repType=ID ')' {    
        $Symbols::cg.handleOffPragma ($offPort.text, $reqType.text, $repType.text); 
      }
	;

define
    : '#define' ID {$Symbols::cg.setIsMacro();}
      constant_expression 
      {$Symbols::cg.handleDefine($ID.text);}
	;

declaration
@init {
  $Symbols::cg.unsetIsTypeDef();
}
    : 'typedef' {$Symbols::cg.setIsTypeDef();}
      declaration_specifiers init_declarator_list ';' 
      | declaration_specifiers init_declarator_list ';'
    ;

init_declarator_list
    : init_declarator (',' init_declarator)*
    ;

init_declarator
    : declarator  ('=' initializer)?
    ;

declarator
    :  direct_declarator
    ;

direct_declarator
    : (ID {
        $Symbols::cg.
         handlePostIdInDirectDeclarator($ID.text);
      }
      | '(' declarator ')')
      declarator_suffix*
    ;

declarator_suffix
    :   '[' constant_expression ']'
    |   '[' ']'
    |   '(' identifier_list ')'
    |   '(' ')'
    ;

identifier_list
    : ID (',' ID)*
    ;

declaration_specifiers
    :   type_specifier
    ;

initializer
    : assignment_expression
    | '{' initializer_list ','? '}'
    ;

initializer_list
    : initializer (',' initializer)*
    ;

define_lable
    : ID //TODO: make sure that the ID is define lable
    ;

offload_statement
    : offload_expression SEMICOLON  
    ;

statement
    :labeled_statement
    |expression_statement
    |selection_statement
    |compound_statement
    |jump_statement
    ;

compound_statement
    :'{' statement+ '}' 
    ;

offload_expression
    :  {$Symbols::cg.setOffString("");} 
       target=ID '=' offId=ID 
       '(' conditional_expression ')' {
         $Symbols::cg.handleOffloadExpression(
          $target.text, $offId.text);
       } 
        ;


macro_expression 
    : {!$Symbols::cg.offloadPorts.
        contains(input.LT(1).getText())}? 
      ID {$Symbols::cg.outString($ID.text);}
      '('{$Symbols::cg.outString("(");} 
      conditional_expression 
      (','{$Symbols::cg.outString(",");} 
      conditional_expression)*')'
      {$Symbols::cg.outString(")");} ; //e.g. access fields of packet 


//TODO for chisel
labeled_statement
    : ID ':' statement
    | 'case'  constant_expression ':' 
      statement 
    | 'default' ':' statement
    ;

expression_statement
scope {
  boolean isFU;
}
@init {
  $expression_statement::isFU = false;
}
    : SEMICOLON | expression SEMICOLON {
        $Symbols::cg.handleExpressionStatement();
      }
      ;

SEMICOLON : ';'  ;
//TODO chisel
selection_statement
    : 'if' {$Symbols::cg.setIsIfStatement();} 
      '(' {$Symbols::cg.handlePreIfCondition();} 
      conditional_expression ')' 
      {$Symbols::cg.handlePostIfCondition();}
      statement  
      {$Symbols::cg.handlePostIfStatement();}
      (options {k=1; backtrack=false;}:'else' 
      {$Symbols::cg.handlePreElseStatement();}
      statement 
      {$Symbols::cg.handlePostElseStatement();}
      )?
      {$Symbols::cg.unsetIsIfStatement();}//| 
      //'switch' '(' expression ')' 
      //statement 
      
    ;

finish_statement
    : 'finish' '(' ')' SEMICOLON {$Symbols::cg.handleFinish();} 
    ;

variablelist 
    :  variable_in_def ( ',' variable_in_def)* ;
    
variable_in_def
    : variable {
        $Symbols::cg.
         handleVariableInDefinition($variable.text);
      } 
	;

variable
    : ID
    ;

type_name
    : specifier_qualifier_list //abstract_declarator?
    ;

specifier_qualifier_list
    : ( type_specifier )+
    ;

type_specifier_struct:
    type_specifier  
    | 'struct {' (type_specifier {
        $Symbols::cg.setTypeName($type_specifier.text);
      } variablelist ';')+ '}'
    ;

type_specifier
    : 
     'char'
    | 'short'
    | 'int' 
    //| 'uint32_t' 
    | 'long'
    | 'unsigned'
    | enum_specifier
    | type_id
    ;

type_id
    : /*{isTypeName(input.LT(1).getText())}?*/ 
      ID  //We turn off this check so that including 
          //is not bugging us for now
      //{System.out.println("//"+$ID.text+" is a type");} 
      //Type checks will be done in chisel later, after all
    ;

abstract_declarator
    : direct_abstract_declarator
    ;

direct_abstract_declarator
    :  ( '(' abstract_declarator ')' | 
        abstract_declarator_suffix ) 
       abstract_declarator_suffix*
    ;

abstract_declarator_suffix
    :  '[' ']'
    |    '[' constant_expression ']'
    |    '(' ')'
    ;    
enum_specifier
options {k=3;}
    : 'enum' '{' enumerator_list '}'
    | 'enum' ID '{' enumerator_list '}'
    | 'enum' ID
    ;

enumerator_list
    : enumerator (',' enumerator)*
    ;

enumerator
    : ID ('=' constant_expression)?
    ;

argument_expression_list
    :   assignment_expression (',' assignment_expression)*
    ;

expression
//  : assignment_expression (',' assignment_expression)*;
//  : assignment_expression | variable operator expression;
    :  assignment_expression  
    ;    

concat_expression
    : '{'{$Symbols::cg.outString("{");} 
       expression (','{$Symbols::cg.outString(",");} 
       expression)* '}' {$Symbols::cg.outString("}");};
    

assignment_expression   
    : id=ID {$Symbols::cg.handleLValue($id.text);} 
      fieldPostfix assignment_operator 
      (trinary_expression | 
       conditional_expression | 
       macro_expression)    
      ;
  
trinary_expression
    :{$Symbols::cg.outString(" Mux(");} conditional_expression '?' 
     {$Symbols::cg.outString(",");} conditional_expression ':' 
     {$Symbols::cg.outString(",");} conditional_expression 
     {$Symbols::cg.outString(")");}
    ; 
lvalue
    : unary_expression
    ;
constant_expression
    : conditional_expression
//    : constant
    ;

conditional_expression
    : logical_or_expression 
      //('?'{$Symbols::cg.outString("?");} expression ':'
      //{$Symbols::cg.outString(":");} conditional_expression)?
    ;
logical_or_expression
    : logical_and_expression ('||' 
       {$Symbols::cg.outString("||");} 
       logical_and_expression)*
    ;

logical_and_expression
    : inclusive_or_expression ('&&' {
        $Symbols::cg.outString("&&");
      } 
       inclusive_or_expression)*
    ;

inclusive_or_expression
    : exclusive_or_expression ('|' {
        $Symbols::cg.outString("|");
      } 
       exclusive_or_expression)*
    ;

exclusive_or_expression
    : and_expression ('^' {$Symbols::cg.outString("^");}
       and_expression)*
    ;

and_expression
    : equality_expression ('&' {$Symbols::cg.outString("&");}
       equality_expression)*
    ;
equality_expression
    : relational_expression (('=='{$Symbols::cg.outString("===");}|
       '!='{$Symbols::cg.outString("!=");}) relational_expression)*
    ;

relational_expression
    : shift_expression (('<'{$Symbols::cg.outString("<");}
       | '>'{$Symbols::cg.outString(">");}
       | '<='{$Symbols::cg.outString("<=");}
       | '>='{$Symbols::cg.outString(">=");}) shift_expression)*
    ;

shift_expression
    : additive_expression 
      (('<<'{$Symbols::cg.outString("<<");}
      |'>>'{$Symbols::cg.outString(">>");}) 
      additive_expression)*
    ;


additive_expression
    : (multiplicative_expression) 
      ('+'{$Symbols::cg.outString("+");} 
      multiplicative_expression | 
      '-'{$Symbols::cg.outString("-");} 
      multiplicative_expression)*
    ;

multiplicative_expression
    : (cast_expression) ('*' {
        $Symbols::cg.outString("*");}
      cast_expression | '/'{
        $Symbols::cg.outString("/");} 
      cast_expression | '%'{
        $Symbols::cg.outString("\%");} 
      cast_expression)*
    ;

cast_expression
    : '(' type_name ')' cast_expression
    | unary_expression
    ;


unary_expression
    : postfix_expression
    | '++' {$Symbols::cg.outString("++");} unary_expression
    | '--' {$Symbols::cg.outString("--");} unary_expression
    | unary_operator {
        $Symbols::cg.outString($unary_operator.text);
      } 
      cast_expression
//    | 'sizeof' unary_expression
//    | 'sizeof' '(' type_name ')'
    ;

postfix_expression
    :   primary_expression ('++' | '--' | '[' expression ']')* 
        //(   
        //'[' expression ']'
        //|   '(' ')'
        //|   '(' argument_expression_list ')'
        //| fieldPostfix          //|   '->' ID
        //  |(('.' ID)+)* //{$Symbols::cg.outString("." + $ID.text);}
        //|   '++'
        //|   '--'
        //)*
    ;

fieldPostfix
    :  ('.' ID {$Symbols::cg.outString("." + $ID.text);})*
    ;

primary_expression
    : ID  {$Symbols::cg.handlePrimaryExpression($ID.text);} 
      fieldPostfix
    | constant 
    | '(' { $Symbols::cg.outString("("); }
        conditional_expression 
    ')' { $Symbols::cg.outString(")"); }
    | ID  array_index {$Symbols::cg.outString($ID.text + 
        $array_index.text);} 
    | ID  array_range {$Symbols::cg.outString($ID.text + 
        $array_range.text);}
    | MAGILLA_INSTR_NAME { 
        $Symbols::cg.
         postInstrNameAsId($MAGILLA_INSTR_NAME.text); 
      }
	;

array_index
    : '[' (DECIMAL_LITERAL | ID) ']';
array_range
    : '[' DECIMAL_LITERAL ':' DECIMAL_LITERAL ']';
/*array_index_low
    : DECIMAL_LITERAL;
array_index_high
    :DECIMAL_LITERAL;
*/
constant
    : hl=HEX_LITERAL {
        $Symbols::cg.outString("UFix(" + 
         $Symbols::cg.hex2decimal($hl.text.substring(2)) + 
         ", width = 64)");}
    | OCTAL_LITERAL
    | dl=DECIMAL_LITERAL {
        $Symbols::cg.outString("UFix(" + $dl.text + ", width = 64)");}
    | CHARACTER_LITERAL
    | STRING_LITERAL
    | BINARY_LITERAL 
    ;    
unary_operator
    : '&'
    | '*'
    | '+'
    | '-'
    | '~'
    | '!'
    ;

assignment_operator
    : '=' { 
        $Symbols::cg.handleSimpleAssignmentOperator(); 
      }
    | '*='
    | '/='
    | '%='
    | '+=' 
    | '-=' 
    | '<<='
    | '>>='
    | '&='
    | '^='
    | '|='
    ;

operator 
    :  '+'{$Symbols::cg.outString("+");} | 
       '-'{$Symbols::cg.outString("-");} | 
       '&'{$Symbols::cg.outString("&");} | 
       '|'{$Symbols::cg.outString("|");} | 
       '&&' {$Symbols::cg.outString("&&");} | 
       '||'{$Symbols::cg.outString("||");};

WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') {$channel=HIDDEN;}
    ;

COMMENT
    :   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;


INSTR_RETURN_TYPE
    :  'instr_addr_t';
    
fragment LETTER    :  'a'..'z' | 'A'..'Z'
    ;
    
fragment DIGIT    :  '0'..'9'
    ;

MAGILLA_INSTR_NAME
    :  'GS_' ID;

jump_statement
    : /*'goto' ID ';'
    | */'continue' ';'
    | 'break' ';'
//    | 'return' ';'
//    | 'return' expression ';'
    ;

ID    :  LETTER (LETTER | DIGIT | '_')*
    ;
CHARACTER_LITERAL
    :   '\'' ( EscapeSequence | ~('\''|'\\') ) '\''
    ;

STRING_LITERAL
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

fragment
HexDigit : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
BinaryDigit : '0' .. '1';

fragment
IntegerTypeSuffix
    :  ('u'|'U')? ('l'|'L')
    //|    ('u'|'U')  ('l'|'L')?
    ;

HEX_LITERAL : '0' ('x'|'X') HexDigit+ IntegerTypeSuffix? ;

BINARY_LITERAL : DECIMAL_LITERAL 'b' BinaryDigit+ ;

DECIMAL_LITERAL : ('-')? ('0' | '1'..'9' '0'..'9'*) IntegerTypeSuffix? ;

OCTAL_LITERAL : '0' ('0'..'7')+ IntegerTypeSuffix? ;

fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   OctalEscape
    ;

fragment
OctalEscape
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;
