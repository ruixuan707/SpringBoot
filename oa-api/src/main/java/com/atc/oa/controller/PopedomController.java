package com.atc.oa.controller;

import com.atc.oa.entity.Popedom;
import com.atc.oa.entity.Role;
import com.atc.oa.service.PopedomService;
import com.atc.oa.service.RoleService;
import com.atc.oa.util.TreeToolUtils;
import com.atc.oa.vo.BPopedom;
import com.atc.oa.vo.PopedomPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Popedom
 *
 * @author Mengke
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/popedom")
public class PopedomController {

    @Autowired
    private PopedomService popedomService;

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody List<BPopedom> bPopedomList){
        try {
            List<Popedom> popedomList = new ArrayList<>();
            for (BPopedom bPopedom : bPopedomList){
                Popedom popedom = new Popedom();
                BeanUtils.copyProperties(bPopedom, popedom);
                popedomList.add(popedom);
            }
            popedomService.saveCollection(popedomList);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }catch (Exception e){
            log.error("保存权限出错:{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<PopedomPage>> getList(){
        //1.将实体对象转为页面对象
        List<Popedom> popedomList = popedomService.findAllByOrder();
        List<PopedomPage> popedomPageList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(popedomList)){
            List<PopedomPage> treeList = new ArrayList<>();
            List<PopedomPage> topTreeList = new ArrayList<>();
            for(Popedom popedom:popedomList){
                PopedomPage popedomPage = new PopedomPage();
                popedomPage.setId(popedom.getId()+"");
                popedomPage.setpId(popedom.getPid()+"");
                popedomPage.setTitle(popedom.getMenuName());
                popedomPage.setChildren(new ArrayList<>());
                treeList.add(popedomPage);
                if(popedom.getPid() == 0L){
                    topTreeList.add(popedomPage);
                }
            }
            for (PopedomPage popedomPage:topTreeList){
                TreeToolUtils.createTree(treeList,popedomPage,"id","pId","children");
                popedomPageList.add(popedomPage);
            }
        }
        return ResponseEntity.ok(popedomPageList);
    }


    @GetMapping("{roleId}")
    public ResponseEntity<List<PopedomPage>> getByRole(@PathVariable Long roleId){
        Role role = roleService.find(roleId);
        List<Popedom> popedomList = popedomService.findAllByOrder();
        List<PopedomPage> popedomPageList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(popedomList)){
            List<PopedomPage> treeList = new ArrayList<>();
            List<PopedomPage> topTreeList = new ArrayList<>();
            for(Popedom popedom:popedomList){
                PopedomPage popedomPage = new PopedomPage();
                popedomPage.setId(popedom.getId()+"");
                popedomPage.setpId(popedom.getPid()+"");
                popedomPage.setTitle(popedom.getMenuName());
                treeList.add(popedomPage);
                if(popedom.getPid() == 0L){
                    topTreeList.add(popedomPage);
                }
            }
            for (PopedomPage popedomPage:topTreeList){
                TreeToolUtils.createTree(treeList,popedomPage,"id","pId","children");
                popedomPageList.add(popedomPage);
            }
        }
        return ResponseEntity.ok(popedomPageList);
    }
}