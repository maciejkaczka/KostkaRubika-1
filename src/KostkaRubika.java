import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.universe.*;

import javax.media.j3d.*;

import javax.swing.*;
import javax.vecmath.*;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;

import com.sun.j3d.utils.picking.*;

import com.sun.j3d.utils.universe.SimpleUniverse;

import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.*;

import javax.vecmath.*;

import java.awt.event.*;

import java.awt.*;

public class KostkaRubika extends JFrame {



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


        for(float i=0;i<0.93;i=i+(float)0.31)
        {
            for(float j=0;j<0.93;j=j+(float)0.31)
            {
                for(float k=0;k<0.93;k=k+(float)0.31)
                {
                    Cubie kostka = new Cubie(size,i,j,k);
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
