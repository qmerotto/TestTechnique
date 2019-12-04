package models;

import java.math.BigInteger;
import java.sql.Timestamp;

public class LongSequence extends AbstractSequence {

    private Timestamp start;
    private Timestamp end;
    private Boolean infinite_duration;

    public LongSequence(){};
    private LongSequence(Integer id, Integer tikeeId, String name, String description, Timestamp start,
                         Timestamp end, Boolean infinite_duration, Boolean uploadToCloud, String imageFormat,
                         Boolean keepLocalCopy, BigInteger sequenceId, String shootingStatus, Integer nbImagesOnSd,
                         Integer nbImagesUploaded){

        super(id, tikeeId, name, description, start, uploadToCloud, imageFormat, keepLocalCopy, sequenceId,
                shootingStatus, nbImagesOnSd, nbImagesUploaded);
        this.start = start;
        this.end = end;
        this.infinite_duration = infinite_duration;
    }
}
