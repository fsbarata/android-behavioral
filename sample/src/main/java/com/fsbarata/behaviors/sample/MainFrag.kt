package com.fsbarata.behaviors.sample

import android.widget.Toast
import com.fsbarata.behaviors.MenuBehavior
import com.fsbarata.behaviors.framework.BehaviorFragment

class MainFrag : BehaviorFragment(R.layout.frag_layout) {
	init {
		addBehavior(MenuBehavior(
				this,
				R.menu.sample_fragment_menu,
				R.id.share to { menuItem ->
					Toast.makeText(context, "Clicked $menuItem", Toast.LENGTH_SHORT).show()
				}
		))

		setHasOptionsMenu(true)
	}
}