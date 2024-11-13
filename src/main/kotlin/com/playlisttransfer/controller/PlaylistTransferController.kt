package com.playlisttransfer.controller

import com.playlisttransfer.service.PlaylistTransferService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/playlist")
class PlaylistTransferController(private val playlistTransferService: PlaylistTransferService) {

    @PostMapping("/transfer")
    suspend fun transferPlaylist(@RequestBody playlistUrl: String): String {
        playlistTransferService.transferPlaylist(playlistUrl)
        return "Transferring $playlistUrl"
    }
}