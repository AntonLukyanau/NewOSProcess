package org.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        Callable<Integer> callable = () -> {
            System.out.println("поток " + Thread.currentThread().getName() + " начинает работу");
            Thread.sleep(15000);
            System.out.println("поток " + Thread.currentThread().getName() + " закончил работу и возвращает значение");
            return (int) (Math.random()*100);
        };
        List<Callable<Integer>> tasks = Arrays.asList(callable, callable, callable);
        ExecutorService executorService = Executors.newFixedThreadPool(tasks.size());

        try {
            List<Future<Integer>> futures = executorService.invokeAll(tasks);
            for (int i = 0; i < futures.size(); i++) {
                System.out.println("Фича" + i + " ждёт ответ.");
                Integer result = futures.get(i).get();
                System.out.println(i + ") Дождались и получили: " + result);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        Thread.currentThread().interrupt();
        executorService.shutdown();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Для завершения работы введите что-нибудь в консоль");
        scanner.nextLine();


        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe cd C:\\Users\\Anton_Lukyanau1\\Documents\\learning\\JAT");
        processBuilder.command("cmd.exe mkdir " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm")));
        try {
            Process start = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Runtime.getRuntime().exec("cmd.exe cd C:\\Users\\Anton_Lukyanau1\\Documents\\learning\\JAT");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}