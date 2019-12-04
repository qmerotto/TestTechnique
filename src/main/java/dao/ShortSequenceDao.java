package dao;

import fakedatas.FakeShortSequence;
import models.ShortSequence;

import java.util.List;

public class ShortSequenceDao {

    public static List<ShortSequence> getShortSequenceListByTikeeId(Integer tikeeId){
        return FakeShortSequence.getFakeShortSequencesList(tikeeId);
    }
    public static ShortSequence getShortSequenceById(Integer id){
        return FakeShortSequence.getFakeShortSequence(id);
    }
    public static void putShortSequence(ShortSequence sequence){
        FakeShortSequence.putFakeShortSequence(sequence.getId(), sequence);
    }
}
