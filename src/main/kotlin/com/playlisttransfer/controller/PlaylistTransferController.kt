import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.playlisttransfer.service.PlaylistTransferService
import com.playlisttransfer.model.youtube.YouTubePlaylistItem

@RestController
@RequestMapping("/api/playlist")
class PlaylistTransferController(private val playlistTransferService: PlaylistTransferService) {

    init {
        println("test1")
    }

    @PostMapping("/transfer")
    suspend fun transferPlaylist(@RequestBody playlistUrl: String): ResponseEntity<List<YouTubePlaylistItem>> {
        println("Received request to transfer playlist: $playlistUrl")

        return try {
            val items = playlistTransferService.transferPlaylist(playlistUrl)
            println("Response from PlaylistTransferService: $items")
            ResponseEntity.ok(items)
        } catch (e: Exception) {
            println("Error during playlist transfer: ${e.message}")
            ResponseEntity.status(500).body(emptyList())
        }
    }
}