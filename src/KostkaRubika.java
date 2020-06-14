
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.*;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Vector;


public class KostkaRubika extends JFrame implements KeyListener, ActionListener {

    public Cubie[][][] kubiki = new Cubie[3][3][3];
    TransformGroup obrocsie;
    public int liczbaruchow = 1;
    public int[][][] zakodowaneKolory = new int[500][27][6]; //wstepnie max 100 ruchow

    Vector<KeyEvent> nagrane_przyciski = new Vector<KeyEvent>();

    boolean key_a;
    boolean key_s;
    boolean key_d;
    boolean key_z;
    boolean key_x;
    boolean key_c;
    boolean key_q;
    boolean key_w;
    boolean key_e;

    boolean odtwarzanie;
    boolean wcisniete;



    public KostkaRubika() {


        super("KostkaRubika");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(true);

        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas3D = new Canvas3D(config);
        canvas3D.setSize(800,600);
        add(canvas3D);

        pack();
        setVisible(true);

        canvas3D.addKeyListener(this);
        BranchGroup scena = createSceneGraph();
        scena.compile();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        OrbitBehavior kamera = new OrbitBehavior(canvas3D,OrbitBehavior.REVERSE_ALL);
        BoundingSphere bound = new BoundingSphere();
        kamera.setSchedulingBounds(bound);
        Transform3D przesuniecie_obserwatora = new Transform3D();
        przesuniecie_obserwatora.set(new Vector3f(0f,0f,4f));

        simpleU.getViewingPlatform().setNominalViewingTransform();


        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(przesuniecie_obserwatora);
        simpleU.getViewingPlatform().setViewPlatformBehavior(kamera);

        simpleU.addBranchGraph(scena);


    }


    public BranchGroup createSceneGraph() {

        Appearance wyglad_sfera = new Appearance();

        TextureLoader loader = new TextureLoader("src/clouds.gif",this);
         ImageComponent2D image = loader.getImage();

        Texture2D chmury = new  Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
                image.getWidth(), image.getHeight());

        chmury.setImage(0, image);
        chmury.setBoundaryModeS(Texture.WRAP);
        chmury.setBoundaryModeT(Texture.WRAP);

        wyglad_sfera.setTexture(chmury);




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
                    objRoot.addChild(kostka.getTgX());
                }


            }

        }
      //  Sphere kula = new Sphere(20f,Sphere.GENERATE_NORMALS_INWARD|Sphere.GENERATE_TEXTURE_COORDS,wyglad_sfera);
       // objRoot.addChild(kula);


        zapiszStanKostki(0,kubiki,zakodowaneKolory);



        return objRoot;
    }

    public void funkcjeObrotu()
    {

        Button a = new Button("click");
        KeyEvent key_A = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_A, 'A');
        KeyEvent key_S = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_S, 'S');
        KeyEvent key_D = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_D, 'D');
        KeyEvent key_Z = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_Z, 'Z');
        KeyEvent key_X = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_X, 'X');
        KeyEvent key_C = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_C, 'C');
        KeyEvent key_Q = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_Q, 'Q');
        KeyEvent key_W = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_W, 'W');
        KeyEvent key_E = new KeyEvent(a, 1, 20, 1, KeyEvent.VK_E, 'E');

        if(key_a == true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(0);
            kubiki = obrotPierwsz(0, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) {nagrane_przyciski.add(key_A);}
            System.out.println(wcisniete);

        }
        else if(key_s ==true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja1();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(1);

            kubiki = obrotPierwsz(1, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) {nagrane_przyciski.add(key_S);}
            System.out.println(wcisniete);

        }
        else if(key_d == true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja2();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(2);

            kubiki = obrotPierwsz(2, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) {nagrane_przyciski.add(key_D);}
            System.out.println(wcisniete);

        }
        else if(key_z ==true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja3();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(3);

            kubiki= obrotDrugi(0, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) { nagrane_przyciski.add(key_Z);}
            System.out.println(wcisniete);

        }
        else if(key_x == true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja4();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(4);

            kubiki= obrotDrugi(1, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) {nagrane_przyciski.add(key_X);}
            System.out.println(wcisniete);

        }
        else if(key_c ==true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja5();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(5);

            kubiki= obrotDrugi(2, kubiki);
            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) {nagrane_przyciski.add(key_C);}
            System.out.println(wcisniete);

        }
        else if(key_q == true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja6();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(6);

            kubiki= obrotTrzeci(0, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) { nagrane_przyciski.add(key_Q);}
            System.out.println(wcisniete);

        }
        else if(key_w ==true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja7();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(7);

            kubiki= obrotTrzeci(1, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) {nagrane_przyciski.add(key_W); }
            System.out.println(wcisniete);

        }
        else if(key_e ==true)
        {
            java.util.Timer timer = new java.util.Timer(true);
            TimerTask timerTask= new animacja8();
            timer.scheduleAtFixedRate(timerTask,0,10);
            cofnijAnimacje(8);

            kubiki= obrotTrzeci(2, kubiki);

            zapiszStanKostki(liczbaruchow,kubiki,zakodowaneKolory);
            liczbaruchow++;
            if (odtwarzanie==false) { nagrane_przyciski.add(key_E);}
            System.out.println(wcisniete);

        }
        else wcisniete=false;

    }


    public static void main(String[] args) {
        KostkaRubika kostkaRubika = new KostkaRubika();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (wcisniete==false) {
            wcisniete=true;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    key_a = true;
                    break;
                case KeyEvent.VK_S:
                    key_s = true;
                    break;
                case KeyEvent.VK_D:
                    key_d = true;
                    break;
                case KeyEvent.VK_Z:
                    key_z = true;
                    break;
                case KeyEvent.VK_X:
                    key_x = true;
                    break;
                case KeyEvent.VK_C:
                    key_c = true;
                    break;
                case KeyEvent.VK_Q:
                    key_q = true;
                    break;
                case KeyEvent.VK_W:
                    key_w = true;
                    break;
                case KeyEvent.VK_E:
                    key_e = true;
                    break;
            }
            funkcjeObrotu();



            if (e.getKeyChar() == 'p') {
                if (liczbaruchow > 5) {
                    odtwarzanie = true;
                    wcisniete = false;
                   // liczbaruchow = liczbaruchow - nagrane_przyciski.size();

                    wczytajStanKostki(liczbaruchow, kubiki, zakodowaneKolory);

                    for (int i = 0; i < nagrane_przyciski.size(); i++) {
                        try {
                            Thread.sleep(600);
                        } catch (InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }
                        keyPressed(nagrane_przyciski.elementAt(i));
                        keyReleased(nagrane_przyciski.elementAt(i));

                    }
                    nagrane_przyciski.clear();
                    odtwarzanie = false;

                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                key_a = false;
                break;
            case KeyEvent.VK_S:
                key_s = false;
                break;
            case KeyEvent.VK_D:
                key_d = false;
                break;
            case KeyEvent.VK_Z:
                key_z = false;
                break;
            case KeyEvent.VK_X:
                key_x = false;
                break;
            case KeyEvent.VK_C:
                key_c = false;
                break;
            case KeyEvent.VK_Q:
                key_q = false;
                break;
            case KeyEvent.VK_W:
                key_w = false;
                break;
            case KeyEvent.VK_E:
                key_e = false;
                break;
        }


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
            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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

            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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

            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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

            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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

            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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

            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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

            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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

            licznik++;
            if (licznik>24) {cancel();
                wcisniete=false;}

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
            licznik++;
            if (licznik>24) {
                wcisniete=false;
                cancel();}

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




    public void zapiszStanKostki( int liczbaruchow, Cubie[][][] kubiki,int[][][] zakodowaneKolory)
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

    public Cubie[][][] obrotPierwsz(int i, Cubie[][][] kubiki)
    {


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
    public Cubie[][][] obrotDrugi(int j, Cubie[][][] kubiki)
    {

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
    public Cubie[][][] obrotTrzeci(int k, Cubie[][][] kubiki)
    {

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