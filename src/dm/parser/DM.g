grammar DM;

options {
	k = 5;
}

@header {
package dm.parser;

import dm.syntax.*;
import dm.gen.*;

import java.util.Set;
import java.util.HashSet;
}


@lexer::header {
package dm.parser;
}
	

program returns [RuleProgram prg] : 
	PREFERENCES COLON r1=rules 
	EXPECTATIONS COLON r2=rules 
	IMPOSSIBLE COLON i=formulas
	EOF
	{ $prg = new RuleProgram($r1.rules, $r2.rules, $i.fmls); }
	;

rules returns [List<Rule> rules] : {$rules = new ArrayList<>();} 
	(r=rule { $rules.add($r.rule); })+;

rule returns [Rule rule] :
	ant=literals RULEARROW con=literals POINT
	{ $rule = new Rule($ant.fml, $con.fml); };
	
formulas returns [List<Set<Literal>> fmls] : { $fmls = new ArrayList<>(); }
	(f=literalset { $fmls.add($f.fml); } POINT)*;

literalset returns [Set<Literal> fml] : {$fml = new HashSet<>(); }
	f1=literal { $fml.add($f1.fml); } (AND f2=literal { $fml.add($f2.fml); } )*;

PREFERENCES : 'Preferences';
EXPECTATIONS : 'Expectations';
IMPOSSIBLE : 'Impossible states';
RULEARROW :	'=>' ;
TRUE	:	'true';


literals returns [Formula fml] : 
	TRUE {$fml = new True(); } | 
	l=literal AND l2=literals { $fml = new And($l.fml, $l2.fml); } |
	l=literal { $fml = $l.fml; };
	
literal returns [Literal fml] :
	{boolean positive = true;} 
	(NOT {positive = false;})? t=TEXT { $fml = new Literal($t.getText(), positive); };

// General Lexing stuff
COMMENT
    : '/*' .* '*/' {$channel=HIDDEN;}
    ;
LINE_COMMENT
    : '%' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    ;
NEWLINE:'\r'? '\n' {skip();} ;
WS  :   (' '|'\t')+ {skip();} ;


TEXT : 'a'..'z'('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'('|')'|','|'~')*;

NOT	:	'~';

POINT	:	'.';
AND		:	'&&';

COLON	:	':';