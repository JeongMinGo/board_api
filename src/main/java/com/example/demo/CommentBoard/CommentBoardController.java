package com.example.demo.CommentBoard;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value ="/commentBoard", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class CommentBoardController {
    private final CommentBoardRepository commentBoardRepository;
    private final ModelMapper modelMapper;

    public CommentBoardController(CommentBoardRepository commentBoardRepository, ModelMapper modelMapper) {
        this.commentBoardRepository=commentBoardRepository;
        this.modelMapper=modelMapper;
    }
    //댓글 등록
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CommentBoardDto commentBoardDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        CommentBoard commentBoard = modelMapper.map(commentBoardDto,CommentBoard.class);
        CommentBoard newCommentBoard = commentBoardRepository.save(commentBoard);

        return ResponseEntity.status(201).body(newCommentBoard);
    }
    //댓글 삭제
    @DeleteMapping("{commentBoardId}")
    public ResponseEntity delete(@PathVariable("commentBoardId") long commentBoardId){
        Optional<CommentBoard> commentBoard = commentBoardRepository.findById(commentBoardId);
        if(!commentBoard.isPresent()){
            return ResponseEntity.badRequest().body("CommentBoard not found");
        }
        CommentBoard updateCommentBoard=commentBoard.get();
        updateCommentBoard.setIsDeleted(true);
        commentBoardRepository.save(updateCommentBoard);
        return ResponseEntity.status(200).body(updateCommentBoard);
    }

    //댓글 수정
    @PutMapping("{commentBoardId}")
    public ResponseEntity update(@RequestBody @Valid CommentBoardUpdateDto commentBoardUpdateDto, @PathVariable("commentBoardId") long commentBoardId, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }
        CommentBoard commentBoard=commentBoardRepository.findById(commentBoardId).get();
        commentBoard.setCommentBoardContent(commentBoardUpdateDto.getBoardContent());

        commentBoardRepository.save(commentBoard);
        return ResponseEntity.status(200).body(commentBoard);
    }
    //

    @GetMapping("{commentBoardId}")
    public ResponseEntity info(@PathVariable("commmentBoardId") long commentBoardId, Errors errors) {
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(errors);
        }
        CommentBoard commentBoard=commentBoardRepository.findById(commentBoardId).get();

        return ResponseEntity.status(200).body(commentBoard);
    }


}
