package ru.javarush.bichahchyan.caesarcipher;

import ru.javarush.bichahchyan.caesarcipher.Tools.ConsoleManager;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в меню Шифра Цезоря");
        boolean isStopped = false;
        while(!isStopped){
            Commander.showMenu();
            String commandNumber = ConsoleManager.getKeyForMenu();
            if(commandNumber != null) {
                switch (commandNumber) {
                    case "1":
                        System.out.println("Для шифрования укажите путь к файлу , место куда сохранить результат и ключ");
                        Commander.encryptCommand();
                        break;
                    case "2":
                        System.out.println("Для Расшифровки укажите путь к зашифрованному файлу , место куда сохранить результат и ключ");
                        Commander.decryptCommand();
                        break;
                    case "3":
                        System.out.println("Для команды BruteForce нужно указать путь к файлу");
                        Commander.brutForceCommand();
                        break;
                    case "exit":
                        isStopped = true;
                        System.out.println("Выход из приложения");
                        break;
                    default:
                        System.out.println("Unknown Command");
                        break;
                }
            }
        }
    }
}
