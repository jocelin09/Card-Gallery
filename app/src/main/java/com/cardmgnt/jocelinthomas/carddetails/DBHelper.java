package com.cardmgnt.jocelinthomas.carddetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.cardmgnt.jocelinthomas.carddetails.Aadhaar.DataModel;
import com.cardmgnt.jocelinthomas.carddetails.CreditCard.CreditCardModel;
import com.cardmgnt.jocelinthomas.carddetails.DebitCard.DebitCardModel;
import com.cardmgnt.jocelinthomas.carddetails.DrivingLicense.DrivinglicenseModel;
import com.cardmgnt.jocelinthomas.carddetails.Mediclaim.MediclaimModel;
import com.cardmgnt.jocelinthomas.carddetails.Pan.PanModel;
import com.cardmgnt.jocelinthomas.carddetails.Passport.PassportModel;
import com.cardmgnt.jocelinthomas.carddetails.VoterID.VotersModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jocelinthomas on 01/05/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CardGallery.db"; //cardhub

    // Table Names
    private static final String TABLE_AADHAAR = "AADHAAR";
    private static final String TABLE_PAN = "PAN";
    private static final String TABLE_PASSPORT = "PASSPORT";
    private static final String TABLE_VOTER = "VOTER";
    private static final String TABLE_DRIVING = "DRIVING";
    private static final String TABLE_MEDICLAIM = "MEDICLAIM";
    private static final String TABLE_CREDITCARD = "CREDITCARD";
    private static final String TABLE_DEBITCARD = "DEBITCARD";


    // AADHAAR Table - column nmaes
    private static final String AADHAAR_ID = "aadhaar_id";
    private static final String KEY_ANAME = "aadhaar_name";
    private static final String KEY_ANUMBER = "aadhaar_num";

    // PAN Table - column names
    private static final String PAN_ID = "pan_id";
    private static final String KEY_PANNAME = "pan_name";
    private static final String KEY_PANNUM = "pan_num";


    //Passport - Column names
    private static final String PASS_ID = "passport_id";
    private static final String KEY_PASSPORTNAME = "passportname";
    private static final String KEY_PASSPORTNUMBER = "passportnumber";

    //VoterID - Column names
    private static final String VOTER_ID = "voter_id";
    private static final String KEY_VOTERNAME = "votername";
    private static final String KEY_VOTERNUMBER = "voternumber";

    //Driving license - Column names
    private static final String DRIVING_ID = "driver_id";
    private static final String KEY_DRIVINGNAME = "drivername";
    private static final String KEY_DRIVINGNUMBER = "drivernumber";

    //MEDICLAIM - Column names
    private static final String MEDICLAIM_ID = "mediclaim_id";
    private static final String KEY_MEDICLAIMNAME = "mediclaimname";
    private static final String KEY_MEDICLAIMNUMBER = "mediclaimnumber";

    //CREDITCARD - Column names
    private static final String CREDITCARD_ID = "creditcard_id";
    private static final String KEY_CREDITCARDNAME = "creditcardname";
    private static final String KEY_CREDITCARDNUMBER = "creditcardnumber";

    //DEBITCARD - Column names
    private static final String DEBITCARD_ID = "debitcard_id";
    private static final String KEY_DEBITCARDNAME = "debitcardname";
    private static final String KEY_DEBITCARDNUMBER = "debitcardnumber";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_AADHAAR = "CREATE TABLE IF NOT EXISTS "
            + TABLE_AADHAAR + "(" + AADHAAR_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_ANAME
            + " TEXT," + KEY_ANUMBER + " TEXT" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_PAN = "CREATE TABLE IF NOT EXISTS " + TABLE_PAN
            + "(" + PAN_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_PANNAME + " TEXT,"
            + KEY_PANNUM + " TEXT" + ")";

    //CREATE TABLE PASSPORT
    private static final String CREATE_TABLE_PASSPORT = "CREATE TABLE IF NOT EXISTS " + TABLE_PASSPORT
            + "(" + PASS_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_PASSPORTNAME + " TEXT,"
            + KEY_PASSPORTNUMBER + " TEXT " + ")";

    //CREATE TABLE VOTERS ID
    private static final String CREATE_TABLE_VOTER = "CREATE TABLE IF NOT EXISTS " + TABLE_VOTER
            + "(" + VOTER_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_VOTERNAME + " TEXT,"
            + KEY_VOTERNUMBER + " TEXT " + ")";

    //CREATE TABLE DRIVING LICENSE
    private static final String CREATE_TABLE_DRIVINGLICENSE = "CREATE TABLE IF NOT EXISTS " + TABLE_DRIVING
            + "(" + DRIVING_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_DRIVINGNAME + " TEXT,"
            + KEY_DRIVINGNUMBER + " TEXT " + ")";

    //CREATE TABLE MEDICLAIM
    private static final String CREATE_TABLE_MEDICLAIM = "CREATE TABLE IF NOT EXISTS " + TABLE_MEDICLAIM
            + "(" + MEDICLAIM_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_MEDICLAIMNAME + " TEXT,"
            + KEY_MEDICLAIMNUMBER + " TEXT " + ")";

    //CREATE TABLE CREDITCARD
    private static final String CREATE_TABLE_CREDITCARD = "CREATE TABLE IF NOT EXISTS " + TABLE_CREDITCARD
            + "(" + CREDITCARD_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_CREDITCARDNAME + " TEXT,"
            + KEY_CREDITCARDNUMBER + " TEXT " + ")";

    //CREATE TABLE DEBITCARD
    private static final String CREATE_TABLE_DEBITCARD = "CREATE TABLE IF NOT EXISTS " + TABLE_DEBITCARD
            + "(" + DEBITCARD_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_DEBITCARDNAME + " TEXT,"
            + KEY_DEBITCARDNUMBER + " TEXT " + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AADHAAR);
        db.execSQL(CREATE_TABLE_PAN);
        db.execSQL(CREATE_TABLE_PASSPORT);
        db.execSQL(CREATE_TABLE_VOTER);
        db.execSQL(CREATE_TABLE_DRIVINGLICENSE);
        db.execSQL(CREATE_TABLE_MEDICLAIM);
        db.execSQL(CREATE_TABLE_CREDITCARD);
        db.execSQL(CREATE_TABLE_DEBITCARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AADHAAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSPORT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICLAIM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDITCARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEBITCARD);
        onCreate(db);
    }

    //Insert aadhaar card data
    public void insertAadhaarData(String aname, String anumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ANAME, aname);
        contentValues.put(KEY_ANUMBER, anumber);
        db.insert(TABLE_AADHAAR, null, contentValues);
        db.close();

    }

    //insert pan card data
    public void insertPanData(String pname, String pnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PANNAME, pname);
        contentValues.put(KEY_PANNUM, pnumber);
        db.insert(TABLE_PAN, null, contentValues);
        db.close();

    }


    //insert passport data
    public void insertPassportdata(String passport_name, String passport_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PASSPORTNAME, passport_name);
        contentValues.put(KEY_PASSPORTNUMBER, passport_num);
        sqLiteDatabase.insert(TABLE_PASSPORT, null, contentValues);
        sqLiteDatabase.close();
    }


    //insert voters data
    public void insertVotersdata(String voter_name, String voter_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_VOTERNAME, voter_name);
        contentValues.put(KEY_VOTERNUMBER, voter_num);
        sqLiteDatabase.insert(TABLE_VOTER, null, contentValues);
        sqLiteDatabase.close();
    }


    //insert driving license data
    public void insertDriversdata(String driver_name, String driver_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DRIVINGNAME, driver_name);
        contentValues.put(KEY_DRIVINGNUMBER, driver_num);
        sqLiteDatabase.insert(TABLE_DRIVING, null, contentValues);
        sqLiteDatabase.close();
    }


    //insert MEDICLAIM data
    public void insertMediclaimdata(String mediclaim_name, String mediclaim_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MEDICLAIMNAME, mediclaim_name);
        contentValues.put(KEY_MEDICLAIMNUMBER, mediclaim_num);
        sqLiteDatabase.insert(TABLE_MEDICLAIM, null, contentValues);
        sqLiteDatabase.close();
    }

    //insert CREDITCARD data
    public void insertCreditCardData(String creditcard_name, String creditcard_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CREDITCARDNAME, creditcard_name);
        contentValues.put(KEY_CREDITCARDNUMBER, creditcard_num);
        sqLiteDatabase.insert(TABLE_CREDITCARD, null, contentValues);
        sqLiteDatabase.close();
    }

    //insert DEBITCARD data
    public void insertDebitCardData(String debitcard_name, String debitcard_num) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DEBITCARDNAME, debitcard_name);
        contentValues.put(KEY_DEBITCARDNUMBER, debitcard_num);
        sqLiteDatabase.insert(TABLE_DEBITCARD, null, contentValues);
        sqLiteDatabase.close();
    }


    ////AADHAR CARD//////
    public List<DataModel> getdata() {
        List<DataModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select DISTINCT * from " + TABLE_AADHAAR + /*" where " + KEY_ANAME + "=" + KEY_ANAME +*/";", null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new DataModel();
            int aadhar_id = cursor.getInt(cursor.getColumnIndexOrThrow("aadhaar_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("aadhaar_name"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("aadhaar_num"));
            dataModel.setId(aadhar_id);
            dataModel.setName(name);
            dataModel.setNumber(number);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (DataModel mo : data) {

            Log.i("Hellomo", "" + mo.getName());
        }

        //

        return data;
    }


    //DELETE/REMOVE
    public boolean deleteaadhaar(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_AADHAAR, AADHAAR_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    ////PAN CARD//////
    public List<PanModel> getpandata() {
        List<PanModel> pandata = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_PAN + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        PanModel panModel = null;
        while (cursor.moveToNext()) {
            panModel = new PanModel();
            int pan_id = cursor.getInt(cursor.getColumnIndexOrThrow("pan_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("pan_name"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("pan_num"));
            panModel.setPanid(pan_id);
            panModel.setPan_name(name);
            panModel.setPan_number(number);
            stringBuffer.append(panModel);
            pandata.add(panModel);
        }

        for (PanModel mo : pandata) {

            Log.i("Hellomo", "" + mo.getPan_name());
        }

        //

        return pandata;
    }


    //DELETE/REMOVE
    public boolean deletepan(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_PAN, PAN_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    ////PASSPORT//////
    public List<PassportModel> getpassdata() {
        List<PassportModel> passportdata = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_PASSPORT + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        PassportModel passportModel = null;
        while (cursor.moveToNext()) {
            passportModel = new PassportModel();
            int pass_id = cursor.getInt(cursor.getColumnIndexOrThrow("passport_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("passportname"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("passportnumber"));
            passportModel.setPassport_id(pass_id);
            passportModel.setPassport_name(name);
            passportModel.setPassport_number(number);
            stringBuffer.append(passportModel);
            passportdata.add(passportModel);
        }

        for (PassportModel mo : passportdata) {

            Log.i("Hellomo", "" + mo.getPassport_name());
        }

        //

        return passportdata;
    }


    //DELETE/REMOVE
    public boolean deletepassport(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_PASSPORT, PASS_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    ////VOTER'S ID CARD//////
    public List<VotersModel> getvoterdata() {
        List<VotersModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_VOTER + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        VotersModel votersModel = null;
        while (cursor.moveToNext()) {
            votersModel = new VotersModel();
            int voter_id = cursor.getInt(cursor.getColumnIndexOrThrow("voter_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("votername"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("voternumber"));
            votersModel.setVotersid(voter_id);
            votersModel.setvoter_name(name);
            votersModel.setvoter_number(number);
            stringBuffer.append(votersModel);
            data.add(votersModel);
        }

        for (VotersModel mo : data) {

            Log.i("Hellomo", "" + mo.getvoter_name());
        }

        //

        return data;
    }


    //DELETE/REMOVE
    public boolean deletevoter(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_VOTER, VOTER_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //driving license//
    public List<DrivinglicenseModel> getdriverdata() {
        List<DrivinglicenseModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_DRIVING + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        DrivinglicenseModel drivinglicenseModel = null;
        while (cursor.moveToNext()) {
            drivinglicenseModel = new DrivinglicenseModel();
            int driving_id = cursor.getInt(cursor.getColumnIndexOrThrow("driver_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("drivername"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("drivernumber"));
            drivinglicenseModel.setDrivingid(driving_id);
            drivinglicenseModel.setdrivinglicense_name(name);
            drivinglicenseModel.setdrivinglicense_number(number);
            stringBuffer.append(drivinglicenseModel);
            data.add(drivinglicenseModel);
        }

        for (DrivinglicenseModel mo : data) {

            Log.i("Hellomo", "" + mo.getdrivinglicense_name());
        }

        //

        return data;
    }


    //DELETE/REMOVE
    public boolean deletedriving(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_DRIVING, DRIVING_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //mediclaim//
    public List<MediclaimModel> getMediclaimdata() {
        List<MediclaimModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_MEDICLAIM + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        MediclaimModel mediclaimModel = null;
        while (cursor.moveToNext()) {
            mediclaimModel = new MediclaimModel();
            int mediclaim_id = cursor.getInt(cursor.getColumnIndexOrThrow("mediclaim_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("mediclaimname"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("mediclaimnumber"));
            mediclaimModel.setmediclaimid(mediclaim_id);
            mediclaimModel.setmediclaim_name(name);
            mediclaimModel.setmediclaim_number(number);
            stringBuffer.append(mediclaimModel);
            data.add(mediclaimModel);
        }

        for (MediclaimModel mo : data) {

            Log.i("Hellomo", "" + mo.getmediclaim_name());
        }

        //

        return data;
    }


    //DELETE/REMOVE
    public boolean deletemediclaim(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_MEDICLAIM, MEDICLAIM_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    ////CREDIT CARD//////
    public List<CreditCardModel> getCreditCardData() {
        List<CreditCardModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_CREDITCARD + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        CreditCardModel creditCardModel = null;
        while (cursor.moveToNext()) {
            creditCardModel = new CreditCardModel();
            int creditcard_id = cursor.getInt(cursor.getColumnIndexOrThrow("creditcard_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("creditcardname"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("creditcardnumber"));
            creditCardModel.setCreditcardid(creditcard_id);
            creditCardModel.setcreditcard_name(name);
            creditCardModel.setcreditcard_number(number);
            stringBuffer.append(creditCardModel);
            data.add(creditCardModel);
        }

        for (CreditCardModel mo : data) {

            Log.i("Hellomo", "" + mo.getcreditcard_name());
        }

        //

        return data;
    }


    //DELETE/REMOVE
    public boolean deletecreditcard(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_CREDITCARD, CREDITCARD_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    ////DEBIT CARD//////
    public List<DebitCardModel> getDebitCardData() {
        List<DebitCardModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_DEBITCARD + ";", null);
        StringBuffer stringBuffer = new StringBuffer();
        DebitCardModel debitCardModel = null;
        while (cursor.moveToNext()) {
            debitCardModel = new DebitCardModel();
            int debitcard_id = cursor.getInt(cursor.getColumnIndexOrThrow("debitcard_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("debitcardname"));
            String number = cursor.getString(cursor.getColumnIndexOrThrow("debitcardnumber"));
            debitCardModel.setdebitcardid(debitcard_id);
            debitCardModel.setdebitcard_name(name);
            debitCardModel.setdebitcard_number(number);
            stringBuffer.append(debitCardModel);
            data.add(debitCardModel);
        }

        for (DebitCardModel mo : data) {

            Log.i("Hellomo", "" + mo.getdebitcard_name());
        }

        //

        return data;
    }


    //DELETE/REMOVE
    public boolean deletedebitcard(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            int result = sqLiteDatabase.delete(TABLE_DEBITCARD, DEBITCARD_ID + "=" + id, null);
            System.out.println("result:::::::::" + result);
            if (result > 0) {
                return true;
            }
            sqLiteDatabase.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
