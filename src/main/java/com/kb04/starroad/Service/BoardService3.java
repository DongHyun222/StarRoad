package com.kb04.starroad.Service;

import com.kb04.starroad.Entity.Board;
import com.kb04.starroad.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService3 {

    @Autowired
    private BoardRepository boardRepository;
}
