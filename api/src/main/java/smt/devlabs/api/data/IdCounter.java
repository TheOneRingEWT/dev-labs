package smt.devlabs.api.data;

import org.springframework.stereotype.Component;

@Component
public class IdCounter {
    private int idCounter = 1;

    public int getNewId(){
        return idCounter++;
    }
}
