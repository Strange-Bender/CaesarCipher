package ru.javarush.bichahchyan.caesarcipher.Tools;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Validator {
    public static boolean isFileExists(String path){
        Path filePath = Paths.get(path);
        boolean isRegularFile = Files.isRegularFile(filePath);
        boolean isExists = Files.exists(filePath);
        boolean res = isRegularFile && isExists;
        return res;
    }

    public static boolean isValidKey(int key, List<Character> alphabet){
        if(key < alphabet.size() && key > 0){
            return true;
        }
        return false;
    }


}