package com.bu200.project.controller;

import com.bu200.common.response.ResponseDTO;
import com.bu200.common.response.Tool;
import com.bu200.project.dto.ReferenceRoomFileDTO;
import com.bu200.project.service.ReferenceRoomService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/projects/{projectCode}/tasks/{taskType}/{taskTypeDetail}/{taskCode}")
public class ReferenceRoomController {
    private final ReferenceRoomService referenceRoomService;
    private final Tool tool;

    public ReferenceRoomController(ReferenceRoomService referenceRoomService, Tool tool) {
        this.referenceRoomService = referenceRoomService;
        this.tool = tool;
    }

    @GetMapping("/task-files")
    public ResponseEntity<ResponseDTO> getTaskFiles(@PathVariable Long taskCode,
                                                    @PathVariable Long taskType,
                                                    @PathVariable String taskTypeDetail,
                                                    @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, 10);
        List<ReferenceRoomFileDTO> referenceRoomFileDTOS = referenceRoomService.getFiles(taskCode, pageable);

        return tool.res(HttpStatus.OK, "taskFile들입니다.", referenceRoomFileDTOS);
    }

    @GetMapping("/task-files/download")
    public ResponseEntity<?> download(@RequestParam String taskFileRename) throws IOException {
        ByteArrayResource file = tool.downloads(taskFileRename);
        if(file == null){return tool.resErr("파일 손상 혹은 없습니다.");}
        return tool.resFile(file);
    }
}
