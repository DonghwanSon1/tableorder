package tableorder.tableorder.domain.image

import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import tableorder.tableorder.global.exception.CommonException
import tableorder.tableorder.global.exception.CommonExceptionCode
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.UUID

@RestController
@RequestMapping("/image")
@Tag(name = "Image", description = "이미지 관련 API")
class ImageController(
    @Value("\${file.dir}")
    private val uploadDir: String,

    @Value("\${file.imageUrl}")
    private val serverUrl: String
) {

    @PostMapping("/upload")
    fun uploadImage(@RequestParam("file") file: MultipartFile): String {
        if (file.isEmpty) throw CommonException(CommonExceptionCode.IMAGE_BAD_REQUEST)
        val uploadPath = Paths.get(uploadDir)
        val imageName: String = UUID.randomUUID().toString()

        val targetLocation = uploadPath.resolve(imageName)
        file.inputStream.use { inputStream ->
            Files.copy(inputStream, targetLocation)
        }

        val imageUrl = "$serverUrl/$imageName"

        return imageUrl
    }

    @GetMapping("/{imageName}")
    fun getImage(@PathVariable imageName: String, response: HttpServletResponse) {
        val imageFile = File(uploadDir, imageName)

        if (!imageFile.exists()) throw CommonException(CommonExceptionCode.NO_IMAGE_EXISTS)

        response.contentType = "image/jpeg"
        response.outputStream.use { out ->
            imageFile.inputStream().use { inputStream ->
                inputStream.copyTo(out)
            }
        }
    }

    @DeleteMapping("/{imageName}")
    fun deleteImage(@PathVariable imageName: String) {
        val path: Path = Paths.get("$uploadDir/$imageName")

        if (!Files.exists(path)) throw CommonException(CommonExceptionCode.NO_IMAGE_EXISTS)
        Files.delete(path)
    }

}