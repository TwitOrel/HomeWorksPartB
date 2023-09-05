public class HW3Exception extends Exception{
    private final String error;
    private final int numOfLineError;
    private final String Line;

    public HW3Exception(String typeOfBug,int numOfLineError,String line){
        this.error = typeOfBug;
        this.numOfLineError = numOfLineError;
        this.Line = line;
    }
    public String getError(){
        return "line number="+this.numOfLineError+", input line="+this.Line+", Error message = "+this.error;
    }
}
