package com.nex.web.spring.controller.ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nex.game.domain.Item;
import com.nex.web.spring.controller.ws.common.RestWebService;

@RestController
@RequestMapping("/item")
public class ItemController extends RestWebService<Item> {

}
