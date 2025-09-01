package personal.project.aggregator.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class StockTickers<E> extends LinkedList<E> {

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    int size;

    public StockTickers(){
        super();
    }
    @Override
    public boolean add(E e){
        if(this.size()>=size){
            super.remove();
        }
        super.add(e);
        return true;
    }

}
