package models;

import io.vertx.core.json.JsonObject;

import java.math.BigInteger;
import java.sql.Timestamp;

public class ShortSequence extends AbstractSequence {
    public static String INTERVAL = "interval";
    public static String DURATION = "duration";

    private Integer interval;
    private Integer duration;

    private ShortSequence(){}
    public ShortSequence(Integer id, Integer tikeeId, String name, String description, Timestamp start,
                          Integer interval, Integer duration, Boolean uploadToCloud, String imageFormat,
                          Boolean keepLocalCopy, BigInteger sequenceId, String shootingStatus, Integer nbImagesOnSd,
                          Integer nbImagesUploaded) {

        super(id, tikeeId, name, description, start, uploadToCloud, imageFormat, keepLocalCopy, sequenceId,
                shootingStatus, nbImagesOnSd, nbImagesUploaded);
        this.interval = interval;
        this.duration = duration;
    }

    public static ShortSequence buildShortSequenceFromJson(JsonObject body){
        ShortSequence sequence = new ShortSequence();

        sequence.setId(body.getInteger(ID));
        sequence.setTikeeId(body.getInteger(TIKEE_ID));
        sequence.setName(body.getString(NAME, "Default name"));
        sequence.setDescription(body.getString(DESCRIPTION, ""));
        sequence.setStart(new Timestamp(body.getLong(START) * 1000));
        sequence.setInterval(body.getInteger(INTERVAL));
        sequence.setDuration(body.getInteger(DURATION));
        sequence.setUploadToCloud(body.getBoolean(UPLOAD_TO_CLOUD, true));
        sequence.setImageFormat(body.getString(IMAGE_FORMAT, "jpeg"));
        sequence.setKeepLocalCopy(body.getBoolean(KEEP_LOCAL_COPY, false));
        sequence.setSequenceId(new BigInteger(Long.toString(body.getLong(SEQUENCE_ID))));
        sequence.setShootingStatus(body.getString(SHOOTING_STATUS, ""));
        sequence.setNbImagesOnSd(body.getInteger(NB_IMG_ON_SD, 0));
        sequence.setNbImagesUploaded(body.getInteger(NB_IMG_UPLOADED, 0));

        return sequence;
    }

    public JsonObject toJson(){
        return new JsonObject()
                .put(ID, id)
                .put(TIKEE_ID, tikeeId)
                .put(NAME, name)
                .put(DESCRIPTION, description)
                .put(START, start.toInstant().getEpochSecond())
                .put(INTERVAL, interval)
                .put(DURATION, duration)
                .put(UPLOAD_TO_CLOUD, uploadToCloud)
                .put(IMAGE_FORMAT, imageFormat)
                .put(KEEP_LOCAL_COPY, keepLocalCopy)
                .put(SEQUENCE_ID, sequenceId)
                .put(SHOOTING_STATUS, shootingStatus)
                .put(NB_IMG_ON_SD, nbImagesOnSd)
                .put(NB_IMG_UPLOADED, nbImagesUploaded);
    }


    /*****************************************************************************************
    /* Getters & setters
    /****************************************************************************************/
    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
