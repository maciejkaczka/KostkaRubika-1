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

        for(float x=(float)-0.31;x<0.32;x=x+(float)0.31)
        {
            j=-1;
            i++;
            for(float y=(float)-0.31;y<0.32;y=y+(float)0.31)
            {
                k=-1;
                j++;
                for(float z=(float)-0.31;z<0.32;z=z+(float)0.31)
                {
                    k++;
                    Cubie kostka = new Cubie(size,x,y,z);
                    kubiki[i][j][k] = kostka;

                    objRotate.addChild(kostka.getTg());
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


        public static void main( String[] args ) {
                new KostkaRubika();
            }

    }
