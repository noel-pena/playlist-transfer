package com.playlisttransfer.data

data class YouTubePlaylistResponse(val items: List<YouTubePlaylistItemWrapper>)
data class YouTubePlaylistItemWrapper(val snippet: YouTubePlaylistItemSnippet?)
data class YouTubePlaylistItemSnippet(val title: String?, val resourceId: ResourceId?)
data class ResourceId(val videoId: String?)

data class YouTubePlaylistItem(val title: String, val videoId: String)