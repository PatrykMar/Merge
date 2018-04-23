package project.merge.model;

public class Items {
    String name;
    String score;

    public Items(int i,String string,String cursorString){

    }

    public Items(String score){
        this.score = score;
    }

    public Items (String name,String score){
        this.name = name;
        this.score = score;
    }

    public Items(){

    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name =name;
    }

    public String getScore(){
        return score;
    }

    public void setScore(String score){
        this.score = score;
    }
}

