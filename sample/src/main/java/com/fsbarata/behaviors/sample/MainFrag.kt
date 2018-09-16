package com.fsbarata.behaviors.sample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fsbarata.behaviors.MenuBehavior
import com.fsbarata.behaviors.framework.BehaviorFragment

class MainFrag : BehaviorFragment() {
	init {
		addBehavior(MenuBehavior(
				{ activity },
				R.menu.sample_menu,
				R.id.share to { menuItem -> Log.d("MainFrag", "Clicked share") }
		))

		setHasOptionsMenu(true)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
			inflater.inflate(R.layout.frag_layout, container, false)
}