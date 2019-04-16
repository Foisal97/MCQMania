package com.example.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.application.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Geography");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
        Category c4=new Category("Bangladesh");
        insertCategory(c4);
        Category c5=new Category("WORLD");
        insertCategory(c5);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1=new Question("When did BANGLADESH got their independence?","1971","1973","1975",1,Question.DIFFICULTY_EASY,Category.BANGLADESH);
        insertQuestion(q1);
        Question q2=new Question("The population of this country is?","16m","17m","18m",2,Question.DIFFICULTY_EASY,Category.BANGLADESH);
        insertQuestion(q2);
        Question q3=new Question("The area of the country is?","147570 sq-km","146570 sq-km","149570 sq-km",1,Question.DIFFICULTY_EASY,Category.BANGLADESH);
        insertQuestion(q3);
        Question q4=new Question("Who is the current pm?","Sheikh hasina","khaleda zia","ershad",1,Question.DIFFICULTY_EASY,Category.BANGLADESH);
        insertQuestion(q4);
        Question q5=new Question("Who is the captain of bd odi team?","Mashrafe","Tamim","Shakib",1,Question.DIFFICULTY_EASY,Category.BANGLADESH);
        insertQuestion(q5);

        Question q11=new Question("Bangladesh got how many cricket world cup?","0","1","2",1,Question.DIFFICULTY_MEDIUM,Category.BANGLADESH);
        insertQuestion(q11);
        Question q12=new Question("Bangladesh has __ districts?","60","64","68",2,Question.DIFFICULTY_MEDIUM,Category.BANGLADESH);
        insertQuestion(q12);
        Question q13=new Question("How many divisions does bd have?","6","7","8",3,Question.DIFFICULTY_MEDIUM,Category.BANGLADESH);
        insertQuestion(q13);
        Question q14=new Question("What is the capital of bangladesh?","Dhaka","Kushtia","Rajshahi",1,Question.DIFFICULTY_MEDIUM,Category.BANGLADESH);
        insertQuestion(q14);
        Question q15=new Question("What is our national fruit?","Mango","Jack-fruit","pine-apple",2,Question.DIFFICULTY_MEDIUM,Category.BANGLADESH);
        insertQuestion(q15);

        Question q21=new Question("How many district does khulna division have?","10","11","12",1,Question.DIFFICULTY_HARD,Category.BANGLADESH);
        insertQuestion(q21);
        Question q22=new Question("What is the home district home mashrafee?","Narail","Khulna","kushtia",1,Question.DIFFICULTY_HARD,Category.BANGLADESH);
        insertQuestion(q22);
        Question q23=new Question("Mashrafee elected MP from which sit?","Narail-2","Narail-1,","Magura-2",1,Question.DIFFICULTY_HARD,Category.BANGLADESH);
        insertQuestion(q23);
        Question q24=new Question("What is the longest sea beach of the world?","Coxs Bazar","Pataya","Goa",1,Question.DIFFICULTY_HARD,Category.BANGLADESH);
        insertQuestion(q24);
        Question q25=new Question("The largest port of the country is?","Benapole","Mongla","Chittagong",3,Question.DIFFICULTY_HARD,Category.BANGLADESH);
        insertQuestion(q25);

        Question q31=new Question("What is the largest democratic country of the world?","India","China","Bangladesh",1,Question.DIFFICULTY_EASY,Category.WORLD);
        insertQuestion(q31);
        Question q32=new Question("Who is the current pm of india?","Naredra Modi","Rahul Gandhi","Alia Bhatt",1,Question.DIFFICULTY_EASY,Category.WORLD);
        insertQuestion(q32);
        Question q33=new Question("Which country has largest population?","China","India","Pakistan",1,Question.DIFFICULTY_EASY,Category.WORLD);
        insertQuestion(q33);
        Question q34=new Question("Who is the current president of USA?","Donald Trump","Barak Obama","Hilary clinton",1,Question.DIFFICULTY_EASY,Category.WORLD);
        insertQuestion(q34);
        Question q35=new Question("How many sits are there in lok shova election?","540","543","545",2,Question.DIFFICULTY_EASY,Category.WORLD);
        insertQuestion(q35);

        Question q41=new Question("Who won the maximum fifa world cup?","Brazil","Italy","Germany",1,Question.DIFFICULTY_MEDIUM,Category.WORLD);
        insertQuestion(q41);
        Question q42=new Question("How many time does Argentina won the world cup?","3","2","1",2,Question.DIFFICULTY_MEDIUM,Category.WORLD);
        insertQuestion(q42);
        Question q43=new Question("What is the capital of Pakistan?","Islamabad","Karachi","Lahore",1,Question.DIFFICULTY_MEDIUM,Category.WORLD);
        insertQuestion(q43);
        Question q44=new Question("What is the currency of UK?","Dollar","Pound","Taka",2,Question.DIFFICULTY_MEDIUM,Category.WORLD);
        insertQuestion(q44);
        Question q45=new Question("How many states are there in india?","25","27","29",2,Question.DIFFICULTY_MEDIUM,Category.WORLD);
        insertQuestion(q45);

        Question q51=new Question("Who has more members?","FIFA","ICC","UN",1,Question.DIFFICULTY_HARD,Category.WORLD);
        insertQuestion(q51);
        Question q52=new Question("Which country will host the 2022 world cup?","Qatara","Japan","Saudi Arabia",1,Question.DIFFICULTY_HARD,Category.WORLD);
        insertQuestion(q52);
        Question q53 =new Question("Who is the current champion of fifa world cup?","France","Germany","Brazil",1,Question.DIFFICULTY_HARD,Category.WORLD);
        insertQuestion(q53);
        Question q54=new Question("Who is the current champion of asian cup?","Japan","Qatar","south-Korea",2,Question.DIFFICULTY_HARD,Category.WORLD);
        insertQuestion(q54);
        Question q55=new Question("Who is the host country of asian cup 2019?","UAE","Qatar","Japan",1,Question.DIFFICULTY_HARD,Category.WORLD);
        insertQuestion(q55);

        Question q61=new Question("What is 2+2?","4","6","8",1,Question.DIFFICULTY_EASY,Category.MATH);
        insertQuestion(q61);
        Question q62 =new Question("What is 2*2?","4","6","8",1,Question.DIFFICULTY_EASY,Category.MATH);
        insertQuestion(q62);
        Question q63=new Question("What is 2-2?","0","1","2",1,Question.DIFFICULTY_EASY,Category.MATH);
        insertQuestion(q63);
        Question q64=new Question("What is 2/2?","0","1","2",2,Question.DIFFICULTY_EASY,Category.MATH);
        insertQuestion(q64);
        Question q65=new Question("Who discovered 0?","Newton","Arya bhatt","konad",2,Question.DIFFICULTY_EASY,Category.MATH);
        insertQuestion(q65);


        Question q71=new Question("What is the value of tan90?","0","0.5","1",3,Question.DIFFICULTY_MEDIUM,Category.MATH);
        insertQuestion(q71);
        Question q72=new Question("sin/cos=?","sec","cosec","tan",3,Question.DIFFICULTY_MEDIUM,Category.MATH);
        insertQuestion(q72);
        Question q73=new Question("sin45/cos45=?","0","0.5","1",3,Question.DIFFICULTY_MEDIUM,Category.MATH);
        insertQuestion(q73);
        Question q74=new Question("3/2=?","1.5","1.6","1.7",1,Question.DIFFICULTY_MEDIUM,Category.MATH );
        insertQuestion(q74);
        Question q75=new Question("(5+5)/2=?","10","15","5",3,Question.DIFFICULTY_MEDIUM,Category.MATH);
        insertQuestion( q75);

        Question q81=new Question("1pound=__kg?","250","350","450",3,Question.DIFFICULTY_HARD,Category.MATH);
        insertQuestion(q81);
        Question q82=new Question("1mile=__km?","1.5","1.6","1.7",2,Question.DIFFICULTY_HARD,Category.MATH);
        insertQuestion(q82);
        Question q83=new Question("1foot=__inch?","10","11","12",3,Question.DIFFICULTY_HARD,Category.MATH);
        insertQuestion(q83);
        Question q84=new Question("1inch=__cm?","2.52","2.53","2.54",3,Question.DIFFICULTY_HARD,Category.MATH);
        insertQuestion(q84);
        Question q85=new Question("1meter=___cm??","100","1000","150",1,Question.DIFFICULTY_HARD,Category.MATH);
        insertQuestion(q85);

        Question q91=new Question("Android is developed by?","Facebook","Oracle","Google",3,Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        insertQuestion(q91);
        Question q92=new Question("Java is developed by?","Google","Facebook","Sun-Microsystem",3,Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        insertQuestion(q92);
        Question q93=new Question("What is the basic language to learn programming?","Phython","C","C++",2,Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        insertQuestion(q93);
        Question q94=new Question("C is developed by?","Bill gates","Mark Zuckerberg","Dennis Ritchie",3,Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        insertQuestion(q94);
        Question q95=new Question("What is the format specifer for an integer?","%d","%s","%f",1,Question.DIFFICULTY_EASY,Category.PROGRAMMING);
        insertQuestion(q95);


        Question q101=new Question("What is the format specifier for String?","%d","%c","%s",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING);
        insertQuestion(q101);
        Question q102=new Question("How many devices are there are run by JAVA?","5bn","4bn","3bn",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING);
        insertQuestion(q102);
        Question q103=new Question("What is the best algorithm to find MST?","Bellmon-ford","Floyed-warshall","Dijkstra",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING);
        insertQuestion(q103);
        Question q104=new Question("What is the simplest sorting algorithm?","Bubble","Quick","Merge",1,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING);
        insertQuestion(q104);
        Question q105=new Question("Which complexity is best?","O(nlogn)","O(n^n)","O(n)",3,Question.DIFFICULTY_MEDIUM,Category.PROGRAMMING);


        Question q111=new Question("JAVA is a name of?","Coffe","tea","sampoo",1,Question.DIFFICULTY_HARD,Category.PROGRAMMING);
        insertQuestion(q111);
        Question q112=new Question("How many time Dijkstra take to make Dijkstra algorithm?","15","18","20",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING);
        insertQuestion(q112);
        Question q113=new Question("Which problem is NPC?","knapsack","sort","3sat problem",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING);
        insertQuestion(q113);
        Question q114=new Question("Which problem is sloved by dynamic programming?","Sort","Fractional Knapsack","0/1 Knapsack",3,Question.DIFFICULTY_HARD,Category.PROGRAMMING);
        insertQuestion(q114);
        Question q115=new Question("What is LCS ?","Least common subsequence","longest common subsequence","none of the above",2,Question.DIFFICULTY_HARD,Category.PROGRAMMING);
        insertQuestion(q115);


        Question q121=new Question("Which of the city is not in Europe?","Barcelona","Real madrid","Delhi",3,Question.DIFFICULTY_EASY,Category.GEOGRAPHY);
        insertQuestion(q121);
        Question q122=new Question("The uk is comprised with how many countries?","7","4","5",2,Question.DIFFICULTY_EASY,Category.GEOGRAPHY);
        insertQuestion(q122);
        Question q123=new Question("Which of the following countries do not share border with france?","spain","Netharlands","Italy",2,Question.DIFFICULTY_EASY,Category.GEOGRAPHY);
        insertQuestion(q123);
        Question q124=new Question("What is the imaginary line called that connects the north and sount pole?","Lambert line","Prime Meridian","Prime axis",2,Question.DIFFICULTY_EASY,Category.GEOGRAPHY);
        insertQuestion(q124);
        Question q125=new Question("Which US state is the Grand Canyon located in?","Arizona","Nevada","New Mexico",1,Question.DIFFICULTY_EASY,Category.GEOGRAPHY);
        insertQuestion(q125);


        Question q131=new Question("Which is the longest river in the world?","Congo","Amazon","Nile",3,Question.DIFFICULTY_MEDIUM,Category.GEOGRAPHY);
        insertQuestion(q131);
        Question q132=new Question("What is the largest body of the ocean?","indian","pacific","atlantic",2,Question.DIFFICULTY_MEDIUM,Category.GEOGRAPHY);
        insertQuestion(q132);
        Question q133=new Question("Which is the smallest country measured by land area?","Italy","Vatican","Tuvalu",2,Question.DIFFICULTY_MEDIUM,Category.GEOGRAPHY);
        insertQuestion(q133);
        Question q134=new Question("What is the approximate size of the earth in km?","30000","40000","50000",2,Question.DIFFICULTY_MEDIUM,Category.GEOGRAPHY);
        insertQuestion(q134);
        Question q135=new Question("Which ocean lies on the east coast of the USA?","indian","pacific","atlantic",3,Question.DIFFICULTY_MEDIUM,Category.GEOGRAPHY);
        insertQuestion(q135);


        Question q141=new Question("Which is the world's highest mountain?","Kilimanjaro","Everest","K2",2,Question.DIFFICULTY_HARD,Category.GEOGRAPHY);
        insertQuestion(q141);
        Question q142=new Question("How many great lakes are there?","6","3","5",3,Question.DIFFICULTY_HARD,Category.GEOGRAPHY);
        insertQuestion(q142);
        Question q143=new Question("Which is the largest river in USA?","Colorado","Missouri","Yukon",2,Question.DIFFICULTY_HARD,Category.GEOGRAPHY);
        insertQuestion(q143);
        Question q144=new Question("The biggest desert in the world is__?","Arabian","Great Australian","Sahara",3,Question.DIFFICULTY_HARD,Category.GEOGRAPHY);
        insertQuestion(q144);
        Question q145=new Question("What country is also known as Persia?","Iraq","UAE","Iran",3,Question.DIFFICULTY_HARD,Category.GEOGRAPHY);
        insertQuestion(q145);




    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}