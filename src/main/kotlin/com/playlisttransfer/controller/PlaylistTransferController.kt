import org.springframework.web.bind.annotation.*
import com.playlisttransfer.service.PlaylistTransferService
import com.playlisttransfer.model.youtube.YouTubePlaylistItem

@RestController
@RequestMapping("/api/playlist")
class PlaylistTransferController(private val playlistTransferService: PlaylistTransferService) {

    @PostMapping("/transfer")
    suspend fun transferPlaylist(@RequestBody playlistUrl: String): List<YouTubePlaylistItem> {
        println("Received request to transfer playlist: $playlistUrl")

        val items = playlistTransferService.transferPlaylist(playlistUrl)

        println("Response from PlaylistTransferService: $items")
        return items
    }
}