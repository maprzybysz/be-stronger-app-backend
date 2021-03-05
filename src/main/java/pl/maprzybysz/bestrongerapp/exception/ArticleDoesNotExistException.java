package pl.maprzybysz.bestrongerapp.exception;

public class ArticleDoesNotExistException extends RuntimeException {
    public ArticleDoesNotExistException(Long id) {
        super("Article with id: " + id + " does not exist");
    }
}
