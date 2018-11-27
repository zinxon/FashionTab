package hk.edu.hkbu.comp.e5225623.fashiontap

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import hk.edu.hkbu.comp.e5225623.fashiontap.json.Photo
import hk.edu.hkbu.comp.e5225623.fashiontap.json.PhotoResponse
import hk.edu.hkbu.comp.e5225623.fashiontap.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.appBarMain.contentMain.listViewModel = ListViewModel<Photo>(BR.threadItem, R.layout.content_main_item)
        setSupportActionBar(toolbar)
        //        binding.appBarMain.contentMain.centerText = "Hello World!"
        //        setContentView(R.layout.activity_main)
        //        fab.setOnClickListener { view ->
        //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show()
        //        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

//        nav_view.setNavigationItemSelectedListener(this)
        refreshThreads()

    }

    fun refreshThreads() {
        FashionService.instance.getFashionPhoto().enqueue(object : Callback<PhotoResponse> {
            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                Log.e("MainActivity", t.message)
            }

            override fun onResponse(call: Call<PhotoResponse>, response: retrofit2.Response<PhotoResponse>) {
                if (response.isSuccessful) {
                    val threads = response.body()?.photo_stream?.photos as List<Photo>
                    for (thread in threads) {
                        Log.d(
                            "getFashionPhoto",
                            thread.user.username + " " + thread.user.name + " before " + System.currentTimeMillis()
                        )

                    }
//                    Log.d("test123",threads.toString())
                    with(binding.appBarMain.contentMain.listViewModel?.items as ObservableArrayList<Photo>) {
                        clear()
                        addAll(threads)
                    }
                }
            }
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_refresh -> {
                refreshThreads()
                return true
            }
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun showThreadActivity(thread:Photo) {
        with(Intent(this, ThreadActivity::class.java)) {
//            putExtra("thread", thread)
            startActivity(this)
            overridePendingTransition(R.anim.child_enter, R.anim.parent_exit)
        }
    }
}
