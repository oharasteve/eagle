// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Controls.Django_AutoEscapeControl;
import com.eagle.programmar.Django.Controls.Django_BlockControl;
import com.eagle.programmar.Django.Controls.Django_BlockTransControl;
import com.eagle.programmar.Django.Controls.Django_CommentControl;
import com.eagle.programmar.Django.Controls.Django_ExtendsControl;
import com.eagle.programmar.Django.Controls.Django_ForControl;
import com.eagle.programmar.Django.Controls.Django_IfControl;
import com.eagle.programmar.Django.Controls.Django_LoadControl;
import com.eagle.programmar.Django.Controls.Django_SpacelessControl;
import com.eagle.programmar.Django.Controls.Django_TransControl;
import com.eagle.tokens.TokenChooser;

public class Django_Control extends TokenChooser
{
	public @CHOICE Django_AutoEscapeControl autoescapeControl;
	public @CHOICE Django_BlockControl blockControl;
	public @CHOICE Django_BlockTransControl blockTransControl;
	public @CHOICE Django_CommentControl commentControl;
	public @CHOICE Django_ExtendsControl extendsControl;
	public @CHOICE Django_ForControl forControl;
	public @CHOICE Django_IfControl ifControl;
	public @CHOICE Django_LoadControl loadControl;
	public @CHOICE Django_SpacelessControl spacelessControl;
	public @CHOICE Django_TransControl transControl;
}
