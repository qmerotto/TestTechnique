package validators;

import static models.ShortSequence.ID;
import static models.ShortSequence.TIKEE_ID;
import static models.ShortSequence.NAME;
import static models.ShortSequence.DESCRIPTION;
import static models.ShortSequence.START;
import static models.ShortSequence.UPLOAD_TO_CLOUD;
import static models.ShortSequence.IMAGE_FORMAT;
import static models.ShortSequence.KEEP_LOCAL_COPY;
import static models.ShortSequence.SEQUENCE_ID;
import static models.ShortSequence.SHOOTING_STATUS;
import static models.ShortSequence.NB_IMG_ON_SD;
import static models.ShortSequence.NB_IMG_UPLOADED;
import static models.ShortSequence.INTERVAL;
import static models.ShortSequence.DURATION;

import dao.ShortSequenceDao;
import io.vertx.core.json.JsonObject;
import models.ShortSequence;

import java.sql.Timestamp;
import java.util.List;

public class ShortSequenceValidator {

    public static Boolean isValidId(Integer id) {return id >= 0;}
    public static Boolean isValidDuration(Integer duration){
        return duration >= 0;
    };
    public static Boolean isValidInterval(Integer interval){
        return interval >= 0;
    };

    public static Boolean isValidBody(JsonObject body){
        if( body.getInteger(ID) == null
            || body.getInteger(TIKEE_ID) == null
            || body.getValue(START) == null
            || body.getInteger(INTERVAL) == null
            || body.getInteger(DURATION) == null ) {
            return false;
        }
        if (!isValidId(body.getInteger(ID)))
            return false;
        if (!isValidDuration(body.getInteger(DURATION)))
            return false;
        if (!isValidInterval(body.getInteger(INTERVAL)))
            return false;

        return true;
    }

    public static Boolean canCreateSequence(JsonObject body){
        Integer id = body.getInteger(ID);
        Integer tikeeId = body.getInteger(TIKEE_ID);
        Integer duration = body.getInteger(DURATION);
        Timestamp ts = new Timestamp(body.getLong(START) * 1000); //Constructor in milliseconds

        return ShortSequenceDao.getShortSequenceById(id) == null
                && !isOverlappingSequences(tikeeId, duration, ts);
    }

    public static Boolean isOverlappingSequences(Integer tikeeId, Integer duration, Timestamp timestamp){
        List<ShortSequence> shortSequenceList = ShortSequenceDao.getShortSequenceListByTikeeId(tikeeId);

        if(shortSequenceList.isEmpty())
            return false;

        //Initial time of the sequence we're testing
        Long ti0 = timestamp.toInstant().getEpochSecond();
        //Final time of the sequence
        Long tf0 = ti0 + duration;

        for(ShortSequence seq : shortSequenceList) {
            Integer currentDuration = seq.getDuration();
            Timestamp currentTimestamp = seq.getStart();

            Long ti1 = currentTimestamp.toInstant().getEpochSecond();
            Long tf1 = ti1 + currentDuration;

            if ((ti1 >= ti0 && ti1 <= tf0)
                    || (tf1 >= ti0 && tf1 <= tf0) //Whether the current sequence begins or ends during the sequence we're testing
                    || (ti1 <= ti0 && tf1 >= tf0)) { //Or the curring sequence is totally overlapping it
                return true;
            }
        }
        return false;
    }
}
