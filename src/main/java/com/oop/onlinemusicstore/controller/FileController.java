package com.oop.onlinemusicstore.controller;

import com.oop.onlinemusicstore.model.Song;
import com.oop.onlinemusicstore.payload.UploadFileResponse;
import com.oop.onlinemusicstore.repository.SongRepository;
import com.oop.onlinemusicstore.service.FileStorageService;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private SongRepository songRepository;

    @PostMapping("/file/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("songImage") MultipartFile songImage,@RequestParam(value = "filename", required = false)String filename
                                        ,@RequestParam("artist") String artist, @RequestParam("genre") String genre, @RequestParam("year") String year) {
        String fileName = fileStorageService.storeFile(file,filename);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadFile/")
                .path(fileName)
                .toUriString();

        String imageFileName = fileStorageService.storeFile(songImage,null);

        String imageFileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadFile/")
                .path(imageFileName)
                .toUriString();

        Song song = new Song();
        song.setArtist(artist);
        song.setDownload_url(fileDownloadUri);
        song.setGenre(genre);
        song.setName(fileName);
        song.setYear(year);
        song.setSize(file.getSize()+"");
        song.setImage_file_url(imageFileDownloadUri);

        songRepository.save(song);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize(), artist, genre, year, imageFileDownloadUri);
    }

    @RequestMapping("/file/allFiles")
    public List<Song> getAllFiles(){

//        List<Song> songs =  songRepository.findAll();
        return songRepository.findAll();
    }

//    @PostMapping("/file/uploadMultipleFiles")
//    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file,null))
//                .collect(Collectors.toList());
//    }

    @GetMapping("/file/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}