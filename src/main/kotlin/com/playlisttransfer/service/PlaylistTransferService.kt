package com.playlisttransfer.service

import org.springframework.stereotype.Service

@Service
class PlaylistTransferService(private val youtubeService: YouTubeService) {

    fun transferPlaylist(playlistUrl: String) {
        if (playlistUrl.contains("music.youtube.com")) {
            youtubeService.transferFromYouTube(playlistUrl)
        } else if (playlistUrl.contains("music.apple.com")) {
            transferFromAppleMusic(playlistUrl)
        }
    }

    private fun transferFromAppleMusic(playlistUrl: String) {
        // TODO: Call Apple Music API: https://developer.apple.com/documentation/applemusicapi/
    }
}