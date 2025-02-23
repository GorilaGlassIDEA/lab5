package by.dima.model.service.generate.id;

import lombok.Data;

@Data
public class IdGenerateMy implements IdGenerateble {
    private long newId = -1;
    private long[] oldId;

    public IdGenerateMy(long... oldId) {
        this.oldId = oldId;
    }

    @Override
    public long generateId() {
        if (oldId.length == 0) {
            newId = 1;
        } else {
            for (long l : oldId) {
                newId = Math.max(newId, l);
            }
            newId++;
        }
        return newId;
    }
}