package com.fsbarata.behaviors.framework

abstract class AbstractFragmentBehavior: AbstractLifecycleBehavior(), IFragmentBehavior {
	override fun onViewCreated() {}
	override fun onDestroyView() {}

	override fun onAttach() {}

	override fun onDetach() {}
}