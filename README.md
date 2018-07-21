# android-behavioral
Android behaviors for activities and fragments logic composition.

Provide consistency and reusability to your activities and fragments using this framework.

## Gradle
	repositories {
		maven { url 'https://jitpack.io' }
	}
	dependencies {
		implementation 'com.github.fsbarata:android-behavioral:1.0'
	}

## Behaviors
### Menu Items
#### Before:
	override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
		when (item.itemId) {
			R.id.action_settings -> ...
			...
			else -> super.onOptionsItemSelected(item)
		}
#### After:
	init {
		addBehavior(MenuBehavior(this, R.menu.main,
				R.id.action_settings to { item -> ... }
				...
		))
	}


### Toolbar Back Arrow
#### Before:
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		...

		setSupportActionBar(findViewById<Toolbar>(mToolbarId))
		supportActionBar?.setDisplayShowHomeEnabled(true)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		...
	}
	
	override fun onSupportNavigateUp(): Boolean {
		activity.onBackPressed()
		return true
	}


#### After:
	init {
		addBehavior(ToolbarBackActivityBehavior(this, R.id.toolbar))
	}

### Action Drawer
#### Before:
	private val drawer by lazy { findViewById<DrawerLayout>(R.id.drawer_layout) }
	private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
	
	override fun onPostCreate(savedInstanceState: Bundle?) {
		super.onPostCreate(savedInstanceState)
		// Sync the toggle state after onRestoreInstanceState has occurred.
		actionBarDrawerToggle.syncState()
	}

	override fun onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START)
		} else {
			super.onBackPressed()
		}
	}

	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			...
		}

		drawer.closeDrawer(GravityCompat.START)
		return true
	}

	private fun setupNavigation() {
		actionBarDrawerToggle = ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
		drawer.addDrawerListener(actionBarDrawerToggle)
		navigationView.setNavigationItemSelectedListener(this)
	}
	
#### After
	init { 
		addBehavior(ToolbarActionDrawerActivityBehavior(this,
				R.id.drawer_layout, R.id.toolbar, R.id.nav_view,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close,
				navigationItemSelectedListener = { false }))
	}


### Write your own
#### Before
	@Inject
	lateinit var presenter: MainPresenter

	override fun onCreate() {
		...
		getInjector().inject(this);
	}

	override fun onStart() {
		super.onStart()
		presenter.start()
	}

	override fun onStop() {
		presenter.stop()
		super.onStop()
	}


#### After
	init {
		addBehavior(PresenterBehavior { presenter })
	}

	@Inject
	lateinit var presenter: MainPresenter
	
	override fun onCreate() {
		...
		getInjector().inject(this);
	}

Where:

	class PresenterBehavior(
		presenterProvider: () -> Presenter
	) : AbstractLifecycleBehavior(), ILifecycleBehavior {
		lateinit var presenter: Presenter

		override fun onStart() {
			super.onStart()
			presenter = presenterProvider()
			presenter.start()
		}
	
		override fun onStop() {
			presenter.stop()
			super.onStop()
		}
	}

	
