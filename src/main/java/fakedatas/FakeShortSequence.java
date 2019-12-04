package fakedatas;

import models.ShortSequence;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FakeShortSequence {

    private static Integer FAKE_TIKEE_ID_0 = 0;
    private static Integer FAKE_TIKEE_ID_1 = 1;
    private static Integer FAKE_TIKEE_ID_2 = 2;
    private static Long TIMESTAMP1 = Long.valueOf("1575483191000");//04/12/2019 à 19:13:11
    private static Long TIMESTAMP2 = Long.valueOf("1575396791000");//03/12/2019 à 19:13:11
    private static Long TIMESTAMP3 = Long.valueOf("1575310391000");//02/12/2019 à 19:13:11
    private static Map<Integer, ShortSequence> FAKE_SHORT_SEQUENCE_MAP = new HashMap<>();
    private static ShortSequence FAKE_SHORT_SEQUENCE_0 = new ShortSequence(
            0,
                FAKE_TIKEE_ID_0,
            "name1",
            "description",
            new Timestamp(TIMESTAMP1),
            15,
            3600,
            true,
            "jpeg",
            true,
            new BigInteger("10000"),
            "running",
            555,
            666);
    private static ShortSequence FAKE_SHORT_SEQUENCE_1 = new ShortSequence(
            1,
            FAKE_TIKEE_ID_1,
            "name2",
            "description",
            new Timestamp(TIMESTAMP2),
            15,
            3600,
            false,
            "bmp",
            false,
            new BigInteger("99000"),
            "stopped",
            111,
            222);
    private static ShortSequence FAKE_SHORT_SEQUENCE_2 = new ShortSequence(
            2,
            FAKE_TIKEE_ID_1,
            "name3",
            "description",
            new Timestamp(TIMESTAMP3),
            15,
            3600,
            false,
            "jpg",
            true,
            new BigInteger("5"),
            "?",
            4,
            5);
    static
    {
        FAKE_SHORT_SEQUENCE_MAP.put(0, FAKE_SHORT_SEQUENCE_0);
        FAKE_SHORT_SEQUENCE_MAP.put(1, FAKE_SHORT_SEQUENCE_1);
        FAKE_SHORT_SEQUENCE_MAP.put(2, FAKE_SHORT_SEQUENCE_2);
    }

    public static ShortSequence getFakeShortSequence(Integer id){
        return FAKE_SHORT_SEQUENCE_MAP.get(id);
    };

    public static List<ShortSequence> getFakeShortSequencesList(Integer tikeeId){
        List<ShortSequence> result = new LinkedList<>();

        for(Map.Entry<Integer, ShortSequence> entry : FAKE_SHORT_SEQUENCE_MAP.entrySet()){
            if (entry.getValue().getTikeeId().equals(tikeeId)){
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public static void putFakeShortSequence(Integer id, ShortSequence sequence){
        FAKE_SHORT_SEQUENCE_MAP.put(id, sequence);
    }

    public static void deleteFakeShortSequence(Integer id){
        FAKE_SHORT_SEQUENCE_MAP.remove(id);
    }
}
