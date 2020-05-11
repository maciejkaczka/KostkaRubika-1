
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;

public class Cubie {
    Box kubik;
    TransformGroup przesuniecie;
    TransformGroup rotacjaX;
    TransformGroup rotacjaY;
    TransformGroup rotacjaZ;


    private  Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);
    private  Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);
    private  Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);
    private  Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);
    private  Color3f Orange = new Color3f(1.0f, 0.4f, 0.0f);
    private  Color3f White = new Color3f(1.0f, 1.0f, 1.0f);
    private  Color3f Black = new Color3f(0.1f, 0.1f, 0.1f);


    private  int[] faces = { Box.TOP, Box.FRONT, Box.RIGHT,
            Box.BACK, Box.LEFT, Box.BOTTOM };

    private Appearance[] app =
            {getAppearance(0), getAppearance(1),
                    getAppearance(2), getAppearance(3),
                    getAppearance(4),getAppearance(5)};


    //Konstruktor ktory tworzy kostkę i koloruje sciany
    public Cubie (float size, float x, float y, float z)
    {

        kubik = new Box(size, size, size,Box.GENERATE_NORMALS, null);

        for (int i = 0; i < faces.length; i++) {
            Shape3D shape = kubik.getShape(faces[i]);
            shape.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
            shape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
            shape.setAppearance(app[i]);


        }

        //transformacja przesuwająca kostki na swoje miejsca


        double zmienna=270;


        Transform3D transform = new Transform3D();
        Transform3D tf = new Transform3D();
        Vector3d vector = new Vector3d( x, y, z);
        transform.setTranslation(vector);



        przesuniecie = new TransformGroup(transform);
        przesuniecie.addChild(kubik);

        rotacjaX = new TransformGroup(tf);

        rotacjaX.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacjaX.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacjaX.addChild(przesuniecie);

        rotacjaY = new TransformGroup(tf);

        rotacjaY.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacjaY.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacjaY.addChild(rotacjaX);


        rotacjaZ = new TransformGroup(tf);

        rotacjaZ.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacjaZ.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacjaZ.addChild(rotacjaY);

    }
    //gettery
    public TransformGroup getTgZ()
    {
        return rotacjaZ;
    }
    public TransformGroup getTgY()
    {
        return rotacjaY;
    }
    public TransformGroup getTgX()
    {
        return rotacjaX;
    }
    // nasza zmienna transformacja
    public Transform3D getTransformX()
    {
        Transform3D temporary = new Transform3D();
         rotacjaX.getTransform(temporary);
         return temporary;
    }

    public Transform3D getTransformY()
    {
        Transform3D temporary = new Transform3D();
        rotacjaY.getTransform(temporary);
        return temporary;
    }

    public Transform3D getTransformZ()
    {
        Transform3D temporary = new Transform3D();
        rotacjaZ.getTransform(temporary);
        return temporary;
    }



    public Appearance getAppearance(int i)
    {


        Appearance apptest = new Appearance();
        Color3f kol = ktoryKolor(i);
        ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
        apptest.setColoringAttributes(att);

        return apptest;



    }

    public Box getCubie()
    {
        return kubik;
    }


    // wybor koloru bo sciany pudelka sa intami
    public Color3f ktoryKolor(int x)
    {
        switch(x)
        {
            case 0: return Red;
            case 1: return Green;
            case 2: return Blue;
            case 3: return Yellow;
            case 4: return Orange;
            case 5: return White;

        }
        return Black;
    }



}
