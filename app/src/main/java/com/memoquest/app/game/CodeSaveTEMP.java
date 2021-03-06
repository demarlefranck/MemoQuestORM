package com.memoquest.app.game;

/*



    private void insertSampleDataQuiz() {

        QuizService quizService = new QuizService();
        QuizContentService quizContentService = new QuizContentService();
        QuizTest quizTest = new QuizTest();
        QuizContentTest quizContentTest = new QuizContentTest();

        for(int i = 0; i != 10; i++){

            Quiz quiz = quizTest.createOneQuiz(i);

            quizService.edit(quiz, (long) -10);

            for(int j = 0; j != 5; j++){

                QuizContent quizContent = quizContentTest.createOneQuizContent(j, quiz);
                quizContentService.edit(quizContent, (long) -10);
            }
        }
    }

    private void test() {



User userCurrent = userService.getUserActive();



        if(userCurrent == null){
        ModalMessages.showWrongMessage(this, "TEST", "Pas de user acif");
        }
        else{
        ModalMessages.showGoodMessage(this, "TEST", "User actif trouve");
        }
        }

































public class Game1ActivityOLD extends ActionBarActivity implements View.OnClickListener {

    private QuizContent quizContent;
    private QuizContentService quizContentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_old);

        quizContentService = new QuizContentService();

        long quizContentId = getObjetbunbleValue();

        if(quizContentId != -1){

            quizContent = quizContentService.findUniqueById(quizContentId);
        }
        else{
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue  :  getObjetbunbleValue() = -1");
        }

    }

    private long getObjetbunbleValue(){
        long objetbunbleValue = -1;
        String bundleKey = "quizContentId";

        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey(bundleKey)){

            objetbunbleValue = this.getIntent().getLongExtra(bundleKey, -1);

        } else{
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue");
        }
        return objetbunbleValue;
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        if(quizContent.getQuestion() != null){
            textViewQuestion.setText(quizContent.getQuestion());
        }

        File file1 = new File(Environment.getExternalStorageDirectory() + File.separator + "MemoQuest" + File.separator + quizContent.getAnswerA());
        if(file1.exists()) {
            ImageView imageViewAnswerA = (ImageView) findViewById(R.id.imageViewAnswerA);
            imageViewAnswerA.setImageURI(Uri.fromFile(file1));
        }


        File file2 = new File(Environment.getExternalStorageDirectory() + File.separator + "MemoQuest" + File.separator + quizContent.getAnswerB());
        if(file2.exists()) {
            ImageView imageViewAnswerB = (ImageView) findViewById(R.id.imageViewAnswerB);
            imageViewAnswerB.setImageURI(Uri.fromFile(file2));
        }


        File file3 = new File(Environment.getExternalStorageDirectory() + File.separator + "MemoQuest" + File.separator + quizContent.getAnswerC());
        if(file3.exists()) {
            ImageView imageViewAnswerC = (ImageView) findViewById(R.id.imageViewAnswerC);
            imageViewAnswerC.setImageURI(Uri.fromFile(file3));
        }


        File file4 = new File(Environment.getExternalStorageDirectory() + File.separator + "MemoQuest" + File.separator + quizContent.getAnswerD());
        if(file4.exists()) {
            ImageView imageViewAnswerD = (ImageView) findViewById(R.id.imageViewAnswerD);
            imageViewAnswerD.setImageURI(Uri.fromFile(file4));
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.imageViewAnswerA) {
            checkAnswer(quizContent.getAnswerA());
        }
        else if(view.getId() == R.id.imageViewAnswerB) {
            checkAnswer(quizContent.getAnswerB());
        }
        else if(view.getId() == R.id.imageViewAnswerC) {
            checkAnswer(quizContent.getAnswerC());
        }
        else if(view.getId() == R.id.imageViewAnswerD) {
            checkAnswer(quizContent.getAnswerD());
        }
    }


    public void checkAnswer(String answerGamer){


        ModalMessages modalMessages = new ModalMessages();

        if(answerGamer.equals(quizContent.getSolution())){
            modalMessages.showGoodMessage(this, "Bien jouer", "Bonne réponse");
        }
        else{

            modalMessages.showWrongMessage(this, "Désolé", "Mauvaise réponse");
        }
    }









   // A SUPPRIMER


    public QuizContent getQuizContentForGame1() {



         //   Simulation de la copie des images devant etre faites par l'intermediaires des services rest

        String filepathName1 = "1-100" + File.separator + "server_id_QuizContent" + File.separator +  "chat.jpg";
        copyDrawableToFile(R.drawable.chat, filepathName1);


        String filepathName2 = "1-100" + File.separator + "server_id_QuizContent" + File.separator + "chien.jpg";
        copyDrawableToFile(R.drawable.chien, filepathName2);


        String filepathName3 = "1-100" + File.separator + "server_id_QuizContent" + File.separator +  "croco.jpg";
        copyDrawableToFile(R.drawable.croco, filepathName3);


        String filepathName4 = "1-100" + File.separator + "server_id_QuizContent" + File.separator +  "giraphe.jpg";
        copyDrawableToFile(R.drawable.giraphe, filepathName4);





        QuizContent quizContent = new QuizContent();
        quizContent.setQuestion("Quel Animal aboie?");
        quizContent.setAnswerA(filepathName1);
        quizContent.setAnswerB(filepathName2);
        quizContent.setAnswerC(filepathName3);
        quizContent.setAnswerD(filepathName4);
        quizContent.setSolution(quizContent.getAnswerB());


        return quizContent;
    }

    private Boolean copyDrawableToFile(int resource, String path){

        File folder = new File(Environment.getExternalStorageDirectory() + File.separator + "MemoQuest" + File.separator + "1-100" + File.separator + "server_id_QuizContent");

        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            Log.d("DEBUG", "Creation dossier OK");

            try {
                Bitmap icon = BitmapFactory.decodeResource(this.getResources(), resource);
                ByteArrayOutputStream bytesIcon = new ByteArrayOutputStream();
                icon.compress(Bitmap.CompressFormat.JPEG, 100, bytesIcon);

                File fileIconChat = new File(Environment.getExternalStorageDirectory() + File.separator + "MemoQuest" + File.separator + path);
                fileIconChat.createNewFile();

                FileOutputStream fo = new FileOutputStream(fileIconChat);
                fo.write(bytesIcon.toByteArray());
                fo.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Log.d("DEBUG", "ECHEC Creation dossier");
            return false;
        }

        return true;
    }




}
*/







    /*
    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 50, 50, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }



    public Bitmap getBitmapFromURL(String src) {
        try {
            java.net.URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }

*/
