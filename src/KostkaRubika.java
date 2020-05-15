import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KostkaRubika extends JFrame implements KeyListener, ActionListener {

    public Cubie[][][] kubiki = new Cubie[3][3][3];
    TransformGroup obrocsie;



    public KostkaRubika() {


        super("KostkaRubika");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GraphicsConfiguration config =
                SimpleUniverse.getPreferredConfiguration();

        Canvas3D canvas3D = new Canvas3D(config);
        canvas3D.setPreferredSize(new Dimension(800, 600));
        add(canvas3D);
        // go.addActionListener(this);

        canvas3D.addKeyListener(this);

        pack();
        setVisible(true);


        BranchGroup scena = createSceneGraph();
        scena.compile();
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);




        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(scena);


    }


    public BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        float size = (float) 0.15;

        obrocsie = new TransformGroup();

        obrocsie.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        obrocsie.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);




        TransformGroup objRotate = new TransformGroup();
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        objRotate.addChild(obrocsie);
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





                    Font czcionka = new Font("Calibri",1,1);
                    czcionka = czcionka.deriveFont((float) 0.1);

                    Font3D font3d = new Font3D(czcionka,null);
                    // Build 3D text geometry using the 3D font
                    Text3D tex = new Text3D();
                    tex.setFont3D(font3d);

                    tex.setString("i"+i+j+k);
                    System.out.println(i +" " + " "+j);
                    tex.setAlignment(Text3D.ALIGN_CENTER);


                    Appearance app = new Appearance();


                    Shape3D shape = new Shape3D(tex,app);


                    Transform3D transform = new Transform3D();
                     Vector3d vector = new Vector3d( x+1, y, z);
                    transform.setTranslation(vector);
                    TransformGroup przesuniecie = new TransformGroup(transform);
                    przesuniecie.addChild(shape);
                    obrocsie.addChild(przesuniecie);
                    obrocsie.addChild(kostka.getTgX());
                }
            }

        }
        objRoot.addChild(objRotate);


        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(objRotate);
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        objRoot.addChild(myMouseRotate);


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


            kubiki = Lprim(0, kubiki);
        }
        if (e.getKeyChar() == 's') {


            kubiki = Lprim(1, kubiki);
        }
        if (e.getKeyChar() == 'r') {


            kubiki = Lprim(2, kubiki);
        }

        if (e.getKeyChar() == 'b') {

           kubiki= B(0, kubiki);


        }
        if (e.getKeyChar() == 'n') {

            kubiki= B(1, kubiki);


        }
        if (e.getKeyChar() == 'm') {

            kubiki= B(2, kubiki);


        }
        if (e.getKeyChar() == 'q') {

            kubiki= xd(0, kubiki);


        }
        if (e.getKeyChar() == 'w') {

            kubiki= xd(1, kubiki);


        }
        if (e.getKeyChar() == 'e') {

            kubiki= xd(2, kubiki);


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public Cubie[][][] Lprim(int i, Cubie[][][] kubiki)
    {

        System.out.println("translacja macierzy");
        Cubie[][][] temp  = new Cubie[3][3][3];
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
        Cubie[][][] temp  = new Cubie[3][3][3];
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

        switch (j){
            case 0:
                Color3f kolortemp4 = kubiki[2][j][2].getColor(Box.BOTTOM);
                Color3f kolortemp5 = kubiki[1][j][2].getColor(Box.BOTTOM);
                temp[2][j][2].setColor(Box.BOTTOM,kubiki[0][j][2].getColor(Box.BOTTOM));
                temp[1][j][2].setColor(Box.BOTTOM,kubiki[0][j][1].getColor(Box.BOTTOM));
                temp[0][j][2].setColor(Box.BOTTOM,kubiki[0][j][0].getColor(Box.BOTTOM));
                temp[0][j][1].setColor(Box.BOTTOM,kubiki[1][j][0].getColor(Box.BOTTOM));
                temp[0][j][0].setColor(Box.BOTTOM,kubiki[2][j][0].getColor(Box.BOTTOM));
                temp[1][j][0].setColor(Box.BOTTOM,kubiki[2][j][1].getColor(Box.BOTTOM));
                temp[2][j][0].setColor(Box.BOTTOM,kolortemp4);
                temp[2][j][1].setColor(Box.BOTTOM,kolortemp5);
                break;
            case 2:
                Color3f kolortemp6 = kubiki[2][j][2].getColor(Box.TOP);
                Color3f kolortemp7 = kubiki[1][j][2].getColor(Box.TOP);
                temp[2][j][2].setColor(Box.TOP,kubiki[0][j][2].getColor(Box.TOP));
                temp[1][j][2].setColor(Box.TOP,kubiki[0][j][1].getColor(Box.TOP));
                temp[0][j][2].setColor(Box.TOP,kubiki[0][j][0].getColor(Box.TOP));
                temp[0][j][1].setColor(Box.TOP,kubiki[1][j][0].getColor(Box.TOP));
                temp[0][j][0].setColor(Box.TOP,kubiki[2][j][0].getColor(Box.TOP));
                temp[1][j][0].setColor(Box.TOP,kubiki[2][j][1].getColor(Box.TOP));
                temp[2][j][0].setColor(Box.TOP,kolortemp6);
                temp[2][j][1].setColor(Box.TOP,kolortemp7);
                break;

        }




        return temp;
    }
    public Cubie[][][] xd(int k, Cubie[][][] kubiki)
    {
        System.out.println("translacja macierzy");
        Cubie[][][] temp  = new Cubie[3][3][3];
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