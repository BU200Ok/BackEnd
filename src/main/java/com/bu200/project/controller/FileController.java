//package com.bu200.project.controller;
//
//import com.bu200.common.response.ResponseDTO;
//import com.bu200.common.response.Tool;
//import com.bu200.project.service.FileService;
//import jakarta.annotation.Resource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/file")
//public class FileController {
//    private final Tool tool;
//    private final FileService fileService;
//    @Value("${file.upload-dir}")
//    private String directPath;
//
//    public FileController(Tool tool, FileService fileService) {
//        this.tool = tool;
//        this.fileService = fileService;
//    }
//    @PostMapping("/upload/{forumCode}")
//    public ResponseEntity<?> uploadFile(@RequestPart List<MultipartFile> files, @PathVariable Long forumCode){
//        List<String> changedFilesName = new ArrayList<>();
//        for(MultipartFile file : files){
//            changedFilesName.add(tool.upload(file));
//        }
//        return ResponseEntity.ok().body(changedFilesName);
//    }
//
//    @GetMapping("/download")
//    public ResponseEntity<ResponseDTO> downloadFile(@RequestParam String fileName) throws IOException {
//        Path path = Paths.get(directPath + fileName);
//        if(Files.exists(path)){
//            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//            return tool.res(HttpStatus.OK, "파일입니다.", resource);
//        }else{
//            return tool.resErr("파일이 없습니다.");
//        }
//    }
//
//}
