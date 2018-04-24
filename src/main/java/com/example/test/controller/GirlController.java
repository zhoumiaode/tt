package com.example.test.controller;

import com.example.test.domain.Girl;
import com.example.test.repository.GirlRepository;
import com.example.test.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询女生列表
     * @return
     */
    @GetMapping(value="girls")
    public List<Girl> girlList() {
        return girlRepository.findAll();
    }
    /**
     * 添加一个女生
     * @return
     */
    @PostMapping(value="girls")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.printf(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        girl.setName(girl.getName());
        girl.setCupSize(girl.getCupSize());
        return girlRepository.save(girl);
    }

    @GetMapping(value="FindById/{id}")
    public Girl GirlFindOne(@PathVariable("id") Integer id){
        System.out.println(1);
        return girlRepository.findById(id).get();
    }

    @PutMapping(value="update")
    public Girl updateGirl(){
        Girl girl=new Girl();
        girl.setId(3);
        girl.setCupSize("33");
        return girlRepository.save(girl);
    }

    @DeleteMapping(value="delete/{id}")
    public void deleteGirl(@PathVariable("id")Integer id){

        Girl girl=new Girl();
        girl.setId(id);
        girlRepository.delete(girl);
    }

    @GetMapping(value="girls/{name}")
    public List<Girl> findByName(@PathVariable("name")String name){

        return girlRepository.findByName(name);
    }

    @PostMapping(value="saveTwo")
    public void insertTwo(){

        girlService.insertTwo();
    }
}
