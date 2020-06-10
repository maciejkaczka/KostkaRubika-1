
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.*;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;



public class KostkaRubika extends JFrame implements KeyListener, ActionListener {

    public Cubie[][][] kubiki = new Cubie[3][3][3];
    TransformGroup obrocsie;
    public int liczbaruchow = 1;
    public int[][][] zakodowaneKolory = new int[100][27][6]; //wstepnie max 100 ruchow


    public KostkaRubika() {


        super("KostkaRubika");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas3D = new Canvas3D(config);
        canvas3D.setPreferredSize(new Dimension(800, 600));
        add(canvas3D);

        canvas3D.addKeyListener(this);

        pack();
        setVisible(true);

        BranchGroup scena = createSceneGraph();
        scena.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
        OrbitBehavior kamera = new OrbitBehavior(canvas3D,OrbitBehavior.REVERSE_ALL);

        BoundingSphere bound = new BoundingSphere();
        kamera.setSchedulingBounds(bound);

        Transform3D przesuniecie_obserwatora = new Transform3D();
        Transform3D rot_obs = new Transform3D();
        rot_obs.rotY((float)(-Math.PI/7));
        przesuniecie_obserwatora.set(new Vector3f(-1.2f,1.5f,2.0f));
        przesuniecie_obserwatora.mul(rot_obs);
        rot_obs.rotX((float)(-Math.PI/6));
        przesuniecie_obserwatora.mul(rot_obs);

        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(przesuniecie_obserwatora);


        simpleU.getViewingPlatform().setViewPlatformBehavior(kamera);
        simpleU.addBranchGraph(scena);


    }


    public BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();
        float size = (float) 0.15;

        int i = -1, j = -1, k = -1;

        for (float x = (float) -0.31; x < 0.32; x = x + (float) 0.31) {
            j = -1;
            i++;
            for (float y = (float) -0.31; y < 0.32; y = y + (float) 0.31) {
                k = -1;
                j++;
                for (float z = (float) -0.31; z < 0.32; z = z + (float) 0.31) {
                    k++;
                    Cubie kostka = new Cubie(size, x, y, z);
                    kubiki[i][j][k] = kostka;




                    //@@@@@@@@@@@@@@@@@@@@@ TEKST INDEKSU @@@@@@@@@@@@@
                    Font czcionka = new Font("Calibri",1,1);
                    czcionka = czcionka.deriveFont((float) 0.1);
                    Font3D font3d = new Font3D(czcionka,null);
                    Text3D tex = new Text3D();
                    tex.setFont3D(font3d);
                    tex.setString("i"+i+j+k);
                    System.out.println(i +" " + " "+j);
                    tex.setAlignment(Text3D.ALIGN_CENTER);
                    Appearance app = new Appearance();
                    Shape3D shape = new Shape3D(tex,app);
                    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


                    Transform3D transform = new Transform3D();
                    Vector3d vector = new Vector3d( x+1, y, z);
                    transform.setTranslation(vector);
                    TransformGroup przesuniecie = new TransformGroup(transform);
                    przesuniecie.addChild(shape);
                    objRoot.addChild(przesuniecie);
                    objRoot.addChild(kostka.getTgX());
                }
            }

        }
        zapiszStanKostki(0,kubiki,zakodowaneKolory);



        return objRoot;
    }


    public static void main(String[] args) {
        KostkaRubika kostkaRubika = new KostkaRubika();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyChar() == 'l') {
            System.out.println("wcisnąalem l");
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(0);
            kubiki = Lprim(0, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }

        if (e.getKeyChar() == 's') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja1();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(1);

            kubiki = Lprim(1, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }
        if (e.getKeyChar() == 'r') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja2();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(2);

            kubiki = Lprim(2, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }

        if (e.getKeyChar() == 'b') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja3();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(3);

            kubiki= B(0, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }
        if (e.getKeyChar() == 'n') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja4();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(4);

            kubiki= B(1, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }
        if (e.getKeyChar() == 'm') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja5();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(5);

            kubiki= B(2, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }
        if (e.getKeyChar() == 'q') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja6();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(6);

            kubiki= xd(0, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }
        if (e.getKeyChar() == 'w') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja7();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(7);

            kubiki= xd(1, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }
        if (e.getKeyChar() == 'e') {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja8();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(8);

            kubiki= xd(2, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
        }
        if (e.getKeyChar() == 'p') {
            //liczbaruchow=liczbaruchow-2;
            System.out.println("CHUJ CI W PIZDE JAVA");
            wczytajStanKostki(liczbaruchow,kubiki,zakodowaneKolory);

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void cofnijAnimacje(int numer_akcji) {
        Transform3D temp;
        Transform3D tempdelta = new Transform3D();
        switch (numer_akcji) {
            case 0:
                {
                tempdelta.rotX(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[0][i][j].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[0][i][j].getTgX().setTransform(temp);

                    }
                }
                break;
            }
            case 1:
                {
                    tempdelta.rotX(-Math.PI / 2);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            temp = kubiki[1][i][j].getTransformX();
                            temp.mul(tempdelta);
                            kubiki[1][i][j].getTgX().setTransform(temp);

                        }
                    }
                    break;
                }
            case 2:
            {
                tempdelta.rotX(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[2][i][j].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[2][i][j].getTgX().setTransform(temp);

                    }
                }
                break;
            }
            case 3:
            {
                tempdelta.rotY(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[i][0][j].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[i][0][j].getTgX().setTransform(temp);

                    }
                }
                break;
            }
            case 4:
            {
                tempdelta.rotY(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[i][1][j].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[i][1][j].getTgX().setTransform(temp);

                    }
                }
                break;
            }
            case 5:
            {
                tempdelta.rotY(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[i][2][j].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[i][2][j].getTgX().setTransform(temp);
                    }
                }
                break;
            }
            case 6:
            {
                tempdelta.rotZ(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[i][j][0].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[i][j][0].getTgX().setTransform(temp);
                    }
                }
                break;
            }
            case 7:
            {
                tempdelta.rotZ(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[i][j][1].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[i][j][1].getTgX().setTransform(temp);
                    }
                }
                break;
            }
            case 8:
            {
                tempdelta.rotZ(-Math.PI / 2);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp = kubiki[i][j][2].getTransformX();
                        temp.mul(tempdelta);
                        kubiki[i][j][2].getTgX().setTransform(temp);
                    }
                }
                break;
            }
        }

    }


    public class animacja extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotX(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[0][i][j].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[0][i][j].getTgX().setTransform(temp);

                }
            }
        }

    }
    public class animacja1 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotX(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[1][i][j].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[1][i][j].getTgX().setTransform(temp);

                }
            }
        }
    }
    public class animacja2 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotX(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[2][i][j].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[2][i][j].getTgX().setTransform(temp);

                }
            }
        }
    }
    public class animacja3 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotY(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[i][0][j].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[i][0][j].getTgX().setTransform(temp);

                }
            }
        }
    }
    public class animacja4 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotY(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[i][1][j].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[i][1][j].getTgX().setTransform(temp);

                }
            }
        }
    }
    public class animacja5 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotY(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[i][2][j].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[i][2][j].getTgX().setTransform(temp);

                }
            }
        }
    }
    public class animacja6 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotZ(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[i][j][0].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[i][j][0].getTgX().setTransform(temp);

                }
            }
        }
    }
    public class animacja7 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotZ(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[i][j][1].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[i][j][1].getTgX().setTransform(temp);

                }
            }
        }
    }
    public class animacja8 extends TimerTask
    {
        int licznik =0;
        public void run()
        {
            System.out.println(licznik);
            licznik++;
            if (licznik>24) {cancel();}

            double zmiana;
            zmiana = Math.PI / 50;
            Transform3D temp = new Transform3D();
            Transform3D tempdelta= new Transform3D();
            tempdelta.rotZ(zmiana);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    temp = kubiki[i][j][2].getTransformX();
                    temp.mul(tempdelta);
                    kubiki[i][j][2].getTgX().setTransform(temp);

                }
            }
        }
    }




    public void zapiszStanKostki( int liczbaruchow, Cubie[][][] kubiki,int[][][] zakodowaneKolory){

        int licznik =0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                for(int k=0;k<3;k++)
                {
                    for(int l=0;l<6;l++)
                    {
                        zakodowaneKolory[liczbaruchow][licznik][l]=kubiki[i][j][k].getColorInt(l);
                    }
                    licznik++;
                }
            }
        }
    }

    public void wczytajStanKostki(int liczbaruchow, Cubie[][][] kubiki,int[][][] zakodowaneKolory)
    {
        int licznik =0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                for(int k=0;k<3;k++)
                {
                    for(int l=0;l<6;l++)
                    {
                        kubiki[i][j][k].setColor(l,zakodowaneKolory[0][licznik][l]);
                    }
                    licznik++;
                }
            }
        }


    }
    public Cubie[][][] Lprim(int i, Cubie[][][] kubiki)
    {

        System.out.println("translacja macierzy");
        Cubie[][][] temp;
        temp = kubiki.clone();


        Color3f kolortemp1 = kubiki[i][2][2].getColor(Box.FRONT);
        Color3f kolortemp2 = kubiki[i][1][2].getColor(Box.FRONT);
        Color3f kolortemp3 = kubiki[i][0][2].getColor(Box.FRONT);


        temp[i][2][2].setColor(Box.FRONT,kubiki[i][2][0].getColor(Box.TOP));
        temp[i][1][2].setColor(Box.FRONT,kubiki[i][2][1].getColor(Box.TOP));
        temp[i][0][2].setColor(Box.FRONT,kubiki[i][2][2].getColor(Box.TOP));

        temp[i][2][0].setColor(Box.TOP,kubiki[i][0][0].getColor(Box.BACK));
        temp[i][2][1].setColor(Box.TOP,kubiki[i][1][0].getColor(Box.BACK));
        temp[i][2][2].setColor(Box.TOP,kubiki[i][2][0].getColor(Box.BACK));

        temp[i][0][0].setColor(Box.BACK,kubiki[i][0][2].getColor(Box.BOTTOM));
        temp[i][1][0].setColor(Box.BACK,kubiki[i][0][1].getColor(Box.BOTTOM));
        temp[i][2][0].setColor(Box.BACK,kubiki[i][0][0].getColor(Box.BOTTOM));

        temp[i][0][2].setColor(Box.BOTTOM,kolortemp1);
        temp[i][0][1].setColor(Box.BOTTOM,kolortemp2);
        temp[i][0][0].setColor(Box.BOTTOM,kolortemp3);

        switch(i) {
            case 0:
                Color3f kolortemp4 = kubiki[i][2][2].getColor(Box.LEFT);
                Color3f kolortemp5 = kubiki[i][2][1].getColor(Box.LEFT);
                temp[i][2][2].setColor(Box.LEFT, kubiki[i][2][0].getColor(Box.LEFT));
                temp[i][2][1].setColor(Box.LEFT, kubiki[i][1][0].getColor(Box.LEFT));
                temp[i][2][0].setColor(Box.LEFT, kubiki[i][0][0].getColor(Box.LEFT));
                temp[i][1][0].setColor(Box.LEFT, kubiki[i][0][1].getColor(Box.LEFT));
                temp[i][0][0].setColor(Box.LEFT, kubiki[i][0][2].getColor(Box.LEFT));
                temp[i][0][1].setColor(Box.LEFT, kubiki[i][1][2].getColor(Box.LEFT));
                temp[i][0][2].setColor(Box.LEFT, kolortemp4);
                temp[i][1][2].setColor(Box.LEFT, kolortemp5);
                break;
            case 2:
                Color3f kolortemp6 = kubiki[i][2][2].getColor(Box.RIGHT);
                Color3f kolortemp7 = kubiki[i][2][1].getColor(Box.RIGHT);
                temp[i][2][2].setColor(Box.RIGHT, kubiki[i][2][0].getColor(Box.RIGHT));
                temp[i][2][1].setColor(Box.RIGHT, kubiki[i][1][0].getColor(Box.RIGHT));
                temp[i][2][0].setColor(Box.RIGHT, kubiki[i][0][0].getColor(Box.RIGHT));
                temp[i][1][0].setColor(Box.RIGHT, kubiki[i][0][1].getColor(Box.RIGHT));
                temp[i][0][0].setColor(Box.RIGHT, kubiki[i][0][2].getColor(Box.RIGHT));
                temp[i][0][1].setColor(Box.RIGHT, kubiki[i][1][2].getColor(Box.RIGHT));
                temp[i][0][2].setColor(Box.RIGHT, kolortemp6);
                temp[i][1][2].setColor(Box.RIGHT, kolortemp7);
                break;
        }
        return temp;
    }


    public Cubie[][][] B(int j, Cubie[][][] kubiki)
    {
        System.out.println("translacja macierzy");
        Cubie[][][] temp;
        temp= kubiki.clone();

        Color3f kolortemp1 = kubiki[0][j][2].getColor(Box.FRONT);
        Color3f kolortemp2 = kubiki[1][j][2].getColor(Box.FRONT);
        Color3f kolortemp3 = kubiki[2][j][2].getColor(Box.FRONT);


        temp[0][j][2].setColor(Box.FRONT,kubiki[0][j][0].getColor(Box.LEFT));
        temp[1][j][2].setColor(Box.FRONT,kubiki[0][j][1].getColor(Box.LEFT));
        temp[2][j][2].setColor(Box.FRONT,kubiki[0][j][2].getColor(Box.LEFT));

        temp[0][j][0].setColor(Box.LEFT,kubiki[2][j][0].getColor(Box.BACK));
        temp[0][j][1].setColor(Box.LEFT,kubiki[1][j][0].getColor(Box.BACK));
        temp[0][j][2].setColor(Box.LEFT,kubiki[0][j][0].getColor(Box.BACK));

        temp[2][j][0].setColor(Box.BACK,kubiki[2][j][2].getColor(Box.RIGHT));
        temp[1][j][0].setColor(Box.BACK,kubiki[2][j][1].getColor(Box.RIGHT));
        temp[0][j][0].setColor(Box.BACK,kubiki[2][j][0].getColor(Box.RIGHT));

        temp[2][j][2].setColor(Box.RIGHT,kolortemp1);
        temp[2][j][1].setColor(Box.RIGHT,kolortemp2);
        temp[2][j][0].setColor(Box.RIGHT,kolortemp3);

        switch (j) {
            case 0:
                Color3f kolortemp4 = kubiki[2][j][2].getColor(Box.BOTTOM);
                Color3f kolortemp5 = kubiki[1][j][2].getColor(Box.BOTTOM);
                temp[2][j][2].setColor(Box.BOTTOM, kubiki[0][j][2].getColor(Box.BOTTOM));
                temp[1][j][2].setColor(Box.BOTTOM, kubiki[0][j][1].getColor(Box.BOTTOM));
                temp[0][j][2].setColor(Box.BOTTOM, kubiki[0][j][0].getColor(Box.BOTTOM));
                temp[0][j][1].setColor(Box.BOTTOM, kubiki[1][j][0].getColor(Box.BOTTOM));
                temp[0][j][0].setColor(Box.BOTTOM, kubiki[2][j][0].getColor(Box.BOTTOM));
                temp[1][j][0].setColor(Box.BOTTOM, kubiki[2][j][1].getColor(Box.BOTTOM));
                temp[2][j][0].setColor(Box.BOTTOM, kolortemp4);
                temp[2][j][1].setColor(Box.BOTTOM, kolortemp5);
                break;
            case 2:
                Color3f kolortemp6 = kubiki[2][j][2].getColor(Box.TOP);
                Color3f kolortemp7 = kubiki[1][j][2].getColor(Box.TOP);
                temp[2][j][2].setColor(Box.TOP, kubiki[0][j][2].getColor(Box.TOP));
                temp[1][j][2].setColor(Box.TOP, kubiki[0][j][1].getColor(Box.TOP));
                temp[0][j][2].setColor(Box.TOP, kubiki[0][j][0].getColor(Box.TOP));
                temp[0][j][1].setColor(Box.TOP, kubiki[1][j][0].getColor(Box.TOP));
                temp[0][j][0].setColor(Box.TOP, kubiki[2][j][0].getColor(Box.TOP));
                temp[1][j][0].setColor(Box.TOP, kubiki[2][j][1].getColor(Box.TOP));
                temp[2][j][0].setColor(Box.TOP, kolortemp6);
                temp[2][j][1].setColor(Box.TOP, kolortemp7);
                break;
        }
        return temp;
    }
    public Cubie[][][] xd(int k, Cubie[][][] kubiki)
    {
        System.out.println("translacja macierzy");
        Cubie[][][] temp;
        temp= kubiki.clone();

        Color3f kolortemp1 = kubiki[2][2][k].getColor(Box.TOP);
        Color3f kolortemp2 = kubiki[1][2][k].getColor(Box.TOP);
        Color3f kolortemp3 = kubiki[0][2][k].getColor(Box.TOP);


        temp[0][2][k].setColor(Box.TOP,kubiki[0][0][k].getColor(Box.LEFT));
        temp[1][2][k].setColor(Box.TOP,kubiki[0][1][k].getColor(Box.LEFT));
        temp[2][2][k].setColor(Box.TOP,kubiki[0][2][k].getColor(Box.LEFT));

        temp[0][2][k].setColor(Box.LEFT,kubiki[0][0][k].getColor(Box.BOTTOM));
        temp[0][1][k].setColor(Box.LEFT,kubiki[1][0][k].getColor(Box.BOTTOM));
        temp[0][0][k].setColor(Box.LEFT,kubiki[2][0][k].getColor(Box.BOTTOM));

        temp[0][0][k].setColor(Box.BOTTOM,kubiki[2][0][k].getColor(Box.RIGHT));
        temp[1][0][k].setColor(Box.BOTTOM,kubiki[2][1][k].getColor(Box.RIGHT));
        temp[2][0][k].setColor(Box.BOTTOM,kubiki[2][2][k].getColor(Box.RIGHT));

        temp[2][0][k].setColor(Box.RIGHT,kolortemp1);
        temp[2][1][k].setColor(Box.RIGHT,kolortemp2);
        temp[2][2][k].setColor(Box.RIGHT,kolortemp3);

        switch (k){
            case 0:
                Color3f kolortemp6 = kubiki[0][2][k].getColor(Box.BACK);
                Color3f kolortemp7 = kubiki[0][1][k].getColor(Box.BACK);
                temp[0][2][k].setColor(Box.BACK,kubiki[0][0][k].getColor(Box.BACK));
                temp[0][1][k].setColor(Box.BACK,kubiki[1][0][k].getColor(Box.BACK));
                temp[0][0][k].setColor(Box.BACK,kubiki[2][0][k].getColor(Box.BACK));
                temp[1][0][k].setColor(Box.BACK,kubiki[2][1][k].getColor(Box.BACK));
                temp[2][0][k].setColor(Box.BACK,kubiki[2][2][k].getColor(Box.BACK));
                temp[2][1][k].setColor(Box.BACK,kubiki[1][2][k].getColor(Box.BACK));
                temp[2][2][k].setColor(Box.BACK,kolortemp6);
                temp[1][2][k].setColor(Box.BACK,kolortemp7);
                break;
            case 2:
                Color3f kolortemp4 = kubiki[0][2][k].getColor(Box.FRONT);
                Color3f kolortemp5 = kubiki[0][1][k].getColor(Box.FRONT);
                temp[0][2][k].setColor(Box.FRONT,kubiki[0][0][k].getColor(Box.FRONT));
                temp[0][1][k].setColor(Box.FRONT,kubiki[1][0][k].getColor(Box.FRONT));
                temp[0][0][k].setColor(Box.FRONT,kubiki[2][0][k].getColor(Box.FRONT));
                temp[1][0][k].setColor(Box.FRONT,kubiki[2][1][k].getColor(Box.FRONT));
                temp[2][0][k].setColor(Box.FRONT,kubiki[2][2][k].getColor(Box.FRONT));
                temp[2][1][k].setColor(Box.FRONT,kubiki[1][2][k].getColor(Box.FRONT));
                temp[2][2][k].setColor(Box.FRONT,kolortemp4);
                temp[1][2][k].setColor(Box.FRONT,kolortemp5);
                break;
        }
        return temp;
    }
}