public class MyArrayDataException extends RuntimeException{

    private Integer i;
    private Integer j;
    public MyArrayDataException(Throwable cause, Integer i, Integer j){
        super(cause);
        this.i = i;
        this.j = j;
    }

    public String getMessage(){
        return "Не удалось преобразовать значение в ячейке ["+ i + ", " + j +"] в число";
    }
}
