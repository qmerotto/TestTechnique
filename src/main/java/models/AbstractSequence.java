package models;

import java.math.BigInteger;
import java.sql.Timestamp;

public class AbstractSequence {

    public static String ID = "id";
    public static String TIKEE_ID = "tikee_id";
    public static String NAME = "name";
    public static String DESCRIPTION = "description";
    public static String START = "start";
    public static String UPLOAD_TO_CLOUD = "upload_to_cloud";
    public static String IMAGE_FORMAT = "image_format";
    public static String KEEP_LOCAL_COPY = "keep_local_copy";
    public static String SEQUENCE_ID = "sequence_id";
    public static String SHOOTING_STATUS = "shooting_status";
    public static String NB_IMG_ON_SD = "nb_images_on_sd";
    public static String NB_IMG_UPLOADED = "nb_images_uploaded";

    protected Integer id;
    protected Integer tikeeId;
    protected String name;
    protected String description;
    protected Timestamp start;
    protected Boolean uploadToCloud;
    protected String imageFormat;
    protected Boolean keepLocalCopy;
    protected BigInteger sequenceId;
    protected String shootingStatus;
    protected Integer nbImagesOnSd;
    protected Integer nbImagesUploaded;

    public AbstractSequence(){};

    protected AbstractSequence(Integer id, Integer tikeeId, String name, String description, Timestamp start,
                               Boolean uploadToCloud, String imageFormat, Boolean keepLocalCopy, BigInteger sequenceId,
                               String shootingStatus, Integer nbImagesOnSd, Integer nbImagesUploaded){
        this.id = id;
        this.tikeeId = tikeeId;
        this.name = name;
        this.description = description;
        this.start = start;
        this.uploadToCloud = uploadToCloud;
        this.imageFormat = imageFormat;
        this.keepLocalCopy = keepLocalCopy;
        this.sequenceId = sequenceId;
        this.shootingStatus = shootingStatus;
        this.nbImagesOnSd = nbImagesOnSd;
        this.nbImagesUploaded = nbImagesUploaded;
    }

    /****************************************************************************************
     /* Getters & setters
     /***************************************************************************************/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTikeeId() {
        return tikeeId;
    }

    public void setTikeeId(Integer tikeeId) {
        this.tikeeId = tikeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Boolean getUploadToCloud() {
        return uploadToCloud;
    }

    public void setUploadToCloud(Boolean uploadToCloud) {
        this.uploadToCloud = uploadToCloud;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public Boolean getKeepLocalCopy() {
        return keepLocalCopy;
    }

    public void setKeepLocalCopy(Boolean keepLocalCopy) {
        this.keepLocalCopy = keepLocalCopy;
    }

    public BigInteger getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(BigInteger sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getShootingStatus() {
        return shootingStatus;
    }

    public void setShootingStatus(String shootingStatus) {
        this.shootingStatus = shootingStatus;
    }

    public Integer getNbImagesOnSd() {
        return nbImagesOnSd;
    }

    public void setNbImagesOnSd(Integer nbImagesOnSd) {
        this.nbImagesOnSd = nbImagesOnSd;
    }

    public Integer getNbImagesUploaded() {
        return nbImagesUploaded;
    }

    public void setNbImagesUploaded(Integer nbImagesUploaded) {
        this.nbImagesUploaded = nbImagesUploaded;
    }
}
