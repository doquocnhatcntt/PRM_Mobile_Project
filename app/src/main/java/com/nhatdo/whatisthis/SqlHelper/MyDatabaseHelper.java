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

import static android.provider.Settings.System.getInt;
import static android.provider.Settings.System.getString;

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
                + Config.COLUMN_TOPIC_DESCRIPTION + " TEXT, "
                + Config.COLUMN_TOPIC_BACKGROUND + " INTEGER "
                + ")";

        //Script to Create Flash Card Table
        String SCRIPT_CREATE_FCD_TABLE = "CREATE TABLE " + Config.TABLE_FCD_NAME + "("
                + Config.COLUMN_FCD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Config.COLUMN_REGISTRATION_NUMBER + " INTEGER NOT NULL,"
                + Config.COLUMN_FCD_NAME + " TEXT NOT NULL,"
                + Config.COLUMN_TOPIC_CODE + " INTEGER NOT NULL, "
                + Config.COLUMN_FCD_DESCRIPTION + " TEXT NOT NULL,"
                + Config.COLUMN_FCD_IMAGE_SOURCE_ID + " INTEGER NOT NULL,"
                + Config.COLUMN_FCD_AUDIO_SOURCE_ID + " INTEGER NOT NULL,"
                + Config.COLUMN_FCD_AUDIO_SOURCE_PRONOUNCE_ID + " INTEGER,"
                + "FOREIGN KEY (" + Config.COLUMN_REGISTRATION_NUMBER + ") REFERENCES " + Config.TABLE_TOPIC_NAME + "(" + Config.COLUMN_TOPIC_REGISTRATION + ") ON UPDATE CASCADE ON DELETE CASCADE, "
                + "CONSTRAINT " + Config.TOPIC_SUB_CONSTRAINT + " UNIQUE (" + Config.COLUMN_REGISTRATION_NUMBER + "," + Config.COLUMN_TOPIC_CODE + ")"
                + ")";

        db.execSQL(SCRIPT_CREATE_TOPIC_TABLE);
        db.execSQL(SCRIPT_CREATE_FCD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "=====> On Update Database");
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_TOPIC_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_FCD_NAME);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        //Enable foreign key constraints like ON UPDATE CASCADE, ON DELETE CASCADE
        //Cause Foreign key support is not enabled in SQLite by default
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    public void insertDefaultValues() {
        if (this.getCountTopic() == 0 || this.getCountFlashCards() == 0) {

            //Create Topics Instance
            Topics animals = new Topics("Animal", 1, "Funny Animal", R.drawable.animal_banner);
            Topics kungfuPanda = new Topics("Kungfu Panda", 2, "Kungfu Panda Character", R.drawable.kungfupanda_character);
            Topics vehicles = new Topics("Vehicles", 3, "Vehicles", R.drawable.vehicle_banner);
            Topics plants = new Topics("Plants", 4, "Plants", R.drawable.plant_banner);
            Topics flowers = new Topics("Flowers", 5, "Flowers", R.drawable.flower_banner);
            Topics test = new Topics("test", 6, "test", R.drawable.flower_banner);
            Topics test2 = new Topics("test2", 7, "test", R.drawable.flower_banner);
            //Add Topics to DB
            addTopic(animals);
            addTopic(kungfuPanda);
            addTopic(vehicles);
            addTopic(plants);
            addTopic(flowers);
            addTopic(test);
            addTopic(test2);

            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //Create Flash Cards - animal - Index 01
            FlashCardDetails bird = new FlashCardDetails(1, "Bird", 1, "He fly in the sky", R.drawable.bird_image, R.raw.bird_sound, R.raw.bird_pronounce);
            FlashCardDetails cat = new FlashCardDetails(1, "Cat", 2, "Do you know Tom and Jerry!", R.drawable.cat_image, R.raw.cat_sound, R.raw.cat_pronounce);
            FlashCardDetails chicken = new FlashCardDetails(1, "Chicken", 3, "We eat KFC in Christmas Day", R.drawable.chicken_image, R.raw.chicken_sound, R.raw.chicken_pronounce);
            FlashCardDetails dog = new FlashCardDetails(1, "Dog", 4, "Furry animal with four legs, pointed nose, and tail", R.drawable.dog_image, R.raw.dog_sound, R.raw.dog_pronounce);
            FlashCardDetails elephant = new FlashCardDetails(1, "Elephant", 5, "Is a huge typically gray mammal", R.drawable.elephant_image, R.raw.elephant_sound, R.raw.elephant_pronounce);
            FlashCardDetails lion = new FlashCardDetails(1, "Lion", 6, "He is the King of all animal", R.drawable.lion_image, R.raw.lion_sound, R.raw.lion_pronounce);
            FlashCardDetails mouse = new FlashCardDetails(1, "Mouse", 7, "He like and eat a lot of cheese", R.drawable.mouse_image, R.raw.mouse_sound, R.raw.mouse_pronounce);
            FlashCardDetails pig = new FlashCardDetails(1, "Pig", 8, "Popular meat in the world ", R.drawable.pig_image, R.raw.pig_sound, R.raw.pig_pronounce);
            FlashCardDetails snake = new FlashCardDetails(1, "Snake", 9, "He has a long body without arms and leg", R.drawable.snake_image, R.raw.snake_sound, R.raw.snake_pronounce);
            FlashCardDetails wolf = new FlashCardDetails(1, "Wolf", 10, "He is similar to a dog but not a dog", R.drawable.wolf_image, R.raw.wolf_sound, R.raw.wolf_pronounce);
            //Add Flash Card to DB
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

            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
            //Create Flash Cards - Kungfu Panda - Index 02
            FlashCardDetails panda = new FlashCardDetails(2, "Panda", 1, "He is Po", R.drawable.po_image, R.raw.panda_pronounce, R.raw.panda_pronounce);
            FlashCardDetails tigress = new FlashCardDetails(2, "Tigress", 2, "She is strongest ", R.drawable.tigress_image_1, R.raw.tigress_pronounce, R.raw.tigress_pronounce);
            FlashCardDetails monkey = new FlashCardDetails(2, "Monkey", 3, "He is funny and fast", R.drawable.monkey_image, R.raw.monkey_pronounce, R.raw.monkey_pronounce);
            FlashCardDetails viper = new FlashCardDetails(2, "Viper", 4, "She small and fastest", R.drawable.viper_image, R.raw.viper_pronounce, R.raw.viper_pronounce);
            FlashCardDetails crane = new FlashCardDetails(2, "Crane", 5, "He can fight in the sky", R.drawable.crane_image, R.raw.crane_pronounce, R.raw.crane_pronounce);
            //Add Flash Card to DB
            addFlashCard(panda);
            addFlashCard(tigress);
            addFlashCard(monkey);
            addFlashCard(viper);
            addFlashCard(crane);
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
        contentValues.put(Config.COLUMN_REGISTRATION_NUMBER, node.getIdTopic());
        contentValues.put(Config.COLUMN_FCD_NAME, node.getName());
        contentValues.put(Config.COLUMN_TOPIC_CODE, node.getCode());
        contentValues.put(Config.COLUMN_FCD_DESCRIPTION, node.getDescription());
        contentValues.put(Config.COLUMN_FCD_IMAGE_SOURCE_ID, node.getImageId());
        contentValues.put(Config.COLUMN_FCD_AUDIO_SOURCE_ID, node.getAudioId());
        contentValues.put(Config.COLUMN_FCD_AUDIO_SOURCE_PRONOUNCE_ID, node.getPronounceAudioId());
        sqLiteDatabase.insert(Config.TABLE_FCD_NAME, null, contentValues);
    }

    public List<Topics> getAllTopicNode() {
        String query = "SELECT * FROM " + Config.TABLE_TOPIC_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<Topics> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Topics node = new Topics(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4));
            list.add(node);
        }
        return list;
    }

    public List<FlashCardDetails> getAllFCNode() {
        String query = "SELECT * FROM " + Config.TABLE_FCD_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<FlashCardDetails> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            FlashCardDetails node = new FlashCardDetails(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7));
            list.add(node);
        }
        return list;
    }

    public List<FlashCardDetails> getFCNodeWithIDTopic(int index) {
        String query = "SELECT * FROM " + Config.TABLE_FCD_NAME + " WHERE " + Config.COLUMN_REGISTRATION_NUMBER + "=" + index;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<FlashCardDetails> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            FlashCardDetails node = new FlashCardDetails(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7));
            list.add(node);
        }
        return list;
    }
}
