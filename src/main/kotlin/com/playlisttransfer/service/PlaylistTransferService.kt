package com.playlisttransfer.service

import com.playlisttransfer.model.youtube.YouTubePlaylistItem
import org.springframework.stereotype.Service

@Service
class PlaylistTransferService(private val youtubeService: YouTubeService) {

    suspend fun transferPlaylist(playlistUrl: String): List<YouTubePlaylistItem> {
        return if (playlistUrl.contains("music.youtube.com")) {
            youtubeService.getPlaylistItems(playlistUrl)
        } else if (playlistUrl.contains("music.apple.com")) {

            println("Apple Music transfer is not yet implemented.")
            emptyList()
        } else {
            println("Unknown platform.")
            emptyList()
        }
    }
}