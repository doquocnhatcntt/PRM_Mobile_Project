package com.nhatdo.whatisthis.SqlHelper;

public class Config {

    public static final String DATABASE_NAME = "what-is-this-DB";

    //Columns of Topics Table
    public static final String TABLE_TOPIC_NAME = "topics";
    public static final String COLUMN_TOPIC_ID = "_id";
    public static final String COLUMN_TOPIC_NAME = "name";
    public static final String COLUMN_TOPIC_REGISTRATION = "registration_no";
    public static final String COLUMN_TOPIC_DESCRIPTION = "description";
    public static final String COLUMN_TOPIC_BACKGROUND = "background";

    //Column of Flash Card Details
    public static final String TABLE_FCD_NAME = "flash_card_details";
    public static final String COLUMN_FCD_ID = "_id";
    public static final String COLUMN_REGISTRATION_NUMBER = "fk_registration_no";
    public static final String COLUMN_FCD_NAME = "name";
    public static final String COLUMN_TOPIC_CODE = "subject_code";
    public static final String COLUMN_FCD_DESCRIPTION = "description";
    public static final String COLUMN_FCD_IMAGE_SOURCE_ID = "image_srcId";
    public static final String COLUMN_FCD_AUDIO_SOURCE_ID = "audio_srcId";
    public static final String TOPIC_SUB_CONSTRAINT = "topic_sub_unique";


}
