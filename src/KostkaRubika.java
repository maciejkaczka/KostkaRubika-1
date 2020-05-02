import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.*;
import javax.swing.*;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import java.awt.*;


public class KostkaRubika extends JFrame {

            public Cubie[][][] kubiki = new Cubie[3][3][3];

            public KostkaRubika() {


                super("KostkaRubika");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setResizable(false);
                GraphicsConfiguration config =
                        SimpleUniverse.getPreferredConfiguration();

                Canvas3D canvas3D = new Canvas3D(config);
                canvas3D.setPreferredSize(new Dimension(800, 600));
                add(canvas3D);
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

        float size =(float)0.15;


        TransformGroup objRotate = new TransformGroup();
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objRotate.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        int i=-1,j=-1,k=-1;

        for(float x=0;x<0.93;x=x+(float)0.31)
        {
            j=-1;
            i++;
            System.out.println("i :" + i);
            for(float y=0;y<0.93;y=y+(float)0.31)
            {
                k=-1;
                j++;
                System.out.println("j :" + j);
                for(float z=0;z<0.93;z=z+(float)0.31)
                {
                    k++;
                    System.out.println("k :" + k);
                    Cubie kostka = new Cubie(size,x,y,z);
                    kubiki[i][j][k] = kostka;


                    //jebanie sie z tekstem---------------------------------
                    Font3D font = new Font3D(new Font("Arial", Font.PLAIN, 1), new FontExtrusion());
                    Text3D tekst = new Text3D(font,"indeks" + i + j +k);
                    Point3f punkt =  new Point3f(i,j,k);
                    tekst.setPosition(punkt);
                    Shape3D textShape = new Shape3D();
                    textShape.setGeometry(tekst);
                    objRotate.addChild(textShape);
                    //--------------------------------------------

                    objRotate.addChild(kostka.getTg());
                }
            }

        }
        objRoot.addChild(objRotate);




        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(objRotate);
        Point3d punktObrotu = new Point3d(0.48,0.48,0.48);
        myMouseRotate.setSchedulingBounds(new BoundingSphere( punktObrotu,0.48));
        objRoot.addChild(myMouseRotate);


        return objRoot;
    }


        public static void main( String[] args ) {
                new KostkaRubika();
            }

    }
