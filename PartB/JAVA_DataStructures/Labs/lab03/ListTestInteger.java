package lab03;

public class ListTestInteger extends ListTest<Integer>{
    private int param = 0;
    @Override
    public Integer getParameterInstance() {
        return param++;
    }

}
