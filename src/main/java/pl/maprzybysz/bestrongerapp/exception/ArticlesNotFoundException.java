package pl.maprzybysz.bestrongerapp.exception;

public class ArticlesNotFoundException extends RuntimeException{
    public ArticlesNotFoundException(String category){
        super("Articles with category "+category+"not found");
    }
}


