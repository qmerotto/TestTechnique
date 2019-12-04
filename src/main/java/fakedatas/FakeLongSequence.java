package fakedatas;

import models.LongSequence;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FakeLongSequence {

    private static Integer FAKE_TIKEE_ID_0 = 0;
    private static Integer FAKE_TIKEE_ID_1 = 1;
    private static Integer FAKE_TIKEE_ID_2 = 2;

    private static LongSequence FAKE_LONG_SEQUENCE_0 = new LongSequence();
    private static LongSequence FAKE_LONG_SEQUENCE_1 = new LongSequence();
    private static LongSequence FAKE_LONG_SEQUENCE_2 = new LongSequence();

    private static Map<Integer, LongSequence> FAKE_LONG_SEQUENCE_MAP = new HashMap<>();

    {
        FAKE_LONG_SEQUENCE_MAP.put(0, FAKE_LONG_SEQUENCE_0);
        FAKE_LONG_SEQUENCE_MAP.put(1, FAKE_LONG_SEQUENCE_1);
        FAKE_LONG_SEQUENCE_MAP.put(2, FAKE_LONG_SEQUENCE_2);
    }

    public static LongSequence getFakeLongSequence(Integer id){
        return FAKE_LONG_SEQUENCE_MAP.get(id);
    };

    public static List<LongSequence> getFakeLongSequencesList(Integer tikeeId){
        List<LongSequence> result = new LinkedList<>();

        for(Map.Entry<Integer, LongSequence> entry : FAKE_LONG_SEQUENCE_MAP.entrySet()){
            if (entry.getValue().getTikeeId().equals(tikeeId)){
                result.add(entry.getValue());
            }
        }
        return result;
    }
}
