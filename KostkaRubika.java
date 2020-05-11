import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KostkaRubika extends JFrame implements KeyListener, ActionListener {

    public Cubie[][][] kubiki = new Cubie[3][3][3];
    TransformGroup obrocsie;
   /* double rotX[][] =     {
                            {1.0,0,0},
                            {0,Math.cos(Math.PI/2),-Math.sin(Math.PI/2)},
                            {0,Math.sin(Math.PI/2),Math.cos(Math.PI/2)}
                            };
    */

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




                    obrocsie.addChild(kostka.getTgZ());
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
            System.out.println("wcisnąalem r=l");
            //wyjasnienie masz przy l

            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {


                    Transform3D temp;

                    temp = kubiki[0][j][k].getTransformX();
                    Transform3D tempDelta = new Transform3D();
                    tempDelta.rotX(Math.PI / 2);
                    temp.mul(tempDelta);

                    kubiki[0][j][k].getTgX().setTransform(temp);




                }
            }
            //tutaj obracamy macierz
            kubiki = Lprim(kubiki);
        }

        if (e.getKeyChar() == 'b') {
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < 3; i++) {

                    System.out.println("wcisnalem b");

                    Transform3D temp;

                    temp = kubiki[i][0][k].getTransformY();

                    Transform3D tempDelta = new Transform3D();


                    tempDelta.rotY(Math.PI/2);

                    temp.mul(tempDelta);

                    kubiki[i][0][k].getTgY().setTransform(temp);


                }
            }
           kubiki= B(kubiki);


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public Cubie[][][] Lprim(Cubie[][][] kubiki)
    {
        System.out.println("translacja macierzy");
        Cubie[][][] temp ;
        temp= kubiki;
        temp[0][0][0] = kubiki[0][0][2];
        temp[0][0][1] = kubiki[0][1][2];
        temp[0][0][2] = kubiki[0][2][2];

        temp[0][1][0] = kubiki[0][0][1];
        temp[0][1][1] = kubiki[0][1][1];
        temp[0][1][2] = kubiki[0][2][1];

        temp[0][2][0] = kubiki[0][0][0];
        temp[0][2][1] = kubiki[0][1][0];
        temp[0][2][2] = kubiki[0][2][0];



        return temp;
    }

    public Cubie[][][] B(Cubie[][][] kubiki)
    {
        System.out.println("translacja macierzy");
        Cubie[][][] temp ;
        temp= kubiki;
        temp[0][0][0] = kubiki[2][0][0];
        temp[0][0][1] = kubiki[1][0][0];
        temp[0][0][2] = kubiki[0][0][0];

        temp[1][0][0] = kubiki[2][0][1];
        temp[1][0][1] = kubiki[1][0][1];
        temp[1][0][2] = kubiki[0][0][1];

        temp[2][0][0] = kubiki[2][0][2];
        temp[2][0][1] = kubiki[1][0][2];
        temp[2][0][2] = kubiki[0][0][2];



        return temp;
    }

}