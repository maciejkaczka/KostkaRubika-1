
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;

import java.awt.*;

public class Cubie {
    Box kubik;
    TransformGroup przesuniecie;
    TransformGroup rotacjaX;
    TransformGroup rotacjaY;
    TransformGroup rotacjaZ;
    Shape3D sciana;

    Transform3D tfX = new Transform3D();
    Transform3D tfY = new Transform3D();
    Transform3D tfZ = new Transform3D();


    private  Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);
    private  Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);
    private  Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);
    private  Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);
    private  Color3f Orange = new Color3f(1.0f, 0.7f, 0.1f);
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
            sciana = kubik.getShape(faces[i]);
            sciana.setCapability(Shape3D.ALLOW_APPEARANCE_READ);
            sciana.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
            sciana.setAppearance(app[i]);


        }

        //transformacja przesuwająca kostki na swoje miejsca


        double zmienna=270;


        Transform3D transform = new Transform3D();
        //Transform3D tf = new Transform3D();
        Vector3d vector = new Vector3d( x, y, z);
        transform.setTranslation(vector);
        Transform3D temp = new Transform3D();





        przesuniecie = new TransformGroup(transform);
        przesuniecie.addChild(kubik);

        temp.mul(tfX);
        temp.mul(tfY);
        temp.mul(tfZ);



        rotacjaX = new TransformGroup(temp);

        rotacjaX.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacjaX.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacjaX.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        rotacjaX.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        rotacjaX.addChild(przesuniecie);
    /*
        rotacjaY = new TransformGroup(tfY);

        rotacjaY.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacjaY.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacjaY.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        rotacjaY.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        rotacjaY.addChild(rotacjaX);


        rotacjaZ = new TransformGroup(tfZ);

        rotacjaZ.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacjaZ.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rotacjaZ.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        rotacjaZ.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        rotacjaZ.addChild(rotacjaY);
        */
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

    public Transform3D getTransformX()
    {

        // rotacjaX.getTransform(tfX);
         return tfX;
    }

    public Transform3D getTransformY()
    {

        //rotacjaY.getTransform(tfY);
        return tfY;
    }

    public Transform3D getTransformZ()
    {

       // rotacjaZ.getTransform(tfZ);
        return tfZ;
    }



    public Appearance getAppearance(int i)
    {


        Appearance apptest = new Appearance();
        Color3f kol = ktoryKolor(i);
        ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
        apptest.setColoringAttributes(att);

        return apptest;



    }

    public Color3f getColor(int i)
    {
        Color3f temp = new Color3f();
        Shape3D  szejp =new Shape3D();
        szejp =  kubik.getShape(i);
        szejp.getAppearance().getColoringAttributes().getColor(temp);
        return temp;

    }

    public void setColor(int i,Color3f kolor)
    {
        Appearance tempapp = new Appearance();
        Color3f kol = kolor;
        ColoringAttributes att = new ColoringAttributes(kol,ColoringAttributes.NICEST);
        tempapp.setColoringAttributes(att);



        Shape3D  szejp;
        szejp =  kubik.getShape(i);
        szejp.setAppearance(tempapp);


    }

    public Appearance getAppearance(Color3f kolor)
    {


        Appearance apptest = new Appearance();
        Color3f kol = kolor;
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
