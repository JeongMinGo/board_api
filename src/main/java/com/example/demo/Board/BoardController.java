package com.example.demo.Board;



import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value="/board", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class BoardController {
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    public BoardController(ModelMapper modelMapper, BoardRepository boardRepository)
    {
        this.boardRepository=boardRepository;
        this.modelMapper=modelMapper;
    }
    //기입
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid BoardDto boardDto, Errors errors){
        if(errors.hasErrors()) {
            ResponseEntity.badRequest().body(errors);
        }
        Board board = modelMapper.map(boardDto, Board.class);
        Board newboard = boardRepository.save(board);

        return  ResponseEntity.status(201).body(newboard);
    }
    //삭제
    @DeleteMapping("{boardId")
    public ResponseEntity delete(@PathVariable("boardId") long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        if(!board.isPresent()) {
            return ResponseEntity.status(404).body("Board not found");
        }
        Board updateBoard = board.get();
        updateBoard.setIsDeleted(true);
        boardRepository.save(updateBoard);
        return ResponseEntity.status(200).body(updateBoard);
    }

    //수정
    @PutMapping("{boardId}")
    public ResponseEntity update(@RequestBody @Valid BoardUpdateDto boardUpdateDto, @PathVariable("boardId") long boardId, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        Board board=boardRepository.findById(boardId).get();
        //수정사항은 제목과 내용밖에 수정할 것이 없다. Dto 새로 만들어서 이것을 대입하여 주면 끝
        board.setBoardTitle(boardUpdateDto.getBoardTitle());
        board.setBoardContent(boardUpdateDto.getBoardContent());

        boardRepository.save(board);
        return ResponseEntity.status(200).body(board);
    }

    //클릭시 세부정도 보여주기 클릭시 해당되는 boardId값을 가져와서 보여준다.
//    @GetMapping("{boardId}")
//    public ResponseEntity info(@PathVariable("boardId") long boardId, Errors errors){
//        if(errors.hasErrors()) {
//            return ResponseEntity.badRequest().body(errors);
//        }
//        Board board= boardRepository.findById(boardId).get();
//        return ResponseEntity.status(200).body(board);
//    }
    @GetMapping(value = "/api")
    public ResponseEntity allCard() throws Exception{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://omgvamp-hearthstone-v1.p.rapidapi.com/cards")
                .get()
                .addHeader("x-rapidapi-host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "cda38e27d1mshc10a87a7c67d89bp104aa8jsn859181f2bea2")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println("done");
        return ResponseEntity.ok().body(response.body().string());
    }
    @GetMapping(value = "/api/{cardId}")
    public ResponseEntity Info(@PathVariable String cardId) throws Exception{
        OkHttpClient client = new OkHttpClient();
        String requestUrl = "https://omgvamp-hearthstone-v1.p.rapidapi.com/cards/"+cardId;

        Request request = new Request.Builder()
                .url(requestUrl)
                .get()
                .addHeader("x-rapidapi-host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "cda38e27d1mshc10a87a7c67d89bp104aa8jsn859181f2bea2")
                .build();

        Response response = client.newCall(request).execute();
        return ResponseEntity.ok().body(response.body().string());
    }
}
//Post --> "create" 리소스를 생성합니다
//Get --> Read 해당 URL의 리소스를 조회합니다.
//Put --> 해당 URL 리소스를 수정합니다
//Delete --> 해당 URL의 리로스를 삭제합니다.