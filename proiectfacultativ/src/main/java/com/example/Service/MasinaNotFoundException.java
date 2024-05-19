package com.example.Service;

public class MasinaNotFoundException extends RuntimeException{
    public MasinaNotFoundException(String mesaj){
        super(mesaj);
    }
}
