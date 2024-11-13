package com.playlisttransfer.service

import com.playlisttransfer.data.YouTubePlaylistItem
import com.playlisttransfer.data.YouTubePlaylistResponse
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder


@Service
class YouTubeService(private val webClient: WebClient) {
    private val youtubeApiBaseUrl = "https://www.googleapis.com/youtube/v3/"

    fun extractPlaylistId(playlistUrl: String): String? {
        val regex = "list=([^&]+)".toRegex()
        val matchResult = regex.find(playlistUrl)
        return matchResult?.groupValues?.get(1)
    }

    suspend fun getPlaylistItems(playlistUrl: String): List<YouTubePlaylistItem> {
        val playlistId = extractPlaylistId(playlistUrl) ?: throw IllegalArgumentException("Invalid YouTube playlist: $playlistUrl")

        val uri = UriComponentsBuilder.fromHttpUrl("$youtubeApiBaseUrl/playlistItems")
            .queryParam("part", "snippet")
            .queryParam("playlistId", playlistId)
            .queryParam("maxResults", 50)
            .build()
            .toUri()

        val response = webClient.get()
            .uri(uri)
            .retrieve()
            .awaitBody<YouTubePlaylistResponse>()

        return response.items.mapNotNull { item ->
            item.snippet?.let { snippet ->
                snippet.resourceId?.videoId?.let { videoId ->
                    YouTubePlaylistItem(
                        title = snippet.title ?: "Unknown Title",
                        videoId = videoId
                    )
                }
            }
        }
    }
}
