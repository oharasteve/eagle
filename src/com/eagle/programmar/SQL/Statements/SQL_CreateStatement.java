// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Constraint;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Statements.SQL_CreateStatement.SQL_CreateTableStatement.SQL_MoreKeyFields;
import com.eagle.programmar.SQL.Symbols.SQL_Field_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Index_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Key_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Role_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Synonym_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Table_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_View_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQL_CreateStatement extends TokenChooser
{
	public static class SQL_CreateTableStatement extends TokenSequence
	{
		public @DOC("sql_create_table.asp") SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public SQL_Keyword TABLE = new SQL_Keyword("TABLE");
		public SQL_Table_Definition table;
		public SQL_Punctuation leftParen = new SQL_Punctuation('(');
		public SQL_CreateField createField;
		public @OPT TokenList<SQL_CreateMoreFields> more;
		public @OPT TokenList<SQL_CreateFieldKey> keys;
		public SQL_Punctuation rightParen = new SQL_Punctuation(')');
		public @OPT TokenList<SQL_CreateOption> options;
		public SQL_Punctuation semicolon = new SQL_Punctuation(';');
	
		public static class SQL_CreateField extends TokenSequence
		{
			public SQL_Field_Definition fieldName;
			public SQL_Type fieldType;
			public @OPT TokenList<SQL_FieldOption> fieldOptions;
			
			public static class SQL_FieldOption extends TokenChooser
			{
				public SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
				
				public static class SQL_FieldNotNull extends TokenSequence
				{
					public @OPT SQL_KeywordChoice NOT = new SQL_KeywordChoice("NON", "NOT");
					public SQL_Keyword NULL = new SQL_Keyword("NULL");
				}
				
				public static class SQL_FieldDefault extends TokenSequence
				{
					public SQL_Keyword DEFAULT = new SQL_Keyword("DEFAULT");
					public SQL_Expression value;
				}
				
				public static class SQL_FieldOnUpdate extends TokenSequence
				{
					public SQL_Keyword ON = new SQL_Keyword("ON");
					public SQL_Keyword UPDATE = new SQL_Keyword("UPDATE");
					public SQL_Expression value;
				}
				
				public static class SQL_FieldComment extends TokenSequence
				{
					public SQL_Keyword COMMENT = new SQL_Keyword("COMMENT");
					public SQL_Literal comment;
				}
				
				public static class SQL_FieldKey extends TokenSequence
				{
					public SQL_Keyword PRIMARY = new SQL_Keyword("PRIMARY");
					public SQL_Keyword KEY = new SQL_Keyword("KEY");
				}
			}
		}
		
		public static class SQL_CreateMoreFields extends TokenSequence
		{
			public SQL_Punctuation comma = new SQL_Punctuation(',');
			public SQL_CreateField createField;
		}
		
		public static class SQL_CreateFieldKey extends TokenChooser
		{
			public static class SQL_CreateFieldPrimaryKey extends TokenSequence
			{
				public SQL_Punctuation comma = new SQL_Punctuation(',');
				public SQL_Keyword PRIMARY = new SQL_Keyword("PRIMARY");
				public SQL_Keyword KEY = new SQL_Keyword("KEY");
				public SQL_Punctuation leftParen = new SQL_Punctuation('(');
				public SQL_Identifier_Reference keyField;
				public @OPT TokenList<SQL_MoreKeyFields> moreFields;
				public SQL_Punctuation rightParen = new SQL_Punctuation(')');
			}
	
			public static class SQL_CreateFieldPlainKey extends TokenSequence
			{
				public SQL_Punctuation comma = new SQL_Punctuation(',');
				public SQL_Keyword KEY = new SQL_Keyword("KEY");
				public SQL_Key_Definition key;
				public SQL_Punctuation leftParen = new SQL_Punctuation('(');
				public SQL_Identifier_Reference keyField;
				public @OPT TokenList<SQL_MoreKeyFields> moreFields;
				public SQL_Punctuation rightParen = new SQL_Punctuation(')');
			}
	
			public static class SQL_CreateFieldUniqueKey extends TokenSequence
			{
				public SQL_Punctuation comma = new SQL_Punctuation(',');
				public SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
				public @OPT SQL_CreateFieldUniqueKeyName name;
				public SQL_Punctuation leftParen = new SQL_Punctuation('(');
				public SQL_Identifier_Reference keyField;
				public @OPT TokenList<SQL_MoreKeyFields> moreFields;
				public SQL_Punctuation rightParen = new SQL_Punctuation(')');

				public static class SQL_CreateFieldUniqueKeyName extends TokenSequence
				{
					public SQL_Keyword KEY = new SQL_Keyword("KEY");
					public SQL_Key_Definition key;
				}
			}
			
			public static class SQL_CreateFieldConstraint extends TokenSequence
			{
				public SQL_Punctuation comma = new SQL_Punctuation(',');
				public SQL_Constraint constraint;
			}
		}
	
		public static class SQL_MoreKeyFields extends TokenSequence
		{
			public SQL_Punctuation comma = new SQL_Punctuation(',');
			public SQL_Identifier_Reference keyField;
		}
	
		public static class SQL_CreateOption extends TokenChooser
		{
			public SQL_Keyword DEFAULT = new SQL_Keyword("DEFAULT");
	
			public static class SQL_CreateEngine extends TokenSequence
			{
				public SQL_Keyword ENGINE = new SQL_Keyword("ENGINE");
				public SQL_Punctuation equals = new SQL_Punctuation('=');
				public SQL_Keyword MYIASM = new SQL_Keyword("MyISAM");
			}
	
			public static class SQL_CreateCharset extends TokenSequence
			{
				public SQL_Keyword CHARSET = new SQL_Keyword("CHARSET");
				public SQL_Punctuation equals = new SQL_Punctuation('=');
				public SQL_KeywordChoice charset = new SQL_KeywordChoice("latin1", "utf8");
			}
	
			public static class SQL_CreateComment extends TokenSequence
			{
				public SQL_Keyword COMMENT = new SQL_Keyword("COMMENT");
				public SQL_Punctuation equals = new SQL_Punctuation('=');
				public SQL_Literal tital;
			}
		}
	}
	
	public static class SQL_CreateIndexStatement extends TokenSequence
	{
		public SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @OPT SQL_Keyword UNIQUE = new SQL_Keyword("UNIQUE");
		public SQL_Keyword INDEX = new SQL_Keyword("INDEX");
		public SQL_Index_Definition index;
		public SQL_Keyword ON = new SQL_Keyword("ON");
		public SQL_Identifier_Reference table;
		public SQL_Punctuation leftParen = new SQL_Punctuation('(');
		public SQL_Identifier_Reference keyField;
		public @OPT TokenList<SQL_MoreKeyFields> moreFields;
		public SQL_Punctuation rightParen = new SQL_Punctuation(')');
		public SQL_Punctuation semicolon = new SQL_Punctuation(';');
	}
	
	public static class SQL_CreateViewStatement extends TokenSequence
	{
		public SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public @OPT SQL_Keyword OR = new SQL_Keyword("OR");
		public @OPT SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
		public SQL_Keyword VIEW = new SQL_Keyword("VIEW");
		public SQL_View_Definition view;
		public SQL_Keyword AS = new SQL_Keyword("AS");
		public SQL_SelectStatement select;
	}
	
	public static class SQL_CreateRoleStatement extends TokenSequence
	{
		public SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public SQL_Keyword ROLE = new SQL_Keyword("ROLE");
		public SQL_Role_Definition role;
		public SQL_Punctuation semicolon = new SQL_Punctuation(';');
	}
	
	public static class SQL_CreateSynonymStatement extends TokenSequence
	{
		public SQL_Keyword CREATE = new SQL_Keyword("CREATE");
		public SQL_Keyword PUBLIC = new SQL_Keyword("PUBLIC");
		public SQL_Keyword SYNONYM = new SQL_Keyword("SYNONYM");
		public SQL_Synonym_Definition synonym;
		public SQL_Keyword FOR = new SQL_Keyword("FOR");
		public @OPT SQL_CreateSynonymForWhom whom;
		public SQL_Punctuation semicolon = new SQL_Punctuation(';');
		
		public static class SQL_CreateSynonymForWhom extends TokenSequence
		{
			public SQL_Punctuation ampersand = new SQL_Punctuation('&');
			public SQL_Identifier_Reference user;
			public SQL_Punctuation dotDot = new SQL_Punctuation("..");
			public SQL_Identifier_Reference group;
		}
	}
}
