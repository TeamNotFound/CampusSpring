package teamNotFound.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

@Controller
public class TestController {

	@Autowired
	private TransferManager amazonS3transfer;

	@PostMapping("/testImage")
	public String testImage(@RequestParam("image") MultipartFile image) throws IOException {
		File imageFilezed = convert(image);
		amazonS3transfer.upload(new PutObjectRequest("campus-bucket", "test-image", imageFilezed));
		imageFilezed.delete();
		
		GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest("campus-bucket", "test-image");
		String url = ((AmazonS3Client) amazonS3transfer.getAmazonS3Client()).generatePresignedUrl(request).toString();

		return "redirect:/Home";
	}

	private File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();

		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
