package hk.edu.hkbu.comp.e5225623.fashiontap

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import android.view.View
import com.google.android.youtube.player.YouTubeBaseActivity
import hk.edu.hkbu.comp.e5225623.fashiontap.databinding.ActivityThreadBinding
import hk.edu.hkbu.comp.e5225623.fashiontap.json.Photo

import kotlinx.android.synthetic.main.activity_thread.*
import me.tatarka.bindingcollectionadapter2.ItemBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView



class ThreadActivity : YouTubeBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        val binding: ActivityThreadBinding = DataBindingUtil.setContentView(this, R.layout.activity_thread)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        val youTubePlayerView = findViewById<View>(R.id.player) as YouTubePlayerView

        youTubePlayerView.initialize("AIzaSyBfemQ9-xiY5eA0X6tKKFpaOa_ZXuLUZ5g",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {

                    // do any work here to cue video, play video, etc.
//                    youTubePlayer.cueVideo("5xVh-7ywKpE")
                    youTubePlayer.loadPlaylist("PLyoPUP8uSFrffq2z_NAZyg6pLCru73zgD")
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {

                }

            })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                overridePendingTransition(R.anim.parent_enter, R.anim.child_exit)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.parent_enter, R.anim.child_exit)
    }
}
