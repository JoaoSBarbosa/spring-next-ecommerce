package br.com.joaosbarbosa.backend.repositories;

import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class Hello {

    @GetMapping("/{name}")
    public String helloWord(@PathVariable String name){
        return "Olá, "+name+"! Seja bem vindo ao projeto\n Agora são: "+ new Date() +", não esta cansado?";
    }
}
