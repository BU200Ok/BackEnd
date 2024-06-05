package com.bu200.mypage.service;

import com.bu200.mypage.repository.FindMyPageProjectRepository;
import com.bu200.mypage.service.DTO.MainPageProjectSearchResponseDTO;
import com.bu200.project.entity.Project;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchMypageProjectService {
    private final FindMyPageProjectRepository findMyPageProjectRepository;
    private final ModelMapper modelMapper;

    public SearchMypageProjectService(FindMyPageProjectRepository findMyPageProjectRepository, ModelMapper modelMapper) {
        this.findMyPageProjectRepository = findMyPageProjectRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<MainPageProjectSearchResponseDTO> myPageProjectSearchService(String accountId, String keyword){
        List<Project> projects = findMyPageProjectRepository.findProjectByAccount_AccountIdAndProjectNameContaining(accountId, keyword);
        if(projects.isEmpty()){
            throw new EntityNotFoundException("keword: " + accountId+ " + "+ keyword +" 인 엔티티들이 존재하지 않습니다.");
        }

        //keyword가 포함된 프로젝트 리스트를 dto로 매핑
        List<MainPageProjectSearchResponseDTO> mainPageProjectSearchResponseDTOS = projects.stream()
                .map(project -> modelMapper.map(project, MainPageProjectSearchResponseDTO.class))
                .collect(Collectors.toList());

        return mainPageProjectSearchResponseDTOS;   //keyword가 포함된 프로젝트 리스트를 반환
    }
}
