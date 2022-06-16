package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //Необходимо реализовать паттерн наблюдатель для следующей ситуации.
    // Имеется два пользователя первый подписан на три группы, второй на 4.
    // Вывести все оповещения групп для всех пользователей.

    public static void main(String[] args) {
        group idea = new group();
        group view = new group();
        group real = new group();
        group save = new group();

        User user1 = new User("Sebastian Koch");
        User user2 = new User("Ulrich Mühe");

        idea.regObserver(user1);
        idea.regObserver(user2);

        view.regObserver(user1);
        view.regObserver(user2);

        real.regObserver(user1);
        real.regObserver(user2);

        save.regObserver(user2);

        idea.setNews("знает идею");
        System.out.println("\n");
        view.setNews("знает соучастников");
        System.out.println("\n");
        real.setNews("знает итоговую версию");
        System.out.println("\n");
        save.setNews("спасает UM от штази");
    }
}
interface Observable {
    void notifyObservers();
    void regObserver(Observer o);
}
interface Observer{
    void update(String news);
}
class group implements Observable{
    List<Observer> list = new ArrayList<>();
    String news = "Новость1";
    void setNews(String news){
        this.news = news;
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for(Observer o: list){
            o.update(news);
        }
    }

    @Override
    public void regObserver(Observer o) {
        list.add(o);
    }
}
class User implements Observer{
    String name;
    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " "+ news);
    }
}