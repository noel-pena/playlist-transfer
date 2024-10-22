package com.playlisttransfer.service

import org.springframework.stereotype.Service

@Service
class PlaylistTransferService {
    fun transferPlaylist(playlistUrl: String) {
        if (playlistUrl.contains("music.youtube.com")) {
            transferFromYouTube(playlistUrl)
        } else if (playlistUrl.contains("music.apple.com")) {
            transferFromAppleMusic(playlistUrl)
        }
    }

    private fun transferFromYouTube(playlistUrl: String) {
        // TODO: Call YouTube API: https://ytmusicapi.readthedocs.io/en/latest/
    }

    private fun transferFromAppleMusic(playlistUrl: String) {
        // TODO: Call Apple Music API: https://developer.apple.com/documentation/applemusicapi/
    }
}