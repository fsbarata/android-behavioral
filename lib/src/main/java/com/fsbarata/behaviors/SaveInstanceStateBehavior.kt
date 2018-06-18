package com.fsbarata.behaviors

import android.os.Bundle
import android.os.Parcelable
import com.fsbarata.behaviors.framework.AbstractLifecycleBehavior

class SaveInstanceStateBehavior<T: Parcelable>(
		private val key: String,
		private val restoreableProvider: () -> IRestoreable<T>
) : AbstractLifecycleBehavior() {

	constructor(restoreable: IRestoreable<T>) : this({ restoreable })

	constructor(key: String, restoreable: IRestoreable<T>) : this(key, { restoreable })

	constructor(restoreableProvider: () -> IRestoreable<T>) : this("", restoreableProvider)

	interface IRestoreable<T: Parcelable> {
		fun saveState(): T

		fun restoreState(state: T)
	}

	override fun onSaveInstanceState(outState: Bundle) {
		outState.putParcelable(key, restoreableProvider().saveState())
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		(savedInstanceState.getParcelable(key) as T?)?.run { restoreableProvider().restoreState(this) }
	}
}
