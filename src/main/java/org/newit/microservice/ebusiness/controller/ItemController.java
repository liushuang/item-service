package org.newit.microservice.ebusiness.controller;

import java.util.List;

import org.newit.microservice.ebusiness.model.Item;
import org.newit.microservice.ebusiness.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

@Controller
public class ItemController{

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/insert")
    @ResponseBody
    public JSONObject insert(@RequestBody Item item) {
        itemService.insert(item);
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }

    @RequestMapping(value = "/item/allList", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> itemAllList(@RequestParam(value = "start", defaultValue = "0") int start,
                                  @RequestParam(value = "limit", defaultValue = "0") int limit,
                                  @RequestParam(value = "param3", defaultValue = "0") String param3){
        System.out.println("get itemAllList" + System.currentTimeMillis() + param3);
        List<Item> itemList = itemService.getItemAllList();
        if(start >= 0 && limit >0){
            if(itemList.size() >= start + limit){
                return itemList.subList(start, start + limit);
            }
        }
        return itemList;
    }

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public Item itemDetail(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }
}
