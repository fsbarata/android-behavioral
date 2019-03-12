package com.fsbarata.behaviors.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.fsbarata.behaviors.MenuBehavior
import com.fsbarata.behaviors.framework.BehaviorFragment

class MainFrag : BehaviorFragment() {
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

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
			inflater.inflate(R.layout.frag_layout, container, false)
}