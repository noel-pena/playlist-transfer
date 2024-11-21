package com.playlisttransfer.service

import com.playlisttransfer.model.youtube.YouTubePlaylistItem
import com.playlisttransfer.model.youtube.YouTubePlaylistResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.util.UriComponentsBuilder


@Service
class YouTubeService(private val webClient: WebClient) {

    @Value("\${google.client.secret}")
    private lateinit var youtubeApiKey: String

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
            .queryParam("key", youtubeApiKey)
            .build()
            .toUri()

        return try {
            println("test")
            val response = webClient.get()
                .uri(uri)
                .retrieve()
                .awaitBody<YouTubePlaylistResponse>()
            response.items.map { item ->
                YouTubePlaylistItem(
                    title = item.snippet?.title ?: "unknown",
                    videoId = item.snippet?.resourceId?.videoId ?: "unknown",
                )
            }
        } catch (e: Exception) {
            println("Error fetching playlist: ${e.message}")
            emptyList()
        }
    }
}
