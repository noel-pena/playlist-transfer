package com.playlisttransfer.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/playlist")
class PlaylistTransferController {

    @PostMapping("/transfer")
    fun transferPlaylist(@RequestBody playlistUrl: String): String {
        return "Transferring $playlistUrl"
    }
}