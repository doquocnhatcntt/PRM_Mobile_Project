package com.nhatdo.whatisthis.SqlHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nhatdo.whatisthis.R;
import com.nhatdo.whatisthis.bean.FlashCardDetails;
import com.nhatdo.whatisthis.bean.Topics;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static MyDatabaseHelper myDatabaseHelper;

    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

    final static String TAG = "MYSQLHEPLER";

    //Database Version
    public static final int DATABASE_VERSION = 1;

    //Database Name
    public static final String DATABASE_NAME = Config.DATABASE_NAME;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MyDatabaseHelper getInstance(Context context) {
        if (myDatabaseHelper == null) {
            synchronized (MyDatabaseHelper.class) {
                if (myDatabaseHelper == null) {
                    myDatabaseHelper = new MyDatabaseHelper(context);
                }
            }
        }
        return myDatabaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Script to Create Topic Table
        String SCRIPT_CREATE_TOPIC_TABLE = "CREATE TABLE " + Config.TABLE_TOPIC_NAME + "("
                + Config.COLUMN_TOPIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_TOPIC_NAME + " TEXT NOT NULL, "
                + Config.COLUMN_TOPIC_REGISTRATION + " INTEGER NOT NULL UNIQUE, "
                + Config.COLUMN_TOPIC_DESCRIPTION + " TEXT, " //nullable
                + Config.COLUMN_TOPIC_BACKGROUND + " INTEGER "
                + ")";

        //Script to Create Flash Card Table
        String SCRIPT_CREATE_FCD_TABLE = "CREATE TABLE " + Config.TABLE_FCD_NAME + "("
                + Config.COLUMN_FCD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Config.COLUMN_REGISTRATION_NUMBER + " INTEGER NOT NULL,"
                + Config.COLUMN_FCD_NAME + " TEXT NOT NULL,"
                + Config.COLUMN_TOPIC_CODE + " INTEGER NOT NULL, "
                + Config.COLUMN_FCD_DESCRIPTION + " TEXT NOT NULL,"
                + Config.COLUMN_FCD_IMAGE_SOURCE_ID + " INTEGER,"
                + Config.COLUMN_FCD_AUDIO_SOURCE_ID + " INTEGER,"
                + "FOREIGN KEY (" + Config.COLUMN_REGISTRATION_NUMBER + ") REFERENCES " + Config.TABLE_TOPIC_NAME + "(" + Config.COLUMN_TOPIC_REGISTRATION + ") ON UPDATE CASCADE ON DELETE CASCADE, "
                + "CONSTRAINT " + Config.TOPIC_SUB_CONSTRAINT + " UNIQUE (" + Config.COLUMN_REGISTRATION_NUMBER + "," + Config.COLUMN_TOPIC_CODE + ")"
                + ")";

        db.execSQL(SCRIPT_CREATE_TOPIC_TABLE);
        db.execSQL(SCRIPT_CREATE_FCD_TABLE);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "On Update Database");
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_TOPIC_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_FCD_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void insertDefaultValues() {
        if (this.getCountTopic() == 0 || this.getCountFlashCards() == 0) {

            //Insert Topics
            Topics animal = new Topics("Animal", 1, "These animal are verry funny, try it!", R.drawable.animal_banner);
            Topics kungfuPanda = new Topics("Kungfu Panda", 2, "Kungfu Panda Character", R.drawable.kungfupanda_character);
            Topics vehicles = new Topics("Vehicles", 3, "Kungfu Panda Character", R.drawable.vehicle_banner);
            Topics plants = new Topics("Plants", 4, "Kungfu Panda Character", R.drawable.plant_banner);
            Topics flowers = new Topics("Flowers", 5, "Kungfu Panda Character", R.drawable.flower_banner);

            addTopic(animal);
            addTopic(kungfuPanda);
            addTopic(vehicles);
            addTopic(plants);
            addTopic(flowers);

            //Insert Flash Cards - animal - Index 01
            FlashCardDetails bird = new FlashCardDetails("A Bird", 1, "He fly in the sky", R.drawable.bird_image, R.raw.bird_sound, 1);
            FlashCardDetails cat = new FlashCardDetails("A Cat", 2, "Do you know Tom and Jerry!", R.drawable.cat_image, R.raw.cat_sound, 1);
            FlashCardDetails chicken = new FlashCardDetails("A Chicken", 3, "We eat it in Christmas Day", R.drawable.chicken_image, R.raw.chicken_sound, 1);
            FlashCardDetails dog = new FlashCardDetails("A Dog", 4, "Is a furry animal with four legs, a pointed nose, and a tail", R.drawable.dog_image, R.raw.dog_sound, 1);
            FlashCardDetails elephant = new FlashCardDetails("A Elephant", 5, "Is a huge typically gray mammal of Africa or Asia with the nose drawn out into a long trunk and two large curved tusks", R.drawable.elephant_image, R.raw.elephant_sound, 1);
            FlashCardDetails lion = new FlashCardDetails("A Lion", 6, "He is the King of all animal", R.drawable.lion_image, R.raw.lion_sound, 1);
            FlashCardDetails mouse = new FlashCardDetails("A Mouse", 7, "He like and eat a lot of cheese", R.drawable.mouse_image, R.raw.mouse_sound, 1);
            FlashCardDetails pig = new FlashCardDetails("A Pig", 8, "Popular meat in the world ", R.drawable.pig_image, R.raw.pig_sound, 1);
            FlashCardDetails snake = new FlashCardDetails("A Snake", 9, "He has a long body without arms and leg", R.drawable.snake_image, R.raw.snake_sound, 1);
            FlashCardDetails wolf = new FlashCardDetails("A Wolf", 10, "He is similar to a dog but not a dog", R.drawable.wolf_image, R.raw.wolf_sound, 1);

            addFlashCard(bird);
            addFlashCard(cat);
            addFlashCard(chicken);
            addFlashCard(dog);
            addFlashCard(elephant);
            addFlashCard(lion);
            addFlashCard(mouse);
            addFlashCard(pig);
            addFlashCard(snake);
            addFlashCard(wolf);

            //Insert Flash Cards - Kungfu Panda - Index 02
            FlashCardDetails birdd = new FlashCardDetails("A Bird", 1, "He fly in the sky", R.drawable.bird_image, R.raw.bird_sound, 2);
            FlashCardDetails catt = new FlashCardDetails("A Cat", 2, "Do you know Tom and Jerry!", R.drawable.cat_image, R.raw.cat_sound, 2);
            FlashCardDetails chickenn = new FlashCardDetails("A Chicken", 3, "We eat it in Christmas Day", R.drawable.chicken_image, R.raw.chicken_sound, 2);
            FlashCardDetails dogg = new FlashCardDetails("A Dog", 4, "Is a furry animal with four legs, a pointed nose, and a tail", R.drawable.dog_image, R.raw.dog_sound, 2);

            addFlashCard(birdd);
            addFlashCard(catt);
            addFlashCard(chickenn);
            addFlashCard(dogg);
        }
    }

    public int getCountTopic() {
        String querryTopic = "SELECT * FROM " + Config.TABLE_TOPIC_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(querryTopic, null);
        return cursor.getCount();
    }

    public int getCountFlashCards() {
        String querryFlashCard = "SELECT * FROM " + Config.TABLE_FCD_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(querryFlashCard, null);
        return cursor.getCount();
    }

    public void addTopic(Topics node) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_TOPIC_NAME, node.getName());
        contentValues.put(Config.COLUMN_TOPIC_REGISTRATION, node.getRegistrationNumber());
        contentValues.put(Config.COLUMN_TOPIC_DESCRIPTION, node.getDescription());
        contentValues.put(Config.COLUMN_TOPIC_BACKGROUND, node.getTopicBackground());
        sqLiteDatabase.insert(Config.TABLE_TOPIC_NAME, null, contentValues);
    }

    public void addFlashCard(FlashCardDetails node) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_FCD_NAME, node.getName());
        contentValues.put(Config.COLUMN_TOPIC_CODE, node.getCode());
        contentValues.put(Config.COLUMN_FCD_DESCRIPTION, node.getDescription());
        contentValues.put(Config.COLUMN_FCD_IMAGE_SOURCE_ID, node.getImageId());
        contentValues.put(Config.COLUMN_FCD_AUDIO_SOURCE_ID, node.getAudioId());
        contentValues.put(Config.COLUMN_REGISTRATION_NUMBER, node.getIdTopic());
        sqLiteDatabase.insert(Config.TABLE_FCD_NAME, null, contentValues);
    }

    public Topics getTopic(String name) {
        Cursor cursor = sqLiteDatabase.query(
                Config.TABLE_TOPIC_NAME,
                new String[]{Config.COLUMN_TOPIC_ID, Config.COLUMN_TOPIC_NAME, Config.COLUMN_TOPIC_REGISTRATION, Config.COLUMN_TOPIC_DESCRIPTION},
                Config.COLUMN_TOPIC_NAME + "=?",
                new String[]{String.valueOf(name)},
                null, null, null, null
        );
        if(cursor != null){
            cursor.moveToFirst();
            Topics node = new Topics(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
            return node;
        }
        return null;
    }

    public FlashCardDetails getFlashCardDetails(String name) {
        Cursor cursor = sqLiteDatabase.query(
                Config.TABLE_FCD_NAME,
                new String[]{Config.COLUMN_FCD_ID, Config.COLUMN_FCD_NAME, Config.COLUMN_FCD_DESCRIPTION, Config.COLUMN_FCD_IMAGE_SOURCE_ID, Config.COLUMN_FCD_AUDIO_SOURCE_ID, Config.COLUMN_REGISTRATION_NUMBER},
                Config.COLUMN_FCD_NAME + "=?",
                new String[]{String.valueOf(name)},
                null, null, null, null
        );
        if(cursor != null){
            cursor.moveToFirst();
            FlashCardDetails node = new FlashCardDetails(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
            return node;
        }
        return null;
    }

    public List<Topics> getAllTopicNode(){
        String query = "SELECT * FROM " + Config.TABLE_TOPIC_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<Topics> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Topics node = new Topics(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4));
            list.add(node);
        }
        return list;
    }

    public List<FlashCardDetails> getAllFCNode(){
        String query = "SELECT * FROM " + Config.TABLE_FCD_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<FlashCardDetails> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            FlashCardDetails node = new FlashCardDetails(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));
            list.add(node);
        }
        return list;
    }
}
