package com.sejoon.book.springboot.service.posts;

import com.sejoon.book.springboot.domain.posts.Posts;
import com.sejoon.book.springboot.domain.posts.PostsRepository;
import com.sejoon.book.springboot.web.dto.PostsListResponseDto;
import com.sejoon.book.springboot.web.dto.PostsResponseDto;
import com.sejoon.book.springboot.web.dto.PostsSaveRequestDto;
import com.sejoon.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    //Transaction이란? -> 데이터베이스의 상태를 변화시키기 위해서 수행하는 작업의 단위
    //2개 이상의 쿼리를 하나의 커넥션으로 묶어 DB에 전송하고, 이 과정에서 에러가
    //발생하면 자도응로 모든 과정을 원래대로 되돌려 놓는다(rollback 처리)
    //일반적으로 spring에서는 service layer에서 @Transcational을 추가해 처리함
    //값을 변경하는 insert, update, delete 같은 메소드에서는 Transactional을 해줘야함
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional()
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)// .map(posts -> new PostsListResponseDto(posts))과 같음
                .collect(Collectors.toList());
    }



}
