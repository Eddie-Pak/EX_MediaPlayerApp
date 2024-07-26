package com.example.mediaplayerapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.mediaplayerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { mediaPlayerPlay() }
        binding.pauseButton.setOnClickListener { mediaPlayerPause() }
        binding.stopButton.setOnClickListener { mediaPlayerStop() }
    }

    private fun mediaPlayerPlay() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 200)
        val intent = Intent(this, MediaPlayerService::class.java).apply {
            action = MEDIA_PLAYER_PLAY
        }
        startService(intent)
    }

    private fun mediaPlayerPause() {
        val intent = Intent(this, MediaPlayerService::class.java).apply {
            action = MEDIA_PLAYER_PAUSE
        }
        startService(intent)
    }

    private fun mediaPlayerStop() {
        val intent = Intent(this, MediaPlayerService::class.java).apply {
            action = MEDIA_PLAYER_STOP
        }
        startService(intent)
    }

    override fun onDestroy() {
        stopService(Intent(this, MediaPlayerService::class.java))
        super.onDestroy()
    }
}