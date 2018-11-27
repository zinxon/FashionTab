package hk.edu.hkbu.comp.e5225623.fashiontap

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.google.android.youtube.player.YouTubeBaseActivity
import hk.edu.hkbu.comp.e5225623.fashiontap.databinding.ActivityThreadBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import hk.edu.hkbu.comp.e5225623.fashiontap.databinding.ActivityMainBinding
import hk.edu.hkbu.comp.e5225623.fashiontap.json.Item
import hk.edu.hkbu.comp.e5225623.fashiontap.json.Photo
import hk.edu.hkbu.comp.e5225623.fashiontap.json.YoutubeChannelResponse
import retrofit2.Call
import retrofit2.Callback


class ThreadActivity : YouTubeBaseActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_thread)
        binding.appBarMain.contentMain.listViewModel = ListViewModel<Item>(BR.channelItem, R.layout.activity_thread)



        YoutubeService.instance.getYoutubeChannel().enqueue(object : Callback<YoutubeChannelResponse> {
            override fun onFailure(call: Call<YoutubeChannelResponse>, t: Throwable) {
                Log.e("ThreadActivity", t.message)
            }

            override fun onResponse(call: Call<YoutubeChannelResponse>, response: retrofit2.Response<YoutubeChannelResponse>) {
                if (response.isSuccessful) {
                    val channel = response.body()
                        Log.d(
                            "getYoutubeChannel",
                            "channel name: " + channel?.items?.get(0)?.snippet?.title
                        +  "\nchannel description: " + channel?.items?.get(0)?.snippet?.description
                                    +  "\nchannel thumbnails: " + channel?.items?.get(0)?.snippet?.thumbnails?.high?.url
                                    +  "\nchannel viewCount: " + channel?.items?.get(0)?.statistics?.viewCount
                                    +  "\nchannel subscriberCount: " + channel?.items?.get(0)?.statistics?.subscriberCount
                                    +  "\nchannel videoCount: " + channel?.items?.get(0)?.statistics?.videoCount
                        )
                }
            }
        })

//        val youtubeFragment = fragmentManager.findFragmentById(R.id.youtubeFragment) as YouTubePlayerFragment

        val youTubePlayerView = findViewById<View>(R.id.player) as YouTubePlayerView

        youTubePlayerView.initialize("AIzaSyBfemQ9-xiY5eA0X6tKKFpaOa_ZXuLUZ5g",
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {

                    // do any work here to cue video, play video, etc.
//                    youTubePlayer.cueVideo("5xVh-7ywKpE")
                    youTubePlayer.loadPlaylist("UU3T5fmgL4Kvk3kG1kF6JFeA")
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
