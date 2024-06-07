package com.bu200.common.response;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.*;

@Component
public class Tool {
    private final ModelMapper modelMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;
    public Tool(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<ResponseDTO> res(HttpStatus code, String msg, Object data){
        return ResponseEntity.ok().body(new ResponseDTO(code, msg, data));
    }
    public ResponseEntity<?> resFile(Object data){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
        return ResponseEntity.ok().headers(headers).body(data);
    }
    public ResponseEntity<ResponseDTO> resErr(String msg){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.BAD_REQUEST,msg, null));
    }
    public ResponseEntity<ResponseDTO> resCustom(HttpStatus code, String msg, Object data){
        return ResponseEntity.ok().body(new ResponseDTO(code, msg, data));
    }
    public <S, T> List<T> convert(List<S> list, Class<T> targetClass) {
        return list.stream()
                .map(value -> modelMapper.map(value, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * 파일을 업로드하는 메서드 파일은 C:\project-files-bu200ServerFile 에 저장된다.
     * 경로가 없다면 생성한다. 에러가 나면 메세지 출력 후 null 을 응답한다. 윈도우에서만 사용 가능.
     * 파일을 확인하고 싶다면 --"http://localhost:8080/uploads/변경된파일명"-- 입력 시 확인 가능
     * @param file 파일 하나를 받음
     * @return UUID로 변경된 파일 이름
     */
    public String upload(MultipartFile file) {
        if(file.getOriginalFilename() != null) {
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());            //파일 이름
            String changedFileName = UUID.randomUUID() + "_" + originalFileName;                    //변경된 파일 이름은 랜덤 UUID_원본파일명 형식으로 저장된다.
            System.out.println(changedFileName);
            try{
                String filePath = uploadDir + File.separator + changedFileName;
                File dest = new File(filePath);
                File directory = new File(dest.getParent());
                if(!directory.exists()){
                    if(directory.mkdirs()){
                        System.out.println("경로가 존재하지 않아 생성하였습니다.");
                    } else {    //경로생성에 실패한 상황
                        throw new IOException("경로가 존재하지 않아 생성하려고 했으나 실패했습니다. : " + directory.getAbsolutePath());
                    }
                }
                file.transferTo(dest);  //저장
                return changedFileName;
            } catch (IOException e) {   //파일 저장 중 에러 발생 상황
                System.err.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

    //파일이 경로 상에 없을 시 null 반환 changedFileName(ex. f605ac83-c993-4055-a753-da53bbc9c5f7_백엔드 공부 로드맵.txt
    //처럼 uuid + originalFileName + 확장자 풀 네임으로
    //사용 시 header에 headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 추가해서
    //return ResponseEntity.ok().headers(headers).body(resource); 이런 식으로 보내주기
    public ByteArrayResource downloads(String changedFileName) throws IOException {
        Path path = Paths.get(uploadDir + File.separator + changedFileName);
        if (Files.exists(path)) {
            return new ByteArrayResource(Files.readAllBytes(path));
        }
        return null;
    }
}
